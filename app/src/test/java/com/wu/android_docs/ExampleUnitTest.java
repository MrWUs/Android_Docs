package com.wu.android_docs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        GuajiCallback callback = (msg, alert) -> System.out.println(msg);
        ArrayList<String> docList = new ArrayList<>();
        docList.add("朱本章");
        Guaji guaji = new Guaji(new Date(), callback);
        guaji.selectDocs = docList;
        guaji.runFun();
    }
}