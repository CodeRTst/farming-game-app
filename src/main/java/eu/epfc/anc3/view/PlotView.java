package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Vegetable;
import eu.epfc.anc3.vm.PlotViewModel;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

class PlotView extends StackPane {



    private final BooleanProperty isFarmerOnPlot = new SimpleBooleanProperty();
    private final SimpleListProperty<Vegetable> elements = new SimpleListProperty<>();
    private static final Image farmerImage = new Image("farmer.png");


     private final ImageView farmerView = new ImageView(farmerImage);
     private final ImageView firstLayerView = new ImageView(new Image("dirt.png"));
     private final ImageView secondLayerView = new ImageView();




     PlotView(PlotViewModel plotViewModel) {

        configBindingAndListener(plotViewModel);
        configFitSizeProperty();
        farmerView.setPreserveRatio(true);

        getChildren().addAll(firstLayerView, secondLayerView, farmerView);
        farmerView.setVisible(isFarmerOnPlot.get());
    }
    


    private void configFitSizeProperty() {
        firstLayerView.fitHeightProperty().bind(this.heightProperty());
        firstLayerView.fitWidthProperty().bind(this.widthProperty());
        secondLayerView.fitHeightProperty().bind(this.heightProperty());
        secondLayerView.fitWidthProperty().bind(this.widthProperty());
    }

    private void configBindingAndListener(PlotViewModel plotViewModel) {
        isFarmerOnPlot.bind(plotViewModel.isFarmerOnPlotProperty());
        elements.bind(plotViewModel.elementsProperty());

        elements.addListener((observableValue, oldValue, newValue) -> setPlotImages());
        isFarmerOnPlot.addListener((observable, oldValue, newValue) -> farmerView.setVisible(newValue));
        this.setOnMouseClicked(e -> plotViewModel.teleport());
    }

    private void setPlotImages() {
         setFirstLayerView();
         setSecondLayerView();
    }

    private void setFirstLayerView() {
        switch (elements.get(0).getPlotElement()) {
            case GRASS -> firstLayerView.setImage(new Image("grass.png"));
            case DIRT -> firstLayerView.setImage(new Image("dirt.png"));
        }
    }

    private void setSecondLayerView() {
        if (elements.get(1) == null)
            secondLayerView.setImage(null);
        else
            switch (elements.get(1).getPlotElement()) {
                case CARROT -> {
                    switch (elements.get(1).getState()) {
                        case SOWING -> secondLayerView.setImage(new Image("carrot1.png"));
                        case STATE2 -> secondLayerView.setImage(new Image("carrot2.png"));
                        case STATE3 -> secondLayerView.setImage(new Image("carrot3.png"));
                        case STATE4 -> secondLayerView.setImage(new Image("carrot4.png"));
                        case ROTTEN -> secondLayerView.setImage(new Image("rotten_carrot.png"));
                    }
                }
                case CABBAGE -> {
                    switch (elements.get(1).getState()) {
                        case SOWING -> secondLayerView.setImage(new Image("cabbage1.png"));
                        case STATE2 -> secondLayerView.setImage(new Image("cabbage2.png"));
                        case STATE3 -> secondLayerView.setImage(new Image("cabbage3.png"));
                        case STATE4 -> secondLayerView.setImage(new Image("cabbage4.png"));
                        case ROTTEN -> secondLayerView.setImage(new Image("rotten_cabbage.png"));
                    }
                }
            }

    }



}