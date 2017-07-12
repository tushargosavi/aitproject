package com.ait.apex.Updated_Test;

import com.ait.apex.RequiredClasses.*;
import com.ait.apex.Test_Classes.RowHashMap;
import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
import com.ait.apex.row.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basic
{

    RandomEnumGenerator publisherGenerator = new RandomEnumGenerator(Publisher.class);
    RandomEnumGenerator advertiserGenerator = new RandomEnumGenerator(Advertiser.class);
    RandomEnumGenerator locationGenerator = new RandomEnumGenerator(Location.class);
    RandomValueGenerator valueGenerator = new RandomValueGenerator();



    @Test
    public void test1()
    {

        AdInfo adInfo = new AdInfo(publisherGenerator.random().toString(), advertiserGenerator.random().toString(), locationGenerator.random().toString(),
                valueGenerator.randomCost(), valueGenerator.randomImpressions(), valueGenerator.randomClicks());

        System.out.println(adInfo.toString());
    }

    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException {

        RowMeta rowMeta = new RowMeta();
        rowMeta.addField("publisher", DataType.STRING);
        rowMeta.addField("advertiser", DataType.STRING);
        rowMeta.addField("location", DataType.STRING);
        rowMeta.addField("cost", DataType.LONG);
        rowMeta.addField("impressions", DataType.LONG);
        rowMeta.addField("clicks", DataType.BOOLEAN);

        String[] keys = {"publisher","advertiser"};
        String vals[] = {"cost"};

        AggregationMetrics metrics = new AggregationMetrics(keys, vals, AggregationTypes.MAX);
        List<AggregationMetrics> metricsList = new ArrayList<>();
        metricsList.add(metrics);

        AggregationHelper helper = new AggregationHelper();
        List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);

        System.out.println(schemaList.get(0));

        AdInfo adInfo = new AdInfo(publisherGenerator.random().toString(), advertiserGenerator.random().toString(), locationGenerator.random().toString(),
                valueGenerator.randomCost(), valueGenerator.randomImpressions(), valueGenerator.randomClicks());

        ByteLength byteLength = new ByteLength();
        System.out.println(adInfo.toString());
        System.out.println(byteLength.getRowLength(schemaList.get(0), adInfo));
        System.out.println(schemaList.get(0).keySchema + "    " + byteLength.getKeyLength(schemaList.get(0).keySchema, adInfo));
        System.out.println(schemaList.get(0).valueSchema);

    }

    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        RowMeta rowMeta = new RowMeta();
        rowMeta.addField("publisher", DataType.STRING);
        rowMeta.addField("advertiser", DataType.STRING);
        rowMeta.addField("location", DataType.STRING);
        rowMeta.addField("cost", DataType.LONG);
        rowMeta.addField("impressions", DataType.LONG);
        rowMeta.addField("clicks", DataType.BOOLEAN);

        String[] keys = {"publisher","advertiser"};
        String vals[] = {"cost"};

        AggregationMetrics metrics = new AggregationMetrics(keys, vals, AggregationTypes.MAX);
        List<AggregationMetrics> metricsList = new ArrayList<>();
        metricsList.add(metrics);

        AggregationHelper helper = new AggregationHelper();
        List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);

        AdInfo adInfo = new AdInfo("potato","starbucks","MH",123, 431, true);

        ByteLength length = new ByteLength();
        PojoBasedCoder coder = new PojoBasedCoder();

        int totalLen = length.getRowLength(schemaList.get(0), adInfo);
        Row row = new Row();

        row.dataBytes = new byte[totalLen];

        row = coder.encoder(schemaList.get(0), adInfo);
        System.out.println(Arrays.toString(row.getDataBytes()));

    }

    //TODO
    //Fix the encoder function to store the first String value in the Row object
    //Debug the encoder function and fix the shit
    //Then push this fucking update to the git
    @Test
    public void tes4() throws NoSuchFieldException, IllegalAccessException {
        RowMeta rowMeta = new RowMeta();
        rowMeta.addField("publisher", DataType.STRING);
        rowMeta.addField("advertiser", DataType.STRING);
        rowMeta.addField("location", DataType.STRING);
        rowMeta.addField("cost", DataType.LONG);
        rowMeta.addField("impressions", DataType.LONG);
        rowMeta.addField("clicks", DataType.BOOLEAN);

        String[] keys = {"publisher","advertiser"};
        String vals[] = {"cost"};

        AggregationMetrics metrics = new AggregationMetrics(keys, vals, AggregationTypes.MAX);
        List<AggregationMetrics> metricsList = new ArrayList<>();
        metricsList.add(metrics);

        AggregationHelper helper = new AggregationHelper();
        List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);

        AdInfo adInfo = new AdInfo("potato","starbucks","MH",123, 431, true);

        ByteLength length = new ByteLength();
        PojoBasedCoder coder = new PojoBasedCoder();
        int totalLen = length.getRowLength(schemaList.get(0), adInfo);
        Row row = new Row();

        row.dataBytes = new byte[totalLen];

        row = coder.encoder(schemaList.get(0), adInfo);

        System.out.println(Arrays.toString(coder.extractKeyRow(row).getDataBytes()));
    }

    @Test
    public void test5() throws NoSuchFieldException, IllegalAccessException {
        RowMeta rowMeta = new RowMeta();
        rowMeta.addField("publisher", DataType.STRING);
        rowMeta.addField("advertiser", DataType.STRING);
        rowMeta.addField("location", DataType.STRING);
        rowMeta.addField("cost", DataType.LONG);
        rowMeta.addField("impressions", DataType.LONG);
        rowMeta.addField("clicks", DataType.BOOLEAN);

        String[] keys = {"publisher","advertiser"};
        String vals[] = {"cost"};

        AggregationMetrics metrics = new AggregationMetrics(keys, vals, AggregationTypes.MAX);
        List<AggregationMetrics> metricsList = new ArrayList<>();
        metricsList.add(metrics);

        AggregationHelper helper = new AggregationHelper();
        List<AggregationSchema> schemaList = helper.createAggregationSchema(rowMeta, metricsList);

        AdInfo adInfo = new AdInfo("potato","starbucks","MH",123, 431, true);

        ByteLength length = new ByteLength();
        PojoBasedCoder coder = new PojoBasedCoder();
        int totalLen = length.getRowLength(schemaList.get(0), adInfo);
        Row row = new Row();

        row.dataBytes = new byte[totalLen];

        row = coder.encoder(schemaList.get(0), adInfo);

        RowHashMap rowHashMap = new RowHashMap();
        rowHashMap.put(row, rowHashCode(row));
        System.out.println(rowHashMap.get(row));
    }

    private int rowHashCode(Row row){
        PojoBasedCoder coder = new PojoBasedCoder();
        Row keyRow = coder.extractKeyRow(row);

        int hashCode = ((keyRow.getDataBytes()).hashCode());

        return hashCode % 1024;
    }

}
