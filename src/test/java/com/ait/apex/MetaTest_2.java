package com.ait.apex;

/**
 * Created by Akshay on 1/27/2017.
 */
public class MetaTest_2
{
	public class TestClass2
	{
		public int intField;
		public char charField;
		public long longField;
		public String stringField;
		
		public TestClass2(int intField, char charField, long longField, String stringField) {
			this.intField = intField;
			this.charField = charField;
			this.longField = longField;
			this.stringField = stringField;
		}
		
		@Override
		public String toString() {
			return "TestClass2{" +
					"intField=" + intField +
					", charField=" + charField +
					", longField=" + longField +
					", stringField='" + stringField + '\'' +
					'}';
		}
	}
	
	public static void main(String[] args) {
		
	}
}
