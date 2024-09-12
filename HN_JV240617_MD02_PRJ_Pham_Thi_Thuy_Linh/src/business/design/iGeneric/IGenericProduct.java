package business.design.iGeneric;

import business.entity.Cart;
import business.entity.Product;

import java.util.Scanner;

public interface IGenericProduct {
    /* PRODUCT
     *showAllProduct()
     *filterProductByCate(Scanner sc)
     *searchProductById(Scanner sc)
     *top5NewProduct(Scanner sc)
     *addProduct(Scanner sc)
     * onSaleProduct(Scanner sc)
     * updateProduct(Scanner sc)
     * deleteProduct(Scanner sc)
     * searchProduct(Scanner sc)
     */
    void showAllProduct(Scanner sc);
    void filterProductByCate(Scanner sc);
     void searchProductById(Scanner sc);
     void top5NewProduct(Scanner sc);
     void addProduct(Scanner sc);
     void onSaleProduct(Scanner sc);
     void updateProduct(Scanner sc);
     void deleteProduct(Scanner sc);
     void searchProduct(Scanner sc);
}
