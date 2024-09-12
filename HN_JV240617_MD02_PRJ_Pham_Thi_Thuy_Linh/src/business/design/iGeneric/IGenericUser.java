package business.design.iGeneric;

import business.entity.Cart;
import business.entity.User;

import java.util.Scanner;

public interface IGenericUser {
    /**USER
     * showAllUserInfo()
     * changeUserStatus(Scanner sc)]
     * deleteUser(Scanner sc)
     * searchUser(Scanner sc)
     * sortUserByNameIncrease()
     * sortUserByNameDecrease()
     * listRole(Scanner sc)
     */
    void showAllUserInfo();
    void changeUserStatus(Scanner sc);
    void deleteUser(Scanner sc);
    void searchUser(Scanner sc);
    void sortUserByNameIncrease();
    void sortUserByNameDecrease();
    void listRole(Scanner sc);
}
