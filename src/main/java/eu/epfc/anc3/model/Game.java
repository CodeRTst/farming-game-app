package eu.epfc.anc3.model;


import eu.epfc.anc3.model.enums.GameStatus;
import eu.epfc.anc3.model.enums.KeyValue;
import eu.epfc.anc3.model.enums.PlotElement;
import javafx.beans.property.*;

public class Game {

    private final Field farmingField;
    private final Farmer farmer;
    private final IntegerProperty dayProperty = new SimpleIntegerProperty();
    private final IntegerProperty scoreProperty = new SimpleIntegerProperty();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.DEPART);



    public Game() {
        farmingField = new Field();
        farmer = new Farmer();
        configBinding();
    }

    private void configBinding() {
        farmingField.farmerPosLineProperty().bind(farmer.lineProperty());
        farmingField.farmerPosColProperty().bind(farmer.colProperty());
        farmingField.dayProperty().bind(dayProperty);
        scoreProperty.bindBidirectional(farmingField.scoreProperty());
    }


    //GETTER
    public GameStatus getGameStatus() {
        return gameStatus.get();
    }
    private int getDay() {
        return dayProperty.get();
    }
    private int getScore() {
        return scoreProperty.get();
    }




    public void updatePlot() {
        switch (getGameStatus()) {
            case PLANT_GRASS -> plant(PlotElement.GRASS);
            case PLANT_CARROT -> plant(PlotElement.CARROT);
            case PLANT_CABBAGE -> plant(PlotElement.CABBAGE);
            case FERTILIZE -> fertilize();
            case COLLECT -> collect();
        }
    }



    private void plant(PlotElement plotElement) {farmingField.plant(plotElement);}
    private void fertilize() {farmingField.fertilize();}
    private void collect() {farmingField.collect();}




    public void move(KeyValue k) {farmer.move(k);}
    public void teleport(int line, int col) {
        farmer.teleport(line,col);
    }





    public void reset() {
        dayProperty.set(1);
        scoreProperty.set(0);
        farmer.reset();
        farmingField.reset();
    }
    public GameMemento save(){return new GameMemento(farmingField.save(), getGameStatus(),farmer.save(), getDay(),getScore());}
    public void restore(GameMemento gameMemento){
        dayProperty.set(gameMemento.getDay());
        scoreProperty.set(gameMemento.getScore());
        farmingField.restore(gameMemento.getPlotMementos());
        gameStatus.set(gameMemento.getGameStatus());
        farmer.restore(gameMemento.getFarmerMemento());
    }





    public ObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }
    public BooleanProperty isFarmerOnPlotProperty(int line, int col) {return farmingField.isFarmerOnPlotProperty(line,col);}
    public IntegerProperty dayProperty() {return dayProperty;}
    public IntegerProperty scoreProperty() {return scoreProperty;}
    public SimpleListProperty<Vegetable> elementsProperty(int i, int j) {return farmingField.elementsProperty(i,j);}




}
