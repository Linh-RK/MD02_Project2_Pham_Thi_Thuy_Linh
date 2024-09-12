package business.ultil.enumList;

import business.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static business.Data.*;
import static business.Data.userList;

public class FixedData {
    public static void main(String[] args) {

            Category cate1 = new Category(1, "Skincare", true, "Sản phẩm dưỡng da");
            Category cate2 = new Category(2, "Cleansing", true, "Sản phẩm làm sạch");
            Category cate3 = new Category(3, "Makeup", true, "Sản phẩm trang điểm");
            Category cate4 = new Category(4, "Cosmetic", true, "Dụng cụ trang điểm");
            categoryList.add(cate1);
            categoryList.add(cate2);
            categoryList.add(cate3);
            categoryList.add(cate4);

            Product p1 = new Product(1, "Serum", cate1, 30, 10, "Serum dưỡng da", "pink", LocalDate.of(2023, 5, 1), "M", LocalDate.of(2023, 10, 1), false);
            Product p2 = new Product(2, "Cream", cate1, 80, 10, "Kem dưỡng da", "white", LocalDate.of(2023, 5, 1), "S", LocalDate.of(2023, 10, 1), false);
            Product p3 = new Product(3, "SRM", cate2, 50, 10, "Sữa rửa mặt", "pink", LocalDate.of(2023, 5, 1), "M", LocalDate.of(2023, 10, 1), false);
            Product p4 = new Product(4, "Cushion", cate3, 40, 10, "Cushion", "beige", LocalDate.of(2023, 5, 1), "XL", LocalDate.of(2023, 10, 1), true);
            Product p5 = new Product(5, "Pad", cate4, 70, 10, "Pad trang điểm", "pink", LocalDate.of(2023, 5, 1), "L", LocalDate.of(2023, 10, 1), false);
            Product p6 = new Product(6, "Brush", cate4, 90, 10, "Cọ trang điểm", "black", LocalDate.of(2023, 5, 1), "M", LocalDate.of(2023, 10, 1), true);
            productList.add(p1);
            productList.add(p2);
            productList.add(p3);
            productList.add(p4);
            productList.add(p5);
            productList.add(p6);

            Address ad1 = new Address(1, 1, "HN", "0987654321", "linh");
            Address ad2 = new Address(2, 1, "TB", "0987654321", "huong");
            Address ad3 = new Address(3, 1, "QN", "0987654321", "trung");
            Address ad4 = new Address(4, 2, "ND", "0987654321", "vi");
            Address ad5 = new Address(5, 2, "TQ", "0987654321", "vu");
            Address ad6 = new Address(6, 3, "BN", "0987654321", "ha");
            Address ad7 = new Address(7, 3, "BG", "0987654321", "lien");
            Address ad8 = new Address(8, 4, "HN", "0987654321", "duyen");
            Address ad9 = new Address(9, 4, "PT", "0987654321", "ngoc");
            addressList.add(ad1);
            addressList.add(ad2);
            addressList.add(ad3);
            addressList.add(ad4);
            addressList.add(ad4);
            addressList.add(ad5);
            addressList.add(ad6);
            addressList.add(ad7);
            addressList.add(ad8);
            addressList.add(ad9);

            List<Address> addressList1 = new ArrayList<>(List.of(ad1, ad2, ad3));
            List<Address> addressList2 = new ArrayList<>(List.of(ad4, ad5));
            List<Address> addressList3 = new ArrayList<>(List.of(ad6, ad7));
            List<Address> addressList4 = new ArrayList<>(List.of(ad8, ad9));
            List<Address> addressList5 = new ArrayList<>();

            Cart c1 = new Cart(1, p1, 2,p1.getProductPrice()*2);
            Cart c2 = new Cart(1, p3, 2,p3.getProductPrice()*2);
            Cart c3 = new Cart(1, p2, 2,p2.getProductPrice()*2);
            Cart c4 = new Cart(2, p4, 2,p4.getProductPrice()*2);
            Cart c5 = new Cart(2, p1, 2,p1.getProductPrice()*2);
            Cart c6 = new Cart(3, p3, 2,p3.getProductPrice()*2);
            Cart c7 = new Cart(4, p2, 1,p2.getProductPrice()*2);
            List<Cart> cartList1 = new ArrayList<>(List.of(c1, c2, c3));
            List<Cart> cartList2 = new ArrayList<>(List.of(c4, c5));
            List<Cart> cartList3 = new ArrayList<>(List.of(c6));
            List<Cart> cartList4 = new ArrayList<>(List.of(c7));
            List<Cart> cartList5 = new ArrayList<>();

            List<Product> wl1= new ArrayList<>();
            List<Product> wl2= new ArrayList<>();
            List<Product> wl3= new ArrayList<>();
            List<Product> wl4= new ArrayList<>();
            List<Product> wl5= new ArrayList<>();


            User admin = new User(Role.ADMIN, 6, "admin", "admin@gmail.com", "Nguyen Van Admin", true, "123abc", "0987654321", LocalDate.of(2023, 5, 1), LocalDate.of(2023, 10, 1), null, null,null);
            User user1 = new User(Role.USER, 1, "linhlinh", "user1@gmail.com", "Nguyen Van 1", true, "123abc", "0987654321", LocalDate.of(2023, 7, 1), LocalDate.of(2023, 10, 1), cartList1, addressList1,wl1);
            User user2 = new User(Role.USER, 2, "huonghuong", "user2@gmail.com", "Nguyen Van 2", true, "123abc", "0987654321", LocalDate.of(2023, 6, 1), LocalDate.of(2023, 10, 1), cartList2, addressList2,wl2);
            User user3 = new User(Role.USER, 3, "duongduong", "user3@gmail.com", "Nguyen Van 3", true, "123abc", "0987654321", LocalDate.of(2023, 9, 1), LocalDate.of(2023, 10, 1), cartList3, addressList3,wl3);
            User user4 = new User(Role.USER, 4, "hunghung", "user4@gmail.com", "Nguyen Van 4", true, "123abc", "0987654321", LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 1), cartList4, addressList4,wl4);
            User user5 = new User(Role.USER, 5, "ngocngoc", "user5@gmail.com", "Nguyen Van 5", true, "123abc", "0987654321", LocalDate.of(2023, 2, 1), LocalDate.of(2023, 9, 1), cartList5, addressList5,wl5);
            userList.add(admin);
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);
            userList.add(user4);
            userList.add(user5);
//    List<Category> categoryList= IOFile.readObjectFromFile(IOFile.PATH_CATEGORY);
//    List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
//    List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
//    List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
//    List<User> currentUser= IOFile.readObjectFromFile(IOFile.PATH_CURRENTUSER);
//    List<Cart> cartList= IOFile.readObjectFromFile(IOFile.PATH_CART);
            IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORY);
            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
            IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
//    IOFile.writeObjectToFile(cartList, IOFile.PATH_CART);
        }
}

//     ConsoleColors.BLUE +

// "| %-5d | %-10s | %-5d | %-15f | %-15f |\n"

//   + ConsoleColors.RESET
