package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.entitys.Vrtuser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void handleButtonCreateUser(ActionEvent event){
        
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("PUVertical");
         //EntityManager em = factory.createEntityManager();
        
        Vrtuser u = new Vrtuser();
        u.setUsrname("TestUser1");
        u.setUsrpassword("1111");
        VrtuserJpaController uc = new VrtuserJpaController(factory);
        
        uc.create(u);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
