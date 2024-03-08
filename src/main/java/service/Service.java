package service;



import domain.Entitate;
import repository.BinaryFileRepository;
import repository.DBRepo;
import repository.Repository;
import repository.TextFileRepository;
import java.util.ArrayList;

public class Service {
//    PENTRU CONSOLA
//    public Repository <Entitate> repo = new Repository<>();
//    public Service(Repository<Entitate> repo) {
//        this.repo = repo;
//    }

//    PENTRU FISIERE TEXT

    public TextFileRepository repo;
    public Service(TextFileRepository repo) {
        this.repo = repo;
    }

//    PENTRU FISIERE BINARE
//    public BinaryFileRepository repo;
//    public Service(BinaryFileRepository repo) {
//        this.repo = repo;
//    }

    public void add (int p1, String p2){
        Entitate e = new Entitate(p1, p2);
        this.repo.add(e);
    }

    public void delete (int i){
        repo.delete(i);
    }

    public void update(int p1, String p2){
        Entitate entitate = new Entitate(p1, p2);
        repo.update(entitate);
    }

    public ArrayList<Entitate> getAllEntitati() {return (ArrayList<Entitate>) this.repo.getEntitati();}

}
