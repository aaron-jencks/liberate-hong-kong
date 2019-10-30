package Entity.Abstract;

import Entity.Interface.ISaveable;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;

public abstract class ASaveable implements ISaveable
{
    @Override
    public void Save()
    {
        JSONObject obj = new JSONObject();
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields
            ) {
                obj.put(f.getName(), f.get(this));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String s = Paths.get("").toAbsolutePath().toString();
        try (FileWriter file = new FileWriter(new File(s + "\\src\\Data\\" + this.getClass().getName() + ".json"))) {
            file.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
