/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofnim;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author pant9060
 */
public class GUIController implements Initializable {
    @FXML
    private Label label,label1;
    
    @FXML
    private TextField textarea1;
    
    //sets the mode as easy, gets the name of the user and puts those as parameters in a new instance of the class
    //marbles.
    @FXML
    private void handleEasy(ActionEvent event) {
        Marbles newEasyGame = new Marbles("easy",getName());
        newEasyGame.subtractNumbers();
    }
    
    //sets the mode as hard, gets the name of the user and puts those as parameters in a new instance of the class
    //marbles.
    @FXML
    private void handleHard(ActionEvent event){
        getName();
        Marbles hardGame = new Marbles("hard",getName());
        hardGame.subtractNumbers();
    }
    
    //gets the name of the user from the textarea and returns that value.
    private String getName(){
        return textarea1.getText();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
