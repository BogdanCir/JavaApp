package main;

import repository.BinaryFileRepository;
import repository.DBRepo;
import repository.Repository;
import repository.TextFileRepository;
import service.Service;
import ui.UI;

public class Main {
    public static void main(String[] args) {
//        Clasa Settings trebuie facut in acelasi fisier
//        String repoType = Settings.getRepositoryType();
        TextFileRepository repo = new TextFileRepository("entitati.txt");
//        BinaryFileRepository repo = new BinaryFileRepository("entitati.bin");
//        DBRepo repo = new DBRepo();
        Service service = new Service(repo);

        UI ui = new UI(service);
        ui.menu();
    }
}