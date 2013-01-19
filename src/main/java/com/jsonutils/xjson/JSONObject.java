package com.jsonutils.xjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONObject {
	
	private final Map jsonObjectItems;
	
	/**
	 * Constructor
	 * @param jsonString JSON string
	 */
	public JSONObject(String jsonString) throws JSONException{
		this.jsonObjectItems = new HashMap();
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
		List<String> keyList = new ArrayList<String>();
		// TODO: Obtain the list of keys
		return keyList;
	}
	
	/**
	 * Returns an enumeration of the keys in JSONObject
	 * @return Iterator An iterator for the keys
	 */
	public Iterator keys(){
		return this.jsonObjectItems.keySet().iterator();
	}
	
	/**
	 * Returns the String value for the given key.
	 * @param key A key in JSON Object
	 * @return String corresponding to the given key
	 * @throws JSONException
	 */
	public String getString(String key) throws JSONException{
		return "";
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
		return false;
	}

	/**
	 * Converts the JSONObject to an XML String
	 * @return XML representation of the JSON object
	 */
	public String toXML(){
		return "";
	}

	/**
	 * Converts the JSONObject to a String
	 * @return String representation of the JSON object
	 */
	public String toString(){
		return new String("");
	}

}
