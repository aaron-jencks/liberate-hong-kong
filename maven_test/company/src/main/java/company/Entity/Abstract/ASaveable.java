package company.Entity.Abstract;

import company.Entity.Interface.ISaveable;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.UUID;

public abstract class ASaveable implements ISaveable {

    public UUID objId;

    public ASaveable(){
        this.objId = UUID.randomUUID();
    }

    @Override
    public UUID save() {
        UUID id = UUID.randomUUID();
        JSONObject obj = new JSONObject();
        boolean append = true;
        String type = this.getClass().getSimpleName();
        // get the fields
        Field[] fields = ISaveable.getAllFields(this.getClass());
        // if it has an id it should already be stored and can be loaded
        if (this.objId != null) {
            id = this.objId;
            obj = ISaveable.loadJsonObject(ISaveable.removeLeadingLetter(this.getClass().getSuperclass().getSimpleName()), id);
            append = false;
        }

        // add the type and id
        obj.put("type", ISaveable.removeLeadingLetter(type));
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
            ISaveable.appendToFile(obj, ISaveable.removeLeadingLetter(type));
        } else {
            // update the existing entry for the object
            ISaveable.updateObjectInFile(obj, ISaveable.removeLeadingLetter(type));
        }
        return id;
    }

    @Override
    public UUID getObjectId() {
        return this.objId;
    }

}
