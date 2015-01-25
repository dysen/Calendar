package com.diosoft.calendar.objects.service;

import com.diosoft.calendar.objects.Utils.Interval;
import com.diosoft.calendar.objects.common.Event;
import com.diosoft.calendar.objects.common.Person;
import com.diosoft.calendar.objects.common.comparators.SortedEventByStartDate;
import com.diosoft.calendar.objects.datastore.CalendarDataStore;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;

public class CalendarServiceImp implements CalendarService {

    public static final Logger logger = Logger.getAnonymousLogger();
    private final CalendarDataStore storage;

    public CalendarServiceImp(CalendarDataStore _storage) {
        this.storage = _storage;
    }

    @Override
    public void addEvent(String name, String description, boolean allDay, GregorianCalendar startDate, GregorianCalendar endDate, String email, List<Person> attenders) throws RemoteException {

        UUID id = UUID.randomUUID();

        Event event = new Event.Builder()
                .title(name)
                .description(description)
                .allDay(allDay)
                .startDate(startDate)
                .endDate(endDate)
                .email(email)
                .attenders(attenders)
                .id(id)
                .build();

        storage.publish(event);
        logger.info("Published even on service side " + name);
    }

    @Override
    public Event removeEvent(String name) throws RemoteException {
        //local code review (vtegza): move logic here - first find event, then remove it by providing UUID or all event @ 1/25/2015
        storage.remove(name);
        //local code review (vtegza): should return event that would be removed or make it void @ 1/25/2015
        return null;
    }

    @Override
    public Event addAttender(String name, Person... newPersons) throws RemoteException {
        //local code review (vtegza): should be implemented @ 1/25/2015
        return null;
    }

    @Override
    //local code review (vtegza): rename without under score @ 1/25/2015
    public Event getEvent(String _eventTitle) throws RemoteException {
        return storage.getEvent(_eventTitle);
    }

    @Override
    public List<Event> searchByInterval(GregorianCalendar searchDateFrom, GregorianCalendar searchDateTo) throws RemoteException {
        return storage.getEventsByInterval(searchDateFrom, searchDateTo);
    }

    @Override
    public boolean checkPersonAvailable(Person person, GregorianCalendar from, GregorianCalendar to) throws RemoteException {
        List<Event> personEvents = storage.getPersonsEvent(person);

        for (Event event : personEvents) {
            Date startDate = event.getStartDate().getTime();
            Date endDate = event.getEndDate().getTime();
            Date fromDate = from.getTime();
            Date toDate = to.getTime();

            if ((fromDate.after(startDate) && fromDate.before(endDate))
                    || (toDate.after(startDate) && toDate.before(endDate))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Interval> searchBestTimeForEvent(Person person, Event event, GregorianCalendar startDate, GregorianCalendar endDate) throws RemoteException {

        List<Interval> bestTimeIntervals = new ArrayList<Interval>();
        //local code review (vtegza): try not to use cyrillic in comments@ 1/25/2015
        //Начало и конец нашего ивента который мы хотим впихнуьт
        Date eventStart = event.getStartDate().getTime();
        Date eventEnd = event.getEndDate().getTime();
        //интервал в котором производитсья поиск
        Date fromDate = startDate.getTime();
        Date toDate = endDate.getTime();

        long eventInterval = eventEnd.getTime() - eventStart.getTime(); //продолжительность event в мс
        long interval = toDate.getTime() - fromDate.getTime(); //продолжительность интервала в котором будет выполняться поиск

        if (eventInterval > interval) {
            return bestTimeIntervals; //событие не влазит в передаваеммый интервал
        }

        TreeSet<Event> personsEvent = new TreeSet<Event>(new SortedEventByStartDate());
        personsEvent.addAll(storage.getPersonsEvent(person));

        for (Event ev : personsEvent) {
            Date localEventStart = ev.getStartDate().getTime();
            Date localEventEnd = ev.getEndDate().getTime();

            //Проверка попадает ли event в интервал
            if (localEventStart.after(fromDate) && localEventStart.before(toDate)) {

                //проверка влезит ли event в интервал между началом нашего интервала и началом текущего события
                if ((localEventStart.getTime() - fromDate.getTime()) >= eventInterval) {
                    bestTimeIntervals.add(new Interval(fromDate, localEventStart));
                }
                fromDate = localEventEnd;
                interval = toDate.getTime() - fromDate.getTime();

                //проверить влезит ли еще один интервал, если нет, вернуть null
                if (localEventEnd.after(eventEnd)
                        || eventInterval > (interval - (localEventEnd.getTime() - localEventStart.getTime()))) {
                    return bestTimeIntervals;
                }

            } else if (localEventEnd.after(fromDate) && localEventEnd.before(toDate)) {
                //Если это последний ивент, и больше не влазит, вернуть список интервалов
                if ((toDate.getTime() - localEventEnd.getTime()) < eventInterval) {
                    return bestTimeIntervals;
                } else if (ev.equals(personsEvent.last())) {
                    //это последний, но еще что-то влазит
                    fromDate = localEventEnd;
                }

            } else {
                //local code review (vtegza): no need in continue @ 1/25/2015
                continue;
            }

        }

        //проверить остался ли еще один интервал
        if ((toDate.getTime() - fromDate.getTime()) >= eventInterval) {
            bestTimeIntervals.add(new Interval(fromDate, toDate));
        }

        return bestTimeIntervals;
    }

    @Override
    public boolean checkPersonAvailableForEvent(Person person, Event checkedEvent) throws RemoteException{
        List<Event> allPersonEvents = storage.getPersonsEvent(person);
        List<Event> exactPersonEvents = storage.getPersonsEvent(person);

        for (Event event : allPersonEvents) {
            if (event.getStartDate().get(1) == checkedEvent.getStartDate().get(1) &&
                    event.getStartDate().get(2) == checkedEvent.getStartDate().get(2) &&
                    event.getStartDate().get(3) == checkedEvent.getStartDate().get(3)) {
                exactPersonEvents.add(event);
            }
        }

        for (Event event : exactPersonEvents) {
            if (event == null) return true;
            if (checkedEvent.getStartDate().getTime().before(event.getStartDate().getTime()) &&
                    checkedEvent.getEndDate().getTime().after(event.getEndDate().getTime())) {
                return false;
            } else if (checkedEvent.getStartDate().getTime().before(event.getStartDate().getTime()) &&
                    checkedEvent.getEndDate().getTime().before(event.getEndDate().getTime())) {
                return false;
            } else if (checkedEvent.getStartDate().getTime().after(event.getStartDate().getTime()) &&
                    checkedEvent.getEndDate().getTime().before(event.getEndDate().getTime())) {
                return false;
            } else if (checkedEvent.getStartDate().getTime().before(event.getStartDate().getTime()) &&
                    checkedEvent.getEndDate().getTime().after(event.getEndDate().getTime())) {
                return false;
            }
        }
        return true;
    }
}
