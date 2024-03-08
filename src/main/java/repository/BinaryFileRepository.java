package repository;

import domain.Entitate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepository extends Repository<Entitate> {
    private String fileName;
    public BinaryFileRepository(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        readFromFile();
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                List<?> deserializedList = (List<?>) obj;

                if (!deserializedList.isEmpty() && deserializedList.get(0) instanceof Entitate) {
                    this.entitati = (ArrayList<Entitate>) obj;
                } else {
                    System.err.println("Unexpected object type in the file.");
                }
            }
        } catch (EOFException e) {
            // Sfârșitul fișierului
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    private void writeInFile() throws IOException {
        try {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(new ArrayList<>(entitati.stream().toList()));
            }
        } catch (IOException e) {
            e.printStackTrace(); //  tratarea erorilor aici
        }
    }

    public void add(Entitate e){
        super.add(e);
        try {
            writeInFile();
        } catch (IOException ex) {
            throw new RuntimeException("Nu s-a salvat in fisierul binar" + ex);
        }
    }

    @Override
    public void delete(int i) {
        super.delete(i);
        try {
            writeInFile();
            System.out.println("S-a sters din fisierul binar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Entitate entitateNoua) {
        super.update(entitateNoua);
        try {
            writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

