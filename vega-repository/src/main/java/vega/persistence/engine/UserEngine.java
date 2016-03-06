package vega.persistence.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Component
public class UserEngine {



    @Async("userEngineExecutor")
    public void  doSomething(String s) {
        System.out.println(Thread.currentThread().getName()+" couccou "+s);
    }
}
