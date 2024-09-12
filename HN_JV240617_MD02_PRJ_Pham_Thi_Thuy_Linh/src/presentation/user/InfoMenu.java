package presentation.user;

import business.entity.User;
import business.service.UserService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.entity.User.displayDetails;
import static business.ultil.enumList.Common.displayUser;
import static presentation.user.AddressMenu.addressMenu;

public class InfoMenu {

    public static void infoMenu(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|---------------------------------INFO MENU-----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị thông tin                                               |");
            System.out.println("|        2. Quản lý thông tin                                                |");
            System.out.println("|        3. Thay đổi mật khẩu                                                |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                    currentUser = userList.get(currentIndex);
                    displayDetails(currentUser);
                    break;
                }
                case "2":{
                    menuEditInfo(sc);
                    break;
                }
                case "3":{
                    User.updatePassword(sc);
                    break;
                }
                case "4":{
                    flag=false;
                    break;
                }
                default:{
                    break;
                }
            }
        }while (flag);
    }

    private static void menuEditInfo(Scanner sc) {
        boolean flag = true;
            do {
                System.out.println("-------------------------------EDIT INFO MENU---------------------------------");
                System.out.println("|                                                                            |");
                System.out.println("|        1. Quản lý địa chỉ                                                  |");
                System.out.println("|        2. Thay đổi các thông tin khác                                      |");
                System.out.println("|        3. Thay đổi mật khẩu                                                |");
                System.out.println("|        4. Quay lại                                                         |");
                System.out.println("|                                                                            |");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":{
                        addressMenu(sc);
                        break;
                    }
                    case "2":{
                        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                        currentUser = userList.get(currentIndex);
                        currentUser.updateUserInfo(sc);
                        userList.set(currentIndex, currentUser);
                        IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                        break;
                    }
                    case "3":{
                        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
                        currentUser = userList.get(currentIndex);
                        User.updatePassword(sc);
                        userList.set(currentIndex, currentUser);
                        IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                        break;
                    }
                    case "4":{
                        flag=false;
                        break;
                    }
                    default:{
                        System.out.println("Please enter your choice");
                        break;
                    }
                }
            }while (flag);
    }
}
