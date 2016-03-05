package vega.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "vega.persistence.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"vega.model"})
@PropertySource(
        name = "props",
        value = { "classpath:repository.properties", "classpath:test.properties" })
public class RepositoryConfig {
    @Value("${some.key}")
    String valueOfThatKey;
}
