package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GsonSerializationTest extends BaseSerializationTest{

    @Test
    public void testPersonToJsonConverter() throws IOException {

        String personJsonActual;
        String personJsonExpected = "{\n" +
                "  \"firstName\": \"Andrii\",\n" +
                "  \"secondName\": \"Lemdianov\",\n" +
                "  \"email\": \"dysen@ukr.net\",\n" +
                "  \"phone\": \"555315465\"\n" +
                "}";
        ;

        personJsonActual = JsonHelper.objectToJson(person);
        Assert.assertTrue(personJsonExpected.equals(personJsonActual));

    }

    @Test
    public void testEventToJsonConverter() throws IOException {
        String eventJsonActual;
        String eventJsonExpected = "{\n" +
                "  \"title\": \"Event Title\",\n" +
                "  \"description\": \"Event description (e.g. Andrii Birthday)\",\n" +
                "  \"email\": \"admin@ukr.net\",\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"attenders\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Andrii\",\n" +
                "      \"secondName\": \"Lemdianov\",\n" +
                "      \"email\": \"dysen@ukr.net\",\n" +
                "      \"phone\": \"555315465\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"startDate\": {\n" +
                "    \"year\": 2008,\n" +
                "    \"month\": 3,\n" +
                "    \"dayOfMonth\": 3,\n" +
                "    \"hourOfDay\": 10,\n" +
                "    \"minute\": 0,\n" +
                "    \"second\": 0\n" +
                "  },\n" +
                "  \"endDate\": {\n" +
                "    \"year\": 2008,\n" +
                "    \"month\": 3,\n" +
                "    \"dayOfMonth\": 3,\n" +
                "    \"hourOfDay\": 12,\n" +
                "    \"minute\": 0,\n" +
                "    \"second\": 0\n" +
                "  },\n" +
                "  \"allDay\": false\n" +
                "}";

        eventJsonActual = JsonHelper.objectToJson(event);

        Assert.assertTrue(eventJsonExpected.equals(eventJsonActual));


    }

    @Test
    public void testMainStoreToJsonConverter() throws IOException {

        String mainDataStoreMapJsonActual;
        String mainDataStoreMapJsonExpected = "{\n" +
                "  \"" + id + "\": {\n" +
                "    \"title\": \"Event Title\",\n" +
                "    \"description\": \"Event description (e.g. Andrii Birthday)\",\n" +
                "    \"email\": \"admin@ukr.net\",\n" +
                "    \"id\": \"" + id + "\",\n" +
                "    \"attenders\": [\n" +
                "      {\n" +
                "        \"firstName\": \"Andrii\",\n" +
                "        \"secondName\": \"Lemdianov\",\n" +
                "        \"email\": \"dysen@ukr.net\",\n" +
                "        \"phone\": \"555315465\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"startDate\": {\n" +
                "      \"year\": 2008,\n" +
                "      \"month\": 3,\n" +
                "      \"dayOfMonth\": 3,\n" +
                "      \"hourOfDay\": 10,\n" +
                "      \"minute\": 0,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"endDate\": {\n" +
                "      \"year\": 2008,\n" +
                "      \"month\": 3,\n" +
                "      \"dayOfMonth\": 3,\n" +
                "      \"hourOfDay\": 12,\n" +
                "      \"minute\": 0,\n" +
                "      \"second\": 0\n" +
                "    },\n" +
                "    \"allDay\": false\n" +
                "  }\n" +
                "}";
        mainDataStoreMapJsonActual = JsonHelper.objectToJson(mainDataStore);
//        assertTrue(mainDataStoreMapJsonExpected.equals(mainDataStoreMapJsonActual));
        Assert.assertEquals(mainDataStoreMapJsonExpected, mainDataStoreMapJsonActual);

    }

    @Test
    public void testAttenderStoreToJsonConverter() throws IOException {
        String attenderStoreMapJsonActual;
        String attenderStoreMapJsonExpected = "{\n" +
                "  \"com.diosoft.calendar.objects.common.Person@71b02e11\": [\n" +
                "    \"" + id + "\"\n" +
                "  ]\n" +
                "}";
        attenderStoreMapJsonActual = JsonHelper.objectToJson(attenderStore);
//        assertTrue(attenderStoreMapJsonExpected.equals(attenderStoreMapJsonActual));
    }

    @Test
    public void testEventStoreToJsonConverter() throws IOException {
        String eventStoreMapJsonActual;
        String eventStoreMapJsonExpected = "{\n" +
                "  \"Event description (e.g. Andrii Birthday)\": \"" + id + "\"\n" +
                "}";

        eventStoreMapJsonActual = JsonHelper.objectToJson(eventStore);
        Assert.assertTrue(eventStoreMapJsonExpected.equals(eventStoreMapJsonActual));
    }




}
