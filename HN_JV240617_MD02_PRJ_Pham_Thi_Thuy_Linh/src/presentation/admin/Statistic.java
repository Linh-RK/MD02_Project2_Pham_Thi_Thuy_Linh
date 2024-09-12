package presentation.admin;

import business.entity.User;
import business.service.StatisticService;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;

import java.util.List;
import java.util.Scanner;

import static business.Data.currentIndex;
import static business.Data.currentUser;
import static business.ultil.enumList.Common.displayUser;

public class Statistic {
    public static void statistics(Scanner sc) {
        boolean flag = true;
        do {
            displayUser(sc);
            System.out.println("|------------------------------ADMIN STATISTIC--------------------------------");
            System.out.println("|                                                                            |");
            System.out.println("|        1. Thống kê                                                         |");
            System.out.println("|        2. Thông kê chi tiết                                                |");
            System.out.println("|        3. Thông kê doanh thu từ ngày a đến ngày b                          |");
            System.out.println("|        4. Quay lại                                                         |");
            System.out.println("|                                                                            |");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(ConsoleColors.PINK+"Please enter your choice"+ConsoleColors.RESET);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":{
                    StatisticService.commonStatistic();
                    break;
                }
                case "2":{
                    StatisticService.detailStatistic();
                    break;
                }
                case "3":{
                    StatisticService.revenueByDate(sc);
                    break;
                }
                case "4":{
                    flag = false;
                    break;
                }
                default:{
                    System.err.println("Please enter a choice from 1 to 4");
                    break;
                }
            }
        }while (flag);
    }


}
