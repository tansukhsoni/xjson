package com.jsonutils.xjson;

import java.util.ArrayList;
import java.util.List;

import javax.management.ObjectName;

import com.jsonutils.xjson.JSONTokenizer.Token;

public class JSONArray {

	private final ArrayList<Object> jsonArrayItems;
	private JSONTokenizer jsonTokenizer;
	
	/**
	 * JSONArray Constructor
	 */
	public JSONArray(String jsonString) throws JSONException{
		this.jsonArrayItems = new ArrayList<Object>();
		jsonTokenizer = new JSONTokenizer(jsonString);
		jsonTokenizer.setCursorPosition(0);
		if (jsonString.charAt(0)== '[' && jsonString.charAt(jsonString.length()-1)== ']')
		{  
			int endFlag=0;
			while (endFlag==0)
			{
				if (jsonTokenizer.currentToken()==Token.START_BRACKET || jsonTokenizer.currentToken()==Token.COMMA)
				{   
					String valueString= jsonTokenizer.getCurrent();
					int flag=0;
					
					if (jsonTokenizer.currentToken()== Token.START_BRACE)
					{
						flag++;
						String tempStr="{";
						JSONObject tempJSONObj;

						while (flag != 0) {
							String currentString = jsonTokenizer.getCurrent();
							if (currentString
									.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
								tempStr = tempStr.concat(ObjectName.unquote(currentString));
							else
								tempStr = tempStr.concat(currentString);
							if (jsonTokenizer.currentToken() == Token.START_BRACE) {
								tempStr = tempStr.concat("{");
								flag++;
							}

							else if (jsonTokenizer.currentToken() == Token.END_BRACE) {
								tempStr = tempStr.concat("}");
								flag--;
							} else {
								Character ch = new Character(
										jsonString.charAt(jsonTokenizer
												.getCursorPosition()));
								String str = ch.toString();
								tempStr = tempStr.concat(str);
							}

						}
						jsonTokenizer.getCurrent();
						tempJSONObj = new JSONObject(tempStr);
						this.jsonArrayItems.add(tempJSONObj);
						tempStr = "";

					}
					else if (jsonTokenizer.currentToken()==Token.START_BRACKET)
					{
						    flag++;
							String tempStr="[";
							JSONArray tempJSONArr;

							while (flag != 0) {
								String currentString = jsonTokenizer.getCurrent();
								if (currentString
										.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
									tempStr = tempStr.concat(ObjectName.unquote(currentString));
								else
									tempStr = tempStr.concat(currentString);
								if (jsonTokenizer.currentToken() == Token.START_BRACKET) {
									tempStr = tempStr.concat("[");
									flag++;
								}

								else if (jsonTokenizer.currentToken() == Token.END_BRACKET) {
									tempStr = tempStr.concat("]");
									flag--;
								} else {
									Character ch = new Character(
											jsonString.charAt(jsonTokenizer
													.getCursorPosition()));
									String str = ch.toString();
									tempStr = tempStr.concat(str);
								}

							}
							jsonTokenizer.getCurrent();
							tempJSONArr = new JSONArray(tempStr);
							this.jsonArrayItems.add(tempJSONArr);
							tempStr = "";

						}
					else
					{
						if (valueString.matches("'([^\\\\']+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*'|\"([^\\\\\"]+|\\\\([btnfr\"'\\\\]|[0-3]?[0-7]{1,2}|u[0-9a-fA-F]{4}))*\""))
							this.jsonArrayItems.add(ObjectName.unquote(valueString));
						else
							this.jsonArrayItems.add(valueString);
					}
						

				}
				else
				{
					endFlag=1;
				}
			}
		}
}
		
		
		/* TODO: Parse items in the array which begins with [ and
		 * ends with ]. The items must be separated by a comma.
		 */
	
	
	/**
	 * Get the string value at the given index in the array. All
	 * items other than JSONObject and JSONArray are treated as strings.
	 * @param index The position of the String in the array
	 * @return String at the given index 
	 */
	public String getString(int index) throws JSONException{
		if(index<= this.length()-1)
		{
			if (this.jsonArrayItems.get(index) instanceof JSONObject)
			{
				JSONObject jo = (JSONObject) this.jsonArrayItems.get(index);
				return jo.toString();
			}
		    else if (this.jsonArrayItems.get(index) instanceof JSONArray)
			{
				JSONArray ja = (JSONArray) this.jsonArrayItems.get(index);
				return ja.toString();
			}
		   else
			return (String) jsonArrayItems.get(index);

		}
		else
			throw new JSONException(index+"out of range");
				
	}

	/**
	 * Get the JSONObject at the given index in the array.
	 * @param index The position of the String in the array
	 * @return JSONObject Value at the given index 
	 */
	public JSONObject getJSONObject(int index) throws JSONException{
		// TODO: Retrieve the value based
		try{
		return (JSONObject) this.jsonArrayItems.get(index);
		}
		catch(ClassCastException ex)
		{
			throw new JSONException("Value corresponding to the mentioned index is not a JSONObject");
		}
	}
	
	/**
	 * Get the JSONArray at the given index in the array.
	 * @param index The position of the String in the array
	 * @return JSONArray Value at the given index 
	 */
	public JSONArray getJSONArray(int index) throws JSONException{
		try{
		return (JSONArray) this.jsonArrayItems.get(index);
		}
		catch(ClassCastException ex)
		{
			throw new JSONException("Value corresponding to the mentioned index is not a JSONArray");
		}
	}
	
	/**
	 * Returns the length of the array.
	 * @return Length of the array 
	 */
	public int length(){
		return this.jsonArrayItems.size();
	}
	
	/**
	 * Converts the JSONArray to and XML String
	 * @return XML representation of the JSON array
	 * @throws JSONException 
	 */
	public String toXML(String attrKey , String textKey) throws JSONException{
		
		String XMLString = "";
		for (int index=0; index<this.jsonArrayItems.size(); index++)
		{
			String className = this.jsonArrayItems.get(index).getClass().getCanonicalName();
			if (className == "com.jsonutils.xjson.JSONObject")
			{
				JSONObject jo = this.getJSONObject(index);
				XMLString = XMLString.concat(jo.toXML(attrKey, textKey));
			}
			else if (className == "com.jsonutils.xjson.JSONArray")
			{
				JSONArray ja = this.getJSONArray(index);
				XMLString = XMLString.concat(ja.toXML(attrKey, textKey));
			}
			else
			{
				XMLString = XMLString.concat("<");
				XMLString = XMLString.concat(this.getString(index));
				XMLString = XMLString.concat(">");
				XMLString = XMLString.concat("</");
				XMLString = XMLString.concat(this.getString(index));
				XMLString = XMLString.concat(">");
				
			}
				
		}
		
		
		return XMLString;
	}
	
	/**
	 * Returns the corresponding XMLNode list representation of the JSONArray
	 * @param attrKey The JSON key which corresponds to XML attributes
	 * @param textKey The JSON key which corresponds to XML text content
	 * @return Corresponding XMLNode list representation
	 */
	public List<XMLNode> toXMLNodeList(String attrKey, String textKey){
		List<XMLNode> xnodeList = null;
		return xnodeList;
	}
	
	/**
	 * Converts the JSONArray to string
	 * @return String representation of the JSON array
	 */
	public String toString(){
		String toStr="[";
		int index =0;
		try
		{
			while (index<this.length())
			{
				toStr=toStr.concat("\\\"");
				toStr=toStr.concat(this.getString(index));
				toStr=toStr.concat("\\\"");
				toStr=toStr + ",";
				index++;

			}
		
		}
		catch(JSONException ex)
		{
			System.out.println("exception while converting to String");
			ex.printStackTrace();
		}
		toStr = toStr.substring(0, toStr.length() - 1);
		toStr = toStr + "]";
		return toStr;
	}
	
	
}
