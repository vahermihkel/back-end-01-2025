package ee.mihkel.veebipood.entity;


import ee.mihkel.veebipood.model.EveryPayStatus;
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
@SequenceGenerator(name = "seq",initialValue = 8986700, allocationSize = 1)
public class Order {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @ManyToOne
    private Person person;

    private Date created;
    private double totalSum;
    private EveryPayStatus paymentStatus;
    // initial -> maksma mindud, aga ei ole vastust
    // settled -> korras
    // failed, abandoned, voided -> mittekorras

//    @ManyToMany
//    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> rows;
}
