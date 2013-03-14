package com.jsonutils.xjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLNode{
	
	private String nodeName;
	private String textValue;
	private Map<String,String> attrMap;
	private List<XMLNode> childList;
		
	public XMLNode()
	{
		this.nodeName=null;
		this.textValue=null;
		attrMap = new HashMap<String,String>();
		childList= new ArrayList<XMLNode>();
	} 
	
	public XMLNode (String XMLString)
	{
		
	}	
	
	public String getNodeName() throws JSONException
	{
		if (this.nodeName==null)
			throw new JSONException("This is the dummy Node with no name");
		else
			return nodeName;
		
	}
	
	public String getTextValue() throws JSONException
	{
		if (this.textValue==null)
			throw new JSONException("This is the dummy node with no textValue");
		else
			return textValue;
		
	}
	
	public Map<String,String> getAttrList() throws JSONException
	{
		/*if (this.attrMap.isEmpty())
			throw new JSONException("Node with no key-value attribute pair");
		else*/
			return attrMap;
		
	}
	
	public String getAttrValue(String attrKey) throws JSONException
	{
		if (this.attrMap.containsKey(attrKey))
			return (String) attrMap.get(attrKey);
		else
			throw new JSONException(attrKey+"is not an attrinute for this Node ");
	}
	
	public List<XMLNode> getChildList() throws JSONException
	{
		/*if (this.childList.isEmpty())
			throw new JSONException("this node has no child attribute");
		else*/
			return childList;
		
		
	}
	
	public void setNodeName(String nodeName)
	{
		this.nodeName=nodeName;
	}
	
	public void setTextValue(String textValue)
	{
		this.textValue=textValue;
	}
	
	public void addAttribute(String attKey,String attrValue)
	{
		this.attrMap.put(attKey, attrValue);
	}
	
	public void addChild(XMLNode child)
	{
		this.removeChild(child);
		this.childList.add(child);
	}
	
	public void addChildList(List<XMLNode> childList)
	{
		for (XMLNode child: childList)
		{
			this.addChild(child);
		}
	}
	
	public boolean removeChild(XMLNode child)
	{
		return this.childList.remove(child);
	}
	
	public String toString()
	{
		StringBuffer toStr= new StringBuffer("");
		toStr.append("<");
		toStr.append(this.nodeName);
		
		for (String attr : attrMap.keySet())
		{
			toStr.append(" ");
			toStr.append(attr);
			toStr.append("=");
			toStr.append(attrMap.get(attr));
			
		}
		toStr.append(">");
		toStr.append(this.textValue);
		for (XMLNode node : childList)
		{
			toStr.append(node.toString());
		}
		toStr.append("</"+this.nodeName+">");
		String returnStr= toStr.toString();
		return returnStr;
	}
}