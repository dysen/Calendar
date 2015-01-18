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

    private static Map<String, UUID> jsonToEvents(String fileName) throws FileNotFoundException {
        return (Map<String, UUID>) jsonToObject(fileName, new TypeToken<Map<String, UUID>>() {
        }.getType());
    }

    public static Map<String, UUID> getEvents() throws FileNotFoundException {
        return jsonToEvents(EVENTS_STORE_FILE);
    }

    public static Map<String, UUID> getEvents(String fileName) throws FileNotFoundException {
        return jsonToEvents(fileName);
    }

    protected static Map<UUID, Event> jsonToMainStore(String fileName) throws FileNotFoundException {
        return (Map<UUID, Event>) jsonToObject(fileName, new TypeToken<Map<UUID, Event>>() {
        }.getType());
    }

    public static Map<UUID, Event> getMainStore() throws FileNotFoundException {
        return jsonToMainStore(MAIN_STORE_FILE);
    }

    public static Map<UUID, Event> getMainStore(String fileName) throws FileNotFoundException {
        return jsonToMainStore(fileName);
    }

    public static void saveJsonToFile(Object jsonObject, String fileName) throws IOException {
        writeJson(objectToJson(jsonObject), fileName);
    }

    public static void saveEventsToFile(Map<String, UUID> jsonObject) throws IOException {
        writeJson(objectToJson(jsonObject), EVENTS_STORE_FILE);
    }

    public static void saveAttendersToFile(Map<Person, HashSet<UUID>> jsonObject) throws IOException {
        writeJson(objectToJson(jsonObject), ATTENDERS_STORE_FILE);
    }

    public static void saveMainStorageToFile(Map<UUID, Event> jsonObject) throws IOException {
        writeJson(objectToJson(jsonObject), MAIN_STORE_FILE);
    }


}
