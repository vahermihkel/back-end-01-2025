package ee.mihkel.veebipood.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

// Caused by: org.postgresql.util.PSQLException: ERROR: syntax error at or near "order"
// Order tabeli nimetus on juba Postgres enda poolt reserveeritud
// Lisaks on reserveeritud "User"
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person person;

    private Date created;
    private double totalSum;

//    @ManyToMany
//    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> rows;
}
