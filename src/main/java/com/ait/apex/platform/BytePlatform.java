package com.ait.apex.platform;

import java.nio.ByteBuffer;

public class BytePlatform {

    public static int getInt(Object object, long offset) throws ClassCastException
    {
     ByteBuffer bb = (ByteBuffer)object;
     return bb.getInt((int) offset);
    }

    public static void putInt(Object object, long offset, int value) throws ClassCastException
    {
        ByteBuffer bb = (ByteBuffer)object;
        bb.putInt((int)offset,value);
    }

    public static byte getBoolean(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.get((int)offset);
    }

    public static void putBoolean(Object object, long offset, byte value) throws ClassCastException
    {   ByteBuffer bb = (ByteBuffer)object;
        bb.put((int)offset,value);
    }

    public static byte getByte(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.get((int)offset);
    }

    public static void putByte(Object object, long offset, byte value)
    {   ByteBuffer bb = (ByteBuffer)object;
        bb.put((int)offset,value);
    }


    public static short getShort(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.getShort((int)offset);
    }

    public static void putShort(Object object, long offset, short value) {
        ByteBuffer bb = (ByteBuffer) object;
        bb.putShort((int) offset, value);

    }

    public static long getLong(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.getLong((int)offset);
    }

    public static void putLong(Object object, long offset, long value)
    {   ByteBuffer bb = (ByteBuffer)object;
        bb.putLong((int)offset,value);
    }

    public static float getFloat(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.getFloat((int)offset);
    }

    public static void putFloat(Object object, long offset, float value)
    {   ByteBuffer bb = (ByteBuffer)object;
        bb.putFloat((int)offset,value);
    }

    public static double getDouble(Object object, long offset)
    {   ByteBuffer bb = (ByteBuffer)object;
        return bb.getDouble((int)offset);
    }

    public static void putDouble(Object object, long offset, double value)
    {   ByteBuffer bb = (ByteBuffer)object;
        bb.putDouble((int)offset,value);
    }


    public static ByteBuffer memoryAllocate(int pagesize)
    {

      return ByteBuffer.allocateDirect(pagesize);
    }
}
