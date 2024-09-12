package presentation.admin;

import business.entity.User;
import business.service.UserService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.displayUser;

public class UserManagement implements Serializable {
    public static void userManagement(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|---------------------------------ADMIN USER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị người dùng (phân trang)                                 |");
            System.out.println("|        2. Sửa thay đổi trạng thái                                          |");
            System.out.println("|        3. Xóa người dùng                                                   |");
            System.out.println("|        4. Tìm kiếm người dùng                                              |");
            System.out.println("|        5. Sắp xếp theo tên theo chiều giảm dần hoặc tăng dần               |");
            System.out.println("|        6. Danh sách quyền của người dùng                                   |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    userService.productPagination(sc);
                    break;
                }
                case "2":{
                    userService.changeUserStatus(sc);
                    break;
                }
                case "3":{
                    userService.deleteUser(sc);
                    break;
                }
                case "4":{
                    userService.searchUser(sc);
                    break;
                }
                case "5":{
                   sortUserMenu(sc);
                    break;
                }
                case "6":{
                    userService.listRole(sc);
                    break;
                }
                case "7":{
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

    private static void sortUserMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("----------------------------------ADMIN USER----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Sắp xếp theo tên tăng dần                                        |");
            System.out.println("|        2. Sắp xếp theo tên giảm dần                                       |");
            System.out.println("|        3. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    userService.sortUserByNameIncrease();
                    break;
                }
                case "2":{
                    userService.sortUserByNameDecrease();
                    break;
                }
                case "3":{
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
