package com.jsonutils.xjson;

public class test{
	
	public static void main(String [] args) throws JSONException
	{
		//JSONObject jo = new JSONObject("{\"menu\":{\"1\":\"sql\", \"2\":\"android\", \"3\":\"mvc\"}}");
		JSONObject newJO = new JSONObject("{\"person\":{\"child\":\"PC\",\"@attr\":{\"sex\":\"M\",\"age\":\"25\"},\"@text\":\"Rahul\"},\"address\":\"Barmer\",\"company\":\"tcs\"}");
		String toStr= newJO.toString();
		System.out.println(toStr);
		//JSONObject jo1= jo.getJSONObject("menu");
		//String test = jo.getString("menu");
		//System.out.println(test);
		//String str = jo1.getString("1");
		//System.out.println(str);
		String str2 = newJO.toXML("@attr","@text");
		System.out.println(str2);
		JSONArray newJA = new JSONArray("[\"Mary\",\"Edith\",\"Sybil\","+"{\"person\":{\"child\":\"PC\",\"@attr\":{\"sex\":\"M\",\"age\":\"25\"},\"@text\":\"Rahul\"},\"address\":\"Barmer\",\"company\":\"tcs\"}"+","+"[\"kailash\",\"Pawan\",\"Ravi\"]"+"]");
		String toX = newJA.toXML("@attr", "@text");
		String toStr2 = newJA.toString();
		System.out.println(toStr2);
		System.out.println(newJA.getJSONObject(3));
		System.out.println(newJA.getJSONArray(4));		
		System.out.println(toX);
		
	}
}