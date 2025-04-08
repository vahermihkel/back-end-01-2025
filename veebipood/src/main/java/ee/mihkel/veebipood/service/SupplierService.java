package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.model.SupplierProduct1;
import ee.mihkel.veebipood.model.SupplierProduct2;
import ee.mihkel.veebipood.model.SupplierProduct3;
import ee.mihkel.veebipood.model.SupplierProduct3Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class SupplierService {

    @Autowired
    RestTemplate restTemplate;

    public List<SupplierProduct1> getProducts1() {
//        RestTemplate restTemplate = new RestTemplate();

        String url = "https://fakestoreapi.com/products";
        SupplierProduct1[] response = restTemplate.exchange(url, HttpMethod.GET, null, SupplierProduct1[].class).getBody();

        return Arrays.stream(response)
                .filter(e -> e.getRating().getRate() > 2)
                .peek(e -> e.setRetailPrice(e.getPrice() * 1.2))
                .toList();
    }

    public List<SupplierProduct2> getProducts2() {
//        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.escuelajs.co/api/v1/products";
        SupplierProduct2[] response = restTemplate.exchange(url, HttpMethod.GET, null, SupplierProduct2[].class).getBody();

        return Arrays.stream(response)
                .filter(e -> e.getCreationAt().equals(e.getCategory().getCreationAt()))
                .peek(e -> e.setRetailPrice(e.getPrice() * 1.2))
                .toList();
    }

    public List<SupplierProduct3> getProducts3() {
//        RestTemplate restTemplate = new RestTemplate();

        String url = "https://dummyjson.com/products";
        SupplierProduct3Response response = restTemplate.exchange(url, HttpMethod.GET, null, SupplierProduct3Response.class).getBody();

        return response.getProducts();
    }
}
