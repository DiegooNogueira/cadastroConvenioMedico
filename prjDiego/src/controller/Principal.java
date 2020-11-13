

package controller;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Principal extends Application  {
    public static Stage palco = new Stage();
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("prjDiegoPU");
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL arquivo = getClass().getResource("/view/Cadastro.fxml");
        Parent raiz = FXMLLoader.load(arquivo);
        Scene cena = new Scene(raiz);
        
        palco.setTitle("Cadastro de Clientes e dependentes");
        palco.setScene(cena);
        palco.show();
    }
    public static void main(String[] args) {
      launch(args);
    }
    
}
