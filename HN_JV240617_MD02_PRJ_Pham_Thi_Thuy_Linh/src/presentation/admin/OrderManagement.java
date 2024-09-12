package presentation.admin;

import business.entity.Order;
import business.entity.User;
import business.service.OrderService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.Data.currentUser;
import static business.ultil.enumList.Common.displayUser;

public class OrderManagement implements Serializable {
    public static void orderManagement (Scanner sc){

        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|--------------------------------ADMIN ORDER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh sách đơn hàng (phân trang)                         |");
            System.out.println("|        2. Tìm kiếm theo mã đơn hàng                                        |");
            System.out.println("|        3. Tìm kiếm theo trạng thái đơn hàng                                |");
            System.out.println("|        4. Xem chi tiết đơn hàng theo mã đơn hàng                           |");
            System.out.println("|        5. Thay đổi trạng thái đơn hàng                                     |");
            System.out.println("|        6. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    List<Order> orderList = IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    orderService.orderPagination(sc,orderList);
                    break;
                }
                case "2":{
                    List<Order> orderList = IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    orderService.findOrderById(sc,orderList);
                    break;
                }
                case "3":{
                    List<Order> orderList = IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    orderService.findByOrderStatus(sc,orderList);
                    break;
                }
                case "4":{
//                    List<Order> orderList = IOFile.readObjectFromFile(IOFile.PATH_ORDER);
                    orderService.orderDetailById(sc);
                    break;
                }
                case "5":{
                    orderService.changeOrderStatus(sc);
                    break;
                }
                case "6":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 6");
                    break;
                }
            }
        }while (flag);
    }
}
