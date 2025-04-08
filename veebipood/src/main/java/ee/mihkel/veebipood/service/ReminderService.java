package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ReminderService {
    // * mis peab olema sekund, et käivituks
    // * * mis peab olema minut, et käivituks
    // * * * mis peab olema tund, et käivituks
    // * * * * mis kuupäeb selles kuus 31max  6
    // * * * * * kuu 12 --> detsembris käivitud
    // * * * * * * nädalapäev

//    @Scheduled(cron = "*/60 * * * * *")
//    public void runEverySecond() {
////        System.out.println("/60");
////        Date date = new Date();
////        System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
//    }
//
//    @Scheduled(cron = "0 * * * * *")
//    public void runEveryMinute() {
////        System.out.println("0");
////        Date date = new Date();
////        System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
//    }
//
//    @Scheduled(cron = "0 17 15 * * 4")
//    public void runThursDay1515OClock() {
////        System.out.println("15.17 started");
////        Date date = new Date();
////        System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
//    }

    @Autowired
    OrderRepository orderRepository;

    // 13.04.2021 13:33 SMS
    // 14.04.2021 13:20 aeg

    // 02.09  10:51 SMS
    // 03.09  10:40 aeg

    // 16.09  10:32 SMS
    // 17.09  10:20 aeg

//    @Scheduled(cron = "*/5 * * * * *")
//    public void runEveryAfterEvery5Seconds() {
//        Date today = new Date();
//        Date date = new GregorianCalendar(Year.now().getValue(), today.getMonth(), 3).getTime();
//        System.out.println(date);
//        Date endDate = new Date();
//        System.out.println(today);
//        List<Order> orders = orderRepository.findByCreatedBetween(date, today);
//        for (Order o: orders) {
//            System.out.println(o.getId());
//        }
//    }

    @Scheduled(cron = "0 */10 8-18 * * 1-5")
    public void makeAReminder() {
        System.out.println("10 minuti tagant");
    }
}
