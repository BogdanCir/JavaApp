package ui;


import domain.Entitate;
import service.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {
    private Service service;
    Scanner scanner = new Scanner(System.in);
    String option = null;

    public UI(Service service) {
        this.service = service;
    }

    public int menu() {
        while (true) {
            printMenu();
            option = scanner.next();
            if (Objects.equals(option, "1")) addUi();
            else if (Objects.equals(option, "2")) deleteUi();
            else if (Objects.equals(option, "3")) updateUi();
            else if (Objects.equals(option, "a")) afisare();
        }
    }

    private void afisare() {
        ArrayList<Entitate> entitati = this.service.getAllEntitati();
        if(entitati != null){
            for(Entitate e : entitati){
                System.out.println("p1: " + e.getP1() + ", p2: " + e.getP2());
            }
        }
        else System.out.println("Nu s-au gasit entitati");
    }

    private void updateUi() {
        System.out.println("p1 (int): ");
        int p1 = scanner.nextInt();
        System.out.println("p2 (string): ");
        String p2 = scanner.next();
        service.update(p1, p2);
    }

    private void deleteUi() {
        System.out.println("i: ");
        int i = scanner.nextInt();
        service.delete(i);
    }

    private void addUi() {
        System.out.println("p1 (int): ");
        int p1 = scanner.nextInt();
        System.out.println("p2 (string): ");
        String p2 = scanner.next();
        service.add(p1, p2);
    }

    private void printMenu() {
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. Update");
        System.out.println("a. Display");
    }

}
