package vega.persistence.repository;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
import vega.model.PortfolioRoutingRule;
import vega.persistence.config.RepositoryConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;

@ContextConfiguration(classes=RepositoryConfig.class)
public class PortfolioRoutingRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {


    @Autowired
    private PortfolioRoutingRepository portfolioRoutingRepository;

    @Test
   // @Rollback
    public void test(){
        PortfolioRoutingRule r1 = new PortfolioRoutingRule();
        r1.setBackendReference("eliot1");
        r1.setSource("source1");
        r1.setTarget("target1");

        PortfolioRoutingRule r2 = new PortfolioRoutingRule();
        r2.setBackendReference("eliot2");
        r2.setSource("source2");
        r2.setTarget("target2");

        ArrayList<PortfolioRoutingRule> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);

        Iterable<PortfolioRoutingRule> res = portfolioRoutingRepository.save(list);

        Iterable<PortfolioRoutingRule> t = portfolioRoutingRepository.findAll();
        for(PortfolioRoutingRule tt : t){
            int y=0;
        }

        Iterator<PortfolioRoutingRule> it = res.iterator();


        PortfolioRoutingRule r11 = new PortfolioRoutingRule();
        r11.setBackendReference("eliot1");
        r11.setSource("source1");
        r11.setTarget("target1");
        r11.setId(it.next().getId());

        PortfolioRoutingRule r22 = new PortfolioRoutingRule();
        r22.setBackendReference("eliot2");
        r22.setSource("source2");
        r22.setTarget("target2");
        //r22.setId(it.next().getId());

        ArrayList<PortfolioRoutingRule> listToDelete = new ArrayList<>();
        listToDelete.add(r11);
        listToDelete.add(r22);

        portfolioRoutingRepository.delete(listToDelete);



        t = portfolioRoutingRepository.findAll();
        for(PortfolioRoutingRule tt : t){
            int y=0;
        }
    }

}