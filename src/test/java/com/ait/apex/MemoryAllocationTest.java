package com.ait.apex;


import com.ait.apex.platform.BytePlatform;
import org.junit.Test;

import java.nio.ByteBuffer;

public class MemoryAllocationTest {
    @Test

    public void Memory()
    {  int offset1=0;
  /* byte[] arr=new byte[6];
    String name1="anurag";

    byte[] ac=name1.getBytes();

    for(int i=0;i<ac.length;i++)
    {
       arr[i]=ac[i];

    }

    for(int i=0;i<arr.length;i++)
    {
        System.out.print(arr[i]);
    }
    System.out.println();
    String an=new String(arr);
    System.out.println(an);*/









        byte[] bytes = new byte[20];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        BytePlatform.putInt(buffer,offset1,50);
        int a=BytePlatform.getInt(buffer,offset1);
        System.out.println(a);

        offset1=offset1+4;
        String name="vishal";

        byte[] ac1=name.getBytes();

        for(int i=0;i<ac1.length;i++)
        {
            BytePlatform.putByte(buffer,offset1+i,ac1[i]);

        }
        byte[] acw=new byte[6];
        for(int i=0;i<ac1.length;i++)
        {
            acw[i]=BytePlatform.getByte(buffer,i+offset1);

        }

        System.out.println(new String(acw));




    }


}
