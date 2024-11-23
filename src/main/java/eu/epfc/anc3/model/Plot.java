package eu.epfc.anc3.model;

import eu.epfc.anc3.model.enums.PlotElement;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class Plot {




    private final BooleanProperty isFarmerOnPlot = new SimpleBooleanProperty(false);
    private final ObservableList<Vegetable> plotElements = FXCollections.observableArrayList(param ->new Observable[]{param.stateVegetableProperty()});
    private final SimpleListProperty<Vegetable> plotElementsProperty = new SimpleListProperty<>(plotElements);
    private final IntegerProperty dayProperty = new SimpleIntegerProperty();
    private boolean dayWithGrass = false;
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty();





    Plot() {
        plotElements.addAll(new Dirt(),null);
        dayProperty.addListener((observable, oldValue, newValue) -> {if (newValue.intValue() > oldValue.intValue() && dayProperty.get() > 1) sleep();});
    }

    void reset() {plotElements.setAll(new Dirt(),null);}





    void fertilize() {
        for (var e : plotElements)
            if (e != null)
                e.fertilize();
    }

    void collect() {
        for (int i = plotElements.size() - 1; i >=0 ; i--) {
            var e = plotElements.get(i);
            if (e != null) {
                e.callback();
                break;
            }
        }
    }


    private void sleep() {
        for (var e : plotElements)
            if (e != null)
                e.grow();
    }



    void plant(PlotElement plotElement) {
        switch (plotElement) {
            case DIRT, GRASS -> setFirstPlotElement(plotElement);
            case CARROT, CABBAGE -> setSecondPlotElement(plotElement);
        }
    }




    private void setFirstPlotElement(PlotElement plotElement) {
        if (plotElements.get(0).getPlotElement() != plotElement)
            switch (plotElement) {
                case GRASS -> {
                    plotElements.set(0,new Grass(() -> setFirstPlotElement(PlotElement.DIRT)));
                    setDayWithGrass(true);
                }
                case DIRT -> {
                    plotElements.set(0,new Dirt());
                    setDayWithGrass(false);
                }
            }
    }

    private void setSecondPlotElement(PlotElement plotElement) {
        if (plotElements.get(1) == null)
            switch (plotElement) {
                case CARROT -> plotElements.set(1,new Carrot(dayWithGrass, this::setNullSecondPlotElement));
                case CABBAGE -> plotElements.set(1,new Cabbage(dayWithGrass, this::setNullSecondPlotElement));
            }
    }


    private void setDayWithGrass(boolean dayWithGrass) {
        this.dayWithGrass = dayWithGrass;
        for (int i = 1; i < plotElements.size(); i++)
            if (plotElements.get(1) != null)
                plotElements.get(1).setNbDayWithGrass(dayWithGrass);
    }

    private void setNullSecondPlotElement() {
        scoreProperty.set(scoreProperty.get() + plotElements.get(1).collect());
        plotElements.set(1,null);
    }
    void setIsFarmerOnPlot(boolean isFarmerOnPlot) {this.isFarmerOnPlot.set(isFarmerOnPlot);}




    private ObservableList<Vegetable> copyList(){
        ObservableList<Vegetable> list = FXCollections.observableArrayList();
        for(var e :plotElements)
            list.add(e!=null ? e.getCopy() : null);
        return list;
    }
    PlotMemento save(){return  new PlotMemento(copyList(), dayWithGrass);}
    void restore(PlotMemento plotMemento){
        ObservableList<Vegetable> list = plotMemento.getList();
        plotElements.setAll(list);
        dayWithGrass = plotMemento.getDayWithGrass();
    }




    BooleanProperty isFarmerOnPlotProperty() {return isFarmerOnPlot;}
    SimpleListProperty<Vegetable> plotElementsProperty() {return plotElementsProperty;}
    IntegerProperty dayProperty() {return dayProperty;}
    IntegerProperty scoreProperty() {return scoreProperty;}

}
