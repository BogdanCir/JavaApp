package repository;


import domain.Entitate;

import java.util.ArrayList;
import java.util.List;

public class Repository <T extends Entitate> {
    public ArrayList <T> entitati;

    public Repository() {
        this.entitati = new ArrayList<>() {
        };
    }

    public void add(T entitate) {
        entitati.add(entitate);
    }

    public void delete(int i){
        for(T entitate : entitati)
            if(entitate.getP1() == i) {
                entitati.remove(entitate);
                return;
            }
    }

    public void update(T entitateNoua) {
        int positionToUpdate = -1;
        for(int i = 0; i < entitati.size(); i++)
            if(entitateNoua.getP1() == entitati.get(i).getP1())
                positionToUpdate = i;

        if (positionToUpdate == -1)
            System.out.println("Nu s-a gasit entitatea");
        entitati.set(positionToUpdate, entitateNoua);
    }

    public List<T> getEntitati() {
        return entitati;
    }

//    public T getEntitateById(int id){
//        for(T entitate: entitati)
//            if(item.getId() == id)
//                return entitate;
//    }

}
