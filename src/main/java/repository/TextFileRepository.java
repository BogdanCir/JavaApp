package repository;

import domain.Entitate;

import java.io.*;

public class TextFileRepository extends Repository<Entitate>{
    private String fileName;
    public TextFileRepository(String fileName) {
        this.fileName = fileName;
        try{
            File file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        readFromFile();
    }


//    CITIRE DIN FISIER
    private void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                Entitate masina = convertReadLineToEntity(linie);
                entitati.add(masina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Entitate convertReadLineToEntity(String linie) {
        String[] parts = linie.split(" "); // Presupunem că datele din fișier sunt separate prin virgulă
        int p1 = Integer.parseInt(parts[0]);
        String p2 = parts[1];

        Entitate patient = new Entitate(p1, p2);
        return patient;
    }





//  SCRIE IN FISIER
    private void writeInFile() throws IOException {
        // FIXME try with resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Entitate entitate : entitati) {
                String linie = convertEntityInLine(entitate);
//                System.out.println("writeInFile " + linie + " " + fileName);
                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String convertEntityInLine(Entitate entitate) {
        String result = entitate.getP1() + " " + entitate.getP2();
        return result; // Trebuie implementat conform logicii entității specifice
    }




//CUD
    public void update(Entitate e){
        super.update(e);
        try {
            writeInFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void delete(int i){
        super.delete(i);
        try {
            writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void add(Entitate e){
        super.add(e);
        try {
            writeInFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
