package Client.Menus;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    @FXML
    private ScrollPane mainscrollpane;

    public static AnchorPane rootP;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = root;
        initializeGui();

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();

            } else
                drawer.open();

        });
    }

    private void initializeGui(){
        // hide scroll pane scroller
        mainscrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainscrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // Assign sidebar UI to drawer
        try {
            VBox box = FXMLLoader.load(getClass().getResource("fxml/SideBarContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Load home screen
        try {
            AnchorPane coinbrowser = FXMLLoader.load(getClass().getResource("fxml/CoinsBrowser.fxml"));
            mainscrollpane.setContent(coinbrowser);
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
