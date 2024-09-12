package business.design.iGeneric;

import java.util.Scanner;

public interface IGenericCart{
    /* CART
     * showAllCart()
     * addToCart(Scanner sc)
     * inputQty(Scanner sc, int i, int productStock)
     * changeQtyProductInCart(Scanner sc)
     * deleteProductInCart(Scanner sc)
     * clearCart(Scanner sc)
     */
     void showAllCart();
     void addToCart(Scanner sc);
     void changeQtyProductInCart(Scanner sc);
     void deleteProductInCart(Scanner sc);
     void clearCart(Scanner sc);

}
