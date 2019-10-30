package company.Entity.Abstract;

import company.Entity.Interface.ISaveable;

import org.json.JSONObject;

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

public abstract class ASaveable implements ISaveable {
    @Override
    public void save() {
        JSONObject obj = new JSONObject();
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        String type = this.getClass().getSuperclass().getSimpleName();
        if (fields.length == 0) {
            fields = this.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        }
        obj.put("type", type);
        try {
            for (Field f : fields) {
                obj.put(f.getName(), f.get(this));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String s = Paths.get("").toAbsolutePath().toString();
        try (FileWriter file = new FileWriter(new File(s + "\\data\\" + this.getClass().getName() + ".json"))) {
            file.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object load() {
        String s = Paths.get("").toAbsolutePath().toString();
        File file = new File(s + "\\data\\" + this.getClass().getName() + ".json");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                //get the next line in file
                String line = sc.nextLine();
                //make the string into json
                JSONObject obj = new JSONObject(line);
                //instantiate it into an object
                Object inst = instantiate(removeLeadingA(obj.get("type").toString()));
                Class<?> clazz = inst.getClass();
                //fill all the attributes
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
