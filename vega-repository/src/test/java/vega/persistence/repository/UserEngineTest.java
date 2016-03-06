package vega.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import vega.model.User;
import vega.model.UserCreatedEvent;
import vega.persistence.config.RepositoryConfig;
import vega.persistence.engine.UserEngine;

@SpringApplicationConfiguration(classes = {RepositoryConfig.class})
@TestExecutionListeners(inheritListeners = false, listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public class UserEngineTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserEngine userEngine;

    @Test
    public void testTaskExecutor(){
        /*for (int i = 0; i < 10; i++) {
            userEngine.doSomething("test");
        }*/
        userEngine.createUser(new UserCreatedEvent(new User()));
    }

    /*@EventListener
    public void handleUserCreatedEvent(UserCreatedEvent creationEvent) {
        userEngine.doSomething("test");
    }*/

}