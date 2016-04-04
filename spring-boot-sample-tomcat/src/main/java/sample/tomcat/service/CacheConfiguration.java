package sample.tomcat.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    /*@Bean
    public CacheManager cacheManager() {
        GuavaCacheManager guavaCacheManager =  new GuavaCacheManager();
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES));
        return guavaCacheManager;
    }*/





    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        GuavaCache cache1 = new GuavaCache("book", CacheBuilder.newBuilder().build());
        GuavaCache cache2 = new GuavaCache("books", CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build());
        simpleCacheManager.setCaches(Arrays.asList(cache1, cache2));
        return simpleCacheManager;
    }
/*

    private GuavaCache getUserCache(){

        GuavaCache cache =  new GuavaCache("user",CacheBuilder.newBuilder()
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

                        }));



    }*/
}
