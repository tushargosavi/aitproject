package com.ait.apex.aggregator.meta;

import com.ait.apex.row.DataType;
import com.ait.apex.row.RowMeta;

import java.util.ArrayList;
import java.util.List;

public class AggregationHelper {
	
	public List<AggregationSchema> createAggregationSchema(RowMeta originalSchema, List<AggregationMetrics> aggregationMetrics) throws NoSuchFieldException, IllegalAccessException {
		
		List<AggregationSchema> schemaList = new ArrayList<>();
		
		for (AggregationMetrics aggregationMeta : aggregationMetrics) {
			
			AggregationSchema schema = new AggregationSchema();

			schema.keySchema = originalSchema.subset(aggregationMeta.getKeys());
			switch (aggregationMeta.getAggTypes()) {
				case COUNT:
					schema.valueSchema = originalSchema.subset(aggregationMeta.getVals());
					break;
				
				case SUM:
					schema.valueSchema = originalSchema.subset(aggregationMeta.getVals());
					break;
				
				case MIN:
					schema.valueSchema = originalSchema.subset(aggregationMeta.getVals());
					break;
				
				case MAX:
					schema.valueSchema = originalSchema.subset(aggregationMeta.getVals());
					break;
			}
			schemaList.add(schema);
		}
		return schemaList;
	}
}
