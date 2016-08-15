package vega.persistence.engine;

import com.google.common.collect.Lists;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProcessExecutor {

    @lombok.Data
    private class Request{
        private int id;
    }



    public  <R> List<List<R>> chunck(List<R> list,int n){
        return Lists.partition(list, n).stream().collect(Collectors.toList());
    }



    public  List<Long> callBackend(List<Long> request) throws InterruptedException {
        Thread.sleep(5000L);
        List<Long> res = new ArrayList<>();
        for (Long r : request ){
            res.add(r);
        }
        return res;
    }


    public  <Request,Response> List<Response> execute(List<Request> requests, Function<List<Request>,List<Response>> func){
        List<List<Request>>  partitions =  chunck(requests,requests.size());

        List<Response> response = new ArrayList<>();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(requests.size());
        try {
            List<Future<List<Response>>> resultList = new ArrayList<>();
            for (List<Request> partition : partitions) {
                Future<List<Response>> result = executor.submit(() -> func.apply(partition));
                resultList.add(result);
            }
            for (Future<List<Response>> future : resultList) {
                try {
                    response.addAll(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
        finally {
            executor.shutdown();

        }
    }

    public static void main(String[] args) {
        ProcessExecutor pe = new ProcessExecutor();
        List<Long> requests = new ArrayList<>();
        for (int i = 1; i <=13 ; i++) {
            requests.add((long) i);
        }
        Instant now = Instant.now();
        System.out.println(" "+Instant.now());
        Function<List<Long>,List<Long>> func = (r) ->{
            try {
                return pe.callBackend(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        };

        List<Long> res = pe.execute(requests,func);
        System.out.println(" "+Instant.now()+" "+res.size());

        /*ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        List<Future<Integer>> resultList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        for (Future<Integer> future : resultList) {
            try {
                System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();*/
    }


}
