package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import junit.framework.TestCase;
import org.junit.BeforeClass;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dysen on 1/18/15.
 */

//local code review (vtegza): no need to extends form TestCase @ 1/25/2015
//local code review (vtegza): there is no actual test @ 1/25/2015
public class BaseSerializationTest extends TestCase {
    protected Map<UUID, Event> mainDataStore;
    protected Map<Person, HashSet<UUID>> attenderStore;
    protected HashSet<UUID> ids;
    protected Map<String, UUID> eventStore;
    protected UUID id = UUID.randomUUID();
    protected Person person;
    protected Event event;

    protected String eventToJson;
    protected String personToJson;
    protected String eventStoreToJson;
    protected String attenderStoreToJson;
    protected String mainDataStoreToJson;

    //local code review (vtegza): create TestHelper and call method instead of extending from it and relaying on @Before @ 1/25/2015
    @BeforeClass
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



        eventToJson = JsonHelper.objectToJson(event);
        personToJson = JsonHelper.objectToJson(person);
        eventStoreToJson = JsonHelper.objectToJson(eventStore);
        attenderStoreToJson = JsonHelper.toJsonAttenders(attenderStore);
        mainDataStoreToJson = JsonHelper.objectToJson(mainDataStore);

    }

}
