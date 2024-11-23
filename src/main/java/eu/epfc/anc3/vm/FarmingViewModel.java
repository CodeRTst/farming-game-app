package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Game;
import eu.epfc.anc3.model.enums.GameStatus;
import eu.epfc.anc3.model.enums.KeyValue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.HashSet;
import java.util.Set;




public class FarmingViewModel {

    private final BooleanProperty gameIsStarted = new SimpleBooleanProperty();
    private final FieldViewModel fieldViewModel;
    private final HeadViewModel headViewModel;
    private final BottomButtonViewModel bottomButtonViewModel;
    private final SideButtonViewModel sideButtonViewModel;
    private final Set<KeyValue> pressedKeys = new HashSet<>();

    private final Game game = new Game();




    public FarmingViewModel() {
        sideButtonViewModel = new SideButtonViewModel(game);
        bottomButtonViewModel = new BottomButtonViewModel(game, sideButtonViewModel::restoreSelectedButtonProperties);
        fieldViewModel = new FieldViewModel(game);
        headViewModel = new HeadViewModel(game);
        configBindingAndListener();
    }

    private void configBindingAndListener() {
        gameIsStarted.bind(game.gameStatusProperty().isNotEqualTo(GameStatus.END).and(game.gameStatusProperty().isNotEqualTo(GameStatus.DEPART)));

        game.gameStatusProperty().addListener((observableValue, gameStatus, t1) -> {
            if (gameStatus == GameStatus.END && t1 == GameStatus.START)
                game.reset();
        });
    }



    public void move(KeyValue k) {game.move(k);}
    public void updatePlotElement() {game.updatePlot();}



    public void pressedKey(KeyValue keyValue) {
        if (gameIsStarted.get()) {
                pressedKeys.add(keyValue);
                keyListChangeOn();
        }
    }

    public void releasedKey(KeyValue keyValue) {
        if (gameIsStarted.get()) {
                pressedKeys.remove(keyValue);
                keyListChangeOn();
            }
    }

    public void keyListChangeOn() {
        for (var c : pressedKeys) {
            if (c == KeyValue.ACTION)
                updatePlotElement();
            else
                move(c);
        }
    }




    public BottomButtonViewModel getBottomButtonViewModel() {return bottomButtonViewModel;}
    public SideButtonViewModel getSideButtonViewModel() {return sideButtonViewModel;}
    public HeadViewModel getHeadViewModel(){return headViewModel;}
    public FieldViewModel getFieldViewModel() {return fieldViewModel;}

}












