package com.diosoft.calendar.objects;

import com.diosoft.calendar.objects.datastore.CalendarDataStoreImpl;
import com.diosoft.calendar.objects.datastore.CalendarDataStoreSQL;
import com.diosoft.calendar.objects.service.CalendarService;
import com.diosoft.calendar.objects.service.CalendarServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("Service started");

        CalendarDataStoreSQL customerDAO = (CalendarDataStoreSQL) context.getBean("calendarDataStoreSQL");
        customerDAO.selectAll();

    }

}
