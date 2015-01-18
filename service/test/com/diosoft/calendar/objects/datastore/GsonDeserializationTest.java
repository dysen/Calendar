package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dysen on 1/18/15.
 */
public class GsonDeserializationTest extends BaseSerializationTest {

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
