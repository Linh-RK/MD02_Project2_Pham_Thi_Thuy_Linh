package business.A0_Test;

import business.entity.Order;
import business.entity.Pagination;
import business.entity.Product;
import business.ultil.enumList.IOFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static business.ultil.enumList.Common.inputNum;

public class PaginationExample {
        public static <T> Pagination<T> paginate(List<T> allItems, int currentPage, int pageSize) {
            int totalItems = allItems.size();
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, totalItems);

            List<T> pageItems = new ArrayList<>();
            if (start < totalItems) {
                pageItems = allItems.subList(start, end);
            }
            return new Pagination<>(pageItems, totalItems, currentPage, pageSize);
        }

        public static void main(String[] args) {
            // Example data
            List<Product> productList = new ArrayList<>();
//            for (int i = 1; i <= 100; i++) {
//                data.add("Item " + i);
//            }
//            System.out.print("["+1+"]   ");
//            int pageSize = 5; // Number of items per page
//            int currentPage = 1; // Current page
            // Example usage
//            Pagination<Product> pagination = paginate(productList, currentPage, pageSize);
//            for (Product item : pagination.getItems()) {
//                System.out.println(item);
//            }
            /**
             * 1. hien page 1
             * trang
             * chon trang
             * doi current
             * display
             * do_while
             */
            Scanner sc = new Scanner(System.in);
            boolean flag = true;
            int pageSize = 5; // Number of items per page
            int currentPage = 1;
            Pagination<Product> pagination = paginate(productList, currentPage, pageSize);
            for (Product product : pagination.getItems()) {
                product.display();
            }
            for (int i = 0; i < productList.size()/pageSize; i++) {
                System.out.print("["+i+1+"]   ");
            }
            System.out.println(productList.size()/pageSize+1+"[Back]");

            System.out.println("Enter page you want to see");
            int pageNum = 1;
            do{
                pageNum = inputNum(sc);
                if(pageNum == productList.size()/pageSize+1){
                    flag = false;
                }else if(pageNum<productList.size()/pageSize+1){
                    pagination = paginate(productList, pageNum, pageSize);
                    for (Product product : pagination.getItems()) {
                        product.display();
                    }
                }else {
                    System.err.println("Please enter a valid page number.");
                }
            }while (flag);

        }



}
