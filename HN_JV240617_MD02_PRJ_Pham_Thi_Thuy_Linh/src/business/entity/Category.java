package business.entity;

import business.ultil.enumList.ConsoleColors;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Scanner;

import static business.Data.categoryList;
import static business.ultil.enumList.Common.inputStatus;
import static business.ultil.enumList.Common.inputString;

public class Category implements Serializable {
    private int cateId;
    private String cateName;
    private String  cateDescription;
    private boolean cateStatus;

    public Category() {
    }

    public Category(int cateId, String cateName, boolean cateStatus,String cateDescription) {

        this.cateId = cateId;
        this.cateName = cateName;
        this.cateStatus = cateStatus;
        this.cateDescription = cateDescription;
    }

    public String getCateDescription() {
        return cateDescription;
    }

    public void setCateDescription(String cateDescription) {
        this.cateDescription = cateDescription;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public boolean isCateStatus() {
        return cateStatus;
    }

    public void setCateStatus(boolean cateStatus) {
        this.cateStatus = cateStatus;
    }

    public void inputCategory(Scanner sc) {
        this.cateId = autoCateId(sc);
        this.cateName = inputCateName(sc);
        this.cateStatus = true;
        System.out.println(ConsoleColors.PINK+"Enter Category description: "+ConsoleColors.RESET);
        this.cateDescription = inputString(sc);
    }

    public void inputUpdateCategory(Scanner sc) {
        this.cateName = inputCateName(sc);
        System.out.println(ConsoleColors.PINK+"Enter Category Status: "+ConsoleColors.RESET);
        this.cateStatus = inputStatus(sc);
        System.out.println(ConsoleColors.PINK+"Enter Category Description: "+ConsoleColors.RESET);
        this.cateDescription = inputString(sc);
    }

    private int autoCateId(Scanner sc) {
        return categoryList.stream().map(Category::getCateId).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+1;
    }

    private String inputCateName(Scanner sc) {
        System.out.println(ConsoleColors.PINK+"Enter the name of the category: "+ConsoleColors.RESET);
        do {
            String input = sc.nextLine();
            if(input.isBlank()) {
                System.err.println("Data cannot be empty");
            }else {
                if(categoryList.stream().anyMatch(e->e.getCateName().equalsIgnoreCase(input))) {
                    System.err.println("Category already exists");
                }else{
                    return input;
                }
            }
        }while(true);
    }

    public void displayCategory() {
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.BLUE+"| %-5d | %-15s | %-10s | %-35s |\n"+ConsoleColors.RESET,cateId,cateName,cateStatus?"Active":"Block",cateDescription);

    }
}
