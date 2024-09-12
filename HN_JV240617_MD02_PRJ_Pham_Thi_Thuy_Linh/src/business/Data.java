package business;

import business.entity.*;
import business.service.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static Category category = new Category();
    public static Product product = new Product();
    public static User user = new User();
    public static User currentUser = new User();
    public static Cart cart = new Cart();
    public static Order order = new Order();
    public static Address address = new Address();
//
    public static CategoryService categoryService = new CategoryService();
    public static ProductService productService = new ProductService();
    public static UserService userService = new UserService();
    public static CartService cartService = new CartService();
    public static OrderService orderService = new OrderService();

    public static List<Category> categoryList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<Product> wishList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Cart> cartList = new ArrayList<>();
    public static List<Order> orderList = new ArrayList<>();
    public static List<Address> addressList = new ArrayList<>();


    public static int currentIndex;




}
