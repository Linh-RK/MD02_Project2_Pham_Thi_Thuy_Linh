package business.entity;

import business.ultil.enumList.ConsoleColors;

import java.io.Serializable;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.inputPhoneNumber;
import static business.ultil.enumList.Common.inputString;

public class Address implements Serializable {
    private int idAddress;
    private int userId;
    private String address;
    private String phoneNumber;
    private String receiver;

    public Address() {
    }

    public Address(int idAddress, int userId, String address, String phoneNumber, String receiver) {
        this.idAddress = idAddress;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.receiver = receiver;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void inputAddress(Scanner sc) {
        this.idAddress = autoIdAddress();
        this.userId = currentUser.getUserId();
        this.address = inputAddAddress(sc);
        this.phoneNumber = inputPhoneNumber(sc);
        System.out.println(ConsoleColors.PINK+"Enter receiver: "+ConsoleColors.RESET);
        this.receiver = inputString(sc);
    }

    public int autoIdAddress() {
        return currentUser.getUserAddressList().stream().map(Address::getIdAddress).max(Integer::compareTo).orElse(0)+1;
    }

    public static String inputAddAddress(Scanner sc) {
        System.out.println(ConsoleColors.PINK+"Enter address: "+ConsoleColors.RESET);
        do {
            String newAddress =  inputString(sc);
            if(!currentUser.getUserAddressList().isEmpty()){
                if(currentUser.getUserAddressList().stream().anyMatch(e->e.getAddress().equalsIgnoreCase(newAddress))) {
                    System.err.println("This address is already exist");
                }else{
                    return newAddress;
                }
            }
        }while (true);
    }
    public void displayAddress() {
        System.out.printf("| %-5s | %-20s | %-15s | %-20s |\n",this.idAddress,this.receiver,this.phoneNumber, this.address);
        System.out.println("-------------------------------------------------------------------------");
    }
}
