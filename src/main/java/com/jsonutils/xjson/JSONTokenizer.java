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
	private int cursorPosition;
	
	/*
	 * Constructor
	 * @param str The json string to tokenize
	 */
	public JSONTokenizer(String str){
		this.jsonString = str;
		setCursorPosition(0);
	}
	
	/*
	 * Sets the cursor at the given position in the string.
	 */
	public void setCursorPosition(int index){
		this.cursorPosition = index;
	}
	
	/* 
	 * returns the current cursor Position
	 */
	
	public int getCursorPosition(){
		return cursorPosition;
	}
	
	
	/*
	 * Returns the next token encountered in the string.
	 */
	public Token nextToken(){

		Token nextToken = Token.EOF;
		if (this.cursorPosition==jsonString.length())
			nextToken=Token.EOF;
		
		else
		{ 
			for (int iterator=this.cursorPosition+1; iterator<jsonString.length(); iterator++)
			{
				switch(jsonString.charAt(iterator)){

				case '{':
					nextToken=Token.START_BRACE;
					break;
				case '}':
					nextToken=Token.END_BRACE;
					break;
				case '[':
					nextToken=Token.START_BRACKET;
					break;
				case ']':
					nextToken=Token.END_BRACKET;
					break;
				case ':':
					nextToken=Token.COLON;
					break;
				case ',':
					nextToken=Token.COMMA;
					break;
				default:
					{if (iterator==jsonString.length())	
						{nextToken=Token.EOF;
					    break;}
					 else					    
					 continue;
					}

			}
				break;
			
		}
		}return nextToken;
		
					
 }
			
	/*
	 * Returns the current token.
	 */
	public Token currentToken(){
		
		Token currentToken = Token.EOF;
	    switch(jsonString.charAt(this.cursorPosition)){
		    
		  case '{':
			  currentToken=Token.START_BRACE;
			  break;
		  case '}':
		      currentToken=Token.END_BRACE;
			  break;
		  case '[':
	   	      currentToken=Token.START_BRACKET;
			  break;
	      case ']':
			  currentToken=Token.END_BRACKET;
		      break;
	      case ':':
			  currentToken=Token.COLON;
		      break;
	      case ',':
			  currentToken=Token.COMMA;
			  break;
	      default:
	    	  System.out.println("cursor is not at right position");
	    	  break;
		    
		  }
		
		
		return currentToken;
	}
	
	/*
	 * Returns the string under the current position of the cursor and advanced the
	 * cursor to the next token. Tokens are skipped and only string data other 
	 * than tokens is returned.
	 */
	public String getCurrent(){
		
		if (this.cursorPosition==jsonString.length())
		{
			System.out.println("Please consult nextToken() before calling getCurrent()");
			return null;
		}
			
		
		int begIndex=this.cursorPosition+1;
		int endIndex=0;
		
		for (int i= begIndex; i<=jsonString.length(); i++)
		{
			char ch = jsonString.charAt(i);
			
			if (ch=='{' || ch=='}' || ch=='['||  ch==']' || ch==':' || ch==',')
			{
				this.cursorPosition=i;
				endIndex=i;
				break;
			}
			else if (i==jsonString.length())
				return null;
			else
				continue;
				
		}
		//if (begIndex==endIndex)
		//{
		//	if ((jsonString.charAt(begIndex)== '{' && jsonString.charAt(endIndex)=='}')  ||  (jsonString.charAt(begIndex)== '[' && jsonString.charAt(endIndex)==']'))
			//	return "";
				
	//	}
		return jsonString.substring(begIndex, endIndex);
	}
}