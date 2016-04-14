package com.test.unittest;

import android.test.AndroidTestCase;

/**
 * Created by qiaoda.zqd on 2016/4/14.
 */
public class StringUtilsTest extends AndroidTestCase {

    public void testTrimString() throws Exception {
        assertEquals("ABC", StringUtils.trimString("ABC", ' '));
        assertEquals("ABC", StringUtils.trimString("ABC ", ' '));
        assertEquals("ABC", StringUtils.trimString(" ABC", ' '));
        assertEquals("ABC", StringUtils.trimString("  ABC   ", ' '));
        assertEquals("ABC", StringUtils.trimString("\"ABC\"", '\"'));
        assertEquals("ABC", StringUtils.trimString("\"ABC\"\"", '\"'));
    }
}
