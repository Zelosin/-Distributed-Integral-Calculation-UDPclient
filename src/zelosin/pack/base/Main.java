package zelosin.pack.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static FXMLLoader mFXMLLoader = new FXMLLoader();
    public static Stage primaryStage;



    @Override
    public void start(Stage primaryStage) throws Exception{



        Main.primaryStage = primaryStage;
        mFXMLLoader.setLocation(getClass().getClassLoader().getResource("zelosin/pack/FXML's/PrimeClientWindow.fxml"));
        mFXMLLoader.load();
        primaryStage.setScene(new Scene(mFXMLLoader.getRoot()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}