package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Game;
import eu.epfc.anc3.model.enums.GameStatus;
import eu.epfc.anc3.model.enums.SideButtonValue;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SideButtonViewModel {


    private final ObjectProperty<GameStatus> gameStatusProperty = new SimpleObjectProperty<>();


    private final BooleanProperty grassButtonIsSelectedProperty = new SimpleBooleanProperty();
    private final BooleanProperty carrotButtonIsSelectedProperty = new SimpleBooleanProperty();
    private final BooleanProperty cabbageButtonIsSelectedProperty = new SimpleBooleanProperty();
    private final BooleanProperty fertilizeButtonIsSelectedProperty = new SimpleBooleanProperty();
    private final BooleanProperty collectButtonIsSelectedProperty = new SimpleBooleanProperty();



    public SideButtonViewModel(Game game) {
        configBindingAndListener(game);
    }


    private void configBindingAndListener(Game game) {
        gameStatusProperty.bindBidirectional(game.gameStatusProperty());

        gameStatusProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue == GameStatus.END)
                unSelectedButtonProperties();
        });
    }

    private void unSelectedButtonProperties() {
        grassButtonIsSelectedProperty.set(false);
        carrotButtonIsSelectedProperty.set(false);
        cabbageButtonIsSelectedProperty.set(false);
        fertilizeButtonIsSelectedProperty.set(false);
        collectButtonIsSelectedProperty.set(false);
    }

    void restoreSelectedButtonProperties() {
        switch (gameStatusProperty.get()){
            case PLANT_CARROT -> carrotButtonIsSelectedProperty.set(true);
            case PLANT_CABBAGE -> cabbageButtonIsSelectedProperty.set(true);
            case FERTILIZE -> fertilizeButtonIsSelectedProperty.set(true);
            case COLLECT -> collectButtonIsSelectedProperty.set(true);
            case START -> unSelectedButtonProperties();
        }
    }



    public void buttonAction(SideButtonValue sideButtonValue) {
        switch (sideButtonValue) {
            case GRASS_BUTTON -> setGameStatusProperty(grassButtonIsSelectedProperty.get() ? GameStatus.PLANT_GRASS : GameStatus.START);
            case CARROT_BUTTON -> setGameStatusProperty(carrotButtonIsSelectedProperty.get() ? GameStatus.PLANT_CARROT : GameStatus.START);
            case CABBAGE_BUTTON -> setGameStatusProperty(cabbageButtonIsSelectedProperty.get() ? GameStatus.PLANT_CABBAGE : GameStatus.START);
            case FERTILIZE_BUTTON -> setGameStatusProperty(fertilizeButtonIsSelectedProperty.get() ? GameStatus.FERTILIZE : GameStatus.START);
            case COLLECT_BUTTON -> setGameStatusProperty(collectButtonIsSelectedProperty.get() ? GameStatus.COLLECT : GameStatus.START);
        }
    }



    private void setGameStatusProperty(GameStatus gameStatus) {
        gameStatusProperty.set(gameStatus);
    }

    public BooleanBinding isEndGameProperty() {return gameStatusProperty.isEqualTo(GameStatus.END).or(gameStatusProperty.isEqualTo(GameStatus.DEPART));}
    public BooleanProperty grassButtonIsSelectedPropertyProperty() {return grassButtonIsSelectedProperty;}
    public BooleanProperty carrotButtonIsSelectedPropertyProperty() {return carrotButtonIsSelectedProperty;}
    public BooleanProperty cabbageButtonIsSelectedPropertyProperty() {return cabbageButtonIsSelectedProperty;}
    public BooleanProperty fertilizeButtonIsSelectedPropertyProperty() {return fertilizeButtonIsSelectedProperty;}
    public BooleanProperty collectButtonIsSelectedPropertyProperty() {return collectButtonIsSelectedProperty;}
}
