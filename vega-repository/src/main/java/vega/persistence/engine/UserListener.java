package vega.persistence.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vega.model.UserCreatedEvent;

@Component
public class UserListener {

    @Autowired
    private UserEngine userEngine;

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent creationEvent) {
        userEngine.doSomething("handleUserCreatedEvent");
    }
}
