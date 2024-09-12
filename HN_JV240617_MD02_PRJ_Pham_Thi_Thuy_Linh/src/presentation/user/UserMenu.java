package presentation.user;

import business.entity.User;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.currentIndex;
import static business.Data.currentUser;
import static business.ultil.enumList.Common.displayUser;
import static presentation.user.CartMenu.cartMenu;
import static presentation.user.InfoMenu.infoMenu;
import static presentation.user.OrderMenu.orderMenu;
import static presentation.user.ProductMenu.productMenu;
import static presentation.user.WishListMenu.wishListMenu;

public class UserMenu {
    public static void UserMenuDisplay(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|---------------------------------USER MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Thông tin sản phẩm                                               |");
            System.out.println("|        2. Quản lý thông tin cá nhân                                        |");
            System.out.println("|        3. Quản lý giỏ hàng                                                 |");
            System.out.println("|        4. Quản lý đơn hàng                                                 |");
            System.out.println("|        5. Quản lý danh sách yêu thích                                      |");
            System.out.println("|        6. Đăng xuất                                                        |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    productMenu(sc);
                    break;
                }
                case "2":{
                    infoMenu(sc);
                    break;
                }
                case "3":{
                    cartMenu(sc);
                    break;
                }
                case "4":{
                    orderMenu(sc);
                    break;
                }
                case "5":{
                    wishListMenu(sc);
                    break;
                }
                case "6":{
                    currentUser = null;
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
