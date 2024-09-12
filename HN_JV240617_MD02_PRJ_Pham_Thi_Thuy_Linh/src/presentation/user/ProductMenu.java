package presentation.user;

import business.entity.User;
import business.service.ProductService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import presentation.admin.ProductManagement;

import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.displayUser;
import static presentation.Main_Menu.commonProductMenu;

public class ProductMenu {
    public static void productMenu(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|-------------------------------PRODUCT MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị sản phẩm                                                |");
            System.out.println("|        2. Tìm kiếm sản phẩm                                                |");
            System.out.println("|        3. Sắp xếp                                                          |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
//                    productService.showAllProduct(sc);
                    commonProductMenu(sc);
                    break;
                }
                case "2":{
                    productService.searchProduct(sc);
                    break;
                }
                case "3":{
                    ProductManagement.sortMenu(sc);
                    break;
                }
                case "4":{
                    flag=false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from the menu");
                    break;
                }
            }
        }while (flag);
    }
}
