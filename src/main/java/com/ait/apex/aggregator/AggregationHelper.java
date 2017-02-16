package com.ait.apex.aggregator;

import com.ait.apex.row.DataType;
import com.ait.apex.row.RowMeta;

import java.util.List;

public class AggregationHelper
{

  public AggregationSchema createAggregationSchema(RowMeta originalSchema, List<AggregationMetrics> aggregationMetrics)
  {
    AggregationSchema schema = new AggregationSchema();
    for (AggregationMetrics aggregationMeta : aggregationMetrics) {
      
      switch (aggregationMeta.getAggTypes()) {
        case COUNT:
          for (String key : aggregationMeta.getKeys()) {
            if (originalSchema.fieldInfoList.contains(key)) {
              schema.keySchema.addField(key, originalSchema.getDataType(key));
            }
          }
          schema.valueSchema.addField("count", DataType.LONG);
          break;

        case SUM:
          for (String key : aggregationMeta.getKeys()) {
            if (originalSchema.fieldInfoList.contains(key)) {
              schema.keySchema.addField(key, originalSchema.getDataType(key));
            }
          }
          schema.valueSchema.addField("sum", DataType.LONG);
          break;

        case MIN:
          for (String key : aggregationMeta.getKeys()) {
            if (originalSchema.fieldInfoList.contains(key)) {
              schema.keySchema.addField(key, originalSchema.getDataType(key));
            }
          }
          schema.valueSchema.addField("min", DataType.LONG);
          break;

        case MAX:
          for (String key : aggregationMeta.getKeys()) {
            if (originalSchema.fieldInfoList.contains(key)) {
              schema.keySchema.addField(key, originalSchema.getDataType(key));
            }
          }
          schema.valueSchema.addField("max", DataType.LONG);
          break;
      }

      return schema;
    }

    return null;
  }

}
