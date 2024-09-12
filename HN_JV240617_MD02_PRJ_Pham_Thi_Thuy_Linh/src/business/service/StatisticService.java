package business.service;

import business.entity.*;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static business.ultil.enumList.Common.inputDate;

public class StatisticService {

    public static void revenueByDate(Scanner sc) {
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println(ConsoleColors.PINK+"Enter the start date you want to search (yyyy-mm-dd)"+ConsoleColors.RESET);
            LocalDate startDate = inputDate(sc);
            System.out.println(ConsoleColors.PINK+"Enter the end date you want to search (yyyy-mm-dd)"+ConsoleColors.RESET);
            LocalDate endDate = inputDate(sc);

            List<Order> resultOrder = orderList.stream().filter(e -> e.getOrderCreateDate().isAfter(startDate) && e.getOrderCreateDate().isBefore(endDate)).toList();
            if(resultOrder.isEmpty()){
                System.err.println("None order found");
            }else {
                double revenue = 0;
                for (int i = 0; i < resultOrder.size(); i++) {
                    if(resultOrder.get(i).getOrderStatus().equals(OrderStatus.SUCCESS)){
                        revenue+= resultOrder.get(i).getOrderTotal();
                    }
                }
                System.out.println("Result");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.BLUE+"| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n"+ConsoleColors.RESET, "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                resultOrder.forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Total Order Count: " + revenue);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    public static void detailStatistic() {
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n"+ConsoleColors.RESET,"Month", "Total Order","Canceled Order", "Processing Order","Success Order","Revenue");
            System.out.println("------------------------------------------------------------------------------------");
            int totalOrder = 0;
            int canceledOrder = 0;
            int processingOrder = 0;
            int successOrder = 0;
            Double revenue =  0.0;
            for (int i = 0; i < 12; i++) {
                totalOrder = orderList.size();
                canceledOrder = orderList.stream().filter(e ->
                        e.getOrderStatus().equals(OrderStatus.CANCEL) || e.getOrderStatus().equals(OrderStatus.DENIED)
                ).toList().size();
                processingOrder = orderList.stream().filter(e ->
                        e.getOrderStatus().equals(OrderStatus.WAITING) || e.getOrderStatus().equals(OrderStatus.DELIVERY)
                ).toList().size();
                successOrder = orderList.stream().filter(e -> e.getOrderStatus().equals(OrderStatus.SUCCESS)).toList().size();
                List<Double> listTotal = orderList.stream().filter(e -> e.getOrderStatus().equals(OrderStatus.SUCCESS)).toList().stream().map(Order::getOrderTotal).toList();
                for (Double total : listTotal) {
                    revenue += total;
                }
                System.out.printf(ConsoleColors.PINK+"| %-20d | %-20d | %-20d | %-20d | %-20d | %-20.0f |\n"+ConsoleColors.RESET, i + 1, totalOrder, canceledOrder, processingOrder, successOrder, revenue);
                System.out.println("------------------------------------------------------------------------------------");
            }
        }
    }

    public static void commonStatistic() {
    List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        double revenue = 0;
        for (Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.SUCCESS)) {
                revenue += order.getOrderTotal();
            }
        }
        System.out.println("------------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.BLUE +"| %-20s | %-20s | %-20s | %-20s | %-20s |\n"+ConsoleColors.RESET,"Total Category","Total Product","Total User","Total Order","Revenue");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s |\n",categoryList.size(),productList.size(),userList.size(),orderList.size(),revenue);
        System.out.println("------------------------------------------------------------------------------------");
    }
}
