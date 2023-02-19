package by.academy.task_10.repository;

import by.academy.task_10.data.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Читаем файл продуктов и создаём коллекцию из продуктов и возвращаем ее
public class ProductFileOperations implements Operations<Product> {

    @Override
    public List <Product> readAll() {
        List <Product> products = new ArrayList<>();

        try (FileReader file = new FileReader("files/products.txt")){
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                final String[] split = line.split(";");
                final int id = Integer.parseInt(split[0]);
                final String name = split[1];
                final LocalDate date = convertingDateFromString(split[2]);
                products.add(new Product(id, name, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }


    // Добавление новых продуктов и запись в файл
    @Override
    public boolean create(Product product) {
        try (FileWriter file = new FileWriter("files/products.txt",true)){
            StringBuffer str = new StringBuffer("\n");
            str.append(product.getId() + ";");
            str.append(product.getName() + ";");
            str.append(product.getDateProduce());
            file.write(String.valueOf(str));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(List<Product> products) {
        try (FileWriter file = new FileWriter("files/products.txt")){
            for (int i = 0; i < products.size(); i++) {
                StringBuffer str = new StringBuffer();
                str.append(products.get(i).getId() + ";");
                str.append(products.get(i).getName() + ";");
                str.append(products.get(i).getDateProduce());
                if (i != products.size()-1){
                    str.append("\n");
                }
                file.write(String.valueOf(str));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
