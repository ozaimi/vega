package vega;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Optimization {

    public enum Headers {

        Date, Open, High, Low, Close, Volume, AdjClose
    }

    @AllArgsConstructor
    @Getter
    private class Data {
        private Date date;
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Double volume;
        private Double adjClose;
    }

    public static void main(String[] args) {
        new Optimization().process();
    }

    public void process() {

        try {
            Reader in = new FileReader("C:\\Users\\ozaimi\\Downloads\\dax.csv");

            Iterable<CSVRecord> records = CSVFormat.RFC4180.withSkipHeaderRecord().withHeader(Headers.class).parse(in);
            SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd");

            boolean isLong = true;

            List<Data> data = new ArrayList<>();
            for (CSVRecord record : records) {
                buildData(parserSDF, data, record);
            }

            // Sorting
            Collections.sort(data, (d1, d2) -> d1.getDate().compareTo(d2.getDate()));
            Map<Integer, List<Data>> dataByYear = data.stream().collect(Collectors.groupingBy(x -> x.getDate().getYear() + 1900));

            Map<Integer, List<Data>> dataByMonthYear = new HashMap<>();

            for (Map.Entry<Integer, List<Data>> d : dataByYear.entrySet()) {
                List<Data> dataByMonth = new ArrayList<>();
                int i = 0;
                for (Data gg : d.getValue()) {

                    if (gg.getDate().getMonth() == i) {
                        dataByMonth.add(gg);
                        i++;
                    }
                }
                if (dataByMonth.size() == 12) {
                    dataByMonthYear.put(d.getKey(), dataByMonth);
                }
            }





            int bidMonth = Calendar.NOVEMBER;
            int sellMonth = Calendar.MAY;
            int period = 2014;
            int leverage = 1;

            List<Perf> perfs = new ArrayList<>();
            for(int i=1990;i<=2014;i++) {
                perfs.add(getPerf(dataByMonthYear, i,leverage,isLong));
            }
           // Collections.sort(perfs, (d1, d2) -> d1.getPerf().compareTo(d2.getPerf()));
            Map<Integer,Integer> sellMonthProba = new HashMap<>();
            Map<Integer,Integer> bidMonthProba = new HashMap<>();
            for(Perf p : perfs){
                int oldS = sellMonthProba.getOrDefault(p.getSellMonth(),0);
                oldS++;
                sellMonthProba.put(p.getSellMonth(),oldS);

                int oldB = bidMonthProba.getOrDefault(p.getBidMonth(),0);
                oldB++;
                bidMonthProba.put(p.getBidMonth(),oldB);
            }

            List<Perf> perfsOptim = new ArrayList<>();

            for(int lev=1;lev<=1;lev++) {
                System.out.println("lev "+lev);
                for (int i = 1990; i <= 2014; i++) {

                    if(lev==1 && i==2006){
                        int ii=0;
                    }
                    PerfDeal perf = getGlobalPerf(dataByMonthYear, 3, 0, i, lev,isLong);
                   for(Deal d : perf.getDeals()){
                       System.out.println(d);
                   }
                    // PerfDeal perf = getGlobalPerf(dataByMonthYear, 11,0, i, lev,isLong);
                    Perf perfM = new Perf(5, 10, perf.getPerf(), i, 2015 - i, 0d, perf.getDeals());
                    perfsOptim.add(perfM);
                    perfM.computePerf();
                    System.out.println(perfM.getPeriod() + " " + perfM.getAvgPerf());
                }
                System.out.println("");
            }




            int y = 0;


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Perf getPerf(Map<Integer, List<Data>> dataByMonthYear, int period,int leverage,boolean isShort) {
        List<Perf> perfs = new ArrayList<>();
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                if(((12- i)+j)<12){
                    PerfDeal globalPerf = getGlobalPerf(dataByMonthYear, i, j,period,leverage,isShort);
                    Perf perf = new Perf(j+1,i+1,globalPerf.getPerf(),period,0,0d,globalPerf.getDeals());
                    perf.computePerf();
                    perfs.add(perf);
                }
            }
        }
        Collections.sort(perfs, (d1, d2) -> d1.getPerf().compareTo(d2.getPerf()));
        return perfs.get(perfs.size()-1);
    }

    private PerfDeal getGlobalPerf(Map<Integer, List<Data>> dataByMonthYear, int bidMonth, int sellMonth, int period,int leverage,boolean isLong) {

        if(period==2008){
            int yy =0;
        }

        Map<Integer, Double> perfByYear = new HashMap<>();
        double globalPerf = 100d;
        List<Deal> deals = new ArrayList<>();
        for (Map.Entry<Integer, List<Data>> d : dataByMonthYear.entrySet()) {
            if (d.getKey() < period) {
                continue;
            }
            Data bidData = d.getValue().get(bidMonth);
            double bid = bidData.getClose();
            if (dataByMonthYear.containsKey(d.getKey() + 1)) {
                Data sellData = dataByMonthYear.get(d.getKey() + 1).get(sellMonth);
                double sell = sellData.getClose();

                double v = ((sell - bid) / bid) * leverage;
                double perf =  isLong ? v : v*-1d;




                perf =  globalPerf<=0.0d ? -1d : perf;

                perfByYear.put(d.getKey(), perf * 100);


                globalPerf =   globalPerf<=0.0d? 0d :
                        (perf >-1d ? (globalPerf * perf + globalPerf):0d);

                deals.add(new Deal(sellData,bidData,leverage));
            }

        }


        return new PerfDeal(globalPerf,deals);
    }


    @Getter
    @ToString
    private class Deal{
        private Date bidDate;
        private Double bidPrice;
        private Date sellDate;
        private Double sellPrice;
        private int leverage;
        private double performanceInPerCent;

        public Deal (Data sellData,Data bidData,int leverage){
            this.sellDate=sellData.getDate();
            this.sellPrice=sellData.getClose();
            this.bidDate=bidData.getDate();
            this.bidPrice=bidData.getClose();
            this.leverage=leverage;
            final double v = (sellPrice - bidPrice) / bidPrice;
            this.performanceInPerCent= v *leverage*100d;

        }
    }


    @AllArgsConstructor
    @Getter
    private class PerfDeal{
        private Double perf;
        private List<Deal> deals;
    }

    @AllArgsConstructor
    @Getter
    private class Perf {
        private int sellMonth;
        private int bidMonth;
        private Double perf;
        private int period;
        private int nbYear;
        private Double avgPerf;
        private List<Deal> deals;



        public double getAveragePerf(){
            return perf!=null ? Math.pow(perf/100d,1d/nbYear):0d;
        }

        public void computePerf() {
            avgPerf = (getAveragePerf()-1d)*100;
        }
    }


    private double computeDrawdown(){


        

        return -1;
    }

    private void buildData(SimpleDateFormat parserSDF, List<Data> data, CSVRecord record) throws ParseException {
        Date date = parserSDF.parse(record.get(Headers.Date));
        Double open = Double.valueOf(record.get(Headers.Open));
        Double high = Double.valueOf(record.get(Headers.High));
        Double low = Double.valueOf(record.get(Headers.Low));
        Double close = Double.valueOf(record.get(Headers.Close));
        Double volume = Double.valueOf(record.get(Headers.Volume));
        Double adjClose = Double.valueOf(record.get(Headers.AdjClose));
        Data mktData = new Data(date, open, high, low, close, volume, adjClose);
        data.add(mktData);
    }
}
