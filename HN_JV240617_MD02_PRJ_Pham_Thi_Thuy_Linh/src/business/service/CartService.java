package business.service;

import business.design.iGeneric.IGenericCart;
import business.entity.Cart;
import business.entity.Product;
import business.entity.User;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class CartService implements Serializable,IGenericCart {
    @Override
    public void showAllCart() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser= userList.get(currentIndex);
        if(currentUser.getCartList()==null||currentUser.getCartList().isEmpty()) {
          System.err.println("Cart is empty");
        }else{
          System.out.println("------------------------------------------------------------------");
          System.out.printf(ConsoleColors.PINK +"| %-5s | %-10s | %-5s | %-15s | %-15s |\n"+ConsoleColors.RESET , "ID", "Product", "Qty", "Price", "Total");
          currentUser.getCartList().forEach(Cart::displayCart);
          System.out.println("------------------------------------------------------------------");
        }
    }
    public void showAllCart2() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser= userList.get(currentIndex);
        if(currentUser.getCartList()==null||currentUser.getCartList().isEmpty()) {
            System.err.println("Cart is empty");
        }else{
            System.out.println("------------------------------------------------------------------");
            System.out.printf(ConsoleColors.PINK +"| %-5s | %-10s | %-5s | %-15s | %-15s |\n"+ConsoleColors.RESET , "ID", "Product", "Qty", "Price", "Total");
            currentUser.getCartList().forEach(Cart::displayCart2);
            System.out.println("------------------------------------------------------------------");
        }
    }


    @Override
    public void addToCart(Scanner sc) {
       List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
       List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
       currentUser= userList.get(currentIndex);
       Cart cartNew = new Cart();
       int productID;
        productService.productPagination(sc,productList);
       System.out.println(ConsoleColors.PINK +"Enter product ID you want to add to cart: "+ConsoleColors.RESET );
       do {
           productID = inputNum(sc);
          int finalProductID = productID;
          if(productList.stream().noneMatch(p->p.getProductId()== finalProductID)) {
                System.err.println("Product ID does not match");
          }else {
    //         ton tai id nhap vao
             int finalProductID1 = productID;
             Product productAdd = productList.stream().filter(p->p.getProductId()== finalProductID1).toList().getFirst();
    //         if product want to add to cart not in cart
             int quantity;
             if(currentUser.getCartList().stream().noneMatch(e->e.getProductInCart().getProductId()==finalProductID1)){
                 quantity= inputQty(sc,0,productAdd.getProductStock());
                cartNew.setProductInCart(productAdd);
                cartNew.setQty(quantity);
                currentUser.getCartList().add(cartNew);
                userList.set(currentIndex, currentUser);
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
             }else{
    //            if product want to add to cart already exist in cart
                Cart cartUpdate = currentUser.getCartList().stream().filter(e->e.getProductInCart().getProductId()==finalProductID1).toList().getFirst();
                quantity= inputQty(sc,cartUpdate.getQty(), productAdd.getProductStock());
                cartUpdate.setQty(cartUpdate.getQty()+quantity);
                int index = currentUser.getCartList().indexOf(cartUpdate);
                currentUser.getCartList().set(index,cartUpdate);
                userList.set(currentIndex,currentUser);
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
             }
             System.out.println(ConsoleColors.PINK+ "Added product to the cart"+ConsoleColors.RESET );
             showAllCart();
             return;
          }
       }while(true);
    }


    public int inputQty(Scanner sc, int i, int productStock) {
          System.out.println(ConsoleColors.PINK +"Enter product Qty: "+ConsoleColors.RESET );
        do {
          int addQty = inputNum(sc);
          if(addQty + i> productStock){
             System.err.println("SORRY! We don't have enough product stock");
          }else{
             return addQty;
          }
        }while(true);
    }

    @Override
    public void changeQtyProductInCart(Scanner sc) {
       List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
       currentUser= userList.get(currentIndex);
       if(currentUser.getCartList().isEmpty()) {
          System.out.println("Cart is empty");
       }else {
          System.out.println(ConsoleColors.PINK+ "Enter product ID you want to edit: "+ConsoleColors.RESET );
          int productID = inputNum(sc);
          if (currentUser.getCartList().stream().noneMatch(c -> c.getProductInCart().getProductId() == productID)) {
             System.err.println("Product does not exist in cart");
          } else {
             Cart productOldCart = currentUser.getCartList().stream().filter(c -> c.getProductInCart().getProductId() == productID).findFirst().get();
             int indexProduct = currentUser.getCartList().indexOf(productOldCart);
    //              Display
             System.out.println("Product you want to edit: ");
             System.out.println("------------------------------------------------------------------");
             System.out.printf(ConsoleColors.BLUE +"| %-5s | %-10s | %-5s | %-15s | %-15s |\n"+ConsoleColors.RESET , "ID", "Product", "Qty", "Price", "Total");
             productOldCart.displayCart();
             System.out.println("------------------------------------------------------------------");
             System.out.println(ConsoleColors.GREEN +"Enter new quantity of product you want to edit: "+ConsoleColors.RESET );
             int quantity = inputNum(sc);

             System.out.println(ConsoleColors.PINK+ "New cart:"+ConsoleColors.RESET );
             productOldCart.setQty(quantity);
             currentUser.getCartList().set(indexProduct, productOldCart);
             userList.set(currentIndex, currentUser);
             IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
             System.out.println(ConsoleColors.GREEN +"Edited successfully"+ConsoleColors.RESET );
             showAllCart();
          }
       }
    }

    @Override
    public void deleteProductInCart(Scanner sc) {
       List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
       currentUser= userList.get(currentIndex);
       showAllCart();
       System.out.println(ConsoleColors.RESET +"Enter product ID you want to delete: "+ConsoleColors.RESET );
       int productID = inputNum(sc);
       if(currentUser.getCartList().stream().noneMatch(c->c.getProductInCart().getProductId()==productID)) {
          System.err.println("Product does not exist in cart");
       }else{
          Cart productOldCart = currentUser.getCartList().stream().filter(c->c.getProductInCart().getProductId()==productID).toList().getFirst();
          int indexProduct =currentUser.getCartList().indexOf(productOldCart);
          currentUser.getCartList().remove(productOldCart);
          userList.set(currentIndex,currentUser);
          IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
          System.out.println(ConsoleColors.RESET+ "Delete successfully"+ConsoleColors.RESET );
          showAllCart();
       }
    }

    @Override
    public void clearCart(Scanner sc) {
       List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
       currentUser= userList.get(currentIndex);
       if(currentUser.getCartList().isEmpty()) {
          System.err.println("Cart is empty");
       }else{
          userList.stream().filter(e->e.getUserId()== currentUser.getUserId()).findFirst().get().getCartList().clear();
          IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
          showAllCart();
       }
    }
}
