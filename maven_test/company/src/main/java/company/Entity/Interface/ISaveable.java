package company.Entity.Interface;

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
import java.util.Arrays;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ISaveable {
    UUID save();
    UUID getObjectId();

    
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
        JSONArray arr = ISaveable.read(fullFile);
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

    /**
     * Read all the objects from the file for the given class name and return them as a jsonArray
     * @param className
     * @return
     */
    public static JSONArray loadAllAsJson(String className) {
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + "data" + File.separator;
        String fullFile = s + className + ".json";
        return ISaveable.read(fullFile);
    }

    /**
     * Load the object of the given type with the supplied id
     * @param classObject
     * @param uuid
     * @return
     */
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
                Object inst = instantiate(removeLeadingLetter(obj.get("type").toString()));
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

    /**
     * Helper function to add two json arrays
     * @param a1
     * @param a2
     * @return
     */
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

    /**
     * Helper function to remove the leading A or I from the class name
     * @param str
     * @return
     */
    static String removeLeadingLetter(String str) {
        char[] arr = str.toCharArray();
        char[] arrCp = str.toCharArray();
        if (arr[0] == 'A') {
            arrCp = Arrays.copyOfRange(arr, 1, arr.length);
        }
        if (arr[0] == 'I') {
            arrCp = Arrays.copyOfRange(arr, 1, arr.length);
        }
        return new String(arrCp);
    }

    /**
     * Helper function to remove the leading namespace 'company.Entity'
     * @param str
     * @return
     */
    static String removeLeadingNamespace(String str) {
        if (str.subSequence(0, 15).equals("company.Entity.")) {
            return str.substring(15);
        }
        return str;
    }

    /**
     * Instantiate an object of the given type
     * //TODO is this still needed?
     * @param className
     * @return
     */
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
     * Traverse the class hierarchy to find all the fields of the given class
     * @param itemClass
     * @return
     */
    public static Field[] getAllFields(Class itemClass) {
        Field[] f0 = itemClass.getDeclaredFields();
        Field[] f1 = itemClass.getSuperclass().getDeclaredFields();
        Field[] f2 = itemClass.getSuperclass().getSuperclass().getDeclaredFields();
        Field[] f = add(f0, f1);
        return add(f, f2);
    }

    /**
     * Helper function to add two simple arrays of fields together
     * @param a1
     * @param a2
     * @return
     */
    private static Field[] add(Field[] a1, Field[] a2) {
        Field[] a0 = new Field[a1.length + a2.length];
        System.arraycopy(a1, 0, a0, 0, a1.length);
        System.arraycopy(a2, 0, a0, a1.length, a2.length);
        return a0;
    }

    /**
     * Write the content to the file
     * @param fileName the name of the file to be written (it will figure out the full path)
     * @param append boolean if it should append to the file or not
     * @param content the content to be written
     */
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

    /**
     * Read the data from the given file (full path) and return it as a json array
     * @param filePath
     * @return
     */
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
}
