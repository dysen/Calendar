package com.diosoft.calendar.objects.Utils;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
    public static final Type MAIN_STORE_TYPE = new TypeToken<Map<UUID, Event>>() {
    }.getType();
    public static final Type EVENT_STORE_TYPE = new TypeToken<Map<String, UUID>>() {
    }.getType();
    public static final Type ATTENDER_STORE_TYPE = new TypeToken<Map<Person, HashSet<UUID>>>() {
    }.getType();


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

    public static String eventToJson(Object object){
        Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new Event.PersonSerializer())
                .create();
        return gson.toJson((Event)object);

    }

    public static String toJsonAttenders(Map<Person, HashSet<UUID>> personHashSetMap){
        GsonBuilder builder = new GsonBuilder();

        Gson gson =
                builder.enableComplexMapKeySerialization().setPrettyPrinting().create();
        Type type = new TypeToken<Map<Person, HashSet<UUID>>>(){}.getType();
        String json = gson.toJson(personHashSetMap, type);
        return json;



//        final Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new Event.PersonSerializer())
//                .create();
//        final JsonElement jsonTree = gson.toJsonTree(personHashSetMap, Map.class);
//        final JsonObject jsonObject = new JsonObject();
//        jsonObject.add("Person", jsonTree);
//        return jsonObject.toString();
    }

    public static Object fromFileToObject(String fileName, Type type) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(readFromJson(fileName), type);
    }
    public static Object jsonToObject(String json, Type type){
        return new Gson().fromJson(json, type);
    }

    public static Map<Person, HashSet<UUID>> getAttenders() throws FileNotFoundException {
        return jsonToAttenders(ATTENDERS_STORE_FILE);
    }

    public static Map<Person, HashSet<UUID>> getAttenders(String fileName) throws FileNotFoundException {
        return jsonToAttenders(fileName);
    }

    private static Map<Person, HashSet<UUID>> jsonToAttenders(String fileNAme) throws FileNotFoundException {
        return (Map<Person, HashSet<UUID>>) fromFileToObject(fileNAme, new TypeToken<Map<Person, HashSet<UUID>>>() {
        }.getType());
    }

    private static Map<String, UUID> jsonToEvents(String fileName) throws FileNotFoundException {
        return (Map<String, UUID>) fromFileToObject(fileName, new TypeToken<Map<String, UUID>>() {
        }.getType());
    }

    public static Map<String, UUID> getEvents() throws FileNotFoundException {
        return jsonToEvents(EVENTS_STORE_FILE);
    }

    public static Map<String, UUID> getEvents(String fileName) throws FileNotFoundException {
        return jsonToEvents(fileName);
    }

    protected static Map<UUID, Event> jsonToMainStore(String fileName) throws FileNotFoundException {
        return (Map<UUID, Event>) fromFileToObject(fileName, new TypeToken<Map<UUID, Event>>() {
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
        writeJson(eventToJson(jsonObject), ATTENDERS_STORE_FILE);
    }

    public static void saveMainStorageToFile(Map<UUID, Event> jsonObject) throws IOException {
        writeJson(objectToJson(jsonObject), MAIN_STORE_FILE);
    }


}
