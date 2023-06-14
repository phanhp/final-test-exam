package mvc.dataGenerate;

import mvc.configuration.Configuration;
import mvc.entity.Order;
import mvc.entity.OrderDetail;
import mvc.entity.Product;
import mvc.repository.OrderDetailRepository;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import mvc.service.MyCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
    static OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
    static OrderDetailRepository orderDetailRepository = (OrderDetailRepository) context.getBean("orderDetailRepository");
    static ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");

    public static void main(String[] args) {
        Main action = new Main();
        MyCode code = new MyCode();
//        action.createProductData();
//        action.createOrder();
//        action.showDetail(4);
//        action.search("17");
        System.out.println(code.isSearchSuccess("Fail check for sure","Try this please"));
    }

    public void search(String search){
        List<Order> list = orderRepository.findOrderByProductName(search);
        for (int i=0; i<list.size();i++){
            System.out.println(list.get(i).getOrderId());
        }
    }

    public void showDetail(int id){
        List<OrderDetail> detailList = orderDetailRepository.findDetailsByOrderId(id);
        for (int i=0; i<detailList.size();i++){
            System.out.println(detailList.get(i).getOrderDetailId());
        }
    }
    public void createProductData(){
        for (int i=1; i<= 250; i++){
            productRepository.save(new Product("product_"+i,166.57+i*0.33));
        }
    }

    public void createOrder(){
        LocalDate date = LocalDate.of(2019, 8, 21);
        for (int i=1; i<=6; i++){
            Order order = new Order(date, "customer_"+i);
            List<OrderDetail>details = new ArrayList<>();
            for (int j=1; j<=3; j++){
                OrderDetail orderDetail = new OrderDetail(2+j+i);
                orderDetail.setProduct(productRepository.findProductById(4*i-j));
                orderDetail.setOrder(order);
                details.add(orderDetail);
            }
            order.setOrderDetail(details);

            orderRepository.save(order);
            for (int j=0; j<details.size();j++){
                orderDetailRepository.save(details.get(j));
            }


            date = date.plusDays(3);
        }
    }


    }

