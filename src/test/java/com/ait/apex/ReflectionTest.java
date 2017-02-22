package com.ait.apex;

import com.ait.apex.aggregator.AggregationSchema;
import com.ait.apex.row.DataType;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.ObjectModifier;
import com.ait.apex.row.RowMeta;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionTest {
	@Test
	public void reflectTest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		ObjectModifier checker = new ObjectModifier();
		System.out.println(Arrays.toString(rowMeta.getClass().getMethods()));
//		Method method = rowMeta.getClass().getMethod("addField", String.class, DataType.class);
//		method.invoke(rowMeta,"type", DataType.CHARACTER );
		
		rowMeta = (RowMeta) checker.addMetaField(rowMeta, "type", DataType.CHARACTER);

		System.out.println(Arrays.toString(rowMeta.getClass().getMethods()));
		
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			System.out.println(fieldInfo.toString());
//			if(checker.checkFieldName(fieldInfo, "type"))
//			{
//				System.out.println("yup");
//			}
//			else
//			{
//				System.out.println("Nope");
//			}
		}
	}
	
	@Test
	public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		RowMeta rowMeta = new RowMeta();
		
		ObjectModifier modifier = new ObjectModifier();
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			System.out.println(fieldInfo.toString());
			System.out.println("Hola");
		}
		String fieldName = "alpahebt";
		rowMeta = modifier.addMetaField(rowMeta, fieldName, DataType.STRING);
		for(FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			System.out.println(fieldInfo.toString());
		}
	}
	@Test
	public void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		AggregationSchema schema = new AggregationSchema();
		ObjectModifier modifier = new ObjectModifier();
		
		for(FieldInfo fieldInfo : schema.keySchema.getFieldInfoList())
		{
			System.out.println(fieldInfo.toString());
		}
		
		String fieldName = "alpha";
		schema.keySchema = modifier.addMetaField(schema.keySchema, fieldName, DataType.STRING);
		for(FieldInfo fieldInfo : schema.keySchema.getFieldInfoList())
		{
			System.out.println(fieldInfo.toString());
		}
	}
}
