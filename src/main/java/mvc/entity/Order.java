package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @Column(name = " customer_name")
    private String customerName;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetail;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Order(LocalDate orderDate, String customerName) {
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public Order() {
    }

    public Order(int orderId, LocalDate orderDate, String customerName, List<OrderDetail> orderDetail) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetail = orderDetail;
    }
}
