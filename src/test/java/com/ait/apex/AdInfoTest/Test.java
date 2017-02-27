package com.ait.apex.AdInfoTest;

import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
import com.ait.apex.aggregator.operations.Operations;
import com.ait.apex.row.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {
	
	@org.junit.Test
	
	public void test1() throws IOException, IllegalAccessException, NoSuchFieldException {
		
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		
		String path = "C:\\Users\\Akshay\\Documents\\InputFile.csv";
		CSVReader csvReader = new CSVReader(new FileReader(path), ',');
		
		ByteLength length = new ByteLength();
		PojoBasedCoder coder = new PojoBasedCoder();
		
		String[] nextLine;
		Map<Row, Row> byteMap = new HashMap<>();
		Map<Row, Row> resultMap = new HashMap<>();
		
		String keys[] = {"publisher","location"};
		String vals[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, vals, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		Operations operations = new Operations();
		
		while((nextLine = csvReader.readNext()) != null)
		{
			AdInfo adInfo = new AdInfo(null, null, null, 0l, 0l, false);
			if(nextLine != null)
			{
				adInfo.setPublisher(nextLine[0]);
				adInfo.setAdvertiser(nextLine[1]);
				adInfo.setLocation((nextLine[2]));
				adInfo.setCost(Long.parseLong(nextLine[3]));
				adInfo.setImpressions((Long.parseLong(nextLine[4])));
				adInfo.setClicks(Boolean.parseBoolean(nextLine[5]));
				
				for(AggregationSchema schema : schemaList)
				{
					int keyLen = length.getByteLength(schema.keySchema, adInfo);
					int valLen = length.getByteLength(schema.valueSchema, adInfo);
					
					Row keyRow = new Row();
					Row valRow = new Row();
					
					keyRow.dataBytes = new byte[keyLen];
					keyRow = coder.encoder(schema.keySchema, adInfo);
					
					valRow.dataBytes = new byte[valLen];
					valRow = coder.encoder(schema.valueSchema, adInfo);
					
					byteMap.put(keyRow, valRow);
					
					for(Map.Entry<Row, Row> entry : byteMap.entrySet())
					{
						resultMap = operations.operations(entry, metricsList.get(0), schemaList.get(0), resultMap);
//			System.out.println(Arrays.toString(entry.getKey().getDataBytes()) +"    "+ Arrays.toString(entry.getValue().getDataBytes()) );
					}
				}
			}
		}
		
		
		
		for(Map.Entry<Row, Row> entry : resultMap.entrySet())
		{
			AdInfo adInfo = new AdInfo();
			adInfo = (AdInfo) coder.decoder(schemaList.get(0).keySchema,entry.getKey(), adInfo);
			adInfo = (AdInfo) coder.decoder(schemaList.get(0).valueSchema,entry.getValue(), adInfo);
			
			System.out.println(adInfo.toString());
		}
	}
	
	@org.junit.Test
	public void test2() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		System.out.println(rowMeta.toString());
		
		String keys[] = {"publisher","location"};
		String vals[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, vals, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		System.out.println(schemaList.toString());
	}
	
	@org.junit.Test
	public void test3() throws IOException, NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		String keys[] = {"publisher","location"};
		String vals[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, vals, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		
		AdInfo adInfo = new AdInfo("potato", "star", "LA", 123,567,true);
		
		PojoBasedExtractor extractor = new PojoBasedExtractor();
		AdInfo adInfo1 = null;
		for (AggregationSchema schema : schemaList)
		{
			adInfo1 = extractor.dataExtractor(schema.keySchema,adInfo);
			
			System.out.println(adInfo1.toString());
		}
	}
	
	@org.junit.Test
	public void test4() throws NoSuchFieldException, IllegalAccessException {
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
		
		ByteLength length = new ByteLength();
		PojoBasedCoder coder = new PojoBasedCoder();
		for(AggregationSchema schema : schemaList)
		{
//			System.out.println(schema.keySchema.toString());
			int keyLen = length.getByteLength(schema.keySchema, adInfo);
			int valLen = length.getByteLength(schema.valueSchema, adInfo);
			
			int keyVarOffset = length.getVarOffset(schema.keySchema);
			int calVarOffset = length.getVarOffset(schema.valueSchema);
				
			Row keyRow = new Row();
			Row valRow = new Row();
			
			keyRow.dataBytes = new byte[keyLen];
			keyRow = coder.encoder(schema.keySchema, adInfo);
//			System.out.println(Arrays.toString(keyRow.getDataBytes()));
			
			valRow.dataBytes = new byte[valLen];
			valRow = coder.encoder(schema.valueSchema, adInfo);
			
			System.out.println(schema.valueSchema);
			System.out.println(Arrays.toString(valRow.getDataBytes()));
			
			byteMap.put(keyRow, valRow);
		}
		
		
	}
	
	@org.junit.Test
	public void test5() throws NoSuchFieldException, IllegalAccessException, IOException {
		
		Map<byte[], byte[]> byteMap = new HashMap<>();
		
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		String keys[] = {"publisher","location"};
		String vals[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, vals, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		
		String path = "C:\\Users\\Akshay\\Documents\\InputFile.csv";
		CSVReader csvReader = new CSVReader(new FileReader(path), ',');
		
		String[] nextLine;
		
		while((nextLine = csvReader.readNext()) != null)
		{
			AdInfo adInfo = new AdInfo(null, null, null, 0l, 0l, false);
			if(nextLine != null)
			{
				adInfo.setPublisher(nextLine[0]);
				adInfo.setAdvertiser(nextLine[1]);
				adInfo.setLocation((nextLine[2]));
				adInfo.setCost(Long.parseLong(nextLine[3]));
				adInfo.setImpressions((Long.parseLong(nextLine[4])));
				adInfo.setClicks(Boolean.parseBoolean(nextLine[5]));
				
				System.out.println(adInfo.toString());
			}
		}
	}
	
	@org.junit.Test
	public void test6() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		String keys[] = {"publisher","location"};
		String val[] = {"cost"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, val, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		
		AdInfo adInfo = new AdInfo("potato", "star", "LA", 123,567,true);
		RowValueFunctions rowValueFunctions = new RowValueFunctions();
		ByteLength length = new ByteLength();
		PojoBasedCoder coder = new PojoBasedCoder();
		for(AggregationSchema schema : schemaList)
		{
			int keyLen = length.getByteLength(schema.keySchema, adInfo);
			int valLen = length.getByteLength(schema.valueSchema, adInfo);
			
			int keyVarOffset = length.getVarOffset(schema.keySchema);
			int calVarOffset = length.getVarOffset(schema.valueSchema);
			
			Row keyRow = new Row();
			Row valRow = new Row();
			
			keyRow.dataBytes = new byte[keyLen];
			keyRow = coder.encoder(schema.keySchema, adInfo);
			
			valRow.dataBytes = new byte[valLen];
			valRow = coder.encoder(schema.valueSchema, adInfo);
			
			AdInfo adInfo1 = new AdInfo();
			
//			System.out.println(schema.valueSchema.toString());
//			adInfo1 = (AdInfo) coder.decoder(schema.valueSchema, valRow, adInfo1);
//			System.out.println(adInfo1.toString());
			
		}
			
	}
}


