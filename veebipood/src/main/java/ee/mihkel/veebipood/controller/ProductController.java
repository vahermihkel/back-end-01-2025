package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// API päringute vastuvõtmiseks. Front-end suhtleb back-endiga selle kaudu.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

//    private ProductRepository productRepository2;
//
//    public ProductController(ProductRepository productRepository) {
//        this.productRepository2 = productRepository;
//    }

    // localhost:8080/products

    @GetMapping("products")
    public List<Product> getProducts() {
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("Coca", 1.2, true, ""));
//        products.add(new Product());
//        return products;
        return productRepository.findByOrderByIdAsc(); // SELECT * FROM product
    }

    // pageable tekitab automaatselt järgmised @RequestParamid juurde:
    // public-products?page=0&size=30&sort=id
    @GetMapping("public-products")
    public Page<Product> getPublicProducts(@RequestParam Long categoryId, Pageable pageable) {
        //pageable.getPageSize()
        if (categoryId == 0) {
            return productRepository.findByOrderByIdAsc(pageable);
        } else {
            return productRepository.findByCategory_IdOrderByIdAsc(categoryId, pageable);
        }
    }

//    @GetMapping("products-by-category")
//    public Page<Product> getProductsByCategory(@RequestParam Long categoryId, Pageable pageable) {
//        return productRepository.findByCategory_IdOrderByIdAsc(categoryId, pageable);
//        // SELECT * FROM product WHERE p.category.id = categoryId
//    }

    //Ei tehta: add-product
    // POST localhost:8080/products?productName=Coca
    // POST localhost:8080/products?productName=Fanta
    @PostMapping("products") // TODO: Ühildama POST ja PUT päringute veateated
    public List<Product> addProduct(@RequestBody Product product) {
        // INSERT INTO product VALUES ()
        //productRepository.save(new Product(productName, 1.2, true, ""));
        if (product.getName() == null) {
            throw new RuntimeException("Toote nimi puudub");
        }
        if (product.getName().toLowerCase().charAt(0) == product.getName().charAt(0)) {
            throw new RuntimeException("Toode väikse tähega");
        }
        if (product.getPrice() < 0) {
            throw new RuntimeException("Hind miinusmärgiga");
        }
        if (product.getId() != null) {
            throw new RuntimeException("ID-d ei tohi lisades lisada (genereerub automaatselt)");
        }
        product.setActive(true);
        productRepository.save(product);
        return productRepository.findByOrderByIdAsc();
    }

    // Lisamisel @RequestParam vs @PathVariable:
    // localhost:8080/products?productName=Coca&price=1.2&active=true&image=http
    // localhost:8080/products/Coca/1.2/true/http

    // @RequestBody {id: 2} --> kustutamisel ja 1 võtmisel EI
    // @RequestParam --> localhost:8080/products?id=1
    // @PathVariable --> localhost:8080/products/1

    // Soovitus:
    // Kasutada @RequestParamit siis kui mul on 2 või enam muutujat ja GET päring
    // Kasutada @PathVariable siis kui mul on täpselt 1 muutuja
    // Kasutada @RequestBody siis kui mul on POST päring


    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findByOrderByIdAsc();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        // Optional --> võib tagastada ka "null"
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(); // eelistatud, sest nimes ütleb exceptionit
        // return productOptional.get(); <--- täpselt sama
        //return productOptional.orElse(null);
    }

    // broneering -> available: false
    // tellimus -> makstud: true
    @PatchMapping("product-active")
    public List<Product> changeProductActive(@RequestParam Long id, @RequestParam boolean active) {
        // kui tahan ühte kindlat välja muuta, siis pean võtma andmebaasist, muutma
        Product product = productRepository.findById(id).orElseThrow(); // findById(id).orElseThrow() . Lazy ehk võtab ainult mälukoha
        product.setActive(active);
        // ja tagasi andmebaasi tervikuna panema
        productRepository.save(product);
        return productRepository.findByOrderByIdAsc();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().toLowerCase().charAt(0) == product.getName().charAt(0)) {
            throw new RuntimeException("Toode peab olema suure tähega");
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Hind peab olema plussmärgiga");
        }
        if (product.getId() == null || productRepository.findById(product.getId()).isEmpty()) {
            throw new RuntimeException("Sellise ID-ga toodet pole");
        }
        productRepository.save(product); // .save kasutatakse nii lisamiseks kui muutmiseks
        return productRepository.findByOrderByIdAsc();
    }


}