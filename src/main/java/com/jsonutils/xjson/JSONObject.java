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
	private Map<String, Object> jsonObjectItems = new HashMap<String, Object>();
	private List<String> keyList = new ArrayList<String>();
	private List<Object> valueList = new ArrayList<Object>();

	/**
	 * Constructor
	 * 
	 * @param jsonString
	 *            JSON string
	 */
	public JSONObject(String jsonString) throws JSONException {

		jsonTokenizer = new JSONTokenizer(jsonString);
		jsonTokenizer.setCursorPosition(0);
		if (jsonString.charAt(0) == '{'
				&& jsonString.charAt(jsonString.length() - 1) == '}')
		{
			String tempStr;
			JSONObject tempJSONObj;
			JSONArray  tempJSONArr;
			int flag = 0;
			int endFlag=0;

			while (endFlag==0) 
			{
                if (jsonTokenizer.currentToken() == Token.START_BRACE
						|| jsonTokenizer.currentToken() == Token.COMMA)
				{
					tempStr = jsonTokenizer.getCurrent();
					if (tempStr.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
						keyList.add(ObjectName.unquote(tempStr));
					else
						keyList.add(tempStr);
						
				}

				else if (jsonTokenizer.currentToken() == Token.COLON)
				{   String valueString=jsonTokenizer.getCurrent();
					
					if (jsonTokenizer.currentToken() == Token.START_BRACE )
					{   flag++;
						tempStr = "{";
						while (flag!=0)
						{  
							String currentString=jsonTokenizer.getCurrent();
							if (currentString.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
								tempStr=tempStr.concat(ObjectName.unquote(currentString));
							else
								tempStr=tempStr.concat(currentString);
							if (jsonTokenizer.currentToken() == Token.START_BRACE )
							{
								tempStr=tempStr.concat("{");
								flag++;								
							}
								
							else if (jsonTokenizer.currentToken() == Token.END_BRACE )
							{
								tempStr=tempStr.concat("}");
								flag--;
							}
							else
							{
								Character  ch = new Character(jsonString.charAt(jsonTokenizer.getCursorPosition()));
								String str = ch.toString();
								tempStr=tempStr.concat(str);
							}
													
						}
						    jsonTokenizer.getCurrent();
						    tempJSONObj = new JSONObject(tempStr);
						    valueList.add(tempJSONObj);
						    tempStr="";
					}
					else if (jsonTokenizer.currentToken() == Token.START_BRACKET)
					{
						flag++;
						tempStr = "[";
						while (flag!=0)
						{
							tempStr=tempStr.concat(jsonTokenizer
									.getCurrent());
							if (jsonTokenizer.currentToken() == Token.START_BRACKET )
							{
								tempStr=tempStr.concat("[");
								flag++;								
							}
								
							else if (jsonTokenizer.currentToken() == Token.END_BRACKET )
							{
								tempStr=tempStr.concat("]");
								flag--;
							}
							else
							{
								Character  ch = new Character(jsonString.charAt(jsonTokenizer.getCursorPosition()));
								String str = ch.toString();
								tempStr=tempStr.concat(str);
							}
													
						}
						    jsonTokenizer.getCurrent();
						    tempJSONArr = new JSONArray(tempStr);
						    valueList.add(tempJSONArr);
						    tempStr="";

					}
					else
					{
						if (valueString.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
							valueList.add(ObjectName.unquote(valueString));
						else
							valueList.add(valueString);
					}
						

				}
				
				else
				{
					endFlag=1;
					break;
				}
								

			}

			for (int i = 0; i < keyList.size(); i++)
			{
				jsonObjectItems.put(keyList.get(i), valueList.get(i));
			}
		}
		else
			throw new JSONException("jsonString not having required format");
		/*
		 * TODO: Parse the string to construct a the JSONObject. The string must
		 * begin with { and end with }. The keys should be separated from the
		 * values with a colon.
		 */
	}

	/**
	 * Returns the list of keys in the json object
	 * 
	 * @return A list of the keys in the JSONObject
	 */
	public List<String> getKeys() {
		// TODO: Obtain the list of keys
		return keyList;
	}

	/**
	 * Returns an enumeration of the keys in JSONObject
	 * 
	 * @return Iterator An iterator for the keys
	 */
	public Iterator<String> keys() {
		return this.jsonObjectItems.keySet().iterator();
	}

	/**
	 * Returns the String value for the given key.
	 * 
	 * @param key
	 *            A key in JSON Object
	 * @return String corresponding to the given key
	 * @throws JSONException
	 */
	public String getString(String key) throws JSONException{
		
		if (this.contains(key))
		{
			if (jsonObjectItems.get(key) instanceof JSONObject)
				{
					JSONObject jo = (JSONObject) jsonObjectItems.get(key);
					return jo.toString();
				}
			else if (jsonObjectItems.get(key) instanceof JSONArray)
				{
					JSONArray ja = (JSONArray) jsonObjectItems.get(key);
					return ja.toString();
				}
			else
				return (String) jsonObjectItems.get(key);
				
			}
		else
			throw new JSONException(key+"key not present in JSON Object");
		}
		

	/**
	 * Returns the JSONObject value for the given key.
	 * 
	 * @param key
	 *            A key in JSON Object
	 * @return JSONObject corresponding to the given key
	 * @throws JSONException
	 */
	public JSONObject getJSONObject(String key) throws JSONException {
		// TODO: Retrieve the value based
		try{
			if (this.contains(key)) {
				return (JSONObject) jsonObjectItems.get(key);
			} else
				throw new JSONException("key not present");
		}
		catch(ClassCastException ex)
		{
			throw new JSONException("Value corresponding to the mentioned key is not a JSONObject");
		}

	}

	/**
	 * Returns the JSONArray value for the given key.
	 * 
	 * @param key
	 *            A key in JSON Object
	 * @return JSONArray corresponding to the given key
	 * @throws JSONException
	 */
	public JSONArray getJSONArray(String key) throws JSONException {
		
		try{
			if(this.contains(key))
			{
				return (JSONArray) jsonObjectItems.get(key);
			}
			else
				throw new JSONException("key not present");
			
		}
		catch(ClassCastException ex)
		{
			throw new JSONException("Value corresponding to the mentioned key is not an JSONArray");
		}
		
	}

	/**
	 * Checks if the JSON Object contains a key.
	 * 
	 * @param key
	 *            The key which is to be checked for membership
	 * @return true if the key is present, false otherwise.
	 * @throws JSONException
	 */
	public boolean contains(String key) throws JSONException {
		if (jsonObjectItems.containsKey(key))
			return true;
		else
			return false;

	}

	/**
	 * Converts the JSONObject to an XML String
	 * 
	 * @return XML representation of the JSON object
	 * @throws JSONException
	 */

	public String toXML(String attrKey , String textKey) throws JSONException {
		try{
		String XMLString = "";
		for (String key : keyList) {
			
			String textValue="";
			String attrString="";
			String childString="";
			XMLString = XMLString.concat("<");
			XMLString = XMLString.concat(key);
			String className = jsonObjectItems.get(key).getClass().getCanonicalName();
			if (className=="com.jsonutils.xjson.JSONObject")
			{
				JSONObject tempJSONObject = this.getJSONObject(key);
				for (String externalKey : tempJSONObject.keyList)
				{
					if (externalKey.equals(attrKey))
					{
						JSONObject tempJSONObject2 = tempJSONObject.getJSONObject(externalKey);
						for (String internalKey : tempJSONObject2.keyList )
						{
							attrString= attrString.concat(" ");
							attrString= attrString.concat(internalKey);
							attrString= attrString.concat("=");
							attrString= attrString.concat("\"");
							attrString= attrString.concat(tempJSONObject2.getString(internalKey));
							attrString= attrString.concat("\"");
							attrString= attrString.concat(",");
						}
						attrString= attrString.substring(0, attrString.length()-1);
						
					}
					else if (externalKey.equals(textKey))
					{
						textValue= tempJSONObject.getString(externalKey);
					}
					else
					{
						childString= childString.concat("<");
						childString= childString.concat(externalKey);
						childString= childString.concat(">");
						childString= childString.concat(tempJSONObject.getString(externalKey));
						childString= childString.concat("</");
						childString= childString.concat(externalKey + ">");
					}
					
				}
				
			}
			
			else if (className=="com.jsonutils.xjson.JSONArray")
			{
				JSONArray jsonArr = this.getJSONArray(key);
				textValue= jsonArr.toXML(attrKey, textKey);
				
			}
			else
			{
				textValue= this.getString(key);
			}
				
			
			XMLString= XMLString.concat(attrString);
			XMLString= XMLString.concat(">");
			XMLString= XMLString.concat(textValue);
			XMLString= XMLString.concat(childString);
			XMLString= XMLString.concat("</");
			XMLString= XMLString.concat(key + ">" );
			
		}
		
		return XMLString;
		}
		catch (ClassCastException ex)
		{
			throw new JSONException("exception during accessing value for key");
		}
	}
	
	/**
	 * Converts the JSONObject to XML representation
	 * @param attrKey The key in JSONObject which corresponds to XML attributes
	 * @param textKey The in JSONObject which corresponds to text content of the xml node
	 * @return Corresponding XML Node
	 * @throws JSONException 
	 */
	public List<XMLNode> toXMLNodeList(String attrKey, String textKey) throws JSONException{
		List<XMLNode> xnodeList=new ArrayList<XMLNode>();
		for (String key: this.keyList)
		{
			XMLNode node = new XMLNode();
			node.setNodeName(key);
			String className = jsonObjectItems.get(key).getClass().getCanonicalName();
			if (className == "com.jsonutils.xjson.JSONObject")
			{
				JSONObject tempJSONObject = this.getJSONObject(key);
				for (String externalKey : tempJSONObject.keyList)
				{
					if (externalKey.equals(attrKey))
					{
						JSONObject tempJSONObject2 = tempJSONObject.getJSONObject(externalKey);
						for (String internalKey : tempJSONObject2.keyList )
						{
							node.addAttribute(internalKey, tempJSONObject2.getString(internalKey));
						}
	
					}
					else if (externalKey.equals(textKey))
					{
						node.setTextValue(tempJSONObject.getString(externalKey));
					}
					else
					{   
						XMLNode xnode = new XMLNode();
					    xnode.setNodeName(externalKey);
					    String className1 = tempJSONObject.jsonObjectItems.get(externalKey).getClass().getCanonicalName();
					    if (className1 == "com.jsonutils.xjson.JSONObject")
					    {
					    	xnode.setTextValue(tempJSONObject.getJSONObject(externalKey).toXML(attrKey, textKey));
					    }
					    else if (className1 == "com.jsonutils.xjson.JSONArray")
					    {
					    	xnode.setTextValue(tempJSONObject.getJSONArray(externalKey).toXML(attrKey, textKey));
					    }
					    else
					    	xnode.setTextValue(tempJSONObject.getString(externalKey));
					    node.addChild(xnode);
					}
					
				}
				
			
			}
			else if (className == "com.jsonutils.xjson.JSONArray")
			{
				JSONArray ja = this.getJSONArray(key);
				List<XMLNode> nodeList= ja.toXMLNodeList(attrKey, textKey);
				node.addChildList(nodeList);
			}
			else
			{
				node.setTextValue(this.getString(key));
			}
			
			xnodeList.add(node);
			
		}
		return xnodeList;
	}
	
	/**
	 * Converts the JSONObject to a String
	 * 
	 * @return String representation of the JSON object
	 */
	public String toString() {

		String str = "{";
		for (String key : keyList) {
			try {
				str = str + "\\\"";
				str = str + key;
				str = str + "\\\"";
				str = str + ":";
				str = str + "\\\"";
				str = str + this.getString(key);
				str = str + "\\\"";
				str = str + ",";
			} catch (JSONException ex) {
				System.out.println(ex.toString());
			}
		}
		str = str.substring(0, str.length() - 1);
		str = str + "}";
		return str;
	}

}
