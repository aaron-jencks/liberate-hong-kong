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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface ISaveable {

    static String ID_STR_CONST = "objId";
    static String TYPE_STR_CONST = "Type";
    static String DATA_DIR_CONST = "data";
    static String DATA_FILE_EXT_CONST = ".json";
    static String ENTITY_NS_CONST = "company.Entity.";

    UUID save();
    void delete();
    UUID getObjectId();
    void setObjectId(UUID id);

    /**
     * Check if the obj exists, update the existing or add a new one
     * 
     * @param obj
     * @param fileName
     */
    public static void saveToFile(JSONObject obj, String fileName) {
        try {
<<<<<<< HEAD
            JSONObject o = loadJsonObject(fileName, (UUID) obj.get(ID_STR_CONST));
=======
            JSONObject o = loadJsonObject(fileName,(UUID) obj.get(ID_STR_CONST));
>>>>>>> ben_dev2
            o.get(ID_STR_CONST);
            updateObjectInFile(obj, fileName);
        } catch (JSONException e) {
            appendToFile(obj, fileName);
        }
    }

    /**
     * Add the item to the existing data file
     * 
     * @param obj
     * @param fileName
     */
    public static void appendToFile(JSONObject obj, String fileName) {
        // turn the file into a json array
        JSONArray objArray = read(fileName);
        // add the new obj to the
        objArray.put(obj);
        write(fileName, false, objArray.toString());
    }

    /**
     * Remove the given json object from the file
     * @param obj
     * @param fileName
     */
    public static void removeFromFile(JSONObject obj, String fileName){
        JSONArray objArray = read(fileName);
        JSONArray newArray = new JSONArray();
        for(int i = 0; i < objArray.length(); i++){
            JSONObject o = objArray.getJSONObject(i);
            if(!o.getString(ID_STR_CONST).equals(obj.getString(ID_STR_CONST))){
                newArray.put(o);
            }
        }
        write(fileName, false, newArray.toString());
    }

    /**
     * Use the given class name to find the obj with the matching id
     * 
     * @param className
     * @param id
     * @return
     */
    public static JSONObject loadJsonObject(String className, UUID id) {
        JSONObject returnObject = new JSONObject();
        // pull the array for all objects of given type
        JSONArray arr = ISaveable.read(className);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            // check if it has the ID we are searching for
            UUID testId = UUID.fromString((String) obj.get(ID_STR_CONST));
            if (testId.equals(id)) {
                returnObject = obj;
            }
        }
        return returnObject;
    }

    /**
     * Read all the objects from the file for the given class name and return them
     * as a jsonArray
     * 
     * @param className
     * @return
     */
    public static JSONArray loadAllAsJson(String className) {
        return ISaveable.read(className);
    }

    /**
     * Load the object of the given type with the supplied id
     * 
     * @param classObject
     * @param uuid
     * @return
     */
    public static Object load(Class classObject, UUID uuid) {
        String s = Paths.get("").toAbsolutePath().toString();
        String classFileName = removeLeadingNamespace(classObject.getName());
        try {
            JSONObject obj = loadJsonObject(classFileName, uuid);
            // instantiate it into an object
            Object inst = instantiate(removeLeadingLetter(obj.get(TYPE_STR_CONST).toString()));
            Class<?> clazz = inst.getClass();
            // fill all the attributes
            Field[] fields = getAllFields(clazz);
            for (Field f : fields) {
                f.setAccessible(true);
                try {
                    f.set(inst, obj.get(f.getName()));
                } catch (Exception e) {
                    try {

                        f.set(inst, UUID.fromString((String)obj.get(f.getName())));
                    } catch (ClassCastException ec) {
                        //load the ids for relations
                        JSONArray a = (JSONArray) obj.get(f.getName());
                        ArrayList<UUID> ids = new ArrayList<>();
                        for(int i = 0; i < a.length(); i++){
                            
                            ids.add(UUID.fromString((String)a.get(i)));
                        }
                        f.set(inst, ids);
                    } catch(JSONException je){
                        System.out.println(f.getName() + " Not found in " + obj.toString());
                    }
                    
                }
            }
            return inst;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new UnknownError("Unable to load");
    }

    /**
     * Helper function to add two json arrays
     * 
     * @param a1
     * @param a2
     * @return
     */
    public static JSONArray add(JSONArray a1, JSONArray a2) {
        JSONArray master = new JSONArray();
        for (int i = 0; i < a1.length(); i++) {
            master.put(a1.getJSONObject(i));
        }
        for (int i = 0; i < a2.length(); i++) {
            master.put(a2.getJSONObject(i));
        }
        return master;
    }

    /**
     * Helper function to remove the leading A or I from the class name
     * 
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
     * 
     * @param str
     * @return
     */
    static String removeLeadingNamespace(String str) {
        try {
            if (str.subSequence(0, 15).equals(ENTITY_NS_CONST)) {
                return str.substring(15);
            }
        } catch (Exception e) {
            System.out.println("Failed to remove leading namespace");
        }
        
        return str;
    }

    /**
     * Instantiate an object of the given type
     * 
     * @param className
     * @return
     */
    static Object instantiate(String className) {
        // Load the class.
        className = ENTITY_NS_CONST + className;
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
     * Replace the existing obj in the data file with the new one Searches by ID
     * 
     * @param obj
     * @param fileName
     */
    public static void updateObjectInFile(JSONObject obj, String fileName) {
        // turn the file into a json array
        JSONArray objArray = read(fileName);
        for (int i = 0; i < objArray.length(); i++) {
            JSONObject testObj = objArray.getJSONObject(i);
            if (testObj.getString(ID_STR_CONST).equals(obj.getString(ID_STR_CONST))) {
                objArray.remove(i);
                objArray.put(obj);
            }
        }
        write(fileName, false, objArray.toString());
    }

    /**
     * Traverse the class hierarchy to find all the fields of the given class
     * 
     * @param itemClass
     * @return
     */
    public static Field[] getAllFields(Class itemClass) {
        Field[] f0 = itemClass.getDeclaredFields();
        Field[] f1 = itemClass.getSuperclass().getDeclaredFields();
        Field[] f2 = itemClass.getSuperclass().getSuperclass().getDeclaredFields();
        Field[] f3 = itemClass.getSuperclass().getSuperclass().getSuperclass().getDeclaredFields();
        Field[] fa = add(f0, f1);
        Field[] fb = add(f2, f3);
        return add(fa, fb);
    }

    /**
     * Helper function to add two simple arrays of fields together
     * 
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
     * 
     * @param fileName the name of the file to be written (it will figure out the
     *                 full path)
     * @param append   boolean if it should append to the file or not
     * @param content  the content to be written
     */
    public static void write(String fileName, boolean append, String content) {
        BufferedWriter buffOut = null;
        String fullFilePath = Paths.get("").toAbsolutePath().toString();
        fullFilePath = fullFilePath + File.separator + DATA_DIR_CONST + File.separator;
        fileName = fileName + DATA_FILE_EXT_CONST;
        File dir = new File(fullFilePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        fullFilePath = fullFilePath + fileName;
        try {
            buffOut = new BufferedWriter(new FileWriter(fullFilePath, append));
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
     * Read the data from the given file and return it as a json array
     * 
     * @param fileName
     * @return
     */
    public static JSONArray read(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        JSONArray arr = new JSONArray();
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + DATA_DIR_CONST + File.separator + fileName + DATA_FILE_EXT_CONST;
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
            arr = new JSONArray(contentBuilder.toString());
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " : file not found.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch(JSONException e){
            // System.out.println(fileName + " not a valid json file");
        }
        return arr;
    }

    /**
     * Wipe the file clean
     * If you're wiping a file for a class you probably want to pass MyClass.class.getName()
     * @param fileName
     */
    public static void clearFile(String fileName) {
        fileName = removeLeadingNamespace(fileName);
        fileName = removeLeadingLetter(fileName);
        String s = Paths.get("").toAbsolutePath().toString();
        s = s + File.separator + DATA_DIR_CONST + File.separator + fileName + DATA_FILE_EXT_CONST;
        try {
            new FileWriter(s, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
