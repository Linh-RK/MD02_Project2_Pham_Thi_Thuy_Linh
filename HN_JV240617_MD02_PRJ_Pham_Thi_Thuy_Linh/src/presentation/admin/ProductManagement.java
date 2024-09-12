package presentation.admin;

import business.entity.Product;
import business.entity.User;
import business.service.ProductService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.Data.currentUser;
import static business.ultil.enumList.Common.displayUser;

public class ProductManagement implements Serializable {
    public static void productManagement(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|-------------------------------ADMIN PRODUCT---------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Hiển thị sản phẩm (phân trang)                                   |");
            System.out.println("|        2. Thêm mới sản phẩm                                                |");
            System.out.println("|        3. Sửa thông tin sản phẩm                                           |");
            System.out.println("|        4. Xóa sản phẩm                                                     |");
            System.out.println("|        5. Tìm kiếm sản phẩm                                                |");
            System.out.println("|        6. Sắp xếp theo giá hoặc tên theo chiều giảm dần hoặc tăng dần      |");
            System.out.println("|        7. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Please enter your choice");
            String choice = sc.nextLine();
            switch (choice) {
                case "1": {
                    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
                    productService.productPagination(sc,productList);
                    break;
                }
                case "2": {
                    productService.addProduct(sc);
                    break;
                }
                case "3": {
                    productService.updateProduct(sc);
                    break;
                }
                case "4": {
                    productService.deleteProduct(sc);
                    break;
                }
                case "5": {
                    productService.searchProduct(sc);
                    break;
                }
                case "6": {
                    sortMenu(sc);
                    break;
                }
                case "7": {
                    flag = false;
                    break;
                }
                default: {
                    break;
                }
            }
        } while (flag);
    }
    public static void sortMenu(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("-------------------------------PRODUCT SORT----------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Sắp xếp theo giá tăng dần                                        |");
            System.out.println("|        2. Sắp xếp theo giá giảm dần                                        |");
            System.out.println("|        3. Sắp xếp theo tên tăng dần                                        |");
            System.out.println("|        4. Sắp xếp theo tên giảm dần                                        |");
            System.out.println("|        5. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    sortPriceIncrease();
                    break;
                }
                case "2":{
                    sortPriceDecrease();
                    break;
                }
                case "3":{
                    sortNameIncrease();
                    break;
                }
                case "4":{
                    sortNameDecrease();
                    break;
                }
                case "5":{
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

    private static void sortPriceIncrease() {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println("Product list sorted by price increasing");
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            productList.stream().sorted(Comparator.comparing(Product::getProductPrice)).forEach(Product::display);
        }
    }

    private static void sortPriceDecrease() {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println("Product list sorted by price decreasing");
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            productList.stream().sorted(Comparator.comparing( Product::getProductPrice)).toList().reversed().forEach(Product::display);
        }
    }

    private static void sortNameIncrease() {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println("Product list sorted by name increasing");
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            productList.stream().sorted(Comparator.comparing( Product::getProductName)).forEach(Product::display);
        }
    }

    private static void sortNameDecrease() {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            System.out.println("Product list sorted by name decreasing");
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            productList.stream().sorted(Comparator.comparing( Product::getProductName)).toList().reversed().forEach(Product::display);
        }
    }
}

