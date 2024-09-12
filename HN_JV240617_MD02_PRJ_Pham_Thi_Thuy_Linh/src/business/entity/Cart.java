package business.entity;


import business.service.ProductService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class Cart  implements Serializable {
    private int userId;
    private Product productInCart;
    private int qty;
    private double total;

    public Cart() {
    }

    public Cart(int userId,Product productInCart, int qty,double total) {
        this.productInCart = productInCart;
        this.qty = qty;
        this.total = this.productInCart.getProductPrice()*this.qty;
    }

    public Product getProductInCart() {
        return productInCart;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setProductInCart(Product productInCart) {
        this.productInCart = productInCart;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public void inputCart(Scanner sc) {
        this.productInCart = inputProductInCart(sc);
        this.qty = inputNum(sc);
    }

    private Product inputProductInCart(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        productService.productPagination(sc,productList);
//        productService.showAllProduct(sc);
        System.out.println(ConsoleColors.PINK+"Enter product id from list before: "+ConsoleColors.RESET);
        do {
            int input = inputNum(sc);
            if(productList.stream().anyMatch(product -> product.getProductId() == input)) {
                return productList.stream().filter(product -> product.getProductId() == input).findAny().get();
            }else{
                System.err.println("Product id is not exist. Please try again.");
            }
        }while (true);
    }

    
    public void displayCart(){
        System.out.println("------------------------------------------------------------------");
        System.out.printf( "| %-5d | %-10s | %-5d | %-15f | %-15f |\n",this.productInCart.getProductId(), this.productInCart.getProductName(), this.qty, this.productInCart.getProductPrice(), this.productInCart.getProductPrice()*qty);
//        System.out.println("-------------------------------------------------------------------------");
    }
    public void displayCart2(){
        System.out.println("------------------------------------------------------------------");
        System.out.printf( "| %-5d | %-15s | %-7d | %-15f | %-15f |\n",this.productInCart.getProductId(), this.productInCart.getProductName(), this.qty, this.productInCart.getProductPrice(), this.productInCart.getProductPrice()*qty);
//        System.out.println("-------------------------------------------------------------------------");
    }

}
