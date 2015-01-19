package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.Utils.Interval;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.GregorianCalendar;
import java.util.List;

public interface CalendarService extends Remote {

    void addEvent(String name, String description, boolean allDay, GregorianCalendar startDate, GregorianCalendar endDate, String email,  List<Person> attenders) throws RemoteException;

    Event removeEvent(String name) throws RemoteException;

    Event addAttender(String name, Person... newPersons) throws RemoteException;

    Event getEvent(String name) throws RemoteException;

    public List<Event> searchByInterval(GregorianCalendar searchDateFrom, GregorianCalendar searchDateTo) throws RemoteException;

    boolean checkPersonAvailable(Person person, GregorianCalendar from, GregorianCalendar to) throws RemoteException;

    List<Interval> searchBestTimeForEvent(Person person, Event event, GregorianCalendar startDate, GregorianCalendar endDate) throws RemoteException;

    boolean checkPersonAvailableForEvent(Person person, Event checkedEvent)  throws RemoteException;

}
