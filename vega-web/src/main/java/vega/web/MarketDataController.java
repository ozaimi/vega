package vega.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vega.model.MarketData;
import vega.service.MarketDataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// http://www.journaldev.com/2552/spring-restful-web-service-example-with-json-jackson-and-client-program
@RestController
@RequestMapping("/MarketData")
public class MarketDataController {

    @Autowired
    private MarketDataService marketDataService;

    @RequestMapping(value={"/get/{id}"},method = {RequestMethod.GET})
    public @ResponseBody MarketData get(@PathVariable("id") int id, @RequestHeader HttpHeaders headers) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String user = (String) auth.getPrincipal();

        List<Long> args = new ArrayList<>();
        args.add((long) id);
        return marketDataService.findMarketDataByDefId(args).get(0);

    }

    @RequestMapping(value={"/getAll"},method = {RequestMethod.GET})
    public @ResponseBody List<MarketData> getAll() {
        List<Long> args = new ArrayList<>();
        args.add(1L);
        args.add(2L);
        args.add(3L);
        return marketDataService.findMarketDataByDefId(args);

    }

    @RequestMapping(value={"/create"},method = {RequestMethod.POST})
    public @ResponseBody MarketData create(@RequestBody MarketData marketData){
        return marketData;
    }

    @RequestMapping(value={"/update"},method = {RequestMethod.PUT})
    public @ResponseBody MarketData update(@RequestBody MarketData marketData){
        return marketData;
    }

    @RequestMapping(value={"/delete/{id}"},method = {RequestMethod.DELETE})
    public @ResponseBody boolean delete(@PathVariable("id") int id){
        return true;
    }
}
