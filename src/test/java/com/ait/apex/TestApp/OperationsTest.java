package com.ait.apex.TestApp;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.EntryField;
import com.ait.apex.row.Row;
import it.unimi.dsi.fastutil.Hash;

import java.util.Map;

public interface OperationsTest extends Hash.Strategy<Row> {
	
	public void test_Operations(EntryField entryField, AggregationMetrics metrics, AggregationSchema schema, Map<Row, Row> resultMap);
}
