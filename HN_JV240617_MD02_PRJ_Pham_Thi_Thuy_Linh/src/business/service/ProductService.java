package business.service;

import business.design.iGeneric.IGenericProduct;
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

import static business.Data.categoryService;
import static business.ultil.enumList.Common.inputNum;
import static business.ultil.enumList.Common.inputString;

public class ProductService implements IGenericProduct, Serializable {
    @Override
    public void showAllProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println(ConsoleColors.PINK+"Product list is empty"+ConsoleColors.RESET);
        }else{
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//            productList.forEach(Product::display);
            for(Product product : productList){
                product.display();
            }
        }
    }
    public void productPagination(Scanner sc,List<Product> productList) {

        boolean flag = true;
        int pageSize = 5; // Number of items per page
        int currentPage = 1;
        Pagination<Product> pagination = PaginationService.paginate(productList, currentPage, pageSize);
        System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//
        for (Product product : pagination.getItems()) {
            product.display();
        }
        for (int i = 1; i < (int)Math.ceil((double) productList.size()/pageSize)+1; i++) {
            System.out.print("["+i+"]   ");
        }
        System.out.println("["+((int)Math.ceil((double) productList.size()/pageSize)+1)+": Back]");

        int pageNum = 1;
        do{
            System.out.println("Enter page you want to see");
            pageNum = inputNum(sc);
            if(pageNum == (int)Math.ceil((double) productList.size()/pageSize)+1){
                flag = false;
            }else if(pageNum<(int)Math.ceil((double) productList.size()/pageSize)+1){
                pagination = PaginationService.paginate(productList, pageNum, pageSize);
                System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Product product : pagination.getItems()) {
                    product.display();
                }
                for (int i = 1; i < (int)Math.ceil((double) productList.size()/pageSize)+1; i++) {
                    System.out.print("["+i+"]   ");
                }
                System.out.println("["+((int)Math.ceil((double) productList.size()/pageSize)+1)+": Back]");
            }else {
                System.err.println("Please enter a valid page number.");
            }
        }while (flag);
    }
    @Override
    public void filterProductByCate(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
        categoryService.categoryPagination(sc);
        System.out.println(ConsoleColors.PINK+"Enter category you want to search: "+ConsoleColors.RESET);
        int categoryId;
        do {
            categoryId = inputNum(sc);
            if(categoryList.isEmpty()){
                System.err.println("Category list is empty");
                return;
            }else{
                if (categoryList.stream().noneMatch(e->e.getCateId()==categoryId)) {
                    System.err.println("Category ID does not exist");
                    return;
                }else{
                    if(productList.isEmpty()){
                        System.err.println("Product list is empty");
                        return;
                    }else{
                        List<Product> list = productList.stream().filter(e->e.getProductCate().getCateId()==categoryId).toList();
                        if(productList.stream().noneMatch(e->e.getProductCate().getCateId()==categoryId)){
                            System.err.println("None product in this category");
                            return;
                        }else{
                            System.out.println(ConsoleColors.GREEN+"Result:"+ConsoleColors.RESET);
//                            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------");
//                            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product","Category","Price","Stock","Color","Size","Created Date","Updated Date","Wish List");
//                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//                            productList.stream().filter(e->e.getProductCate().getCateId()==categoryId).forEach(Product::display);
                            productPagination(sc,list);
                            return;
                        }
                    }
                }
            }
        }while(true);
    }

    @Override
    public void searchProductById(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else {
            System.out.println(ConsoleColors.PINK+"Enter product ID you want to search:"+ConsoleColors.RESET);
            int id = inputNum(sc);
            if (productList.stream().noneMatch(e -> e.getProductId() == id)) {
                System.err.println("Product ID does not exist");
            } else {
                System.out.println(ConsoleColors.GREEN+"Result:"+ConsoleColors.RESET);
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                productList.stream().filter(e -> e.getProductId()==id).forEach(Product::display);
            }
        }
    }

    @Override
    public void top5NewProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            List<Product> list = productList.stream().sorted(Comparator.comparing(Product::getProductCreatedDate)).toList().reversed();
            System.out.println(ConsoleColors.GREEN+"Result"+ConsoleColors.RESET);
            if(productList.size()<=5){
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                list.forEach(Product::display);
                productPagination(sc,list);
            }else{
                List<Product> result =new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    result.add(list.get(i));
                }
                productPagination(sc,result);
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                for (int i = 0; i < 5; i++) {
//                    list.get(i).display();
//                }
            }
        }
    }

    @Override
    public void addProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println(ConsoleColors.PINK+"Enter the number of products you want to add"+ConsoleColors.RESET);
        int number = inputNum(sc);
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputProduct(sc);
            productList.add(newProduct);
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
        }
        System.out.println(ConsoleColors.GREEN+"Add product successfully"+ConsoleColors.RESET);
    }

    @Override
    public void onSaleProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(productList.isEmpty()){
            System.err.println("Product list is empty");
        }else{
            List<Product> list = productList.stream().filter(e->e.getProductStock()>0).toList();
            if(list.isEmpty()){
                System.err.println("No product onsale !");
            }else{

                System.out.println(ConsoleColors.GREEN+"Result:"+ConsoleColors.RESET);
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
//                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//                list.forEach(Product::display);
                productPagination(sc,list);
            }
        }
    }

    @Override
    public void updateProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println(ConsoleColors.PINK+"Enter the id of the product ID you want to edit"+ConsoleColors.RESET);
        int id = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("Product ID does not exist");
        }else{
            Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
            product.inputUpdateProduct(sc);
            product.setProductId(product.getProductId());
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
            System.out.println(ConsoleColors.GREEN+"Update product successfully"+ConsoleColors.RESET);
            product.display();
        }
    }

    @Override
    public void deleteProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        // thực hiện logic delete
        System.out.println(ConsoleColors.PINK+"Enter the id of the product ID you want to delete"+ConsoleColors.RESET);
        int id = inputNum(sc);
        if(productList.stream().noneMatch(e->e.getProductId()==id)){
            System.err.println("Product ID does not exist");
        }else{
            Product product = productList.stream().filter(e->e.getProductId()==id).findFirst().get();
            productList.remove(product);
            // xóa xong thì lưu lại dữ liệu vào file
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
        }
    }

    @Override
    public void searchProduct(Scanner sc) {
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        System.out.println(ConsoleColors.PINK+"Enter the keyword you want to search:"+ConsoleColors.RESET);
        String keyword = inputString(sc);
        List<Product> result = new ArrayList<>();
        for(Product product:productList){
            if((productList.stream().noneMatch(e->e.getProductName().toLowerCase().contains(keyword.toLowerCase())) &&
                    productList.stream().noneMatch(e->e.getProductDescription().toLowerCase().contains(keyword.toLowerCase())))){
                result.add(product);
            }
        }
        if(result.isEmpty()){
            System.err.println("No result matching keyword " + keyword);
        }else {
            System.out.println(ConsoleColors.GREEN+"Result:"+ConsoleColors.RESET);
//            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//            System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-15s | %-10s | %-10s |  %-10s | %-10s |  %-12s | %-10s | %-10s |\n "+ConsoleColors.RESET, "ID", "Product", "Category", "Price", "Stock", "Color", "Size", "Created Date", "Updated Date", "Wish List");
//            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
//            result.forEach(Product::display);
            productPagination(sc,result);
        }
    }


}
