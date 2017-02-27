package com.ait.apex.AdInfoTest;

import com.ait.apex.row.FieldInfo;
import com.ait.apex.row.GetFieldValues;
import com.ait.apex.row.RowMeta;

public class PojoBasedExtractor {
	
	public AdInfo dataExtractor(RowMeta rowMeta, Object object) throws NoSuchFieldException, IllegalAccessException {
		GetFieldValues values = new GetFieldValues();
		
		AdInfo adInfo = new AdInfo();
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList()) {
			switch (fieldInfo.getDataType()) {
				case STRING:
					if((fieldInfo.getName()).equals("publisher"))
					{
						adInfo.setPublisher(values.getString(object, fieldInfo.getName()));
					}
					else if((fieldInfo.getName()).equals("advertiser"))
					{
						adInfo.setAdvertiser(values.getString(object, fieldInfo.getName()));
					}
					else if((fieldInfo.getName()).equals("location"))
					{
						adInfo.setLocation(values.getString(object, fieldInfo.getName()));
					}
					break;
				
				case INTEGER:

					break;
				
				case LONG:

					break;
				
				case CHARACTER:

					break;
				
				case DOUBLE:

					break;
				
				case BOOLEAN:

					break;
				
				case FLOAT:

					break;
			}
		}
		return adInfo;
	}
}
