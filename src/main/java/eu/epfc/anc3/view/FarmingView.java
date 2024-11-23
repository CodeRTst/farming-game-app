package eu.epfc.anc3.view;

import eu.epfc.anc3.model.enums.KeyValue;
import eu.epfc.anc3.vm.FarmingViewModel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FarmingView extends BorderPane {

    private final FarmingViewModel farmingViewModel = new FarmingViewModel();


    private static final int SCENE_MIN_WIDTH = 1100;
    private static final int SCENE_MIN_HEIGHT = 650;


    static final int FIELD_VIEW_WIDTH = 900;
    static final int FIELD_VIEW_HEIGHT = 540;
    static final int FIELD_WIDTH = 15;
    static final int FIELD_LENGTH = 25;



    public FarmingView(Stage stage) {
        start(stage);
    }

    private void start(Stage stage) {
        configMainComponents();
        Scene scene =  new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Project ANC3 2223 g01");
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        configEventHandler(scene,stage);
    }



    private void configMainComponents() {
        configBottomButton();
        configField();
        configHead();
        configSideButton();
    }

    private void configEventHandler(Scene scene, Stage stage) {
        EventHandler<KeyEvent> released = e -> {
            switch (e.getCode()) {
                case SPACE -> farmingViewModel.releasedKey(KeyValue.ACTION);
                case UP -> farmingViewModel.releasedKey(KeyValue.UP);
                case DOWN -> farmingViewModel.releasedKey(KeyValue.DOWN);
                case LEFT -> farmingViewModel.releasedKey(KeyValue.LEFT);
                case RIGHT -> farmingViewModel.releasedKey(KeyValue.RIGHT);
            }
        };



        EventHandler<KeyEvent> pressed = e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                stage.close();
            else
                switch (e.getCode()) {
                    case SPACE -> farmingViewModel.pressedKey(KeyValue.ACTION);
                    case UP -> farmingViewModel.pressedKey(KeyValue.UP);
                    case DOWN -> farmingViewModel.pressedKey(KeyValue.DOWN);
                    case LEFT -> farmingViewModel.pressedKey(KeyValue.LEFT);
                    case RIGHT -> farmingViewModel.pressedKey(KeyValue.RIGHT);
                }
        };


        scene.setOnKeyReleased(released);
        scene.setOnKeyPressed(pressed);
    }


    private void configBottomButton() {
        BottomButtonView bottomButtonView = new BottomButtonView(farmingViewModel.getBottomButtonViewModel());
        setBottom(bottomButtonView);
    }
    private void configSideButton() {
        SideButtonView sideButtonView = new SideButtonView(farmingViewModel.getSideButtonViewModel());
        setRight(sideButtonView);
    }
    private void configField() {
        FieldView fieldView = new FieldView(farmingViewModel.getFieldViewModel());
        setCenter(fieldView);
    }
    private void configHead(){
        HeadView headView = new HeadView(farmingViewModel.getHeadViewModel());
        setTop(headView);
    }

}


