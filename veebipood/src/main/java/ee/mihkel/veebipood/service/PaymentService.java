package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.model.*;
import ee.mihkel.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

@Service
public class PaymentService {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    OrderRepository orderRepository;

    @Value("${every-pay.base-url}")
    String everyPayUrl;

    @Value("${every-pay.customer-url}")
    String customerUrl;

    @Value("${every-pay.username}")
    String username;

    @Value("${every-pay.password}")
    String password;

    public EveryPayLink getEveryPayLink(Order order) {

        String url = everyPayUrl + "/oneoff";


        EveryPayBody body = new EveryPayBody();
        body.setAccount_name("EUR3D1");
        body.setNonce(ZonedDateTime.now().toString() + Math.random());
        body.setTimestamp(ZonedDateTime.now().toString());
        body.setAmount(order.getTotalSum());
        body.setOrder_reference(order.getId().toString());
        body.setCustomer_url(customerUrl);
        body.setApi_username(username);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EveryPayBody> httpEntity = new HttpEntity<>(body, headers);

        String link = restTemplate.exchange(url, HttpMethod.POST, httpEntity, EveryPayResponse.class).getBody().getPayment_link();

        EveryPayLink everyPayLink = new EveryPayLink();
        everyPayLink.setLink(link);
        return everyPayLink;
    }

    public boolean checkOrderStatus(String paymentReference) {

        String apiUsername = username;
        String url = customerUrl + "/" + paymentReference + "?api_username=" + apiUsername + "&detailed=false";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        EveryPayCheck response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EveryPayCheck.class).getBody();
        Order order = orderRepository.findById(Long.parseLong(response.getOrder_reference())).orElseThrow();
        order.setPaymentStatus(EveryPayStatus.valueOf(response.getPayment_state()));
        orderRepository.save(order);
        return response.getPayment_state().equals(EveryPayStatus.settled.toString().toLowerCase());
    }
}
