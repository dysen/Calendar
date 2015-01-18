package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GsonSerializationTest {
    private Map<UUID, Event> mainDataStore;
    private Map<Person, HashSet<UUID>> attenderStore;
    private HashSet<UUID> ids;
    private Map<String, UUID> eventStore;
    private UUID id = UUID.randomUUID();
    private Person person;
    private Event event;

    @Before
    public void setUp() {

        person = new Person.Builder().
                firstName("Andrii").
                secondName("Lemdianov").
                email("dysen@ukr.net").
                phone("555315465").
                build();

        event = new Event.Builder()
                .title("Event Title")
                .startDate(new GregorianCalendar(2008, Calendar.APRIL, Calendar.TUESDAY, 10, 0))
                .endDate(new GregorianCalendar(2008, Calendar.APRIL, Calendar.TUESDAY, 12, 0))
                .description("Event description (e.g. Andrii Birthday)")
                .email("admin@ukr.net")
                .attenders(Arrays.asList(person))
                .id(id)
                .build();
        eventStore = new HashMap<String, UUID>();
        eventStore.put(event.getDescription(), id);

        mainDataStore = new HashMap<UUID, Event>();
        mainDataStore.put(id, event);

        attenderStore = new HashMap<Person, HashSet<UUID>>();
        ids = new HashSet<UUID>();
        ids.add(id);
        attenderStore.put(person, ids);

    }


    @Test
    public void testPesrsonToJsonConverter() throws IOException {

        String personJsonActual;
        String personJsonExpected = "{\n" +
                "  \"firstName\": \"Andrii\",\n" +
                "  \"secondName\": \"Lemdianov\",\n" +
                "  \"email\": \"dysen@ukr.net\",\n" +
                "  \"phone\": \"555315465\"\n" +
                "}";
        ;

        personJsonActual = JsonHelper.objectToJson(person);
        assertTrue(personJsonExpected.equals(personJsonActual));

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

        assertTrue(eventJsonExpected.equals(eventJsonActual));


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
        assertEquals(mainDataStoreMapJsonExpected, mainDataStoreMapJsonActual);

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
        assertTrue(eventStoreMapJsonExpected.equals(eventStoreMapJsonActual));
    }

}
