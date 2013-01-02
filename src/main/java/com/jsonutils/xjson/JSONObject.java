package com.jsonutils.xjson;

import java.util.ArrayList;
import java.util.List;

public class JSONObject {
	
	/**
	 * Constructor
	 */
	public JSONObject(){
		
	}
	
	/**
	 * Constructor
	 * @param jsonString JSON string
	 */
	public JSONObject(String jsonString){
	
	}
	
	/**
	 * Returns the list of keys in the json object
	 * @return
	 */
	public List<String> getKeyList(){
		List<String> keyList = new ArrayList<String>();
		// TODO: Obtain the list of keys
		return keyList;
	}
	
	public JSONObject getValue(String key){
		// TODO: Retrieve the value based
		return new JSONObject();
	}
	
	public String toString(){
		return new String("");
	}

}
