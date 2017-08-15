package com.ait.apex.Updated_Test;

import com.ait.apex.RequiredClasses.AdInfo;
import com.ait.apex.Test_Classes.MemoryPage;
import com.ait.apex.row.PojoBasedCoder;
import com.ait.apex.row.Row;
import org.junit.Test;

import java.util.Arrays;

public class POC1 {

    public static Row row;
    public static PojoBasedCoder coder = new PojoBasedCoder();

    public static int rowHashCode(Row row){

        Row keyRow = coder.extractKeyRow(row);

        int hashCode = ((keyRow.getDataBytes()).hashCode());

        return hashCode % 1024;
    }

    void getRow(AdInfo adInfo){
        try{
            row = Basic.getTestResult(adInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){

        AdInfo adInfo = new AdInfo();
        getRow(adInfo);
        PojoBasedCoder coder = new PojoBasedCoder();
        Row keyRow = coder.extractKeyRow(row);
        int totalLen = row.dataBytes[0];
        int offset = 0;

        byte[] testBytes = new byte[4096];

        System.arraycopy(row.getDataBytes(), 0, testBytes, offset, totalLen);
        offset += totalLen;
        System.out.println(Arrays.toString(testBytes));

        System.out.println("Offset: " + offset);
    }

    @Test
    public void test2(){

        AdInfo adInfo = new AdInfo("ADC","BDC","PAK",200, 555, true);

        MemoryPage memoryPage = new MemoryPage();
        getRow(adInfo);
        System.arraycopy(row.getDataBytes(), 0, memoryPage.getPage(), memoryPage.offset, row.dataBytes[0]);

        int length = row.dataBytes[0];
        System.out.println(Arrays.toString(row.getDataBytes()));
        System.out.println(Arrays.toString(memoryPage.getPage()));
        System.out.println(memoryPage.offset);
        memoryPage.setOffset(length);
        memoryPage.setPageAddress(0);
        System.out.println(memoryPage.offset);

        Row keyRow = coder.extractKeyRow(row);
        int hashCode = rowHashCode(keyRow);
        int tableSize = 1024;
//        memoryPage.addressMap.put((hashCode % tableSize), memoryPage.getPageAddress());

        System.out.println(memoryPage.addressMap.get(hashCode % tableSize));
    }

    @Test
    public void test3(){
        Address address= new Address();
        AdInfo adInfo = new AdInfo("ADC","BDC","PAK",200, 555, true);

        MemoryPage memoryPage = new MemoryPage();
        getRow(adInfo);
        System.arraycopy(row.getDataBytes(), 0, memoryPage.getPage(), memoryPage.offset, row.dataBytes[0]);

        memoryPage.setPageAddress(memoryPage.getOffset());
        address.setAddress(0, memoryPage.getPageAddress());
        int rowLength = row.dataBytes[0];
        memoryPage.setOffset(rowLength);
        int rowHashCode = rowHashCode(coder.extractKeyRow(row));
        memoryPage.addressMap.put(rowHashCode, address);
        System.out.println("PageAddress: "+memoryPage.getPageAddress() +", Offset: "+memoryPage.getOffset());

        AdInfo adInfo2 = new AdInfo("ADC","BDDVC","PAK",200, 555, true);
        getRow(adInfo2);
        System.arraycopy(row.getDataBytes(), 0, memoryPage.getPage(), memoryPage.getOffset(), row.dataBytes[0]);
        memoryPage.setPageAddress(memoryPage.getOffset());
        address.setAddress(0, memoryPage.getPageAddress());
        memoryPage.setOffset(memoryPage.getOffset() + row.dataBytes[0]);
        memoryPage.addressMap.put(rowHashCode(coder.extractKeyRow(row)), address);

        System.out.println("PageAddress: "+memoryPage.getPageAddress() +", Offset: "+memoryPage.getOffset());
    }

}
