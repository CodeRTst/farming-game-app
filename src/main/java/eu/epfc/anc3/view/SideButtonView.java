package eu.epfc.anc3.view;

import eu.epfc.anc3.model.enums.SideButtonValue;
import eu.epfc.anc3.vm.SideButtonViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

class SideButtonView extends VBox {

    private final ToggleButton grassButton = new ToggleButton("Plant grass");
    private final ToggleButton carrotButton = new ToggleButton("Plant carrot");
    private final ToggleButton cabbageButton = new ToggleButton("Plant cabbage");
    private final ToggleButton fertilizeButton = new ToggleButton("Fertilize");
    private final ToggleButton collectButton = new ToggleButton("Collect");
    private final ImageView carrot4 = new ImageView(new Image("carrot4.png"));
    private final ImageView cabbage4 = new ImageView(new Image("cabbage4.png"));
    private final ImageView grass = new ImageView(new Image("grass.png"));
    private final ImageView watering = new ImageView(new Image("watering_can.png"));
    private final ImageView shovel = new ImageView(new Image("shovel.png"));
    private final SideButtonViewModel sideButtonViewModel;


    public SideButtonView(SideButtonViewModel sideButtonViewModel) {
        this.sideButtonViewModel = sideButtonViewModel;
        configButton();
        manageButton();
        configBinding();
    }


    private void configButton() {
        grassButton.setFocusTraversable(false);
        carrotButton.setFocusTraversable(false);
        cabbageButton.setFocusTraversable(false);
        fertilizeButton.setFocusTraversable(false);
        collectButton.setFocusTraversable(false);

        grassButton.setGraphic(grass);
        carrotButton.setGraphic(carrot4);
        cabbageButton.setGraphic(cabbage4);
        fertilizeButton.setGraphic(watering);
        collectButton.setGraphic(shovel);

        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(grassButton,carrotButton, cabbageButton,fertilizeButton,collectButton);

        

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(grassButton,carrotButton, cabbageButton,fertilizeButton,collectButton);
    }


    private void manageButton() {
        grassButton.setOnMouseClicked(e -> sideButtonViewModel.buttonAction(SideButtonValue.GRASS_BUTTON));
        carrotButton.setOnMouseClicked(e -> sideButtonViewModel.buttonAction(SideButtonValue.CARROT_BUTTON));
        cabbageButton.setOnMouseClicked(e -> sideButtonViewModel.buttonAction(SideButtonValue.CABBAGE_BUTTON));
        fertilizeButton.setOnMouseClicked(e -> sideButtonViewModel.buttonAction(SideButtonValue.FERTILIZE_BUTTON));
        collectButton.setOnMouseClicked(e -> sideButtonViewModel.buttonAction(SideButtonValue.COLLECT_BUTTON));
    }

    private void configBinding() {
        grassButton.selectedProperty().bindBidirectional(sideButtonViewModel.grassButtonIsSelectedPropertyProperty());
        carrotButton.selectedProperty().bindBidirectional(sideButtonViewModel.carrotButtonIsSelectedPropertyProperty());
        cabbageButton.selectedProperty().bindBidirectional(sideButtonViewModel.cabbageButtonIsSelectedPropertyProperty());
        fertilizeButton.selectedProperty().bindBidirectional(sideButtonViewModel.fertilizeButtonIsSelectedPropertyProperty());
        collectButton.selectedProperty().bindBidirectional(sideButtonViewModel.collectButtonIsSelectedPropertyProperty());

        grassButton.disableProperty().bind(sideButtonViewModel.isEndGameProperty());
        carrotButton.disableProperty().bind(sideButtonViewModel.isEndGameProperty());
        cabbageButton.disableProperty().bind(sideButtonViewModel.isEndGameProperty());
        fertilizeButton.disableProperty().bind(sideButtonViewModel.isEndGameProperty());
        collectButton.disableProperty().bind(sideButtonViewModel.isEndGameProperty());
    }
}
