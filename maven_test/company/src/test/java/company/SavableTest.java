package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import company.Entity.Interface.ISaveable;

public class SavableTest {

    /**
     * Rigorous Test :-)
     * Sanity check
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Creates a simple json array of two simple json objects
     * @return
     */
    private JSONArray buildSimpleArray(){
        JSONArray a = new JSONArray();
        a.put(buildSimpleObject());
        a.put(buildSimpleObject());
        return a;
    }

    /**
     * Creates a simple json object with an id and a 'Message'
     * @return
     */
    private JSONObject buildSimpleObject(){
        UUID id = UUID.randomUUID();
        JSONObject o = new JSONObject();
        o.put(ISaveable.ID_STR_CONST, id.toString());
        o.put("Message", "Hello World");
        return o;
    }

    /**
     * Make sure read/write file works
     */
    @Test
    public void readWriteFileTest(){
        JSONArray a = buildSimpleArray();
        JSONObject o = a.getJSONObject(0);
        ISaveable.write("test", false, a.toString());
        JSONArray a2 = ISaveable.read("test");
        JSONObject o2 = a2.getJSONObject(0);
        boolean isSame = o2.getString("Message").equals(o.get("Message"));
        assertTrue("Failed to read/write simple json to file", isSame);
        // ISaveable.clearFile("test");
    }

    /**
     * Write an object to file, then try to update the object
     */
    // @Test
    public void testObjectUpdate(){

    }
}