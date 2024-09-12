package business.service;

import business.entity.Product;
import business.entity.User;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class WishListService implements Serializable {
    public static void showAllWishList(){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        if(currentUser.getWishList().isEmpty()){
            System.err.println("WishList is empty");
        }else {
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n ", "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            currentUser.getWishList().forEach(Product::display);
        }
    }
    public static void addNewProductIWishList(Scanner sc){
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser =  userList.get(currentIndex);
        productService.productPagination(sc,productList);
        System.out.println("Enter product ID you want to add: ");
        int id = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("The product ID you want to add does not exist");
        }else{
            if(currentUser.getWishList().stream().anyMatch(e->e.getProductId()==id)){
                System.err.println("The product ID you want to add already exists");
            }else {
                Product product = productList.stream().filter(e -> e.getProductId() == id).findFirst().get();
                currentUser.getWishList().add(product);
                userList.set(currentIndex, currentUser);
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                System.out.println(ConsoleColors.GREEN+"Add product in wish list successfully"+ConsoleColors.RESET);
            }
        }
        showAllWishList();
    }
    public static void deleteProductInWisList(Scanner sc){
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser =  userList.get(currentIndex);
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(currentUser.getWishList().size());///
        System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        currentUser.getWishList().forEach(Product::display);
        System.out.println(ConsoleColors.PINK+"Enter product ID you want to delete: "+ConsoleColors.RESET);
        int id = inputNum(sc);
        if(currentUser.getWishList().stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("The product ID you want to delete does not exist");
        }else{
            Product product = currentUser.getWishList().stream().filter(e->e.getProductId()==id).findFirst().get();
            currentUser.getWishList().remove(product);
            userList.set(currentIndex,currentUser);
            IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            System.out.println(ConsoleColors.GREEN+"Delete product in wish list successfully"+ConsoleColors.RESET);
        }
        showAllWishList();
    }
}
