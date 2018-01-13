package Client.Menus.Controllers;

import Client.ClientContainer;
import Shared.NewsPiece;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class NewsViewController {
    public Accordion accordion;
    private ClientContainer clientContainer;
    private ObservableList<NewsPiece> newsPieces;

    public void setClientContainer(ClientContainer clientContainer) {
        this.clientContainer = clientContainer;
        this.newsPieces = clientContainer.getNews();

        Platform.runLater(fillAccordion());
    }

    private void loadContent(WebView webView, NewsPiece newsPiece){
        WebEngine webEngine = webView.getEngine();
        webEngine.load(newsPiece.getGuid());
    }

    private Task<Void> fillAccordion(){
        Task fillAccordion = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (NewsPiece n : newsPieces) {

                    WebView webView = new WebView();
                    webView.setMinSize(400,600);

                    TitledPane tp = new TitledPane();
                    tp.onMouseClickedProperty().set(event -> loadContent(webView, n));
                    tp.setText(n.getDescription());
                    tp.setContent(webView);

                    accordion.getPanes().add(tp);
                }
                return null;
            }
        };
        return fillAccordion;
    }

}
