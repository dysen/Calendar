package com.diosoft.calendar.objects.Utils;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class JsonHelper {

    private static final String MAIN_STORE_FILE = "mainDataStoreMap.json";
    private static final String EVENTS_STORE_FILE = "eventStoreMap.json";
    private static final String ATTENDERS_STORE_FILE = "attenderStoreMap.json";


    public static void writeJson(String json, String fileName) throws IOException {
        FileOutputStream fOut = new FileOutputStream(new File(fileName));
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append(json);
        myOutWriter.close();
        fOut.close();


    }

    public static BufferedReader readFromJson(String fileName) throws FileNotFoundException {
        return new BufferedReader(
                new FileReader(fileName));

    }


    public static String objectToJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);

    }


    public static Object jsonToObject(String fileName, Type type) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(readFromJson(fileName), type);

    }


    public static Map<Person, HashSet<UUID>> getAttenders() throws FileNotFoundException {
        return jsonToAttenders(ATTENDERS_STORE_FILE);
    }
    public static Map<Person, HashSet<UUID>> getAttenders(String fileName) throws FileNotFoundException {
        return jsonToAttenders(fileName);
    }

    private static Map<Person, HashSet<UUID>> jsonToAttenders(String fileNAme) throws FileNotFoundException {
        return (Map<Person, HashSet<UUID>>) jsonToObject(fileNAme, new TypeToken<Map<Person, HashSet<UUID>>>() {
        }.getType());
    }


    public static Map<String, UUID> getEvents() throws FileNotFoundException {
        return (Map<String, UUID>) jsonToObject(EVENTS_STORE_FILE, new TypeToken<Map<String, UUID>>() {
        }.getType());
    }

    public static Map<UUID, Event>  getMainStore() throws FileNotFoundException {
        return (Map<UUID, Event> ) jsonToObject(MAIN_STORE_FILE, new TypeToken<Map<UUID, Event> >() {
        }.getType());

    }

    public static void saveJsonToFile(Object jsonObject) throws IOException {
        jsonObject.getClass();
        jsonObject.getClass().getCanonicalName();
        String o = "";
        writeJson(objectToJson(jsonObject), "");



    }

}
