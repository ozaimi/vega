package sample.tomcat.service;

import java.util.*;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.cache.CacheStatisticsProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@MonitoredWithSpring
@Component
public class UserCache {

    private  LoadingCache<Long, User> cache;

   // @Autowired
   // private CacheStatisticsProvider cacheStatisticsProvider;

    @Autowired
    private CacheManager cacheManager;

    private static Map<Long, User> loadFromBackend(Iterable<? extends Long> keys){
        Map<Long, User> result = new HashMap<Long, User>();
        for (Long l : keys){
            User u = new User();
            u.setId(l.intValue());
            result.put(l,u);
        }
        return result;
        }
    private static User loadFromBackend(Long l){
        User u = new User();
        u.setId(l.intValue());

        return u;
    }


    public  void init2(){

        List<Long> ids = new ArrayList();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        try {
            Map<Long,User> res = cache.getAll(ids);
            int yy =11;

            ids = new ArrayList();
            ids.add(1L);
            ids.add(4L);

            cache.invalidateAll();

           // Thread.sleep(3*1000L);

            res = cache.getAll(ids);

            //Thread.sleep(12*1000L);



            ids = new ArrayList();
            ids.add(1L);
            ids.add(5L);

            res = cache.getAll(ids);
            res = cache.getAll(ids);
            yy =11;
            Thread.sleep(60*1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public  void init(){
        cache = CacheBuilder.newBuilder()
                .recordStats()
                .maximumSize(1000)
                //.expireAfterWrite(2, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<Long, User>() {
                            public User load(Long key) { // no checked exception
                                return loadFromBackend(key);
                                //return getAllGraphsFromDatabase(keys);
                            }

                            public Map<Long, User> loadAll(Iterable<? extends Long> keys) {
                                return loadFromBackend(keys);
                                //return getAllGraphsFromDatabase(keys);
                            }
                        });
        //GuavaCache guavaCache1 = new GuavaCache("book",cache);

        //SimpleCacheManager sc = new SimpleCacheManager();
        //sc.setCaches(Arrays.asList(cache));
      //  ((GuavaCacheManager)cacheManager).setCacheLoader();
        init2();


    }

    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void doSomething() {
        System.out.println("coucou "+new Date());
    }
}
