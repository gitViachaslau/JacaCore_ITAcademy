package by.academy.task_10.service;

import by.academy.task_10.data.Order;
import by.academy.task_10.data.Product;
import by.academy.task_10.repository.Operations;

import java.time.LocalDate;
import java.util.List;


public class OrderService {

    private Operations<Order> operations;

    public OrderService(Operations<Order> operations) {
        this.operations = operations;
    }

    public List<Order> getOrders (){
        return operations.readAll();
    }

    public boolean createOrder(LocalDate date, List orderList){
        List<Order> list = this.getOrders();
        int id = 0;
        if(list.size() != 0){
            id += list.size() + 1;
        }
        else{
            id += 1;
        }
        return operations.create(new Order(id, date, orderList));
}

    public boolean deleteOrder(int id){
            return false;
        }


}
