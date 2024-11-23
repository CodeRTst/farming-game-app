//ANC3 2223 g01
package eu.epfc.anc3.app;

import eu.epfc.anc3.view.FarmingView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        new FarmingView(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
