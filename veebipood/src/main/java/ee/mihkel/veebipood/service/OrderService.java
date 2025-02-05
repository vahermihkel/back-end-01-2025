package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ProductRepository productRepository;

    public void saveOrder(List<OrderRow> rows, Long personId) {
        Order order = new Order();
        order.setCreated(new Date());
        order.setRows(rows); // siin võtab ainult ID'd kasutusele
        order.setTotalSum(calculateSum(rows));

        Person person = personRepository.findById(personId).orElseThrow();
        order.setPerson(person);
        orderRepository.save(order);
    }

    private double calculateSum(List<OrderRow> rows) {
        double sum = 0;
        for (OrderRow row: rows) {
            Product product = productRepository.findById(row.getProduct().getId()).orElseThrow();
            sum += product.getPrice() * row.getQuantity();
        }
        return sum;
    }


}
