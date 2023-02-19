package by.academy.task_10.controller;

import by.academy.task_10.data.Order;
import by.academy.task_10.data.Product;
import by.academy.task_10.repository.ProductFileOperations;
import by.academy.task_10.service.OrderService;
import by.academy.task_10.service.ProductService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleController {

    static String CHECK_MASK_DATE = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";


    private ProductService productService;
    private OrderService orderService;

    public ConsoleController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public void startApp() {


        while (true) {

            Scanner sc = new Scanner(System.in);
            System.out.println("\n======== Добро пожаловать ========");
            System.out.println("Введите 1 для работы с заказами");
            System.out.println("Введите 2 для работы с товарами");
            System.out.println("Введите 0 для завершения работы");
            System.out.print("-->");

            String input = sc.nextLine();

            if ("0".equals(input)) {
                break;
            }

            if ("1".equals(input)) {
                boolean checkInput = false;
                do {
                    System.out.println("======== Работа с заказами ========");
                    System.out.println("Введите 1 для отображения списка заказов");
                    System.out.println("Введите 2 для добавления заказа");
                    System.out.println("Введите 3 для удаления заказа (по ID)");
                    System.out.println("Введите 0 для возврата в меню");
                    System.out.print("-->");
                    input = sc.nextLine();
                    switch (input) {
                        case "1":
                            checkInput = true;
                            final List<Order> orders = orderService.getOrders();
                            for (Order obj : orders) {
                                System.out.println(obj.toString());
                            }
                            break;
                        case "2":
                            checkInput = true;
                            boolean orderCreated = this.createOrder();
                            String messageCreate = orderCreated ? "Заказ добавлен" : "Ошибка добавления заказа";
                            System.out.println(messageCreate);
                            break;
                        case "3":
                            checkInput = true;
                            break;
                        case "0":
                            checkInput = true;
                            break;
                        default:
                            System.out.println("\n!!! Вы ввели недопустимое знаечние =(, попробуйте еще раз !!!\n");
                    }
                } while (!checkInput);
            } else if ("2".equals(input)) {
                boolean checkInput = false;
                do {
                    System.out.println("======== Работа с товарами ========");
                    System.out.println("Введите 1 для отображения списка товаров");
                    System.out.println("Введите 2 для добавления товара");
                    System.out.println("Введите 3 для удаления товара (по ID)");
                    System.out.println("Введите 0 для возврата в меню");
                    System.out.print("-->");
                    input = sc.nextLine();

                    switch (input) {
                        case "1":
                            checkInput = true;
                            final List<Product> products = productService.getOrders();
                            for (Product obj : products) {
                                System.out.println(obj.toString());
                            }
                            break;
                        case "2":
                            checkInput = true;
                            boolean productCreated = this.createProduct();
                            String messageCreate = productCreated ? "Продукт добавлен" : "Ошибка добавления продукта";
                            System.out.println(messageCreate);
                            break;
                        case "3":
                            checkInput = true;
                            boolean productDelete = this.deleteProduct();
                            String messageDelete = productDelete ? "Продукт удалён" : "Ошибка удаления продукта";
                            System.out.println(messageDelete);
                            break;
                        case "0":
                            checkInput = true;
                            break;
                        default:
                            System.out.println("\n!!! Вы ввели недопустимое знаечние =(, попробуйте еще раз !!!\n");
                    }
                } while (!checkInput);
            } else {
                System.out.println("\n!!! Вы ввели недопустимое знаечние =(, попробуйте еще раз !!!\n");
            }
        }


    }

    private boolean createProduct() {
        boolean checkInput = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("======== Добавление товара ========");
        System.out.println("Введите название товара");
        System.out.print("-->");
        final String nameInput = sc.nextLine();
        LocalDate dateInput;
        String date;

        do {
            System.out.println("Введите дату производства в формате ГГГГ-ММ-ДД");
            System.out.print("-->");
            date = sc.nextLine();
            Pattern pattern = Pattern.compile(CHECK_MASK_DATE);
            Matcher matcher = pattern.matcher(date);
            boolean match = matcher.matches();
            if (match) {
                checkInput = true;
            }
        } while (!checkInput);
        dateInput = createDateFromString(date);
        dateInput = createDateFromString(sc.nextLine());
        final boolean productCreated = productService.createProduct(nameInput, dateInput);
        return productCreated;
    }


    private boolean deleteProduct() {
        boolean checkInput = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("======== Удаление товара ========");
        System.out.println("Введите название товара");
        System.out.print("-->");
        final String nameInput = sc.nextLine();
        LocalDate dateInput;
        String date;

        do {
            System.out.println("Введите дату производства в формате ГГГГ-ММ-ДД");
            System.out.print("-->");
            date = sc.nextLine();
            Pattern pattern = Pattern.compile(CHECK_MASK_DATE);
            Matcher matcher = pattern.matcher(date);
            boolean match = matcher.matches();
            if (match) {
                checkInput = true;
            }
        } while (!checkInput);
        dateInput = createDateFromString(date);
        final boolean productDelete = productService.deleteOrder(nameInput, dateInput);
        return productDelete;

    }


    private boolean createOrder() {
        List <Product> products = new ProductFileOperations().readAll();
        List <Product> productsInOrder = new ProductFileOperations().readAll();
        productsInOrder.clear();

        boolean checkInput = false;
        boolean cycle = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("\n======== Добавление заказа ========");
        LocalDate dateInput;
        String date;

        do {
            System.out.println("Введите дату производства в формате ГГГГ-ММ-ДД");
            System.out.print("-->");
            date = sc.nextLine();
            Pattern pattern = Pattern.compile(CHECK_MASK_DATE);
            Matcher matcher = pattern.matcher(date);
            boolean match = matcher.matches();
            if (match) {
                checkInput = true;
            }
        } while (!checkInput);
        dateInput = createDateFromString(date);

        System.out.println(products);

        while(true){
            String idInput;
                System.out.println("Вводите ID товаров к заказу поочерёдно. Товары с такими ID должны существовать.\n" +
                        "Для завершения ввода ID товаров введите 0.");
                System.out.print("-->");
                idInput = sc.nextLine();

                if ("0".equals(idInput)){
                    break;
                }
                else{
                    for(int i = 0; i < products.size(); i++){
                        String obj = String.valueOf(products.get(i).getId());
                        if (idInput.equals(obj)){
                            productsInOrder.add(products.get(i));
                        }
                }
            }
        }
        return orderService.createOrder(dateInput, productsInOrder);
    }


    private LocalDate createDateFromString(String data) {
        final String[] split = data.split("-");
        final int year = Integer.parseInt(split[0]);
        final int month = Integer.parseInt(split[1]);
        final int day = Integer.parseInt(split[2]);
        return LocalDate.of(year, month, day);
    }
}

