package by.academy.task_10.data;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private LocalDate dateProduce;

    public Product(int id, String name, LocalDate dateProduce) {
        this.id = id;
        this.name = name;
        this.dateProduce = dateProduce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateProduce() {
        return dateProduce;
    }

    public void setDateProduce(LocalDate dateProduce) {
        this.dateProduce = dateProduce;
    }

    @Override
    public String toString() {
        return  "ID=" + id +
                ", Наименование: '" + name + '\'' +
                "; Дата производства: " + dateProduce;
    }
}
