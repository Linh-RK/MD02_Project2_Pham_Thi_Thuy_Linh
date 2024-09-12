package business.service;

import business.design.iGeneric.IGenericCategory;
import business.entity.Cart;
import business.entity.Category;
import business.entity.Pagination;
import business.entity.Product;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.categoryList;
import static business.Data.productList;
import static business.ultil.enumList.Common.inputNum;
import static business.ultil.enumList.Common.inputString;

public class CategoryService implements IGenericCategory, Serializable{
    @Override
    public void addCategory(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        System.out.println(ConsoleColors.PINK+"Enter the number of categories you want to add"+ ConsoleColors.RESET);
        int number = inputNum(sc);
        for (int i = 0; i < number; i++) {
            System.out.println("Category " + (i + 1));
            Category category = new Category();
            category.inputCategory(sc);
            categoryList.add(category);
            IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
        }
        System.out.println(ConsoleColors.GREEN+"Added Category successfully"+ ConsoleColors.RESET);
        categoryPagination(sc);
    }
    public void categoryPagination(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
        boolean flag = true;
        int pageSize = 5; // Number of items per page
        int currentPage = 1;
        Pagination<Category> pagination = PaginationService.paginate(categoryList, currentPage, pageSize);
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.PINK+"| %-5s | %-15s | %-10s | %-35s |\n"+ ConsoleColors.RESET,"ID","Category","Status","Description");
        for (Category category : pagination.getItems()) {
            category.displayCategory();
        }
        System.out.println("------------------------------------------------------------------------------");

        for (int i = 1; i <= (int)Math.ceil((double) categoryList.size()/pageSize)+1; i++) {
            System.out.print("["+i+"]   ");
        }
        System.out.println("["+((int)Math.ceil((double) categoryList.size()/pageSize)+1)+": Back]");

        int pageNum = 1;
        do{
            System.out.println("Enter page you want to see");
            pageNum = inputNum(sc);
            if(pageNum == (int)Math.ceil((double) categoryList.size()/pageSize)+1){
                flag = false;
            }else if(pageNum<(int)Math.ceil((double) categoryList.size()/pageSize)+1){
                pagination = PaginationService.paginate(categoryList, pageNum, pageSize);
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.PINK+"| %-5s | %-15s | %-10s | %-35s |\n"+ ConsoleColors.RESET,"ID","Category","Status","Description");
                for (Category category : pagination.getItems()) {
                    category.displayCategory();
                }
                System.out.println("------------------------------------------------------------------------------");
                for (int i = 1; i <= (int)Math.ceil((double) categoryList.size()/pageSize)+1; i++) {
                    System.out.print("["+i+"]   ");
                }
            }else {
                System.err.println("Please enter a valid page number.");
            }
        }while (flag);
    }
    @Override
    public void showAllCategory() {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        if(categoryList.isEmpty()){
            System.err.println("Category list is empty");
        }else{
            System.out.println("------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.PINK+"| %-5s | %-15s | %-10s | %-35s |\n"+ ConsoleColors.RESET,"ID","Category","Status","Description");
            categoryList.forEach(Category::displayCategory);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    @Override
    public void updateCategory(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        System.out.println(ConsoleColors.PINK+"Enter the category ID you want to update"+ ConsoleColors.RESET);
        int id = inputNum(sc);
        if(categoryList.stream().noneMatch(e->e.getCateId()==id)){
            System.err.println("Category ID does not exist");
        }else {
            categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get().inputUpdateCategory(sc);
            categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get().setCateId(id);
            IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
            System.out.println(ConsoleColors.GREEN+"Update Category successfully"+ ConsoleColors.RESET);
            categoryPagination(sc);
        }
    }

    @Override
    public void deleteCategory(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        System.out.println(ConsoleColors.PINK+"Enter the category ID you want to delete"+ConsoleColors.RESET);
        int id = inputNum(sc);
        if (categoryList.stream().noneMatch(e -> e.getCateId() == id)) {
            System.err.println("Category ID does not exist");
        } else {
            if (productList.stream().anyMatch(e -> e.getProductCate().getCateId() == id)) {
                System.err.println("Category have product inside, cannot delete");
            } else {
                categoryList.remove(categoryList.stream().filter(e -> e.getCateId() == id).findFirst().get());
                IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
                System.out.println(ConsoleColors.GREEN+"Update Category successfully"+ ConsoleColors.RESET);
                categoryPagination(sc);
            }
        }
    }

    @Override
    public void searchCate(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        System.out.println(ConsoleColors.PINK+"Please enter search keys:"+ConsoleColors.RESET);
            String key = inputString(sc);
            if(categoryList.stream().noneMatch(e->e.getCateName().contains(key))){
                System.err.println("No result match");
            }else{
                System.out.println("Result:");
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.BLUE+"| %-5s | %-15s | %-10s | %-35s |\n"+ConsoleColors.RESET,"ID","Category","Status","Description");
                categoryList.stream().filter(e->e.getCateName().contains(key)).toList().forEach(Category::displayCategory);
                System.out.println("------------------------------------------------------------------------------");
            }
    }

    @Override
    public void sortCate(Scanner sc) {
        List<Category> categoryList= IOFile.readObjectFromFile( IOFile.PATH_CATEGORY);
        System.out.println(ConsoleColors.GREEN+"Result sort by name increasing order:"+ConsoleColors.RESET);
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.BLUE+"| %-5s | %-15s | %-10s | %-35s |\n"+ConsoleColors.RESET,"ID","Category","Status","Description");
        categoryList.stream().sorted(Comparator.comparing(Category::getCateName)).forEach(Category::displayCategory);
        System.out.println("------------------------------------------------------------------------------");
    }
}
