package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    // localhost:8080/orders
    @PostMapping("orders")
    public List<Order> saveOrder(@RequestBody List<OrderRow> rows) {
        Long personId = Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
        orderService.saveOrder(rows, personId);
        return orderRepository.findAll();
    }

    @GetMapping("orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
