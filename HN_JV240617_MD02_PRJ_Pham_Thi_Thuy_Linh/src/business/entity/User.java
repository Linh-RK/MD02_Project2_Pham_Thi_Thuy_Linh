package business.entity;

import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.*;

public class User implements Serializable {
    private Role role;                      //1
    private int userId;                     //2
    private String userName;                //3
    private String userEmail;               //4
    private String userFullName;            //5
    private boolean userStatus =true;             //6
    private String userPassword;            //7
    private String userPhoneNumber;         //8
    private List<Address> userAddressList=new ArrayList<>();      //9
    private LocalDate userCreatedDate;      //10
    private LocalDate userUpdatedDate;      //11
    private List<Cart> cartList= new ArrayList<>();            //13
    private List<Product> wishList= new ArrayList<>();            //13

    public User() {
    }

    public User(Role role, int userId, String userName, String userEmail, String userFullName, boolean userStatus,
                String userPassword, String userPhoneNumber, LocalDate userCreatedDate, LocalDate userUpdatedDate,  List<Cart> cartList, List<Address> addresList,List<Product> wishList) {
        this.role = role;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
        this.userStatus = userStatus;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userCreatedDate = userCreatedDate;
        this.userUpdatedDate = userUpdatedDate;
        this.cartList = cartList;
        this.userAddressList= addresList;
        this.wishList = wishList;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<Address> getUserAddressList() {
        return userAddressList;
    }

    public void setUserAddressList(List<Address> userAddressList) {
        this.userAddressList = userAddressList;
    }

    public LocalDate getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(LocalDate userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public LocalDate getUserUpdatedDate() {
        return userUpdatedDate;
    }

    public void setUserUpdatedDate(LocalDate userUpdatedDate) {
        this.userUpdatedDate = userUpdatedDate;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    public void signIn(Scanner sc) {
        this.role = Role.USER;
        this.userId = autoUserId(sc);
//        this.userName = inputUserName(sc);
        this.userEmail = inputUserEmail(sc);
//        this.userFullName = inputUserFullName(sc);
        this.userStatus = true;
        this.userPassword = inputUserPassword(sc);
//        this.userPhoneNumber = inputUserPhoneNumber(sc);
//        this.userAddress = inputUserAddress(sc);
        this.userCreatedDate = currentDate();
//        this.userUpdatedDate = inputUserUpdatedDate(sc);
//        this.cartList = null;
    }
    public void updateUserInfo(Scanner sc) {
        this.userId = currentUser.getUserId();
        this.userName = inputUserName(sc);
        System.out.println(ConsoleColors.PINK+"Please enter your full name: "+ConsoleColors.RESET);
        this.userFullName = inputString(sc);
        this.userUpdatedDate = currentDate();
    }

    public static void updatePassword(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser = userList.get(currentIndex);
        System.out.println(ConsoleColors.PINK+"Please enter your old password: "+ConsoleColors.RESET);
        String oldPassword = inputString(sc);
        if(!currentUser.getUserPassword().equals(oldPassword)) {
            System.err.println( "Password doesn't match!" );
//            quen mat khau
        }else{
            System.out.println(ConsoleColors.PINK+"Please enter your new password: "+ConsoleColors.RESET);
            String newPassword = inputUserPassword(sc);
            currentUser.setUserPassword(newPassword);
            userList.set(currentIndex,currentUser);
            IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            System.out.println(ConsoleColors.PINK+"Password updated!"+ConsoleColors.RESET);
        }
    }

    private int autoUserId(Scanner sc) {
        return userList.stream().map(User::getUserId).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+1;
    }

    private String inputUserName(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println(ConsoleColors.PINK+"Enter user name: "+ConsoleColors.RESET);
        do {
            String userName = sc.nextLine();
            if(userName.length() <6 || userName.length() > 100) {
                System.err.println("Username must be between 6 and 100 characters");
            }else {
                if(!userName.matches("^[a-zA-Z0-9]*$")) {
                    System.err.println("Username must contain only letters and numbers");
                }else{
                    if(userList.stream().anyMatch(user -> user.getUserName().equals(userName))) {
                        System.err.println("Username is already in use");
                    }else {
                        return userName;
                    }
                }
            }
        }while (true);
    }

    private String inputUserEmail(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);

        System.out.println(ConsoleColors.PINK+"Enter email address: "+ConsoleColors.RESET);
        do{
            String email = sc.nextLine();
            if(email.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")){
                    System.err.println("Email is incorrect format. Please try again.");
                } else {
                    if(userList.stream().anyMatch(user -> user.getUserEmail().equals(email) )) {
                        if(currentUser.getUserEmail().equals(email)) {
                            return email;
                        }else {
                            System.err.println("Email is already in use");
                        }
                    }else {
                        return email;
                    }
                }
            }
        }while(true);
    }

    public static String inputUserPassword(Scanner sc) {
        System.out.println(ConsoleColors.PINK+"Enter password: "+ConsoleColors.RESET);
        do{
            String password = sc.nextLine();
            if(password.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
//                if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$")){
                if(!password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{6,}$")){
                    System.err.println("Password is incorrect format. Please try again.");
                } else {
                    return password;
                }
            }
        }while(true);
    }

    public static void displayDetails(User user) {
        List<Order> historyOrder = orderList.stream().filter(e->e.getUserId()==currentUser.getUserId()).toList();
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","ID " , user.getUserId());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","Name " , user.getUserName());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","Email " , user.getUserEmail());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","FullName " , user.getUserFullName());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","Status " , user.getUserStatus());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","Password " , user.getUserPassword());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","PhoneNumber " , user.getUserPhoneNumber());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","CreatedDate " , user.getUserCreatedDate());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-15s :| %-50s | \n","UpdatedDate " , user.getUserUpdatedDate());
        System.out.println("-------------------------------------------------------------------------");
        if(user.getUserAddressList()==null || user.getUserAddressList().isEmpty()){
            System.out.println(ConsoleColors.PURPLE+"Address list is empty"+ConsoleColors.RESET);
        }else{
        System.out.printf(ConsoleColors.PURPLE+"| %-15s : %-51s |\n"+ConsoleColors.RESET,"AddressList","");
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-15s | %-20s |\n"+ConsoleColors.RESET,"ID","Receiver","Phone","Address");
            System.out.println("-------------------------------------------------------------------------");
            user.getUserAddressList().forEach(Address::displayAddress);
        }
        if(historyOrder.isEmpty()){
            System.out.println(ConsoleColors.PURPLE+"History order is empty"+ConsoleColors.RESET);
        }else{
            System.out.printf(ConsoleColors.PURPLE+"| %-15s : %-50s | "+ConsoleColors.RESET,"HistoryOrder","");
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-15s | %-7s | %-15s | %-15s |\n"+  ConsoleColors.RESET,"ID", "Product", "Qty", "Price", "Total");
            historyOrder.forEach(Order::displayOrder);
            System.out.println("-------------------------------------------------------------------------");

        }

        if(user.getCartList()==null||user.getCartList().isEmpty()){
            System.out.println(ConsoleColors.PURPLE+"Cart is empty"+ConsoleColors.RESET);
        }else{
            System.out.println(ConsoleColors.PURPLE+"CartList: "+ConsoleColors.RESET);
            System.out.println("-------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-15s | %-7s | %-15s | %-15s |\n"+ConsoleColors.RESET,"ID", "Product", "Qty", "Price", "Total");
            user.getCartList().forEach(Cart::displayCart2);
            System.out.println("-------------------------------------------------------------------------");
        }

    }
    public  void displayUser() {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.BLUE+"| %-5d | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET,this.userId,this.userName,this.userEmail,this.userStatus?"Active":"Block",this.userPhoneNumber,this.userCreatedDate);
    }
    public static void switchUserStatus(Scanner sc, User user){
        user.setUserStatus(!user.getUserStatus());
    }
}
