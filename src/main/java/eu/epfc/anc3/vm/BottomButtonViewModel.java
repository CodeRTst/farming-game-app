package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.CareTaker;
import eu.epfc.anc3.model.Game;
import eu.epfc.anc3.model.enums.GameStatus;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;


public class BottomButtonViewModel {

    private final StringProperty startStop = new SimpleStringProperty("start");
    private final ObjectProperty<GameStatus> gameStatusProperty = new SimpleObjectProperty<>();
    private final IntegerProperty dayProperty = new SimpleIntegerProperty(1);
    private final BooleanProperty savedProperty = new SimpleBooleanProperty(false);
    private  final Runnable restoreButton;


    private final CareTaker careTaker = new CareTaker();
    private final Game game;




    public BottomButtonViewModel(Game game, Runnable restoreButton) {
        this.game = game;
        this.restoreButton = restoreButton;
        gameStatusProperty.bindBidirectional(game.gameStatusProperty());
        game.dayProperty().bindBidirectional(dayProperty);
    }

    private void setGameStatus(GameStatus gameStatus) {
        gameStatusProperty.set(gameStatus);
    }




    public void buttonActionSave(){
        this.savedProperty.set(true);
        careTaker.saveMemento(game.save());
    }

    public void buttonActionRestore(){
        game.restore(careTaker.getGameMemento());
        restoreButton.run();
    }

    public void buttonActionStart() {
        if (gameStatusProperty.get() == GameStatus.END || gameStatusProperty.get() == GameStatus.DEPART) {
            setGameStatus(GameStatus.START);
            startStop.set("end");
        } else {
            setGameStatus(GameStatus.END);
            startStop.set("start");
        }
    }

    public void buttonActionSleep() {dayProperty.set(dayProperty.get()+1);}




    public BooleanBinding disableRestoreButtonProperty(){
        return isEndGameProperty().or(savedProperty.not());
    }
    public BooleanBinding isEndGameProperty() {return gameStatusProperty.isEqualTo(GameStatus.END).or(gameStatusProperty.isEqualTo(GameStatus.DEPART));}
    public StringProperty startStopProperty() {return startStop;}
}

