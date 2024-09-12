package presentation;

import business.entity.*;
import business.service.ProductService;
import business.ultil.enumList.Common;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.Role;
import presentation.admin.ProductManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.Data.userList;
import static business.ultil.enumList.Common.inputString;
import static presentation.admin.AdminMenu.Admin_Menu;
import static presentation.user.UserMenu.UserMenuDisplay;

public class Main_Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------MENU-------------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Đăng nhập                                                        |");
            System.out.println("|        2. Đăng ký                                                          |");
            System.out.println("|        3. Thông tin sản phẩm                                               |");
            System.out.println("|        4. Quên mật khẩu                                                    |");
            System.out.println("|        5. Thoát                                                            |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    logIn(sc);
                    break;
                }
                case "2":{
                    user.signIn(sc);
                    break;
                }
                case "3":{
                    commonProductMenu(sc);
                    break;
                }
                case "4":{
                    forgotPassword(sc);
                    break;
                }
                case "5":{
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Please enter a choice form 1 -> 5");
                    break;
                }
            }
        }while (true);
    }

    public static void commonProductMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("--------------------------------PRODUCT MENU----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Chi tiết thông tin sản phẩm theo id                              |");
            System.out.println("|        2. Danh sách sản phẩm theo danh mục                                 |");
            System.out.println("|        3. Danh sách sản phẩm mới                                           |");
            System.out.println("|        4. Danh sách sản phẩm được bán                                      |");
            System.out.println("|        5. Tìm kiếm sản phẩm theo tên hoặc mô tả                            |");
            System.out.println("|        6. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    productService.searchProductById(sc);
                    break;
                }
                case "2":{
                    productService.filterProductByCate(sc);
                    break;
                }
                case "3":{
                    productService.top5NewProduct(sc);
                    break;
                }
                case "4":{
                    productService.onSaleProduct(sc);
                    break;
                }
                case "5":{
                    productService.searchProduct(sc);
                    break;
                }
                case "6":{
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


    private static LocalDate currentdate() {
        LocalDate today = LocalDate.now( );
        System.out.println(today);
        return today;
    }
    private static void logIn(Scanner sc) {

        System.out.println(ConsoleColors.PINK +"Please enter your email" + ConsoleColors.RESET);
        String email = inputString(sc);
        System.out.println(ConsoleColors.PINK +"Please enter your password"+ ConsoleColors.RESET);
        String password = inputString(sc);
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.stream().noneMatch(e->e.getUserEmail().equals(email))){
            System.err.println("Email or password incorrect");
        }else{
            if(!userList.stream().filter(e->e.getUserEmail().equals(email)).findFirst().get().getUserPassword().equals(password)){
                System.err.println("Email or password incorrect");
            } else {

                currentUser = userList.get(currentIndex);
                currentUser=userList.stream().filter(e->e.getUserEmail().equals(email)).findFirst().get();
                for (int i = 0; i < userList.size(); i++) {
                    if(userList.get(i).getUserEmail().equals(email)){
                        currentIndex=i;
                        break;
                    }
                }
//                Nho luu currentIndex
                System.out.println(ConsoleColors.GREEN+"Login Successful"+ ConsoleColors.RESET);
                if(currentUser.getRole()==Role.ADMIN){
                    Admin_Menu(sc);
                }else if(currentUser.getRole()==Role.USER){
                    if(!currentUser.getUserStatus()){
                        System.err.println("Your account has been locked");
                    }else {
                        UserMenuDisplay(sc);
                    }
                }
            }
        }
    }

    private static void forgotPassword(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser =userList.get(currentIndex);

        System.out.println(ConsoleColors.PINK+"Please enter your email:"+ConsoleColors.RESET);
        String email = inputString(sc);
        System.out.println(ConsoleColors.PINK+"Please enter your phone number:"+ConsoleColors.RESET);
        String phoneNumber = inputString(sc);

        if(currentUser.getUserEmail().equals(email)){
            System.err.println("Email or password incorrect");
        }else if(currentUser.getUserPhoneNumber().equals(phoneNumber)){
            System.err.println("Email or password incorrect");
        } else{
            System.out.println(ConsoleColors.PINK+"Please enter your new password:"+ConsoleColors.RESET);
            String password = User.inputUserPassword(sc);
            System.out.println(ConsoleColors.PINK+"Please enter your new password again:"+ConsoleColors.RESET);
            String passwordConfirm = inputString(sc);
            if(!password.equals(passwordConfirm)){
                System.err.println("Passwords do not match");
            }else{
                currentUser.setUserPassword(password);
                userList.set(currentIndex,currentUser);
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            }
        }
    }
}
