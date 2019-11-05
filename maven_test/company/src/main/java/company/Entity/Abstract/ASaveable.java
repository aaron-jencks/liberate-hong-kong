package company.Entity.Abstract;

import company.Entity.Interface.ISaveable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public abstract class ASaveable implements ISaveable {

    public UUID objId;

    public ASaveable(){
        this.objId = UUID.randomUUID();
    }

    public static void write(String fileName, boolean append, String content) {
        BufferedWriter buffOut = null;
        try {
            String s = Paths.get("").toAbsolutePath().toString();
            buffOut = new BufferedWriter(new FileWriter(fileName, append));
            buffOut.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffOut != null) {
                    buffOut.close();
                } else {
                    System.out.println("Buffer not initialized");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONArray read(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        JSONArray arr = new JSONArray();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
            arr = new JSONArray(contentBuilder.toString());
        } catch (FileNotFoundException e) {
            arr = new JSONArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static Field[] getAllFields(Class itemClass) {
        Field[] f0 = itemClass.getDeclaredFields();
        Field[] f1 = itemClass.getSuperclass().getDeclaredFields();
        Field[] f2 = itemClass.getSuperclass().getSuperclass().getDeclaredFields();
        Field[] f = add(f0, f1);
        return add(f, f2);
    }

    private static Field[] add(Field[] a1, Field[] a2) {
        Field[] a0 = new Field[a1.length + a2.length];
        System.arraycopy(a1, 0, a0, 0, a1.length);
        System.arraycopy(a2, 0, a0, a1.length, a2.length);
        return a0;
    }

    @Override
    public UUID save() {
        UUID id = UUID.randomUUID();
        JSONObject obj = new JSONObject();
        boolean append = true;
        String type = this.getClass().getSimpleName();
        // get the fields
        Field[] fields = getAllFields(this.getClass());
        // if it has an id it should already be stored and can be loaded
        if (this.objId != null) {
            id = this.objId;
            obj = loadJsonObject(removeLeadingA(this.getClass().getSuperclass().getSimpleName()), id);
            append = false;
        }

        // add the type and id
        obj.put("type", removeLeadingA(type));
        obj.put("ID", id);
        // add each field
        try {
            for (Field f : fields) {
                if (f.canAccess(this)) {
                    obj.put(f.getName(), f.get(this));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (append) {
            // append the new obj to the entity file
            appendToFile(obj, removeLeadingA(type));
        } else {
            // update the existing entry for the object
            updateObjectInFile(obj, removeLeadingA(type));
        }
        return id;
    }

    /**
     * Replace the existing obj in the data file with the new one
     * 
     * @param obj
     * @param fileName
     */
    public static void updateObjectInFile(JSONObject obj, String fileName) {
        // get the full path to the data dir
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + "data" + File.separator;
        String fullFile = s + fileName + ".json";
        // turn the file into a json array
        JSONArray objArray = read(fullFile);
        for (int i = 0; i < objArray.length(); i++) {
            JSONObject testObj = objArray.getJSONObject(i);
            if (testObj.getString("ID").equals(obj.getString("ID"))) {
                objArray.remove(i);
                objArray.put(obj);
            }
        }
        write(fullFile, false, objArray.toString());
    }

    /**
     * Add the item to the existing data file
     * 
     * @param obj
     * @param fileName
     */
    public static void appendToFile(JSONObject obj, String fileName) {
        // get the full path to the data dir
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + "data" + File.separator;
        String fullFile = s + fileName + ".json";
        // turn the file into a json array
        JSONArray objArray = read(fullFile);
        // add the new obj to the
        objArray.put(obj);
        write(fullFile, false, objArray.toString());
    }

    /**
     * Use the given class name to find the obj with the matching id
     * 
     * @param className
     * @param id
     * @return
     */
    public static JSONObject loadJsonObject(String className, UUID id) {
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + "data" + File.separator;
        String fullFile = s + className + ".json";
        JSONObject returnObject = new JSONObject();
        // pull the array for all objects of given type
        JSONArray arr = ASaveable.read(fullFile);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            // check if it has the ID we are searching for
            UUID testId = UUID.fromString((String) obj.get("ID"));
            if (testId.equals(id)) {
                returnObject = obj;
            }
        }
        return returnObject;
    }

    public static JSONArray loadAllAsJson(String className) {
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + "data" + File.separator;
        String fullFile = s + className + ".json";
        return ASaveable.read(fullFile);
    }

    public static Object load(Class classObject, UUID uuid) {

        String s = Paths.get("").toAbsolutePath().toString();
        String classFileName = removeLeadingNamespace(classObject.getName());
        try {
            JSONArray arr = loadAllAsJson(classFileName);
            // check if it has the ID we are searching for
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                UUID id = UUID.fromString((String) obj.get("ID"));
                if (!id.equals(uuid)) {
                    continue;
                }
                // instantiate it into an object
                Object inst = instantiate(removeLeadingA(obj.get("type").toString()));
                Class<?> clazz = inst.getClass();
                // fill all the attributes
                Field[] fields = getAllFields(clazz);
                for (Field f : fields) {
                    if (f.canAccess(inst)) {
                        f.set(inst, obj.get(f.getName()));
                    }
                }
                return inst;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new UnknownError("Unable to load");
    }

    public static JSONArray add(JSONArray a1, JSONArray a2){
        JSONArray master = new JSONArray();
        for(int i = 0; i < a1.length(); i++){
            master.put(a1.getJSONObject(i));
        }
        for(int i = 0; i < a2.length(); i++){
            master.put(a2.getJSONObject(i));
        }
        return master;
    }

    static String removeLeadingA(String str) {
        char[] arr = str.toCharArray();
        char[] arrCp = str.toCharArray();
        if (arr[0] == 'A') {
            arrCp = Arrays.copyOfRange(arr, 1, arr.length);
        }
        return new String(arrCp);
    }

    static String removeLeadingNamespace(String str) {
        if (str.subSequence(0, 15).equals("company.Entity.")) {
            return str.substring(15);
        }
        return str;
    }

    static Object instantiate(String className) {
        // Load the class.
        className = "company.Entity." + className;
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            // Search for an "appropriate" constructor.
            for (Constructor<?> ctor : clazz.getConstructors()) {
                Object[] args = null;
                // we want the empty constructor so the jsonObject can fill out the parameters
                if (ctor.getParameterTypes().length > 0) {
                    continue;
                }
                return clazz.cast(ctor.newInstance(args));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Don't know how to instantiate " + className);
    }
}
