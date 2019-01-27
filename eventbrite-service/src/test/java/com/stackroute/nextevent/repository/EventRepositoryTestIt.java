package com.stackroute.nextevent.repository;


import com.stackroute.nextevent.model.Category;
import com.stackroute.nextevent.model.Event;
import com.stackroute.nextevent.model.SubCategory;
import com.stackroute.nextevent.model.Venu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
//@DataMongoTest
@SpringBootTest
public class EventRepositoryTestIt {

    @Autowired
    private EventRepository eventRepository;

    private Event event;

    private Venu venu;
    private SubCategory subCategory;
    private Category category;
    private List<Event> eventList;

    @Before
    public void setUp(){
        event= new Event();
        Date dateFormat = new Date("1234/12/23 23:23:23");
        venu= new Venu("12","bangalore","bangalore",40);
        subCategory= new SubCategory("1","music1");
        List<SubCategory> subCategoryList= new ArrayList<>();
        subCategoryList.add(subCategory);
        category = new Category("12","music",subCategoryList,18);
        event = new Event( "123","123E","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
        System.out.println("___________"+event);
    }
    @After
    public void tearDown(){

        event = null;
        eventRepository.deleteAll();
    }

    @Test
    public void testSaveEventSuccess(){

        eventRepository.save(event);
        Event fetchEvent = eventRepository.findByEventId(event.getEventId());
        Assert.assertEquals(fetchEvent.getName() ,event.getName());
         //Assert.assertNotNull(eventRepository.getOne(event.getId()));
//        eventRepository.insert(event);
//        Event getEvent = eventRepository.findByEventId(event.getEventId());
//        Assert.assertEquals(getEvent.getName() ,event.getName());
    }
    @Test
    public void testSaveEventFailure() {
        Date dateFormat = new Date("1234/12/23 23:23:23");
        venu= new Venu("12","bangalore","bangalore",40);
        subCategory= new SubCategory("1","music1");
        List<SubCategory> subCategoryList= new ArrayList<>();
        subCategoryList.add(subCategory);
        Category category = new Category("12","music",subCategoryList,18);
       Event newEvent = new Event( "1234","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
        //Event newEvent = new Event((long) 1234,"123E","Event1","this is event1", dateFormat, dateFormat,dateFormat,"Music","Bangalore","imageurl");
        eventRepository.save(newEvent);
        eventRepository.save(event);
        Event fetchEvent = eventRepository.findByEventId(event.getEventId());
        Assert.assertNotEquals(fetchEvent,event);

    }

    @Test
    public void testUpdateEvent(){
        eventRepository.insert(event);
        Event getEvent = eventRepository.findByEventId(event.getEventId());
        System.out.println("++++"+getEvent);
        getEvent.setName("updated event");
        eventRepository.save(getEvent);
        Event getNewEvent = eventRepository.findByEventId(event.getEventId());
        Assert.assertEquals("updated event" , getNewEvent.getName());
    }

    @Test
    public void testEventUpdateFailure() {
        eventRepository.save(event);
        Event getEvent = eventRepository.findByEventId(event.getEventId());
        getEvent.setName("updated Event");
        eventRepository.save(getEvent);
        Assert.assertNotEquals(event,getEvent);
    }

    @Test
    public void testDeleteEvent(){
        eventRepository.save(event);
        Event getEvent = eventRepository.findByEventId(event.getEventId());
        eventRepository.delete(getEvent);
        Assert.assertEquals(Optional.empty(),eventRepository.findById(event.getId()));

    }

    @Test
    public void testDeleteEventFailure() {
        Date dateFormat = new Date("1234/12/23 23:23:23");
        venu= new Venu("12","bangalore","bangalore",40);
        subCategory= new SubCategory("1","music1");
        List<SubCategory> subCategoryList= new ArrayList<>();
        subCategoryList.add(subCategory);
        category = new Category("12","music",subCategoryList,18);
        Event newEvent = new Event( "1234","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
       // Event newEvent = new Event((long) 1234,"123E","Event1","this is event1", dateFormat, dateFormat,dateFormat,"Music","Bangalore","imageurl");
        eventRepository.save(newEvent);
        eventRepository.save(event);
        Event getEvent = eventRepository.findByEventId(event.getEventId());
        eventRepository.delete(getEvent);
        Assert.assertNotEquals(false,eventRepository.findByEventId(event.getEventId()));
    }

        @Test
        public void testGetAllEvent(){
            eventRepository.save(event);
            Date dateFormat = new Date("1234/12/23 23:23:23");
            Venu venu= new Venu("12","bangalore","bangalore",40);
            SubCategory subCategory= new SubCategory("1","music1");
            List<SubCategory> subCategoryList= new ArrayList<>();
            subCategoryList.add(subCategory);
            Category category = new Category("12","music",subCategoryList,18);
            Event newEvent = new Event( "1234","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
            //Event newEvent = new Event((long) 1234,"123E","Event1","this is event1", dateFormat, dateFormat,dateFormat,"Music","Bangalore","imageurl");
            eventRepository.save(newEvent);
            List<Event> list = eventRepository.findAll();
            Assert.assertEquals(2,list.size());
            Assert.assertEquals("123E",list.get(0).getEventId());

        }

}








//package com.stackroute.nextevent.repository;
//        import com.stackroute.nextevent.model.Category;
//        import com.stackroute.nextevent.model.Event;
//        import com.stackroute.nextevent.model.SubCategory;
//        import com.stackroute.nextevent.model.Venu;
//        import org.junit.After;
//        import org.junit.Assert;
//        import org.junit.Before;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.test.context.junit4.SpringRunner;
//
//        import java.util.ArrayList;
//        import java.util.Date;
//        import java.util.List;
//        import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class EventRepositoryTest {
//
//    @Autowired
//   EventRepository eventRepository;
//
//   private Event event;
//   private Venu venu;
//   private Category category;
//   private SubCategory subCategory;
//   private List<SubCategory> subCategoryList;
//
//
//    @Before
//    public void setUp() {
//        subCategoryList = new ArrayList<>();
//        Date dateFormat = new Date("1234/12/23 23:23:23");
//        venu= new Venu("12","bangalore","bangalore",40);
//        subCategory= new SubCategory("1","music1");
//        subCategoryList.add(subCategory);
//        category = new Category("12","music",subCategoryList,18);
//        event = new Event( "123","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
//
//
//    }
//
//    @After
//    public void tearDown() {
//        event = null;
//        eventRepository.deleteAll();
//    }
//    @Test
//    public void testSaveGifSuccess() {
//        eventRepository.save(event);
//        Event getEvent = eventRepository.findByEventId(event.getEventId());
//        Assert.assertEquals(getEvent.getName(),event.getName());
//    }
//    @Test
//    public void testSaveGifFailure() {
//        Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
//        gifRepository.save(newGif);
//        gifRepository.save(gif);
//        Gif fetchGif = gifRepository.findByGiphyId(gif.getGiphyId());
//        Assert.assertNotEquals(fetchGif,gif);
//
//    }
//    @Test
//    public void testUpdateCaptionSuccess(){
//        gifRepository.save(gif);
//        Gif fetchGif = gifRepository.findByGiphyId(gif.getGiphyId());
//        fetchGif.setCaption("updating the caption");
//        gifRepository.save(fetchGif);
//        Gif newGif = gifRepository.findByGiphyId(gif.getGiphyId());
//        Assert.assertEquals("updating the caption" , newGif.getCaption());
//    }
//    @Test
//    public void testUpdateCaptionFailure() {
//        gifRepository.save(gif);
//        Gif fetchGif = gifRepository.findByGiphyId(gif.getGiphyId());
//        fetchGif.setCaption("updating the caption");
//        gifRepository.save(fetchGif);
//        Assert.assertNotEquals(gif,fetchGif);
//    }
//    @Test
//    public void testDeleteGifSuccess() {
//        gifRepository.save(gif);
//        Gif fetchGif = gifRepository.findByGiphyId(gif.getGiphyId());
//        gifRepository.delete(fetchGif);
//        Assert.assertEquals(Optional.empty(), gifRepository.findById(gif.getGifId()));
//    }
//    @Test
//    public void testDeleteGifFailure() {
//        Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
//        gifRepository.save(newGif);
//        gifRepository.save(gif);
//        Gif fetchGif = gifRepository.findByGiphyId(newGif.getGiphyId());
//        gifRepository.delete(fetchGif);
//        System.out.println(gifRepository.findByGiphyId(gif.getGiphyId()));
//        Assert.assertNotEquals(false,gifRepository.findByGiphyId(gif.getGiphyId()));
//    }
//    @Test
//    public void testGetAllGifs() {
//        Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
//        gifRepository.save(newGif);
//        gifRepository.save(gif);
//        List<Gif> gifList = gifRepository.findAll();
//        Assert.assertEquals(2,gifList.size());
//        Assert.assertEquals("gif125",gifList.get(0).getGiphyId());
//
//    }


//}
