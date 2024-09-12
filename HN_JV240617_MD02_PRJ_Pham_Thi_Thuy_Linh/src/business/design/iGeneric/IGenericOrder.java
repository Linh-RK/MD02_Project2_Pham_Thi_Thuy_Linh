package business.design.iGeneric;

import business.entity.Cart;
import business.entity.Order;

import java.util.List;
import java.util.Scanner;

public interface IGenericOrder {
    /* ORDER
     * addOrderCheckOut(Scanner sc,List<Cart> cartList)
     * showAllOrder(List<Order> orderList)
     * findOrderById(Scanner sc,List<Order> orderList)
     * findByOrderStatus(Scanner sc,List<Order> orderList)
     * isExistInEnum(String status,List<Order> orderList)
     * orderDetailById(Scanner sc)
     * cancelOrder(Scanner sc, List<Order> order)
     * changeOrderStatus(Scanner sc)
     * filterByDate(Scanner sc, List<Order> orderList)
     * showDetailById(Scanner sc,List<Order> orderList)
     */void addOrderCheckOut(Scanner sc, List<Cart> cartList);
     void showAllOrder(List<Order> orderList);
     void findOrderById(Scanner sc,List<Order> orderList);
     void findByOrderStatus(Scanner sc,List<Order> orderList);
     boolean isExistInEnum(String status, List<Order> orderList);
     void orderDetailById(Scanner sc);
     void cancelOrder(Scanner sc, List<Order> order);
     void changeOrderStatus(Scanner sc);
     void filterByDate(Scanner sc, List<Order> orderList);
     void showDetailById(Scanner sc,List<Order> orderList);
}
