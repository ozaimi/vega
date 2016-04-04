package vega.persistence.engine;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import vega.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class UserCache {

    private static LoadingCache<Long, User> cache;


    private static Map<Long, User> loadFromBackend(Iterable<? extends Long> keys){
        Map<Long, User> result = new HashMap<>();
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


    public static void main(String[] args){
        init();
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

            Thread.sleep(3*1000L);

            res = cache.getAll(ids);

            Thread.sleep(12*1000L);



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


    public static void init(){
         cache = CacheBuilder.newBuilder()
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

                            /*public ListenableFuture<User> reload(final Long key, User prevGraph) {
                                if (neverNeedsRefresh(key)) {
                                    return Futures.immediateFuture(prevGraph);
                                } else {
                                    // asynchronous!
                                    return ListenableFutureTask.create(new Callable<User>() {
                                        public User call() {
                                            return null;
                                            //return getGraphFromDatabase(key);
                                        }
                                    });
                                }
                            }*/
                        });

    }
}
