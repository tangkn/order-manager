package org.pdev;


import org.pdev.Models.Customer;
import org.pdev.Models.Invoice;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Invoice> invoices = new ArrayList<>();

    public static void main(String[] args) {


        Customer customer1 = new Customer("Ahmet", "IT", 6);
        addCustomer(customer1);
        addInvoice(400, LocalDate.of(2024, 1, 15), customer1);

        Customer customer2 = new Customer("Can", "IT", 6);
        addCustomer(customer2);
        addInvoice(2802, LocalDate.of(2024, 6, 20), customer2);
        addInvoice(4000, LocalDate.of(2024, 6, 20), customer2);
        addInvoice(6020, LocalDate.of(2024, 6, 20), customer2);

        Customer customer3 = new Customer("Ali", "MEDICAL", 6);
        addCustomer(customer3);
        addInvoice(600, LocalDate.of(2024, 6, 20), customer3);
        addInvoice(600, LocalDate.of(2024, 6, 20), customer3);
        System.out.println("*------ALL CUSTOMER-----*");
        listAllCustomers();
        System.out.println("*-----CUSTOMER CONTAINS C------*");
        listCustomersWithC();
        System.out.println("*-----AMOUNT OF INVOICES FOR JUNE------*");
        listTotalAmountOfInvoicesForJune();
        System.out.println("*-----ALL INVOICES------*");
        listAllInvoices();
        System.out.println("*-----INVOICES OVER 1500------*");
        listInvoicesOver1500();
        System.out.println("*----AVERAGE OF INVOICES OVER 1500-------*");
        calculateAverageOfInvoicesOver1500();
        System.out.println("*-----INVOICES UNDER 500------*");
        listCustomersWithInvoicesUnder500();
        System.out.println("*-----SECTORS OF CUSTOMER WITH AVERAGE INVOICE BELOW 750 IN JUNE------*");
        listSectorsOfCustomerWithAverageInvoiceBelow750InJune();
        System.out.println("*-----------*");
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void addInvoice(double amount, LocalDate date, Customer customer) {
        invoices.add(new Invoice(amount, date, customer));
    }

    public static void listAllCustomers() {
        customers.stream().forEach(c -> System.out.println("Customer Name: " + c.getName()));
    }

    public static void listCustomersWithC() {
        customers.stream()
                .filter(customer -> customer.getName().contains("C"))
                .forEach(customer -> System.out.println(customer.getName()));
    }

    public static void listTotalAmountOfInvoicesForJune() {
        double total = invoices.stream()
                .filter(invoice -> invoice.getDate().getMonth() == Month.JUNE)
                .mapToDouble(Invoice::getAmount)
                .sum();
        System.out.println("Total Amount of June Invoices: " + total);
    }

    public static void listAllInvoices() {
        invoices.forEach(invoice ->
                System.out.println("Customer: " + invoice.getCustomer().getName() + ", Amount: " + invoice.getAmount()));
    }

    public static void listInvoicesOver1500() {
        invoices.stream()
                .filter(invoice -> invoice.getAmount() > 1500)
                .forEach(invoice ->
                        System.out.println("Customer: " + invoice.getCustomer().getName() + ", Amount: " + invoice.getAmount()));
    }

    public static void calculateAverageOfInvoicesOver1500() {
        double average = invoices.stream()
                .filter(invoice -> invoice.getAmount() > 1500)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0);
        System.out.println("Average of Invoices Over 1500TL: " + average);
    }

    public static void listCustomersWithInvoicesUnder500() {
        invoices.stream()
                .filter(invoice -> invoice.getAmount() < 500)
                .map(Invoice::getCustomer)
                .distinct()
                .forEach(customer -> System.out.println(customer.getName()));
    }

    public static void listSectorsOfCustomerWithAverageInvoiceBelow750InJune() {
        Map<String, Double> averageInvoicesBySector = invoices.stream()
                .filter(invoice -> invoice.getDate().getMonth() == Month.JUNE)
                .collect(Collectors.groupingBy(
                        invoice -> invoice.getCustomer().getSector(),
                        Collectors.averagingDouble(Invoice::getAmount)
                ));

        averageInvoicesBySector.entrySet().stream()
                .filter(entry -> entry.getValue() < 750)
                .map(Map.Entry::getKey)
                .distinct()
                .forEach(System.out::println);
    }
}
/* mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------------< org.pdev:OrderManager >------------------------
[INFO] Building OrderManager 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ OrderManager ---
[INFO] Deleting C:\Users\gokha\IdeaProjects\OrderManager\target
[INFO]
[INFO] --- resources:3.3.0:resources (default-resources) @ OrderManager ---
[INFO] Copying 0 resource
[INFO]
[INFO] --- compiler:3.10.1:compile (default-compile) @ OrderManager ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 4 source files to C:\Users\gokha\IdeaProjects\OrderManager\target\classes
[INFO]
[INFO] --- resources:3.3.0:testResources (default-testResources) @ OrderManager ---
[INFO] skip non existing resourceDirectory C:\Users\gokha\IdeaProjects\OrderManager\src\test\resources
[INFO]
[INFO] --- compiler:3.10.1:testCompile (default-testCompile) @ OrderManager ---
[INFO] Changes detected - recompiling the module!
[INFO]
[INFO] --- surefire:3.0.0:test (default-test) @ OrderManager ---
[INFO]
[INFO] --- jar:3.3.0:jar (default-jar) @ OrderManager ---
[INFO] Building jar: C:\Users\gokha\IdeaProjects\OrderManager\target\OrderManager-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- install:3.1.0:install (default-install) @ OrderManager ---
[INFO] Installing C:\Users\gokha\IdeaProjects\OrderManager\pom.xml to C:\Users\gokha\.m2\repository\org\pdev\OrderManager\1.0-SNAPSHOT\OrderManager-1.0-SNAPSHOT.pom
[INFO] Installing C:\Users\gokha\IdeaProjects\OrderManager\target\OrderManager-1.0-SNAPSHOT.jar to C:\Users\gokha\.m2\repository\org\pdev\OrderManager\1.0-SNAPSHOT\OrderManager-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.985 s
[INFO] Finished at: 2024-02-27T22:27:25+03:00
[INFO] ------------------------------------------------------------------------
 */