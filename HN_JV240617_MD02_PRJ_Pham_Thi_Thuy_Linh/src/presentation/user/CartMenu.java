package presentation.user;

import business.entity.User;
import business.service.CartService;
import business.service.OrderService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.displayUser;

public class CartMenu {
    public static void cartMenu(Scanner sc) {

        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|---------------------------------CART MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh sách giỏ hàng                                      |");
            System.out.println("|        2. Thêm mới sản phẩm vào giỏ hàng                                   |");
            System.out.println("|        3. Thay đổi số lượng sản phẩm trong giỏ hàng                        |");
            System.out.println("|        4. Xóa sản phẩm trong giỏ hàng                                      |");
            System.out.println("|        5. Xóa toàn bộ sản phẩm trong giỏ hàng                              |");
            System.out.println("|        6. Đặt hàng                                                         |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    cartService.showAllCart();
                    break;
                }
                case "2":{
                    cartService.addToCart(sc);
                    break;
                }
                case "3":{
                    cartService.changeQtyProductInCart(sc);
                    break;
                }
                case "4":{
                    cartService.deleteProductInCart(sc);
                    break;
                }
                case "5":{
                    cartService.clearCart(sc);
                    break;
                }
                case "6":{
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser= userList.get(currentIndex);
                    orderService.addOrderCheckOut(sc,currentUser.getCartList());
                    break;
                }
                case "7":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 7");
                    break;
                }
            }
        }while (flag);
    }
}
