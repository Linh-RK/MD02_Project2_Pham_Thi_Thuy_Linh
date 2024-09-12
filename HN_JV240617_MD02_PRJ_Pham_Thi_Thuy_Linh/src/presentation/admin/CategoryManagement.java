package presentation.admin;

import business.entity.User;
import business.service.CategoryService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.displayUser;

public class CategoryManagement {
    public static void categoryManagement (Scanner sc){

        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|-------------------------------ADMIN CATEGORY--------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị danh mục (phân trang)                                   |");
            System.out.println("|        2. Thêm mới danh mục                                                |");
            System.out.println("|        3. Sửa thông tin danh mục                                           |");
            System.out.println("|        4. Xóa danh mục                                                     |");
            System.out.println("|        5. Tìm kiếm danh mục                                                |");
            System.out.println("|        6. Sắp xếp theo chiều giảm dần hoặc tăng dần                        |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);

            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    categoryService.categoryPagination(sc);
                    break;
                }
                case "2":{
                    categoryService.addCategory(sc);
                    break;
                }
                case "3":{
                    categoryService.updateCategory(sc);
                    break;
                }
                case "4":{
                    categoryService.deleteCategory(sc);
                    break;
                }
                case "5":{
                    categoryService.searchCate(sc);
                    break;
                }
                case "6":{
                    categoryService.sortCate(sc);
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
