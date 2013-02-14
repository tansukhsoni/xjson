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
    		JSONObject jsonObject = new JSONObject("{\"Name\":\"Tom\",\"City\":\"NY\",\"Age\":\"25\"}");
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
    		
    		List<XMLNode> xnodeList = jsonObject.toXMLNodeList("", "");
    		assertEquals(xnodeList.size(), 3);
    		assertEquals(xnodeList.get(0).getNodeName(), "Name");
    		assertEquals(xnodeList.get(1).getNodeName(), "City");
    		assertEquals(xnodeList.get(2).getNodeName(), "Age");
    		
    		assertEquals(xnodeList.get(0).getChildList().size(), 1);
    		assertEquals(xnodeList.get(1).getChildList().size(), 1);
    		assertEquals(xnodeList.get(2).getChildList().size(), 1);
    		
    		assertEquals(xnodeList.get(0).getChildList().get(0).getNodeName(), "Tom");
    		assertEquals(xnodeList.get(1).getChildList().get(0).getNodeName(), "NY");
    		assertEquals(xnodeList.get(2).getChildList().get(0).getNodeName(), "25");
    		
    	}
    	catch(JSONException ex){
    		ex.printStackTrace();
    		assert(false);
    	}
    }
    
    public void testJSONObject2(){
    	
    	StringBuilder jsonString = new StringBuilder();
    	jsonString.append("{\"Person\":{\"Firstname\":\"John\", \"Lastname\":\"Miller\"}, \"Siblings\":[{\"Firstname\":\"Kate\", \"Lastname\":\"Branson\"},");
    	jsonString.append("{\"Firstname\":\"Amy\", \"Lastname\":\"Ross\"}]}");
    	try{
    		JSONObject jo = new JSONObject(jsonString.toString());
    		List<String> keys = jo.getKeys();
    		assertEquals(keys.get(0),"Person");
    		assertEquals(keys.get(1),"Siblings");
    		
    		JSONObject personJO = jo.getJSONObject("Person");
    		assertEquals(personJO.getKeys().size(), 2);
    		
    		assertEquals(personJO.getString("Firstname"), "John");
    		assertEquals(personJO.getString("Lastname"), "Miller");
    		
    		JSONArray siblingsJA = jo.getJSONArray("Siblings");
    		assertEquals(siblingsJA.length(), 2);
    		assertEquals(siblingsJA.getJSONObject(0).getString("Firstname"), "Kate");
    		assertEquals(siblingsJA.getJSONObject(0).getString("Lastname"), "Branson");
    		assertEquals(siblingsJA.getJSONObject(1).getString("Firstname"), "Amy");
    		assertEquals(siblingsJA.getJSONObject(1).getString("Lastname"), "Ross");
    		
    		List<XMLNode> xnodeList = jo.toXMLNodeList("", "");
    		
    		assertEquals(xnodeList.size(), 2);
    		assertEquals(xnodeList.get(0).getNodeName(), "Person");
    		assertEquals(xnodeList.get(0).getChildList().size(), 2);
    		assertEquals(xnodeList.get(1).getNodeName(), "Siblings");
    		assertEquals(xnodeList.get(1).getChildList().size(), 2);
    		
    		assertEquals(xnodeList.get(0).getChildList().get(0).getNodeName(), "Firstname");
    		assertEquals(xnodeList.get(0).getChildList().get(0).getChildList().size(), 1);
    		assertEquals(xnodeList.get(0).getChildList().get(0).getChildList().get(0).getNodeName(), "John");
    		assertEquals(xnodeList.get(0).getChildList().get(1).getNodeName(), "Lastname");
    		assertEquals(xnodeList.get(0).getChildList().get(1).getChildList().size(), 1);
    		assertEquals(xnodeList.get(0).getChildList().get(1).getChildList().get(0).getNodeName(), "Miller");
    		
    		//TODO: Add more test cases
    		
    	} catch (JSONException ex){
    		ex.printStackTrace();
    	}
    }
}
