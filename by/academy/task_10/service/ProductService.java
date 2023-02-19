package by.academy.task_10.service;

import by.academy.task_10.data.Product;
import by.academy.task_10.repository.Operations;

import java.time.LocalDate;
import java.util.List;

public class ProductService {

    private Operations<Product> operations;

    public ProductService(Operations<Product> operations){
        this.operations = operations;
    }

    public List<Product> getOrders (){
        return operations.readAll();
    }


    public boolean createProduct (String name, LocalDate date){
        List<Product> list = this.getOrders();
        int id = 0;
        // Проверка есть ли такая позиция и вычисление номера ID
        for(int i = 0; i < list.size(); i++){
            id = list.get(i).getId();
            if ((list.get(i).getName().equals(name)) && (list.get(i).getDateProduce().equals(name))){
                return false;
            }
        }
        id += 1;
        return operations.create(new Product(id, name, date));
    }

    public boolean deleteOrder(String name, LocalDate date){
        // Проверяем есть ли такой товар
        List<Product> list = this.getOrders();
        boolean status = false;
        for (int i = 0; i < list.size(); i++){
            if ((list.get(i).getName().equals(name)) && (list.get(i).getDateProduce().equals(date))){
                list.remove(i);
                status = true;
            }
        }
        if (status){
            return operations.delete(list);
        }
        else{
            return false;
        }
    }
}
