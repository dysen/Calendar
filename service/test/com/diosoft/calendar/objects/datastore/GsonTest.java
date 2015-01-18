package com.diosoft.calendar.objects.datastore;

import com.diosoft.calendar.objects.Utils.JsonHelper;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.*;
import java.util.*;


public class GsonTest {




    @Test
    public void testGsonStorage() throws IOException {


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String personJson;
        String eventJson;
        String mainDataStoreMapJson;
        String attenderStoreMapJson;
        String eventStoreMapJson;
        Map<UUID, Event> mainDataStoreActual;



        UUID id = UUID.randomUUID();

        Person person = new Person.Builder().firstName("Andrii").secondName("Lemdianov").email("dysen@ukr.net").phone("555315465").build();

        Event event = new Event.Builder()
                .title("Event Title")
                .startDate(new GregorianCalendar(2008, Calendar.APRIL, Calendar.TUESDAY, 10, 0))
                .endDate(new GregorianCalendar(2008, Calendar.APRIL, Calendar.TUESDAY, 12, 0))
                .description("Event description (e.g. Andrii Birthday)")
                .email("admin@ukr.net")
                .attenders(Arrays.asList(person))
                .id(id)
                .build();

        personJson = gson.toJson(person);
        eventJson = gson.toJson(event);

        Map<UUID, Event> mainDataStore = new HashMap<UUID, Event>();
        mainDataStore.put(id, event);

        Map<Person, HashSet<UUID>> attenderStore= new HashMap<Person, HashSet<UUID>>();
        HashSet<UUID> ids = new HashSet<UUID>();
        ids.add(id);
        attenderStore.put(person, ids);


        Map<String, UUID> eventStore = new HashMap<String, UUID>();
        eventStore.put(event.getDescription(), id);

        mainDataStoreMapJson = gson.toJson(mainDataStore);
        attenderStoreMapJson = gson.toJson(attenderStore);
        eventStoreMapJson = gson.toJson(eventStore);


        System.out.println(personJson);
        System.out.println("======================");
        System.out.println(eventJson);
        System.out.println("======================");
        System.out.println(mainDataStoreMapJson);
        System.out.println("======================");
        System.out.println(attenderStoreMapJson);
        System.out.println("======================");
        System.out.println(eventStoreMapJson);
        System.out.println("======================");

//------------------------
//Store map and read it
        File mainDataStoreMapFile = new File("mainDataStoreMap.json");
        FileOutputStream fOut = new FileOutputStream(mainDataStoreMapFile);
        OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
        myOutWriter.append(mainDataStoreMapJson);
        myOutWriter.close();
        fOut.close();

        File attenderStoreMapFile = new File("attenderStoreMap.json");
        FileOutputStream fOut1 = new FileOutputStream(attenderStoreMapFile);
        OutputStreamWriter myOutWriter1 =new OutputStreamWriter(fOut1);
        myOutWriter1.append(mainDataStoreMapJson);
        myOutWriter1.close();
        fOut.close();


        File eventStoreMapFile = new File("eventStoreMap.json");
        FileOutputStream fOut2 = new FileOutputStream(eventStoreMapFile);
        OutputStreamWriter myOutWriter2 =new OutputStreamWriter(fOut2);
        myOutWriter2.append(mainDataStoreMapJson);
        myOutWriter2.close();
        fOut2.close();

        mainDataStore.getClass();
        // read object from files
        BufferedReader br = new BufferedReader(
                new FileReader("mainDataStoreMap.json"));
        mainDataStoreActual = gson.fromJson(br, new TypeToken<Map<UUID, Event>>(){}.getType());
        assert     mainDataStoreActual.equals(mainDataStore);


    }
}
