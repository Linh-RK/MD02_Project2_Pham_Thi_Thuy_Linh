package business.service;

import business.design.iGeneric.IGenericOrder;
import business.entity.*;
import business.ultil.enumList.ConsoleColors;
import business.ultil.enumList.IOFile;
import business.ultil.enumList.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static business.Data.*;
import static business.ultil.enumList.Common.*;
import static presentation.admin.OrderManagement.orderManagement;
import static presentation.user.CartMenu.cartMenu;

public class OrderService implements IGenericOrder, Serializable {
    @Override
    public void addOrderCheckOut(Scanner sc, List<Cart> cartList) {
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        List<User> userList= IOFile.readObjectFromFile(IOFile.PATH_USER);
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        currentUser = userList.get(currentIndex);
        if(cartList.isEmpty()) {
            System.err.println("Cart is empty, please add product to the cart");
        }else {
            System.out.println(ConsoleColors.GREEN+"Check out:"+ConsoleColors.RESET);
            Order orderCheckOut = new Order();
            orderCheckOut.inputOrder(sc, cartList);
            int n = orderCheckOut.getOrderCartList().size();
//            System.out.println("so san pham trong order " + n);
            System.err.println("Please confirm your order:");
            orderCheckOut.displayOrderDetails();
            System.out.println(ConsoleColors.GREEN+"Enter your choice (Y/N):"+ConsoleColors.RESET);
            String answer = inputAnswer(sc);
            if (answer.equalsIgnoreCase("Y")) {
//            subtract qty from stock
                System.out.println("product in order "+orderCheckOut.getOrderCartList().size());
                Product p;
                int indexProduct;
                for (Cart cart : orderCheckOut.getOrderCartList()) {
                    p = productList.stream().filter(e->e.getProductId()==cart.getProductInCart().getProductId()).findFirst().get();
                    indexProduct= productList.indexOf(p);
                    p.setProductStock(p.getProductStock() - cart.getQty());
                    productList.set(indexProduct, p);
                    IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
                }
//           khong biet da cap nhat len tren userList chua
                orderList.add(orderCheckOut);
                System.out.println(ConsoleColors.GREEN+"Danh sach order"+ConsoleColors.RESET);
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf(ConsoleColors.BLUE+"| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n"+ConsoleColors.RESET, "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                orderList.forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
                IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
                currentUser.getCartList().clear();
//                -----
                userList.set(currentIndex, currentUser);
                IOFile.writeObjectToFile(userList, IOFile.PATH_USER);
                System.out.println(ConsoleColors.GREEN+"Order successfully, thank you"+ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.GREEN+"Canceled order"+ConsoleColors.RESET);
                cartMenu(sc);
            }
        }
    }

    @Override
    public void showAllOrder(List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
            orderList.forEach(Order::displayOrder);
            System.out.println("--------------------------------------------------------------------------");
        }
    }

    public void orderPagination(Scanner sc,List<Order> orderList) {
        boolean flag = true;
        int pageSize = 5; // Number of items per page
        int currentPage = 1;
        Pagination<Order> pagination = PaginationService.paginate(orderList, currentPage, pageSize);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
        for (Order order : pagination.getItems()) {
            order.displayOrder();
        }
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 1; i < (int)Math.ceil((double) orderList.size()/pageSize)+1; i++) {
            System.out.print("["+i+"]   ");
        }
        System.out.println("["+((int)Math.ceil((double) orderList.size()/pageSize)+1)+": Back]");

        int pageNum = 1;
        do{
            System.out.println("Enter page you want to see");
            pageNum = inputNum(sc);
            if(pageNum == (int)Math.ceil((double) orderList.size()/pageSize)+1){
                flag = false;
            }else if(pageNum<(int)Math.ceil((double) orderList.size()/pageSize)+1){
                pagination = PaginationService.paginate(orderList, pageNum, pageSize);
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
                for (Order order : pagination.getItems()) {
                    order.displayOrder();
                }
                System.out.println("--------------------------------------------------------------------------");
                for (int i = 1; i <= (int)Math.ceil((double) orderList.size()/pageSize)+1; i++) {
                    System.out.print("["+i+"]   ");
                }
                System.out.println("["+((int)Math.ceil((double) orderList.size()/pageSize)+1)+": Back]");

            }else {
                System.err.println("Please enter a valid page number.");
            }
        }while (flag);
    }



    @Override
    public void findOrderById(Scanner sc, List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                System.out.println("Result");
                System.out.println("-------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n", "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    @Override
    public void findByOrderStatus(Scanner sc, List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("List if order status");
            for (OrderStatus element : OrderStatus.values())
                System.out.println(element);
            System.out.println("Please enter the order status you want to search");
            String status = inputString(sc);
            if (!isExistInEnum(status,orderList)) {
                System.err.println("Order status not found");
            } else {
                System.out.println(status);
                System.out.println("Result");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-10s | \n","ID", "Serial","User ID","Total","Status","Created Date");
                orderList.stream().filter(e -> e.getOrderStatus().name().equalsIgnoreCase(status)).forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    @Override
    public void filterByDate(Scanner sc, List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Enter the start date you want to search (yyyy-mm-dd)");
            LocalDate startDate = inputDate(sc);
            System.out.println("Enter the end date you want to search (yyyy-mm-dd)");
            LocalDate endDate = inputDate(sc);

            List<Order> resultOrder = orderList.stream().filter(e -> {
                return e.getOrderCreateDate().isAfter(startDate) && e.getOrderCreateDate().isBefore(endDate);
            }).toList();
            if(resultOrder.isEmpty()){
                System.err.println("None order found");
            }else {
                System.out.println("Result");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-5s | %-10s | %-10s | %-11s | \n", "ID", "Serial", "User ID", "Total", "Status", "Created Date");
                resultOrder.forEach(Order::displayOrder);
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    @Override
    public void showDetailById(Scanner sc, List<Order> orderList) {
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            orderPagination(sc,orderList);
            System.out.println("Enter the ID order you want to show detail");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            }else {
                System.out.println("Result");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(Order::displayOrderDetails);
            }
        }
    }

    @Override
    public boolean isExistInEnum(String status, List<Order> orderList) {
        return orderList.stream().map(Order::getOrderStatus).toList().stream().anyMatch(e->e.name().equalsIgnoreCase(status));
    }

    @Override
    public void orderDetailById(Scanner sc) {
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);

        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show detail");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                System.out.println("Result");
                orderList.stream().filter(e -> e.getOrderId() == id).forEach(Order::displayOrderDetails);
            }
        }
    }

    @Override
    public void cancelOrder(Scanner sc, List<Order> order) {
        List<Order> orderList= IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
        if(order == null || order.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show change status");
            int id = inputNum(sc);
            if (order.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                Order orderFind = orderList.stream().filter(e -> e.getOrderId() == id).findFirst().get();
                int indexOrder = orderList.indexOf(orderFind);
                if (orderFind.getOrderStatus().equals(OrderStatus.WAITING)) {
                    System.out.println("Do you want to cancel this order ?");
                    System.out.println("(Y/N)");
                    String answer = inputAnswer(sc);
                    if (answer.equalsIgnoreCase("y")) {
                        orderFind.setOrderStatus(OrderStatus.CANCEL);
                        orderList.set(indexOrder, orderFind);
//                        update lai gio hang
                        List<Cart> cancelCart = orderFind.getOrderCartList();
                        Product p;
                        int idProduct;
                        for (Cart cart : cancelCart) {
                            p = productList.stream().filter(e -> e.getProductId() == cart.getProductInCart().getProductId()).findFirst().get();
                            p.setProductStock(p.getProductStock() + cart.getQty());
                            idProduct = productList.indexOf(p);
                            productList.set(idProduct, p);
                            IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
                        }
                        IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
                    } else if (answer.equalsIgnoreCase("n")) {
                        orderManagement(sc);
                    }
                }
            }
        }
    }

    @Override
    public void changeOrderStatus(Scanner sc) {
        List<Order> orderList = IOFile.readObjectFromFile(IOFile.PATH_ORDER);
        if(orderList == null || orderList.isEmpty()){
            System.err.println("Order list is empty");
        }else {
            System.out.println("Please enter the order ID you want to show change status");
            int id = inputNum(sc);
            if (orderList.stream().noneMatch(e -> e.getOrderId() == id)) {
                System.err.println("Order ID not found");
            } else {
                Order orderChange = orderList.stream().filter(e -> e.getOrderId() == id).findFirst().get();
                int idProduct;
                int orderIndex =orderList.indexOf(orderChange);
                Product p;
                if (orderChange.getOrderStatus().equals(OrderStatus.WAITING)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  CONFIRM");
                    System.out.println("2.  DENIED");
                    System.out.println("3.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2") || answer.equals("3")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.CONFIRM);
                            orderList.set(orderIndex, orderChange);
                            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
                            System.out.println("Confirm order successfully");
                            break;
                        }

                        case "2": {
                            orderChange.setOrderStatus(OrderStatus.DENIED);
                            orderList.set(orderIndex, orderChange);
                            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
//                      update lai gio hang
                            List<Cart> cancelCart = orderChange.getOrderCartList();
                            for (Cart cart : cancelCart) {
                                List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
                                p = productList.stream().filter(e -> e.getProductId() == cart.getProductInCart().getProductId()).findFirst().get();
                                p.setProductStock(p.getProductStock() + cart.getQty());
                                idProduct = productList.indexOf(p);
                                productList.set(idProduct, p);
                                IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
                            }
                            System.out.println("Denied order successfully");
                            List<Product> productList= IOFile.readObjectFromFile(IOFile.PATH_PRODUCT);
                            productService.productPagination(sc,productList);
                            break;
                        }
                        case "3": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                }
                else if (orderChange.getOrderStatus().equals(OrderStatus.CONFIRM)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  DELIVERY");
                    System.out.println("2.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.DELIVERY);
                            orderList.set(orderIndex, orderChange);
                            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
                            break;
                        }
                        case "2": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                }
                else if (orderChange.getOrderStatus().equals(OrderStatus.DELIVERY)) {
                    System.out.println("Enter the new order status");
                    System.out.println("1.  SUCCESS");
//                    System.out.println("2.  DENIED");
                    System.out.println("2.  BACK");
                    String answer;
                    do {
                        answer = sc.nextLine();
                    } while (!(answer.equals("1") || answer.equals("2") || answer.equals("3")));
                    switch (answer) {
                        case "1": {
                            orderChange.setOrderStatus(OrderStatus.SUCCESS);
                            orderList.set(orderIndex, orderChange);
                            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
                            System.out.println("Order successfully");
                            break;
                        }
//                        case "2": {
//                            orderChange.setOrderStatus(OrderStatus.DENIED);
//                            orderList.set(orderIndex, orderChange);
//                            IOFile.writeObjectToFile(orderList, IOFile.PATH_ORDER);
//                            List<Cart> cancelCart = orderChange.getOrderCartList();
//                            for (Cart cart : cancelCart) {
//                                p = productList.stream().filter(e -> e.getProductId() == cart.getProductInCart().getProductId()).findFirst().get();
//                                p.setProductStock(p.getProductStock() + cart.getQty());
//                                idProduct = productList.indexOf(p);
//                                productList.set(idProduct, p);
//                                IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCT);
//                            }
//                            System.out.println("Order be delivery failed");
//                            productService.productPagination(sc);
//                            break;
//                        }
                        case "2": {
                            return;
                        }
                        default: {
                            System.err.println("Please enter validate choice!");
                            break;
                        }
                    }
                }
            }
        }
    }

}
