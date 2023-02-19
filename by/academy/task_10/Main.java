package by.academy.task_10;

import by.academy.task_10.controller.ConsoleController;
import by.academy.task_10.data.Order;
import by.academy.task_10.data.Product;
import by.academy.task_10.repository.Operations;
import by.academy.task_10.repository.OrderFileOperations;
import by.academy.task_10.repository.ProductFileOperations;
import by.academy.task_10.service.OrderService;
import by.academy.task_10.service.ProductService;

public class Main {
    public static void main(String[] args) {
        Operations<Product> operationsProduct = new ProductFileOperations();
        Operations<Order> operationsOrder = new OrderFileOperations();
        ProductService productService = new ProductService(operationsProduct);
        OrderService orderService = new OrderService(operationsOrder);


        ConsoleController console = new ConsoleController(productService, orderService);
        console.startApp();
    }
}
