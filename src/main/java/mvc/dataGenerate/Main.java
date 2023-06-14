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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
//        System.out.println(code.isSearchSuccess("Fail check for sure","Try this please"));
    }

    public void search(String search) {
        List<Order> list = orderRepository.findOrderByProductName(search);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getOrderId());
        }
    }

    public void showDetail(int id) {
        List<OrderDetail> detailList = orderDetailRepository.findDetailsByOrderId(id);
        for (int i = 0; i < detailList.size(); i++) {
            System.out.println(detailList.get(i).getOrderDetailId());
        }
    }

    public void createProductData() {
        double a = 5000;
        int[] productType = new int[]{1, 1, 1, 1, 1};
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        df.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 1; i <= 500; i++) {
            Product product = new Product();
            String productName = "";
            double productPrice = 0;

            if (i % 5 == 0) {
                productName = "product_type_A_number_" + productType[0];
                productPrice = Double.valueOf(df.format(0.16 * a + 5000 + i * 2.73));
                product.setProName(productName);
                product.setUnitPrice(productPrice);
                productRepository.save(product);
                productType[0]++;
            } else if (i % 7 == 0) {
                productName = "product_type_B_number_" + productType[1];
                productPrice = Double.valueOf(df.format(0.29 * a + 2000 - i * 1.24));
                product.setProName(productName);
                product.setUnitPrice(productPrice);
                productRepository.save(product);
                productType[1]++;
            } else if (i % 11 == 0) {
                productName = "product_type_C_number_" + productType[2];
                productPrice = Double.valueOf(df.format(0.08 * a + 3500 + i * 3.27));
                product.setProName(productName);
                product.setUnitPrice(productPrice);
                productRepository.save(product);
                productType[2]++;
            } else if (i % 13 == 0) {
                productName = "product_type_D_number_" + productType[3];
                productPrice = Double.valueOf(df.format(1.21 * a - 1500 + i * 6.27));
                product.setProName(productName);
                product.setUnitPrice(productPrice);
                productRepository.save(product);
                productType[3]++;
            } else {
                productName = "product_type_E_number_" + productType[4];
                productPrice = Double.valueOf(df.format(0.67 * a + 2000 - i * 4.46));
                product.setProName(productName);
                product.setUnitPrice(productPrice);
                productRepository.save(product);
                productType[4]++;
            }

            if (productPrice > 8000) {
                a = productPrice / 2;
            } else if (productPrice < 2000) {
                a = productPrice * 3.5;
            } else {
                a = productPrice;
            }
        }
    }

    public void createOrder() {
        LocalDate date = LocalDate.of(2019, 8, 21);
        ArrayList<String> customerNameList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            customerNameList.add("Customer_" + i);
        }
        customerNameList.add("Passersby");

        for (int i = 1; i <= 60; i++) {
            Order order = new Order();
            order.setOrderDate(date);
            if (i % 5 == 0) {
                order.setCustomerName(customerNameList.get(0));
            } else if (i % 7 == 0) {
                order.setCustomerName(customerNameList.get(1));
            } else if (i % 11 == 0) {
                order.setCustomerName(customerNameList.get(2));
            } else if (i % 13 == 0) {
                order.setCustomerName(customerNameList.get(3));
            } else {
                order.setCustomerName(customerNameList.get(4));
            }

            List<OrderDetail> details = new ArrayList<>();
            int numberBuy = 1;
            if (i % 4 == 0) {
                numberBuy = 2;
            } else if (i % 7 == 0) {
                numberBuy = 3;
            } else if (i % 15 == 0) {
                numberBuy = 5;
            }
            for (int j = 1; j <= numberBuy; j++) {
                OrderDetail orderDetail = new OrderDetail();
                int quantity = 1;
                if (i % 2 == 0 && j % 2 == 0) {
                    quantity = 2;
                } else if (i % 3 == 0 && j % 3 == 0) {
                    quantity = 3;
                }
                orderDetail.setQuantity(quantity);

                int productId = 1;
                if (i % 4 == 0) {
                    productId = 3 * i + 17 * j;
                } else if (i % 6 == 0) {
                    productId = 21 * i + 14 * j;
                } else if (i % 11 == 0) {
                    productId = 5 * i + 14 * j;
                }
                if (productId > 500) {
                    productId = 500;
                }
                orderDetail.setProduct(productRepository.findProductById(productId));
                orderDetail.setOrder(order);
                details.add(orderDetail);
            }
            order.setOrderDetail(details);
            orderRepository.save(order);
            for (int j = 0; j < details.size(); j++) {
                orderDetailRepository.save(details.get(j));
            }
            if (i % 3 == 0) {
                date = date.plusDays(1);
            } else if (i % 7 == 0) {
                date = date.plusDays(2);
            }
        }
    }


}

