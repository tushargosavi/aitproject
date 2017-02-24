package com.ait.apex.AdInfoTest;

import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
import com.ait.apex.row.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	
	@org.junit.Test
	
	public void test1() throws IOException, IllegalAccessException {
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
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, AggregationTypes.MAX));
		
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
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, AggregationTypes.MAX));
		
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
		
		String keys[] = {"publisher","location"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, AggregationTypes.MAX));
		
		AggregationHelper helper = new AggregationHelper();
		List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);
		
		AdInfo adInfo = new AdInfo("potato", "star", "LA", 123,567,true);
		
		ByteLength length = new ByteLength();
		PojoBasedCoder coder = new PojoBasedCoder();
		for(AggregationSchema schema : schemaList)
		{
			System.out.println(schema.keySchema.toString());
			int len = length.getByteLength(schema.keySchema, adInfo);
			int varoffset = length.getVarOffset(schema.keySchema);
			
			Row keyRow = new Row();
			keyRow.dataBytes = new byte[len];
			keyRow = coder.encoder(schema.keySchema, adInfo);
			System.out.println(Arrays.toString(keyRow.getDataBytes()));
			
			AdInfo info = new AdInfo();
			info = (AdInfo) coder.decoder(schema.keySchema, keyRow, info);
			System.out.println(info.toString());
		}
		
		
	}
	
	@org.junit.Test
	public void test5() throws NoSuchFieldException, IllegalAccessException, IOException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("publisher", DataType.STRING);
		rowMeta.addField("advertiser", DataType.STRING);
		rowMeta.addField("location", DataType.STRING);
		rowMeta.addField("cost", DataType.LONG);
		rowMeta.addField("impressions", DataType.LONG);
		rowMeta.addField("clicks", DataType.BOOLEAN);
		
		String keys[] = {"publisher","location"};
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(new AggregationMetrics(keys, AggregationTypes.MAX));
		
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
}

