package com.test.unittest;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by qiaoda.zqd on 2016/4/14.
 */
public class StringUtilsTest {
    @Test
    public void testTrimString() throws Exception {
        assertEquals("ABC", StringUtils.trimString("ABC", ' '));
        assertEquals("ABC", StringUtils.trimString("ABC ", ' '));
        assertEquals("ABC", StringUtils.trimString(" ABC", ' '));
        assertEquals("ABC", StringUtils.trimString("  ABC   ", ' '));
        assertEquals("ABC", StringUtils.trimString("\"ABC\"", '\"'));
        assertEquals("ABC", StringUtils.trimString("\"ABC\"\"", '\"'));
    }
}
