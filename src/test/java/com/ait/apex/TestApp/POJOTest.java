package com.ait.apex.TestApp;

import com.ait.apex.AdInfoTest.AdInfo;
import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
import com.ait.apex.row.DataType;
import com.ait.apex.row.EntryField;
import com.ait.apex.row.Row;
import com.ait.apex.row.RowMeta;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.io.IOException;
import java.util.*;

public class POJOTest
{
	@org.junit.Test
	public void test1() throws NoSuchFieldException, IllegalAccessException, IOException {
		
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		Map<Row, Row> byteMap = new HashMap<>();
		
		String keys[] = {"publisher","location"};
		String val[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, val, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		
		AdInfo adInfo = new AdInfo("potato", "star", "LA", 123,567,true);
		
		Map<Row, Row> rowMap = new Object2ObjectOpenHashMap<>();
		TestByteLength byteLength = new TestByteLength();
		TestPojoBasedCoder coder = new TestPojoBasedCoder();
		POJOBasedOperations pojoBasedOperations = new POJOBasedOperations();
		
		for(AggregationSchema schema : schemaList)
		{
			int totalLength = byteLength.getRowLength(schema, adInfo);
			System.out.println("Total row length : "+totalLength);
			
			int keyLength = byteLength.getKeyLength(schema.keySchema, adInfo);
			System.out.println("Key length : " +keyLength);
			
			int valLength = byteLength.getValLength(schema.valueSchema, adInfo);
			System.out.println("Val length : " +valLength);
			
			int keyVarOffset = byteLength.getKeyVarOffset(schema.keySchema);
			System.out.println("Key var offset is : " +keyVarOffset);
			
			Row row = coder.encoder(schema, adInfo);
			System.out.println("Row dataBytes : " +Arrays.toString(row.getDataBytes()));
			
			Row keyRow = coder.extractKeyRow(row, 12, keyLength);
			System.out.println(Arrays.toString(keyRow.getDataBytes()));
			
			Row valRow = coder.extractValRow(row, (totalLength - valLength), valLength);
			System.out.println(Arrays.toString(valRow.getDataBytes()));
			
			EntryField entryField = new EntryField(row, valRow);
			
			pojoBasedOperations.test_Operations(entryField, metricsList.get(0), schema, rowMap);

		}
	}
}

/*

Row format :
	
    --------------------------------------------------------------
	| Total Length | Key Length | Val Length | Key Row | Val Row |
	--------------------------------------------------------------
	
				------------------------------------------
	Key Row --> | String offset | Values | String values |
				------------------------------------------
				
				
	            ------------------------------------------
	Val Row --> | String offset | Values | String values |
				------------------------------------------
 */