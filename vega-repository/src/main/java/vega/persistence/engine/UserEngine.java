package vega.persistence.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vega.model.UserCreatedEvent;

@Component
public class UserEngine {


    private final ApplicationEventPublisher publisher;

    @Autowired
    public UserEngine(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }



    public void createUser(UserCreatedEvent order) {
        this.publisher.publishEvent(new UserCreatedEvent(order));
    }


    @Async("userEngineExecutor")
    public void  doSomething(String s) {
        System.out.println(Thread.currentThread().getName()+" couccou "+s);
    }
}
