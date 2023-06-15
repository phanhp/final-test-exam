package mvc.controller;

import mvc.entity.Order;
import mvc.entity.OrderDetail;
import mvc.repository.OrderDetailRepository;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import mvc.service.MyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PageController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/test-page", method = RequestMethod.GET)
    public String toTestPage(Model model) {
        model.addAttribute("text", "TEST PAGE");
        return "test/testPage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showOrder(Model model) {
        List<Order> orderList = (List<Order>) orderRepository.findAllOrder();
        model.addAttribute("orderList", orderList);
        model.addAttribute("msg", "This is order list");
        return "home/home";
    }

    @RequestMapping(value = "/view-detail/{id}", method = RequestMethod.GET)
    public String showDetail(Model model, @PathVariable int id) {
        List<OrderDetail> detailList = orderDetailRepository.findDetailsByOrderId(id);
        model.addAttribute("detailList", detailList);
        model.addAttribute("msg", "This is detail list");
        return "home/detail";
    }

    //search orderlist by customer name, order date, product name
    //Cách 1, tạo 3 querry rồi add vào map
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findOrderBySearchToHomePage(@RequestParam("searchInput") String searchValue, Model model) {
        Map<Integer, Order> map = new HashMap<>();
        List<Order> fromCustomerName = orderRepository.findOrderByCustomerName(searchValue);
        if (fromCustomerName != null) {
            for (int i = 0; i < fromCustomerName.size(); i++) {
                map.put(fromCustomerName.get(i).getOrderId(), fromCustomerName.get(i));
            }
        }
        List<Order> fromOrderDate = orderRepository.findOrderByDate(searchValue);
        if (fromOrderDate != null) {
            for (int i = 0; i < fromOrderDate.size(); i++) {
                map.put(fromOrderDate.get(i).getOrderId(), fromOrderDate.get(i));
            }
        }
        List<Order> fromProduct = orderRepository.findOrderByProductName(searchValue);
        if (fromProduct != null) {
            for (int i = 0; i < fromProduct.size(); i++) {
                map.put(fromProduct.get(i).getOrderId(), fromProduct.get(i));
            }
        }

        List<Order> orderList = new ArrayList<>(map.values());
        model.addAttribute("orderList", orderList);
        model.addAttribute("msg", "This is searched list");
        return "home/home";
    }

    //Cách 2 search, dùng thuật toán để tìm
    //Đang fail do check tìm kiếm tương đương cho ra quá nhiều kết quả true
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String findOrderBySearch(@RequestParam("searchInput") String searchValue, Model model) {
//        MyCode myMethod = new MyCode();
//        Map<Integer, Order> map = new HashMap<>();
//        List<Order> allOrderList = orderRepository.findAllOrder();
//        for (int i = 0; i < allOrderList.size(); i++) {
//            if (myMethod.isSearchSuccess(searchValue, allOrderList.get(i).getCustomerName())
//                    | searchValue.equalsIgnoreCase(allOrderList.get(i).getOrderDate().toString())) {
//                map.put(allOrderList.get(i).getOrderId(), allOrderList.get(i));
//            }
//            List<OrderDetail>details = allOrderList.get(i).getOrderDetail();
//            for (int j = 0; j < details.size();j++){
//                if(myMethod.isSearchSuccess(searchValue, details.get(j).getProduct().getProName())){
//                    map.put(allOrderList.get(i).getOrderId(), allOrderList.get(i));
//                    break;
//                }
//            }
//        }
//        List<Order> orderList = new ArrayList<>(map.values());
//        model.addAttribute("orderList", orderList);
//        model.addAttribute("msg", "This is searched list");
//        return "home/home";
//    }

}
