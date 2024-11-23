package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.HeadViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

class HeadView extends HBox {
    private final HeadViewModel headViewModel;
    private final Label lblScore = new Label("Score: ");
    private final Label lblDay = new Label("Day: ");
    private final TextField txtScore = new TextField("0");
    private final TextField txtDay = new TextField("1");


     private final IntegerProperty dayProperty = new SimpleIntegerProperty();
     private final IntegerProperty scoreProperty = new SimpleIntegerProperty();



     HeadView(HeadViewModel headViewModel){
        this.headViewModel = headViewModel;
        configHeader();
        configBindingAndListener();
     }

    private  void configHeader() {
         HBox hbxScore = new HBox(lblScore,txtScore);
         HBox hbxDay = new HBox(lblDay,txtDay);

         hbxScore.setAlignment(Pos.CENTER);
         hbxDay.setAlignment(Pos.CENTER);


        txtScore.setDisable(true);
        txtScore.setPrefWidth(66);
        txtScore.setAlignment(Pos.CENTER);
        txtDay.setDisable(true);
        txtDay.setPrefWidth(33);
        txtDay.setAlignment(Pos.CENTER);


        setPadding(new Insets(10));
        setSpacing(250);
        setAlignment(Pos.CENTER);
        getChildren().addAll(hbxScore,hbxDay);

    }

    private void configBindingAndListener(){
        dayProperty.bind(headViewModel.dayProperty());
        scoreProperty.bind(headViewModel.scoreProperty());

        dayProperty.addListener((observable -> txtDay.setText(dayProperty.get() + "")));
        scoreProperty.addListener(observable -> txtScore.setText(scoreProperty.get() + ""));
    }

}
