package com.diosoft.calendar.objects.common.comparators;

import com.diosoft.calendar.objects.common.Event;
import java.util.Comparator;
import java.util.Date;

public class SortedEventByStartDate implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        Date o1StarDate = o1.getStartDate().getTime();
        Date o2StarDate = o2.getStartDate().getTime();
        return  o1StarDate.compareTo(o2StarDate);
    }

}
