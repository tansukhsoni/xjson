package com.jsonutils.xjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.ObjectName;

import com.jsonutils.xjson.JSONTokenizer.Token;
	
public class JSONObject {
	
	 private JSONTokenizer jsonTokenizer;
	 private Map<String,Object> jsonObjectItems = new HashMap<String,Object>();
	 private List<String> keyList = new ArrayList<String>();
	 private List<Object> valueList = new ArrayList<Object>();

	
	/**
	 * Constructor
	 * @param jsonString JSON string
	 */
	public JSONObject(String jsonString) throws JSONException{
		
		jsonTokenizer = new JSONTokenizer (jsonString);
		jsonTokenizer.setCursorPosition(0);
		int flag=0;
	    while (jsonTokenizer.nextToken()!=Token.EOF)
	    {   String str = ObjectName.unquote(jsonTokenizer.getCurrent());
	    	if (flag%2==0)
		    	keyList.add(str);
	    	else
	    		valueList.add(str);
	    	flag++;
	    	

	    }
	    
	    for (int i =0; i<keyList.size();i++)
	    {
	    	jsonObjectItems.put(keyList.get(i), valueList.get(i));
	    }
     
		/* TODO: Parse the string to construct a the JSONObject. The
		 * string must begin with { and end with }. The keys should be
		 * separated from the values with a colon.
		 */
	}
	
	/**
	 * Returns the list of keys in the json object
	 * @return A list of the keys in the JSONObject
	 */
	public List<String> getKeys(){
		// TODO: Obtain the list of keys
		return keyList;
	}
	
	/**
	 * Returns an enumeration of the keys in JSONObject
	 * @return Iterator An iterator for the keys
	 */
	public Iterator<String> keys(){
		return this.jsonObjectItems.keySet().iterator();
	}
	
	/**
	 * Returns the String value for the given key.
	 * @param key A key in JSON Object
	 * @return String corresponding to the given key
	 * @throws JSONException
	 */
	public String getString(String key) throws JSONException{
		return (String) jsonObjectItems.get(key);
	}

	/**
	 * Returns the JSONObject value for the given key.
	 * @param key A key in JSON Object
	 * @return JSONObject corresponding to the given key
	 * @throws JSONException
	 */
	public JSONObject getJSONObject(String key) throws JSONException{
		// TODO: Retrieve the value based
		return new JSONObject("");
	}
	
	/**
	 * Returns the JSONArray value for the given key.
	 * @param key A key in JSON Object
	 * @return JSONArray corresponding to the given key
	 * @throws JSONException
	 */
	public JSONArray getJSONArray(String key) throws JSONException{
		return new JSONArray("");
	}

	/**
	 * Checks if the JSON Object contains a key.
	 * @param key The key which is to be checked for membership
	 * @return true if the key is present, false otherwise.
	 * @throws JSONException
	 */
	public boolean contains(String key) throws JSONException{
		if (jsonObjectItems.containsKey(key))
			return true;
		else
		    return false;
		
	}

	/**
	 * Converts the JSONObject to an XML String
	 * @return XML representation of the JSON object
	 * @throws JSONException 
	 */
	
	public String toXML() throws JSONException{
		String XMLString="";
		for (String key : keyList)
		{
		   	XMLString = XMLString.concat("<");
		   	XMLString = XMLString.concat(key);
		   	XMLString = XMLString.concat(">");
		   	XMLString = XMLString.concat(this.getString(key));
		   	XMLString = XMLString.concat("</");
		   	XMLString = XMLString.concat(key);
		   	XMLString = XMLString.concat(">");
		   	
		   	
		}
		return XMLString;
	}

	/**
	 * Converts the JSONObject to a String
	 * @return String representation of the JSON object
	 */
	public String toString(){
		
		String str = "\"{";
		for (String key : keyList)
		{  try
		 {
			str = str+"\\\"";
			str = str+ key;
			str = str+"\\\"";
			str = str+":";
			str = str+"\\\"";
			str = str+this.getString(key);
			str = str+"\\\"";
			str = str+",";
		 }	
		catch (JSONException ex)
		 {
			System.out.println(ex.toString());
		 }
		}
		str=str.substring(0,str.length()-1);
		str= str+"}\"";
		return str;
	}

}
