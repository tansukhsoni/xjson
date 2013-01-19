package com.jsonutils.xjson;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JSONArrayTest extends TestCase{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JSONArrayTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JSONArrayTest.class );
    }
    
    public void testJSONArray1(){
    	try{
    		JSONArray jsonArray = new JSONArray("[\"Mary\",\"Edith\",\"Sybil\"]");
    		
    		assertEquals(jsonArray.length(), 3);
    		
    		assertEquals(jsonArray.getString(0), "Mary");
    		assertEquals(jsonArray.getString(1), "Edith");
    		assertEquals(jsonArray.getString(2), "Sybil");
    		
    	} catch(JSONException ex){
    		System.out.println(ex.toString());
    	}
    }
}

    