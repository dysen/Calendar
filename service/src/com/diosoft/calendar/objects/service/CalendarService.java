package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by denis on 13.01.15.
 */
public interface CalendarService extends Remote {

    void addEvent(String name, String description, boolean allDay, GregorianCalendar startDate, GregorianCalendar endDate, String email,  List<Person> attenders) throws RemoteException;

    Event removeEvent(String name) throws RemoteException;

    Event addAttender(String name, Person... newPersons) throws RemoteException;

    Event getEvent(String name) throws RemoteException;

    public List<Event> searchByInterval(GregorianCalendar searchDateFrom, GregorianCalendar searchDateTo);

}
