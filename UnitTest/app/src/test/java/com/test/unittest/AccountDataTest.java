package com.test.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by qiaoda.zqd on 2016/10/28.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountDataTest {
    @Mock
    AccountData accountData;

    @Test
    public void testIsNotNull(){
        assertNotNull(accountData);
    }

    @Test
    public void testIsLogin(){
        when(accountData.isLogin()).thenReturn(true);
        boolean isLogin=accountData.isLogin();
        assertTrue(isLogin);
    }

    @Test
    public void testGetUserName(){
        when(accountData.getUserName()).thenReturn("Jack");
        assertEquals("Jack",accountData.getUserName());
    }

    @Test
    public void testSetUserName(){
        accountData.setUserName("wang");
        verify(accountData).setUserName(Matchers.eq("wang"));
    }

    @Test
    public void testIsLoginTimes(){
        accountData.isLogin();
        accountData.isLogin();
        verify(accountData,times(2)).isLogin();
    }
}