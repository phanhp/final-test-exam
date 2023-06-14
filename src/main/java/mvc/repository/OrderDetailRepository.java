package mvc.repository;

import mvc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select * from order_details where order_id = ?1", nativeQuery = true)
    List<OrderDetail> findDetailsByOrderId(int id);
}
