package vega.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import vega.persistence.config.RepositoryConfig;
import vega.persistence.repository.UserRepository;
import vega.model.User;
import static org.testng.Assert.*;

@SpringApplicationConfiguration(classes = {RepositoryConfig.class})
@TestExecutionListeners(inheritListeners = false, listeners = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })

public class UserRepositoryTest extends AbstractTestNGSpringContextTests{


    @Autowired
    private UserRepository userRepository;
    @Test
    @Rollback(true)
    @Transactional
    public void testRepo(){
        Iterable<User> users = userRepository.findAll();
        User user = new User();
        user.setUserName("test7");
        userRepository.save(user);

        User u2 = userRepository.findByUserName("test7");
        u2.setUserName("test8");

        userRepository.save(u2);
    }

}