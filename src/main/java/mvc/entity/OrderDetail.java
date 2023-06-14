package mvc.entity;

import javax.persistence.*;

@Entity
@Table (name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "order_detail_id")
    private int orderDetailId;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetail(int quantity) {
        this.quantity = quantity;
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, Order order, Product product, int quantity) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
