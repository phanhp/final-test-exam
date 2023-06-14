package mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private int proId;

    @Column (name = "product_name")
    private String proName;

    @Column (name = "unit_price")
    private double unitPrice;

    @OneToMany (mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product(String proName, double unitPrice) {
        this.proName = proName;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

    public Product(int proId, String proName, double unitPrice, List<OrderDetail> orderDetail) {
        this.proId = proId;
        this.proName = proName;
        this.unitPrice = unitPrice;
        this.orderDetail = orderDetail;
    }
}
