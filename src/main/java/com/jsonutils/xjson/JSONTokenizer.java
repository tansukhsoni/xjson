package com.jsonutils.xjson;

/*
 * JSONTokenizer is used to split a json string into tokens to be used for parsing the string.
 */
public class JSONTokenizer {
	
	public static enum Token{
		START_BRACE,
		END_BRACE,
		START_BRACKET,
		END_BRACKET,
		COLON,
		COMMA,
		EOF
	}
	
	private String jsonString;
	
	/*
	 * Constructor
	 * @param str The json string to tokenize
	 */
	public JSONTokenizer(String str){
		this.jsonString = str;
		setCursor(0);
	}
	
	/*
	 * Sets the cursor at the given position in the string.
	 */
	private void setCursor(int index){
		
	}
	
	/*
	 * Returns the next token encountered in the string.
	 */
	public Token nextToken(){
		Token nextToken = Token.EOF;
		return nextToken;
	}
	
	/*
	 * Returns the current token.
	 */
	public Token currentToken(){
		Token currentToken = Token.EOF;
		return currentToken;
	}
	
	/*
	 * Returns the string under the current position of the cursor and advanced the
	 * cursor to the next token. Tokens are skipped and only string data other 
	 * than tokens is returned.
	 */
	public String getCurrent(){
		return "";
	}
}
