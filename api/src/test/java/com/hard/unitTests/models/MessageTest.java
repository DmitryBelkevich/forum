package com.hard.unitTests.models;

import com.hard.models.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageTest {
    private Message message;

    @Before
    public void init() {
        message = new Message();
    }

    @After
    public void destroy() {
        message = null;
    }

    @Test
    public void shouldReturnFormattedDate() {
        Date date = new Date();
        message.setDate(date);

        String result = message.getFormattedDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.yyyy HH:mm:ss");

        String str = dateFormat.format(date);
        Assert.assertEquals(str, result);
    }
}
