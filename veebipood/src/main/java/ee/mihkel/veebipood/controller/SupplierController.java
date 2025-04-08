package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.model.SupplierProduct1;
import ee.mihkel.veebipood.model.SupplierProduct2;
import ee.mihkel.veebipood.model.SupplierProduct3;
import ee.mihkel.veebipood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("supplier1")
    public List<SupplierProduct1> getSupplier1Products() {
        return supplierService.getProducts1();
    }

    @GetMapping("supplier2")
    public List<SupplierProduct2> getSupplier2Products() {
        return supplierService.getProducts2();
    }

    @GetMapping("supplier3")
    public List<SupplierProduct3> getSupplier3Products() {
        return supplierService.getProducts3();
    }

}
