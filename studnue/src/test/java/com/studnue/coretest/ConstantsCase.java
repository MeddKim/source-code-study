package com.studnue.coretest;

import com.studnue.core.Constants;
import org.junit.Test;

public class ConstantsCase {
    @Test
    public void test1(){
        Constants constants = new Constants(ConstantDemo.class);

        System.out.println(constants.getSize());
        System.out.println(constants.asString("LAST_NAME"));
//        System.out.println(constants.getValues("first"));
        System.out.println(constants.getValuesForProperty(""));
    }
    public static class ConstantDemo{
        public final static String first_name = "Harry";
        public final static String LAST_NAME = "Portter";
        public final static Integer MAX_SIZE = 100;
        public final static Integer minSize = 10;
    }

}


