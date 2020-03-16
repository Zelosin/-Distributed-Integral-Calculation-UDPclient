package zelosin.pack.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import zelosin.pack.base.EchoClient;

public class ClientController {

    @FXML
    public Button dIPConnectButton, dIPDisconnectButton;

    @FXML
    public TextField dIPTxt;

    private EchoClient tClient;

    public void initialize(){

    }

    public void onMouseClickedConnect(MouseEvent mouseEvent) {
        dIPTxt.setDisable(true);
        tClient = new EchoClient(dIPTxt.getText());
        tClient.start();
    }

    public void onMouseClickedDisconnect(MouseEvent mouseEvent) {
        dIPTxt.setDisable(false);
        tClient.disconnection();
    }
}
