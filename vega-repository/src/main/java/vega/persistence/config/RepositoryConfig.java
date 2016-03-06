package vega.persistence.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;

@Configuration
@EnableJpaRepositories(basePackages = "vega.persistence.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"vega.model"})
@PropertySource(
        name = "props",
        value = { "classpath:repository.properties", "classpath:test.properties" })
@EnableTransactionManagement
@EnableAsync
@ComponentScan("vega.persistence.engine")
public class RepositoryConfig {
    @Value("${some.key}")
    String valueOfThatKey;

    @Bean(name = "userEngineExecutor")
    public TaskExecutor userEngineExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("userEngineExecutor");

        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
