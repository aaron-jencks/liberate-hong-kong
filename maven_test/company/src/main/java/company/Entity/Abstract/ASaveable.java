package main.java.company.Entity.Abstract;

import main.java.company.Entity.Interface.ISaveable;

import main.java.org.json.JSONArray;
import main.java.org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public abstract class ASaveable implements ISaveable {

    public UUID objId;

    @Override
    public UUID save() {
        UUID id = UUID.randomUUID();
        JSONObject obj = new JSONObject();
        // if it has an id it should already be stored and can be loaded
        if (this.objId != null) {
            id = this.objId;
            obj = loadJsonObject(removeLeadingA(this.getClass().getSuperclass().getSimpleName()), id);
        }
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        String type = this.getClass().getSuperclass().getSimpleName();
        if (fields.length == 0) {
            fields = this.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        }
        obj.put("type", type);
        obj.put("ID", id);
        try {
            for (Field f : fields) {
                obj.put(f.getName(), f.get(this));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        AppendToFile(obj, removeLeadingA(type));
        return id;
    }

    /**
     * Add the JsonObject to the end of the file specified by className
     * @param obj
     * @param className
     */
    public static void AppendToFile(JSONObject obj, String className) {
        String s = Paths.get("").toAbsolutePath().toString();
        File file = new File(s + "\\data\\company.Entity." + className + ".json");
        File tempFile = new File(s + "\\data\\company.Entity." + className + "-temp.json");
        JSONArray objArray = new JSONArray();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // get the next line in file
                String line = sc.nextLine();
                // make the string into json
                objArray = new JSONArray(line);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(objArray.length());
        objArray.put(obj);
        System.out.println(objArray.length());
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(objArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(file);
    }

    /**
     * Search the file for className to find the matching UUID, removes the found object from the file
     * @param className
     * @param id
     * @return
     */
    public static JSONObject loadJsonObject(String className, UUID id) {
        String s = Paths.get("").toAbsolutePath().toString();
        File file = new File(s + "\\data\\company.Entity." + className + ".json");
        File tempFile = new File(s + "\\data\\company.Entity." + className + "-temp.json");
        JSONObject returnObject = new JSONObject();
        JSONArray objArray = new JSONArray();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // get the next line in file
                String line = sc.nextLine();
                // make the string into json
                JSONArray arr = new JSONArray(line);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    // check if it has the ID we are searching for
                    UUID testId = UUID.fromString((String) obj.get("ID"));
                    if (testId.equals(id)) {
                        sc.close();
                        returnObject = obj;
                    } else {
                        objArray.put(obj);
                    }
                }

            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(objArray.toString());
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(objArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        tempFile.renameTo(file);
        return returnObject;
    }

    /**
     * Load an object from the className file given the UUID
     * @param className
     * @param uuid
     * @return
     */
    public static Object load(String className, UUID uuid) {
        String s = Paths.get("").toAbsolutePath().toString();
        File file = new File(s + "\\data\\company.Entity." + className + ".json");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                // get the next line in file
                String line = sc.nextLine();
                // make the string into json
                JSONArray arr = new JSONArray(line);
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
                    Field[] fields = clazz.getSuperclass().getDeclaredFields();
                    if (fields.length == 0) {
                        fields = clazz.getSuperclass().getSuperclass().getDeclaredFields();
                    }
                    for (Field f : fields) {
                        f.set(inst, obj.get(f.getName()));
                    }
                    sc.close();
                    return inst;
                }

            }
            sc.close();

        } catch (FileNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new UnknownError("Unable to load");
    }

    static String removeLeadingA(String str) {
        char[] arr = str.toCharArray();
        char[] arrCp = str.toCharArray();
        if (arr[0] == 'A') {
            arrCp = Arrays.copyOfRange(arr, 1, arr.length);
        }
        return new String(arrCp);
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
                return clazz.cast(ctor.newInstance(args));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Don't know how to instantiate " + className);
    }
}
