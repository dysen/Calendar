package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by denis on 13.01.15.
 */
public interface CalendarService extends Remote {

    Event removeEvent(String name) throws RemoteException;

    Event addAttender(String name, Person... newPersons) throws RemoteException;

    Event getEvent(String name) throws RemoteException;
}
