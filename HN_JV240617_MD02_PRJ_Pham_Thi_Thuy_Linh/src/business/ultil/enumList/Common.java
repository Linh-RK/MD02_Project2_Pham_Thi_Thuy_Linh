package business.ultil.enumList;

import business.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static business.Data.currentIndex;
import static business.Data.currentUser;

public class Common {
    public static void displayUser(Scanner sc) {
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        currentUser= userList.get(currentIndex);
        System.out.println("");
        for (int i = 0; i < currentUser.getUserName().length()+12; i++) {
            System.out.print("-");
        }
        System.out.println("");
        System.out.println("|    "+"\uD83D\uDC68"+ConsoleColors.PINK+currentUser.getUserName()+ConsoleColors.RESET+"    |");
    }
    public static String inputString(Scanner sc){
        String input ;
        do {
            input = sc.nextLine();
            if(input.isBlank()){
                System.err.println("Data cannot be empty.Please try again");
            }else{
                return input;
            }
        }while(true);
    }
    public static int inputNum(Scanner sc){
        String input;
        do {
            input = sc.nextLine();
            if(input.isBlank()){
                System.err.println("Data cannot be empty.Please try again");
            }else{
                try{
                    int num = Integer.parseInt(input);
                    if(num <= 0){
                        System.err.println("Number cannot be less than or equal to zero.Please try again");
                    }else{
                        return num;
                    }
                }catch(NumberFormatException e){
                    System.err.println("Must input a number.");
                }
            }
        }while(true);
    }
    public static boolean inputStatus(Scanner sc){
        do {
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("true" )||input.equalsIgnoreCase("false" )){
                return Boolean.parseBoolean(input);
            }else{
                System.err.println("Status must be 'true' or 'false'. Please try again");
            }
        }while(true);
    }
    public static String inputPhoneNumber(Scanner sc) {
        System.out.println(ConsoleColors.PINK+"Enter Phone Number: "+ConsoleColors.RESET);
        do{
            String phoneNumber = sc.nextLine();
            if(phoneNumber.isBlank()){
                System.err.println("Data can't be blank. Please try again.");
            }else {
                if(!phoneNumber.matches("[0-9]{10,11}")){
                    System.err.println("Phone number is incorrect format. Please try again.");
                } else {
                    return phoneNumber;
                }
            }
        }while(true);
    }
    public  static LocalDate currentDate(){
        LocalDate today = LocalDate.now( );
        return today;
    }
    public static String inputAnswer(Scanner sc){
        System.out.println(ConsoleColors.PINK+"Enter Answer (Y/N): "+ConsoleColors.RESET);
        do {
            String answer = sc.nextLine();
            if(!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))){
                System.err.println("Answer must be 'y' or 'n'. Please try again.");
            }else{
                return answer;
            }
        }while(true);
    }

    public static LocalDate inputDate(Scanner sc){
        do {
            String input = sc.nextLine();
            if(!input.matches("\\d{4}-\\d{2}-\\d{2}")){
                System.err.println("Date format is incorrect. Please try again.");
            }else {
                String[] inputArr = input.split("-");
                LocalDate day = LocalDate.of(Integer.parseInt(inputArr[0]),Integer.parseInt(inputArr[1]),Integer.parseInt(inputArr[2]));
                return day;
            }
        }while(true);
    }
}
