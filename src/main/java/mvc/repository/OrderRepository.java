package mvc.repository;

import mvc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select * from orders", nativeQuery = true)
    List<Order> findAllOrder();

    @Query(value = "select * from orders where order_id =?1", nativeQuery = true)
    Order findOrderById(int id);

    @Query(value = "select * from orders where customer_name like %?1%", nativeQuery = true)
    List<Order> findOrderByCustomerName(String customerName);

    @Query(value = "select * from orders where order_date like %?1%", nativeQuery = true)
    List<Order> findOrderByDate(String orderDate);

    @Query(value = "select * from orders a join order_details b on a.order_id = b.order_id join products c on b.product_id = c.product_id where c.product_name like %?1%", nativeQuery = true)
    List<Order> findOrderByProductName(String productName);
}
