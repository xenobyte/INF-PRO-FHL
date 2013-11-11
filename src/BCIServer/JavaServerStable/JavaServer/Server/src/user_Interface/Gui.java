package user_Interface;

import ConstantsAndResponsePackages.Constants;
import threads.Controller;
import udpCom.UDPServer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Gui extends Application{
    private Stage stage;
    private Scene scene;
    private Controller ctrl;
    private StringParser parser;
    private int sizex = 800;
    private int sizey = 600;
    private static TextArea threadField;
    private static TextArea threadInf;
    private TextField commandLine;
    private UDPServer u = new UDPServer(Constants.FORWARDINGPORT, Constants.SERVERIP, Constants.SERVERPORT);
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        ctrl = new Controller(Constants.SERVERIP, Constants.SERVERPORT);
        parser = new StringParser(ctrl); 
        stage = new Stage();
        scene = new Scene(new Group(), sizex, sizey);
        stage.setResizable(false);
        ObservableList<Node> content = ((Group) scene.getRoot()).getChildren();
        
        threadField = new TextArea();
        threadField.setMinHeight(sizey - 60);
        threadField.setMinWidth((sizex / 2) -20);
        threadField.setMaxWidth((sizex / 2) -20);
        threadField.relocate(10, 10);
        threadField.setEditable(false);
        content.add(threadField);
        
        threadInf = new TextArea();
        threadInf.setMinHeight(sizey - 60);
        threadInf.setMinWidth((sizex / 2) -20);
        threadInf.setMaxWidth((sizex / 2) -20);
        threadInf.relocate(10 + (sizex / 2), 10);
        threadInf.setEditable(false);
        content.add(threadInf);
        
        commandLine = new TextField();
        commandLine.setMinHeight(40);
        commandLine.setMinWidth(sizex - 20);
        commandLine.setMaxWidth(sizex - 20);
        commandLine.relocate(10, sizey - 40);
        commandLine.setEditable(true);
        content.add(commandLine);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER  && commandLine.getText() != "") {                    
                    System.out.println(commandLine.getText());
                    //String f = commandLine.getText().
                    String s = parser.doMagic(commandLine.getText().replaceAll(" ", ""));
                    if(s != null){
                        threadInf.clear();
                        threadInf.appendText(s);
                    }
                    threadField.clear();
                    threadField.appendText(ctrl.getString());
                    commandLine.clear();
                    
                }   
            }
        });
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent arg0) {
                System.exit(0);
            }
        });
        
        stage.setScene(scene);
        stage.show();
        
    }

}
