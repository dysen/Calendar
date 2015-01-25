package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import org.junit.Test;

/**
 * Created by dysen on 1/18/15.
 */
public class GsonDeserializationTest extends BaseSerializationTest {

    //local code review (vtegza): follow structure @ 1/25/2015
    // initialize variable inputs

    // initialize mocks

    // initialize class to test

    // invoke method on class to test

    // assert return value

    // verify mock expectations
    @Test
    public void testPersonDeserialization() {

        assertEquals(person, JsonHelper.jsonToObject(personToJson, Person.class));

    }

    @Test
    public void testEventDeserialization() {

        assertEquals(event, JsonHelper.jsonToObject(eventToJson, Event.class));

    }

    @Test
    public void testEventStoreDeserialization() {

        assertEquals(eventStore, JsonHelper.jsonToObject(eventStoreToJson, JsonHelper.EVENT_STORE_TYPE));

    }

    @Test
    public void testAttendersStoreDeserialization() {

        assertEquals(attenderStore, JsonHelper.jsonToObject(attenderStoreToJson, JsonHelper.ATTENDER_STORE_TYPE));

    }

    @Test
    public void testMainStoreDeserialization() {

        assertEquals(mainDataStore, JsonHelper.jsonToObject(mainDataStoreToJson, JsonHelper.MAIN_STORE_TYPE));

    }

}
