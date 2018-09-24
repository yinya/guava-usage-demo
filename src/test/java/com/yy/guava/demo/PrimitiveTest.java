package com.yy.guava.demo;

import com.google.common.base.Joiner;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

@RunWith(SpringRunner.class)
public class PrimitiveTest {

    @Test
    public void byteTest(){
        byte[] byteArray = {1,2,3,4,5,5,7,9,9};

        //convert array of primitives to array of objects
        List<Byte> objectArray = Bytes.asList(byteArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        byteArray = Bytes.toArray(objectArray);
        System.out.print("[ ");

        for(int i = 0; i< byteArray.length ; i++) {
            System.out.print(byteArray[i] + " ");
        }

        System.out.println("]");
        byte data = 5;

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Bytes.contains(byteArray, data));

        //Returns the index
        System.out.println("Index of 5: " + Bytes.indexOf(byteArray,data));

        //Returns the last index maximum
        System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray,data));

    }


    @Test
    public void shortTest(){
        short[] shortArray = {1,2,3,4,5,6,7,8,9};

        //convert array of primitives to array of objects
        List<Short> objectArray = Shorts.asList(shortArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        shortArray = Shorts.toArray(objectArray);
        System.out.print("[ ");

        for(int i = 0; i< shortArray.length ; i++) {
            System.out.print(shortArray[i] + " ");
        }

        System.out.println("]");
        short data = 5;

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Shorts.contains(shortArray, data));

        //Returns the minimum
        System.out.println("Min: " + Shorts.min(shortArray));

        //Returns the maximum
        System.out.println("Max: " + Shorts.max(shortArray));
        data = 2400;

        //get the byte array from an integer
        byte[] byteArray = Shorts.toByteArray(data);

        // 9  96
        // 9 * 256 + 96 = 2400
        for(int i = 0; i< byteArray.length ; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("");

        System.err.println(Joiner.on(",").join(Bytes.asList(byteArray).toArray()));

        //
        System.out.println("max power of two :" + Shorts.MAX_POWER_OF_TWO + "; max short :"+ Short.MAX_VALUE);
    }

    @Test
    public void convertTest(){
        //T... ==== []T ???

        //[]T  --> List<T>
        String[] strArray0 = new String[]{"asssd", "osdf"};
        List<String> strList0 = Arrays.asList(strArray0);
        System.err.println(Joiner.on(";").join(strList0.iterator()));

        //List<T> --> []T
        List<String> strArray1 = Arrays.asList("abc", "efg");
        String[] array1 = strArray1.toArray(new String[strArray1.size()]);
        System.err.println(Joiner.on(",").join(array1));
        //or
        String[] array2 = strArray1.stream().toArray(String[]::new);
        System.err.println(Joiner.on(":").join(array2));


    }
    private <T> T[] array(T... args){
        return args;
    }


    @Test
    public void intTest(){
        int[] intArray = {1,2,3,4,5,6,7,8,9};

        //convert array of primitives to array of objects
        List<Integer> objectArray = Ints.asList(intArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        intArray = Ints.toArray(objectArray);
        System.out.print("[ ");

        for(int i = 0; i< intArray.length ; i++) {
            System.out.print(intArray[i] + " ");
        }

        System.out.println("]");

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? " + Ints.contains(intArray, 5));

        //Returns the minimum
        System.out.println("Min: " + Ints.min(intArray));

        //Returns the maximum
        System.out.println("Max: " + Ints.max(intArray));

        //get the byte array from an integer
        //big endian.
        byte[] byteArray = Ints.toByteArray(1120000);
        for(int i = 0; i< byteArray.length ; i++) {
            System.out.print(byteArray[i] + " ");
        }
        System.out.println("");

        int myInt = Ints.fromByteArray(byteArray);
        System.err.println("myInt " + myInt);


        System.err.println(Joiner.on(",").join(Bytes.asList(byteArray)));
    }

    @Test
    public void longTest(){
        long[] longArray = {1,2,3,4,5,6,7,8,9};

        //convert array of primitives to array of objects
        List<Long> objectArray = Longs.asList(longArray);
        System.out.println(objectArray.toString());

        //convert array of objects to array of primitives
        longArray = Longs.toArray(objectArray);
        System.out.print("[ ");

        for(int i = 0; i< longArray.length ; i++) {
            System.out.print(longArray[i] + " ");
        }

        System.out.println("]");

        //check if element is present in the list of primitives or not
        System.out.println("5 is in list? "+ Longs.contains(longArray, 5));

        //Returns the minimum
        System.out.println("Min: " + Longs.min(longArray));

        //Returns the maximum
        System.out.println("Max: " + Longs.max(longArray));

        //get the byte array from an integer
        byte[] byteArray = Longs.toByteArray(20000);

        for(int i = 0; i< byteArray.length ; i++) {
            System.out.print(byteArray[i] + " ");
        }

    }
}
