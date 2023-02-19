package by.academy.task_10.repository;

import by.academy.task_10.data.Order;
import by.academy.task_10.data.Product;
import by.academy.task_10.service.OrderService;
import by.academy.task_10.service.ProductService;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderFileOperations implements Operations<Order> {

    @Override
    public List<Order> readAll() {

        List <Order> orders = new ArrayList<>();

        try (FileReader file = new FileReader("files/orders.txt")){
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                List <Product> products = new ProductFileOperations().readAll();
                List <Product> productsNew = new ProductFileOperations().readAll();
                productsNew.clear();
                String line = scan.nextLine();
                final String[] split = line.split(";");
                final int id = Integer.parseInt(split[0]);
                final LocalDate date = convertingDateFromString(split[1]);

                for(int j = 2; j < split.length; j++){
                    String str2 = split[j];
                    for(int i = 0; i < products.size(); i++){
                        String str1 = String.valueOf(products.get(i).getId());
                        if(str1.equals(str2)){
                            productsNew.add(products.get(i));
                        }
                    }
                }
                orders.add(new Order(id, date, productsNew));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public boolean create(Order order) {
        try (FileWriter file = new FileWriter("files/orders.txt",true)){
            StringBuffer str = new StringBuffer("\n");
            str.append(order.getId() + ";");
            str.append(order.getDateOrder() + ";");

            String[] array = new String [order.getOrderList().size()];
            for (int i = 0; i < array.length; i++){
                array[i] = String.valueOf(order.getOrderList().get(i).getId());
            }
            for(String obj: array){
                str.append(obj + ";");
            }
            file.write(String.valueOf(str));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(List<Order> order) {
            return false;
        }




    // Преобразуем строковое представление даты в объектный тип LocalDate
    private LocalDate convertingDateFromString(String data){
        final String[] split = data.split("-");
        final int year = Integer.parseInt(split[0]);
        final int month = Integer.parseInt(split[1]);
        final int day = Integer.parseInt(split[2]);
        return LocalDate.of(year, month, day);
    }
}
