package com.ait.apex.Updated_Test;

import com.ait.apex.Test_Classes.MemoryPage;
import com.ait.apex.row.Row;
import org.junit.Test;

public class New_Basic_Test {

    public static Row row;

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
        int hashCode = Basic.rowHashCode(row);
        System.out.println(hashCode);

    }

    @Test
    public void test2(){
        getRow();
        int hashCode = Basic.rowHashCode(row);
        MemoryPage memoryPage = new MemoryPage();
        System.out.println(row.getDataBytes().length);
    }


    @Test
    public void test3(){
        getRow();
        int totalLen = row.dataBytes[0];
        System.out.println(totalLen);
    }


}
