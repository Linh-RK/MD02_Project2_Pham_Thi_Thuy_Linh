package presentation.user;

import business.service.WishListService;
import business.ultil.enumList.ConsoleColors;

import java.util.Scanner;

import static business.Data.currentUser;
import static business.ultil.enumList.Common.displayUser;

public class WishListMenu {
    public static void wishListMenu(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|-------------------------------WISHLIST MENU---------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh sách                                               |");
            System.out.println("|        2. Thêm mới sản phẩm yêu thích                                      |");
            System.out.println("|        3. Xóa sản phẩm yêu thích                                           |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    WishListService.showAllWishList();
                    break;
                }
                case "2":{
                    WishListService.addNewProductIWishList(sc);
                    break;
                }
                case "3":{
                    WishListService.deleteProductInWisList(sc);
                    break;
                }
                case "4":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 5");
                    break;
                }
            }
        }while (flag);
    }
}
