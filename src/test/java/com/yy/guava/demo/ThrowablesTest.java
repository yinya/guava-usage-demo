package com.yy.guava.demo;

import com.google.common.base.Throwables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class ThrowablesTest {

    @Test
    public void test(){


        try {
            showcaseThrowables();

        } catch (InvalidInputException e) {
            //get the root cause
            System.out.println(Throwables.getRootCause(e));

        } catch (Exception e) {
            //get the stack trace in string format
            System.out.println(Throwables.getStackTraceAsString(e));
        }

        try {
            showcaseThrowables1();

        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    public void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt(-3.0);
        } catch (Throwable e) {
            //check the type of exception and throw it
            Throwables.throwIfInstanceOf(e, InvalidInputException.class);
            Throwables.throwIfUnchecked(e);
        }
    }

    public void showcaseThrowables1() {
        try {
            int[] data = {1,2,3};
            getValue(data, 4);
        } catch (Throwable e) {
            Throwables.throwIfInstanceOf(e, IndexOutOfBoundsException.class);
            Throwables.throwIfUnchecked(e);
        }
    }

    public double sqrt(double input) throws InvalidInputException {
        if(input < 0) throw new InvalidInputException();
        return Math.sqrt(input);
    }

    public double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public void dummyIO() throws IOException {
        throw new IOException();
    }
}


class InvalidInputException extends Exception {
}
