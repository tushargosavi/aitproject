package com.ait.apex.Updated_Test;

import com.ait.apex.Test_Classes.MemoryPage;
import com.ait.apex.row.PojoBasedCoder;
import com.ait.apex.row.Row;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class POC1 {

    public static Row row;
    public static PojoBasedCoder coder = new PojoBasedCoder();

    public static int rowHashCode(Row row){

        Row keyRow = coder.extractKeyRow(row);

        int hashCode = ((keyRow.getDataBytes()).hashCode());

        return hashCode % 1024;
    }

    void getRow(){
        try{
            row = Basic.getTestResult();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){

        getRow();
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

        MemoryPage memoryPage = new MemoryPage();
        getRow();
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
        memoryPage.addressMap.put((hashCode % tableSize), memoryPage.getPageAddress());

        System.out.println(memoryPage.addressMap.get(hashCode % tableSize));
    }

}
