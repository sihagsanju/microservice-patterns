package com.stackroute.nextevent.service;


import com.stackroute.nextevent.exception.EventAlreadyExist;
import com.stackroute.nextevent.exception.EventNotFound;
import com.stackroute.nextevent.model.Category;
import com.stackroute.nextevent.model.Event;
import com.stackroute.nextevent.model.SubCategory;
import com.stackroute.nextevent.model.Venu;
import com.stackroute.nextevent.repository.EventRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventServcieTest {

    @Mock
    private EventRepository eventRepository;
   @InjectMocks
    private EventServiceImpl eventService;
    private Event event;
    private List<Event> eventList;
    private Optional optional;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventList = new ArrayList<>();
        Date dateFormat = new Date("1234/12/23 23:23:23");
        Venu venu= new Venu("12","bangalore","bangalore",40);
        SubCategory subCategory= new SubCategory("1","music1");
        List<SubCategory> subCategoryList= new ArrayList<>();
        subCategoryList.add(subCategory);
        Category category = new Category("12","music", subCategoryList,18);
        event = new Event( "123","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
       // event = new Event((long) 123,"123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,"music","Bangalore","imageurl");
        eventList.add(event);
        event = new Event( "1234","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
        eventList.add(event);
        optional = Optional.of(event);

    }

    @After
    public void tearDown() {
        event = null;
    }

    @Test
    public void testSaveEventSuccess() throws EventAlreadyExist {
        when(eventRepository.save(event)).thenReturn(event);
        System.out.println("++++"+event);
        Event getEvent = eventService.saveEvent(event);
        System.out.println("+++++++"+getEvent);
        Assert.assertEquals(event,getEvent);
        verify(eventRepository,times(1)).save(event);
        verify(eventRepository,times(1)).findById(event.getId());
    }

    @Test
    public void testSaveEventFailure() throws EventAlreadyExist {
        when(eventRepository.save(event)).thenReturn(event);
        when(eventRepository.findByEventId(event.getEventId())).thenReturn(event);
        Event getEvent = eventService.saveEvent(event);
        System.out.println("+++++"+getEvent);
        Assert.assertEquals(event,getEvent);
        verify(eventRepository,times(1)).save(event);
        verify(eventRepository,times(1)).findById(event.getId());
    }

    @Test
    public void testUpdateEventSuccess() throws EventNotFound {
        when(eventRepository.existsById(event.getId())).thenReturn(true);
        when(eventRepository.findById(event.getId())).thenReturn(optional);
         event.setName("updated Event");
        System.out.println("in test check========"+eventService.updateEvent(event,event.getId()));
        Event getEvent = eventService.updateEvent(event,event.getId());
        System.out.println("in test+++++"+getEvent);
        Assert.assertEquals(getEvent.getName(),"updated Event");
        verify(eventRepository,times(4)).findById(event.getId());
        verify(eventRepository,times(2)).save(event);

    }

    @Test(expected = EventNotFound.class)
    public void testUpdateEventFailure() throws EventNotFound {
        when(eventRepository.existsById(event.getId())).thenReturn(false);
        Event getEvent = eventService.updateEvent(event,event.getId());
        Assert.assertEquals(false,getEvent);
        verify(eventRepository,times(2)).findById(event.getId());
        verify(eventRepository,times(1)).save(event);
    }

    @Test
    public void testDeleteEventSuccess() throws EventNotFound {
        when(eventRepository.existsById(event.getId())).thenReturn(true);
        when(eventRepository.findById(event.getId())).thenReturn(optional);
        boolean getEvent = eventService.deleteEvent(event.getId());
        Assert.assertEquals(true,getEvent);
        verify(eventRepository,times(1)).findById(event.getId());
        verify(eventRepository,times(1)).deleteById(event.getId());
    }
    @Test(expected = EventNotFound.class)
    public void testDeleteEventFailure() throws EventNotFound {
        when(eventRepository.existsById(event.getId())).thenReturn(false);
        boolean getEvent = eventService.deleteEvent(event.getId());
        Assert.assertEquals(false,getEvent);
        verify(eventRepository,times(1)).findById(event.getId());
        verify(eventRepository,times(1)).deleteById(event.getId());
    }

        @Test
    public void testGetAllEventsSuccess() throws EventNotFound {
        when(eventRepository.findAll()).thenReturn(eventList);
        List<Event> list = eventService.getAllEvent();
        Assert.assertEquals(list,eventList);
        verify(eventRepository,times(1)).findAll();
    }
    @Test(expected = EventNotFound.class)
    public void testFindByEventId() throws EventNotFound {
        when(eventRepository.findByEventId(event.getEventId())).thenReturn(event);
        Event getEvent = eventService.getEventById(event.getId());
        Assert.assertEquals(getEvent,event);
        verify(eventRepository,times(1)).findByEventId(event.getEventId());
    }



}
