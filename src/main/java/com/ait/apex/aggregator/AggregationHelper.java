package com.ait.apex.aggregator;

import com.ait.apex.row.DataType;
import com.ait.apex.row.ObjectModifier;
import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.RowMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AggregationHelper
{
  public ObjectModifier checker = new ObjectModifier();
  public AggregationSchema createAggregationSchema(RowMeta originalSchema, List<AggregationMetrics> aggregationMetrics) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    AggregationSchema schema = new AggregationSchema();
    for (AggregationMetrics aggregationMeta : aggregationMetrics) {
      
      switch (aggregationMeta.getAggTypes()) {
        case COUNT:
          for (String key : aggregationMeta.getKeys()) {
            if(checker.checkFieldName(originalSchema, key))
            {
              schema.keySchema = checker.addMetaField(schema.keySchema, key, DataType.STRING);
              }
            }
            schema.valueSchema = checker.addMetaField(schema.valueSchema, "count", DataType.LONG);
          break;

        case SUM:
          for (String key : aggregationMeta.getKeys()) {
            if(checker.checkFieldName(originalSchema, key))
            {
              schema.keySchema = checker.addMetaField(schema.keySchema, key, DataType.STRING);
            }
          }
          schema.valueSchema = checker.addMetaField(schema.valueSchema, "sum", DataType.LONG);
          break;

        case MIN:
          for (String key : aggregationMeta.getKeys()) {
            if(checker.checkFieldName(originalSchema, key))
            {
              schema.keySchema = checker.addMetaField(schema.keySchema, key, DataType.STRING);
            }
          }
          schema.valueSchema = checker.addMetaField(schema.valueSchema, "min", DataType.LONG);
          break;

        case MAX:
          for (String key : aggregationMeta.getKeys()) {
          if(checker.checkFieldName(originalSchema, key))
          {
            schema.keySchema = checker.addMetaField(schema.keySchema, key, DataType.STRING);
          }
        }
        schema.valueSchema = checker.addMetaField(schema.valueSchema, "max", DataType.LONG);
          break;
      }

    }

    return schema;
  }

}
