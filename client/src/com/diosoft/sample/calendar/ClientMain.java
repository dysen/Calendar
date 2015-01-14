package com.diosoft.sample.calendar;

import com.diosoft.calendar.objects.service.CalendarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.rmi.RemoteException;

public class ClientMain {

    public static void main(String[] args) throws RemoteException {

        ApplicationContext context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
        CalendarService service = (CalendarService) context.getBean("calendarService");

      service.addEvent("test", null, null, null, null);

    }

}
