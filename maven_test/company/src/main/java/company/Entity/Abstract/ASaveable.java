package company.Entity.Abstract;

import company.Entity.Interface.ISaveable;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.UUID;

public abstract class ASaveable implements ISaveable {

    private UUID objId;

    /**
     * Simple constructor
     * Sets the uuid
     */
    public ASaveable() {
        this.objId = UUID.randomUUID();
        this.save();
    }

    /**
     * Delete self from db
     */
    @Override
    public void delete() {
        JSONObject o = ISaveable.loadJsonObject(this.getClass().getSimpleName(), this.objId);
        ISaveable.removeFromFile(o, this.getClass().getSimpleName());
    }

    /**
     * Save self to db
     */
    @Override
    public UUID save() {
        JSONObject obj = new JSONObject();
        String type = this.getClass().getSimpleName();
        // get the fields
        Field[] fields = ISaveable.getAllFields(this.getClass());
        // add the type and id
        obj.put(ISaveable.TYPE_STR_CONST, ISaveable.removeLeadingLetter(type));
        // add each field
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                obj.put(f.getName(), f.get(this));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        ISaveable.saveToFile(obj, ISaveable.removeLeadingLetter(type));
        return this.getObjectId();
    }

    @Override
    public UUID getObjectId() {
        return this.objId;
    }

    @Override
    public void setObjectId(UUID id) {
        this.objId = id;
        this.save();
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        String type = this.getClass().getSimpleName();
        String output = "";
        // get the fields
        Field[] fields = ISaveable.getAllFields(this.getClass());
        // add the type and id
        obj.put(ISaveable.TYPE_STR_CONST, ISaveable.removeLeadingLetter(type));
        // add each field
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                obj.put(f.getName(), f.get(this));
                // output += f.getName() + " : " + f.get(this) + ", ";
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

}
