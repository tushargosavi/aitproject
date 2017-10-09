package com.ait.apex.aggregator.AggOperations;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.Row;
import com.ait.apex.row.RowValueFunctions;

import java.util.Map;

public class MIN {
    public Map<Row, Row> minOperation(Map.Entry<Row, Row> byteEntry, AggregationMetrics metrics,
                                      AggregationSchema schema, Map<Row, Row> resultMap){
        Row valRow;
        RowValueFunctions rowValueFunctions = new RowValueFunctions();
        if(resultMap.containsKey(byteEntry.getKey()))
        {
            long currentVal = rowValueFunctions.readLong(byteEntry.getValue(), schema.valueSchema);
            valRow = resultMap.get(byteEntry.getKey());
            long currentMapVal = rowValueFunctions.readLong(valRow, schema.valueSchema);

            if(currentVal > currentMapVal)
            {
                valRow = rowValueFunctions.updateLong(resultMap.get(byteEntry.getKey()), schema.valueSchema, currentVal);
                resultMap.put(byteEntry.getKey(), valRow);
            }
        }
        else
        {
            resultMap.put(byteEntry.getKey(), byteEntry.getValue());
        }
        return resultMap;
    }
}
