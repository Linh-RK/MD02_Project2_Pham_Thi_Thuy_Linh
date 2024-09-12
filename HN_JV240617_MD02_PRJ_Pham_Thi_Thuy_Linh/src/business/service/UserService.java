package business.service;

import business.design.iGeneric.IGenericUser;
import business.entity.Pagination;
import business.entity.Product;
import business.entity.User;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.Role;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputNum;

public class UserService implements IGenericUser, Serializable {
    @Override
    public void showAllUserInfo() {
            List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
            userList.forEach(User::displayUser);
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }
    public void productPagination(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        boolean flag = true;
        int pageSize = 5; // Number of items per page
        int currentPage = 1;
        Pagination<User> pagination = PaginationService.paginate(userList, currentPage, pageSize);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
        for (User user : pagination.getItems()) {
            user.displayUser();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (int i = 1; i <= userList.size()/pageSize+1; i++) {
            System.out.print("["+i+"]   ");
        }
        System.out.println("["+((int)Math.ceil((double) userList.size()/pageSize)+1)+": Back]");

        int pageNum = 1;
        do{
            System.out.println("Enter page you want to see");
            pageNum = inputNum(sc);
            if(pageNum == (int)Math.ceil((double) userList.size()/pageSize)+1){
                flag = false;
            }else if(pageNum<=userList.size()/pageSize+1){
                pagination = PaginationService.paginate(userList, pageNum, pageSize);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.PINK+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
                for (User user : pagination.getItems()) {
                    user.displayUser();
                }
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                for (int i = 1; i <= userList.size()/pageSize+1; i++) {
                    System.out.print("["+i+"]   ");
                }
                System.out.println("["+((int)Math.ceil((double) userList.size()/pageSize)+1)+": Back]");
            }else {
                System.err.println("Please enter a valid page number.");
            }
        }while (flag);
    }




    @Override
    public void changeUserStatus(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println(ConsoleColors.PINK+"Please enter ID user you want to change status"+ConsoleColors.RESET);
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                User.switchUserStatus(sc,userList.stream().filter(e->e.getUserId()==id).findFirst().get());
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
            }
        productPagination(sc);
    }

    @Override
    public void deleteUser(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println(ConsoleColors.PINK+"Please enter ID user you want to delete"+ConsoleColors.RESET);
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                userList.remove(userList.stream().filter(e->e.getUserId()==id).findFirst().get());
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                System.out.println(ConsoleColors.GREEN+"User successfully deleted"+ConsoleColors.RESET);
            }
        productPagination(sc);
    }

    @Override
    public void searchUser(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        System.out.println(ConsoleColors.PINK+"Please enter ID user you want to search:"+ConsoleColors.RESET);
            int id = inputNum(sc);
            if(userList.stream().noneMatch(e->e.getUserId()==id)){
                System.err.println("User ID does not exist");
            }else{
                System.out.println(ConsoleColors.GREEN+"Result:"+ConsoleColors.RESET);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
                userList.stream().filter(e->e.getUserId()==id).findFirst().get().displayUser();
                System.out.println("------------------------------------------------------------------------------------------------------------------");
            }
    }

    @Override
    public void sortUserByNameIncrease() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
    if(userList.isEmpty()){
        System.err.println("User list is empty");
    }else {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
        userList.stream().sorted(Comparator.comparing(User::getUserName)).toList().forEach(User::displayUser);
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }
    }

    @Override
    public void sortUserByNameDecrease() {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        if(userList.isEmpty()){
            System.err.println("User list is empty");
        }else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf(ConsoleColors.BLUE+"| %-5s | %-20s | %-20s | %-10s | %-20s | %-20s |\n"+ConsoleColors.RESET, "ID", "Name", "Email", "Status", "PhoneNumber", "CreatedDate");
            userList.stream().sorted(Comparator.comparing(User::getUserName)).toList().reversed().forEach(User::displayUser);
            System.out.println("------------------------------------------------------------------------------------------------------------------");

        }
    }

    @Override
    public void listRole(Scanner sc) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            System.out.println(role);
        }
    }
}
