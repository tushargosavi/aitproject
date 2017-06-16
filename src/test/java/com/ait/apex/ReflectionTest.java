<<<<<<< HEAD
package com.ait.apex;

import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.DataType;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.RowMeta;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ReflectionTest {
	@Test
	public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		AggregationSchema schema = new AggregationSchema();
		
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
		
		String fieldName = "alpha";
		schema.keySchema.addField(fieldName, DataType.CHARACTER);
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
	}
	
	@Test
	public void test4() {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		String fieldName = "alpha";
		rowMeta.addField(fieldName, DataType.CHARACTER);
		
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
		
		System.out.println("------------------------------------");
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
	}
	
	@Test
	public void test5() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		Assert.assertTrue(rowMeta.isKeyPresent("age"));
	}
}
=======
package com.ait.apex;

import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.DataType;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.RowMeta;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ReflectionTest {
	@Test
	public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		AggregationSchema schema = new AggregationSchema();
		
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
		
		String fieldName = "alpha";
		schema.keySchema.addField(fieldName, DataType.CHARACTER);
		for (FieldInfo fieldInfo : schema.keySchema.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
	}
	
	@Test
	public void test4() {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		String fieldName = "alpha";
		rowMeta.addField(fieldName, DataType.CHARACTER);
		
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
		
		System.out.println("------------------------------------");
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			System.out.println(fieldInfo.toString());
		}
	}
	
	@Test
	public void test5() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		Assert.assertTrue(rowMeta.isKeyPresent("age"));
	}
}
>>>>>>> 248b3ac67de6bb5fa32f960d18f27e44fe91965c
