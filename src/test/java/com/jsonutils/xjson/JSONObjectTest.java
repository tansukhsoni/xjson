package com.jsonutils.xjson;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JSONObjectTest extends TestCase{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JSONObjectTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JSONObjectTest.class );
    }
    
    public void testJSONObject1(){
    	try{
    		JSONObject jsonObject = new JSONObject("{\"Name\":\"Tom\", \"City\":\"NY\", \"Age\":\"25\"}");
    		List<String> keys = jsonObject.getKeys();
    		assertEquals(keys.get(0),"Name");
    		assertEquals(keys.get(1),"City");
    		assertEquals(keys.get(2),"Age");
    		
    		assertEquals(jsonObject.getString(keys.get(0)), "Tom");
    		assertEquals(jsonObject.getString(keys.get(1)), "NY");
    		assertEquals(jsonObject.getString(keys.get(2)), "25");
    		
    		assertEquals(jsonObject.contains("Name"), true);
    		assertEquals(jsonObject.contains("City"), true);
    		assertEquals(jsonObject.contains("Age"), true);
    		
    		assertEquals(jsonObject.toXML(), "<Name>Tom</Name><City>NY</City><Age>25</Age>");
    		
    	}
    	catch(JSONException ex){
    		System.out.println(ex.toString());
    		assert(false);
    	}
    }
}
