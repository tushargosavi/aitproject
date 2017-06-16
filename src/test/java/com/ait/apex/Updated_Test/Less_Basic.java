package com.ait.apex.Updated_Test;


import com.ait.apex.RequiredClasses.AdInfo;
import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
import com.ait.apex.row.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Less_Basic
{
    PojoBasedCoder coder = new PojoBasedCoder();

    public int hashcode(Row row)
    {
        Row keyRow = coder.extractKeyRow(row);

        int hashCode = ((keyRow.getDataBytes()).hashCode());

        return hashCode % 1024;
    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Hashtable<Row, Integer> storeHash = new Hashtable<>();

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
        Row row = coder.encoder(schemaList.get(0), adInfo);

        System.out.println(Arrays.toString(row.getDataBytes()));
        System.out.println(hashcode(row));

        storeHash.put(row, hashcode(row));

    }

}
