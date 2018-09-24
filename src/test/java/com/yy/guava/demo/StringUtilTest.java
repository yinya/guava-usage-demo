package com.yy.guava.demo;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class StringUtilTest {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();


    @Test
    public void joinerTest(){
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1,2,3,4,5,null,6)));
    }

    @Test
    public void spiltTest(){
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog."));
    }



    @Test
    public void charMatcherTest(){
        System.out.println(CharMatcher.digit().retainFrom("mahesh123"));    // only the digits
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom("     Mahesh     Parashar ", ' '));

        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.inRange('0', '9').replaceFrom("mahesh123", "*"));  // star out all digits


        System.out.println(CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("mahesh123@"));

        // eliminate all characters that aren't digits or lowercase

        // TODO: 2018/9/23 support reg???
    }

    @Test
    public void caseFormatTest(){
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, data));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, data));
    }

    @Test
    public void stringUtil02() throws DecoderException {
        //byte[] --> hex String.
        int a = 200;
        byte[] bytes = Ints.toByteArray(a);
        System.err.println(bytesToHex(bytes));
        System.err.println(Hex.encodeHex(bytes));

        String str =  "000000C8";

        System.err.println(Ints.fromByteArray(Hex.decodeHex(str.toCharArray())));

    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexToBytes(String str){
        return null;
    }
}
