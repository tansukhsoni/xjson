package com.jsonutils.xjson;

import java.util.ArrayList;

public class JSONArray {

	private final ArrayList jsonArrayItems;
	
	/**
	 * JSONArray Constructor
	 */
	public JSONArray(String jsonString) throws JSONException{
		this.jsonArrayItems = new ArrayList();
		/* TODO: Parse items in the array which begins with [ and
		 * ends with ]. The items must be separated by a comma.
		 */
	}
	
	/**
	 * Get the string value at the given index in the array. All
	 * items other than JSONObject and JSONArray are treated as strings.
	 * @param index The position of the String in the array
	 * @return String at the given index 
	 */
	public String getString(int index) throws JSONException{
		return "";
	}

	/**
	 * Get the JSONObject at the given index in the array.
	 * @param index The position of the String in the array
	 * @return JSONObject Value at the given index 
	 */
	public JSONObject getJSONObject(int index) throws JSONException{
		// TODO: Retrieve the value based
		return new JSONObject("");
	}
	
	/**
	 * Get the JSONArray at the given index in the array.
	 * @param index The position of the String in the array
	 * @return JSONArray Value at the given index 
	 */
	public JSONArray getJSONArray(int index) throws JSONException{
		return new JSONArray("");
	}
	
	/**
	 * Returns the length of the array.
	 * @return Length of the array 
	 */
	public int length(){
		return -1;
	}
	
	/**
	 * Converts the JSONArray to and XML String
	 * @return XML representation of the JSON array
	 */
	public String toXML(){
		return "";
	}
	
	/**
	 * Converts the JSONArray to string
	 * @return String representation of the JSON array
	 */
	public String toString(){
		return new String("");
	}
	
}
