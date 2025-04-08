package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.model.EveryPayLink;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import ee.mihkel.veebipood.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    // localhost:8080/orders
    @PostMapping("orders")
    public EveryPayLink saveOrder(@RequestBody List<OrderRow> rows) throws ExecutionException {
        Long personId = Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
        Order order = orderService.saveOrder(rows, personId);
        return paymentService.getEveryPayLink(order);
    }

    @GetMapping("orders")
    public Page<Order> getOrders(Pageable pageable) {
        Long personId = Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
        return orderRepository.findByPerson_IdOrderByCreatedDesc(personId, pageable);
    }

    // ID 29 settled ---> bfedc97c0c3786c6b9e7f385e7a5ae8b180238c01bc0f16587ba74ee4a4b684f
    // ID 31 voided ---> 36a1185bccd3eeda44b698c718acb2a361d0bad8bf0d75cbc4c3fa5cf6dd60ec
    // ID 8986700 voided ---> 6fb52399a8404154759220b68380f6c3a373d7cc8a9c47b1feeb9923bf5afbcf

    @GetMapping("order-status")
    public boolean checkOrderStatus(@RequestParam String paymentReference) {
        return paymentService.checkOrderStatus(paymentReference);
    }
}
