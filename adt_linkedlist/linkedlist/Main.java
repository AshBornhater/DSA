package linkedlist;

import entity.Laptop;
import sub.SinglyLinkedList;

public class Main {

    public static void main(String[] args) {
        SinglyLinkedList laptopList = new SinglyLinkedList();

        Laptop l1 = new Laptop("Asus", "ROG Zephyrus G14", 28000000);
        Laptop l2 = new Laptop("Lenovo", "Legion Pro 5", 22000000);
        Laptop l3 = new Laptop("Apple", "MacBook Air M2", 17000000);

        laptopList.append(l1);
        laptopList.append(l2);
        laptopList.addFirst(l3); 

        System.out.println("=== List of Laptops in LinkedList ===");
        laptopList.display();
        System.out.println("Total units: " + laptopList.size());

        System.out.println("\nIs Legion Pro 5 present? " + laptopList.contains(l2));

        System.out.println("\nRemoving Lenovo Legion...");
        laptopList.remove(l2);

        System.out.println("\n=== List of Laptops After Deletion ===");
        laptopList.display();
        System.out.println("Current total units: " + laptopList.size());
    }
}