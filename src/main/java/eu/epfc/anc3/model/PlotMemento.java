package eu.epfc.anc3.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlotMemento {
    private final ObservableList<Vegetable> elements;
    private final boolean dayWithGrass;


    PlotMemento(ObservableList<Vegetable> elements, boolean dayWithGrass){
        this.elements = elements;
        this.dayWithGrass = dayWithGrass;
    }


    boolean getDayWithGrass(){
        return dayWithGrass;
    }

    ObservableList<Vegetable> getList(){
        ObservableList<Vegetable> list = FXCollections.observableArrayList();
        for(var e : elements){
            list.add(e!=null ? e.getCopy() : null);
        }
        return list;
    }

}
