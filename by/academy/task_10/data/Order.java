package by.academy.task_10.data;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private LocalDate dateOrder;
    private List<Product> orderList;

    public Order(int id, LocalDate dateOrder, List<Product> orderList) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.orderList = orderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Дата заказа: " + dateOrder + ", Товары в заказе: " + orderList;
    }
}
