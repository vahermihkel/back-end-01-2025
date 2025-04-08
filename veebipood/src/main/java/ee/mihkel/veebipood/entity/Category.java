package ee.mihkel.veebipood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @ColumnDefault("false")
    private boolean active;

//    private List<Product> products;   category_products
    //                                   category    product
    //                                       1          1
    //                                       1          2

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
