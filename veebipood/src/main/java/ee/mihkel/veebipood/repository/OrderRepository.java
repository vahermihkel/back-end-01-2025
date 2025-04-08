package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    Page<Order> findByPerson_IdOrderByCreatedDesc(Long id, Pageable pageable);

    List<Order> findByCreatedBetween(Date createdStart, Date createdEnd);

}
