package com.jsonutils.xjson;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JSONTokenizerTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JSONTokenizerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JSONTokenizerTest.class );
    }
    
    public void testApp(){
    	tokenizerTest1();
    	tokenizerTest2();
    	tokenizerTest3();
    	tokenizerTest4();
    }

    public void tokenizerTest1(){
    	JSONTokenizer tokenizer = new JSONTokenizer("{\"key\":\"value\"}");
    	assertEquals(JSONTokenizer.Token.START_BRACE, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.nextToken());
    	assertEquals("\"key\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.END_BRACE, tokenizer.nextToken());
    	assertEquals("\"value\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.END_BRACE, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.EOF, tokenizer.nextToken());
    }
    
    public void tokenizerTest2(){
    	JSONTokenizer tokenizer = new JSONTokenizer("{}");
    	assertEquals(JSONTokenizer.Token.START_BRACE, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.END_BRACE, tokenizer.nextToken());
    	assertEquals("", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.END_BRACE, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.EOF, tokenizer.nextToken());
    }
    
    public void tokenizerTest3(){
    	JSONTokenizer tokenizer = new JSONTokenizer("[\"key1\":\"value1\",\"key2\":\"value2\"]");
    	assertEquals(JSONTokenizer.Token.START_BRACKET, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.nextToken());
    	assertEquals("\"key1\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.COMMA, tokenizer.nextToken());
    	assertEquals("\"value1\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.COMMA, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.nextToken());
    	assertEquals("\"key2\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.COLON, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.END_BRACKET, tokenizer.nextToken());
    	assertEquals("\"value2\"", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.END_BRACKET, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.EOF, tokenizer.nextToken());
    }
    
    public void tokenizerTest4(){
    	JSONTokenizer tokenizer = new JSONTokenizer("[]");
    	assertEquals(JSONTokenizer.Token.START_BRACKET, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.END_BRACKET, tokenizer.nextToken());
    	assertEquals("", tokenizer.getCurrent());
    	assertEquals(JSONTokenizer.Token.END_BRACKET, tokenizer.currentToken());
    	assertEquals(JSONTokenizer.Token.EOF, tokenizer.nextToken());
    }
}
