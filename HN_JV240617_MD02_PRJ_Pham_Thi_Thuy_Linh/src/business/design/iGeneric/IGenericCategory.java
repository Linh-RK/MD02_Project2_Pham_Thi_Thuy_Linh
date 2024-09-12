package business.design.iGeneric;

import business.entity.Cart;
import business.entity.Category;

import java.util.Scanner;

public interface IGenericCategory {
    /* CATEGORY
     * addCategory(Scanner sc)
     * showAllCategory()
     * updateCategory(Scanner sc)
     * deleteCategory(Scanner sc)
     * searchCate(Scanner sc)
     * sortCate(Scanner sc)
     */
    void addCategory(Scanner sc);
    void showAllCategory();
    void updateCategory(Scanner sc);
    void deleteCategory(Scanner sc);
    void searchCate(Scanner sc);
    void sortCate(Scanner sc);
}
