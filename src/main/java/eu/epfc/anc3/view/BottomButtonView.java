package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.BottomButtonViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class BottomButtonView extends HBox {

    private final Button startStopButton = new Button();
    private final Button sleepButton = new Button("sleep");
    private final Button saveButton = new Button("save");
    private final Button restoreButton = new Button("restore");


    private final BottomButtonViewModel bottomButtonViewModel;

     BottomButtonView(BottomButtonViewModel bottomButtonViewModel) {
        this.bottomButtonViewModel = bottomButtonViewModel;
        configButton();
        manageButton();
        configBinding();
    }


    private void configButton() {
        startStopButton.setFocusTraversable(false);
        sleepButton.setFocusTraversable(false);
        saveButton.setFocusTraversable(false);
        restoreButton.setFocusTraversable(false);


        setPadding(new Insets(10));
        setSpacing(100);
        setAlignment(Pos.CENTER);
        getChildren().addAll(startStopButton, sleepButton,saveButton,restoreButton);

    }

    private void manageButton() {
        startStopButton.setOnMouseClicked(e -> bottomButtonViewModel.buttonActionStart());
        sleepButton.setOnMouseClicked(e -> bottomButtonViewModel.buttonActionSleep());
        saveButton.setOnMouseClicked(e -> bottomButtonViewModel.buttonActionSave());
        restoreButton.setOnMouseClicked(e -> bottomButtonViewModel.buttonActionRestore());
    }

    private void configBinding() {
        startStopButton.textProperty().bind(bottomButtonViewModel.startStopProperty());
        sleepButton.disableProperty().bind(bottomButtonViewModel.isEndGameProperty());
        saveButton.disableProperty().bind(bottomButtonViewModel.isEndGameProperty());
        restoreButton.disableProperty().bind(bottomButtonViewModel.disableRestoreButtonProperty());
    }



}