
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Contato;
import model.Dependente;



public class CadastroController implements Initializable {

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtDataNascCliente;

    @FXML
    private JFXComboBox<Character> cbxSexoCliente;

    @FXML
    private Spinner<Double> spnRendaMensal;

    @FXML
    private TextField txtNomeDependente;

    @FXML
    private Spinner<Integer> spnIdade;

    @FXML
    private JFXComboBox<String> cbxParentesco;

    @FXML
    private JFXComboBox<Character> cbxSexoDependente;

    @FXML
    private Button btnAddDependente;

    @FXML
    private ListView<Dependente> lstDependentes;

    @FXML
    private JFXComboBox<String> cbxClassificacao;

    @FXML
    private JFXComboBox<String> cbxTipo;

    @FXML
    private TextField txtContato;

    @FXML
    private Button btnAddContato;

    @FXML
    private ListView<Contato> lstContatos;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnLimpar;
    
    private void fechar(){
        System.exit(0);
    }
    String Comunicado = "";
    boolean ok;
    
    //adiciona o objeto Depen a listView com a condição de que todos os campos estejam preenchidos
    private void adicionarDependentes(){
        if(txtNomeDependente.getText().length()>0 &&
           cbxSexoDependente.getValue() != null &&
           cbxParentesco.getValue() != null     ){         
            Dependente Depen;
                 Depen = new Dependente(
                 txtNomeDependente.getText(),
                 Integer.valueOf(spnIdade.getEditor().getText()),
                 cbxSexoDependente.getValue(),
                 cbxParentesco.getValue()                  
                 );        
        lstDependentes.getItems().add(Depen); 
        limparDependente();
        }else{
               Comunicado = "Preencha os campos corretamente!!!";
            JOptionPane.showMessageDialog (null, Comunicado);
        }     
    }
    //adiciona o objeto Contat a listView com a condição de que todos os campos estejam preenchidos
    private void adicionarContatos(){
        if(txtContato.getText().length()>0 &&
           cbxClassificacao.getValue() != null &&
           cbxTipo.getValue() != null     ){ 
        Contato Contat;
         Contat = new Contato(
                 cbxTipo.getValue(),
                 cbxClassificacao.getValue(),
                 txtContato.getText()                
                 );       
        lstContatos.getItems().add(Contat);
        limparContato();
        }else{
            Comunicado = "Preencha os campos corretamente!!!";
            JOptionPane.showMessageDialog (null, Comunicado);
        }
    }
    
    //limpa todos os campos em geral
    private void limpar(){
        txtNomeCliente.setText("");
        txtNomeDependente.setText("");
        txtDataNascCliente.setText("");        
        spnRendaMensal.getEditor().setText("1200");
        spnIdade.getEditor().setText("15");
        lstDependentes.getItems().clear();
        txtContato.setText("");
        lstContatos.getItems().clear();
        cbxSexoCliente.getSelectionModel().clearSelection();
        cbxParentesco.getSelectionModel().clearSelection();
        cbxSexoDependente.getSelectionModel().clearSelection();
        cbxClassificacao.getSelectionModel().clearSelection();
        cbxTipo.getSelectionModel().clearSelection();
        Comunicado = ""; 
    }
    
    //limpa só os campos do dependente
    private void limparDependente(){
        txtNomeDependente.setText("");
        cbxSexoDependente.getSelectionModel().clearSelection();
        cbxParentesco.getSelectionModel().clearSelection();
        spnIdade.getEditor().setText("15");
    }
    
    //limpa só os campos do contato
    private void limparContato(){
        txtContato.setText("");
        cbxClassificacao.getSelectionModel().clearSelection();
        cbxTipo.getSelectionModel().clearSelection();
    }
    //salva o cadastro do cliente caso todos os campos do cliente estejam preenchidos e tenha no minimo um contato
    private void salvar(){
         //pega o numero de objetos na list view
         int totalDependentes = lstDependentes.getItems().size();
         int totalContatos = lstContatos.getItems().size(); 
         
         //valida se o nome do cliente foi preenchido
         if("".equals(txtNomeCliente.getText())){           
               Comunicado = "Nome do cliente não informado!!!";
            JOptionPane.showMessageDialog (null, Comunicado);
            
          //valida se a data de Nascimento do cliente foi preenchida
         }else if("".equals(txtDataNascCliente.getText())){
             Comunicado = "Data de Nascimento do cliente não informado!!!";
            JOptionPane.showMessageDialog (null, Comunicado);
            
            //valida se o sexo do cliente foi preenchido
         }else if(cbxSexoCliente.getValue() == null){
             Comunicado = "Sexo do cliente não informado!!!";
            JOptionPane.showMessageDialog (null, Comunicado);
         }else{
             //valida se tem pelo menos um contato
             if(totalContatos > 0){
                EntityManager em = Principal.emf.createEntityManager();
                Cliente cliente = new Cliente(txtNomeCliente.getText(), txtDataNascCliente.getText(), cbxSexoCliente.getValue(), Double.valueOf(spnRendaMensal.getEditor().getText().replace(",",".")));
               em.getTransaction().begin();
               em.persist(cliente);
               em.getTransaction().commit();

               for (int aux = 0; aux < totalDependentes; aux++) {
                   lstDependentes.getItems().get(aux).setTitular(cliente);
                   em.getTransaction().begin();
                   em.persist(lstDependentes.getItems().get(aux));
                   em.getTransaction().commit();
              }

              for (int aux = 0; aux < totalContatos; aux++) {
                   lstContatos.getItems().get(aux).setCliente(cliente);
                   em.getTransaction().begin();
                   em.persist(lstContatos.getItems().get(aux));
                   em.getTransaction().commit();
              }
            
               em.close();           
               Comunicado = "Cadastro feito com sucesso !!!";
               JOptionPane.showMessageDialog (null, Comunicado);
               limpar();
            }else{
                Comunicado = "Necessário pelo menos um contato!!!";
               JOptionPane.showMessageDialog (null, Comunicado);
            } 
         }
         Comunicado = "";
    }
    
    //seta os valores no spinner de renda mensal
    private void configurarRendaMensal(){
        SpinnerValueFactory rendas = new SpinnerValueFactory.DoubleSpinnerValueFactory(1050, 50000, 1200, 0.01);
        spnRendaMensal.setValueFactory(rendas);
        spnRendaMensal.setEditable(true);
    }
    
    //seta os valores no spinner de idade
    private void configurarIdade(){
        SpinnerValueFactory idades = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120, 15, 1);
        spnIdade.setValueFactory(idades);
        spnIdade.setEditable(true);
    }
    
    //seta os valores nas Combo box de sexo
    private void preencherSexos(){
        List<Character> sexos = new ArrayList<>();
        
        sexos.add('M');
        sexos.add('F');
        
        
        Collections.sort(sexos);
        
        cbxSexoCliente.getItems().addAll(sexos);
        cbxSexoDependente.getItems().addAll(sexos);
        
    }
    
    //seta os valores nas Combo box na classificacao do contato
    private void preencherClassificacao(){
        List<String> classificacoes = new ArrayList<>();
        
        classificacoes.add("Pessoal");
        classificacoes.add("Profissional");
        
        
        Collections.sort(classificacoes);
        
        cbxClassificacao.getItems().addAll(classificacoes);
        
    }
    
    //seta os valores nas Combo box tipo do contato
    private void preencherTipo(){
        List<String> tipos = new ArrayList<>();
        
        tipos.add("Telefone");
        tipos.add("E-mail");
        
        
        Collections.sort(tipos);
        
        cbxTipo.getItems().addAll(tipos);
        
    }
    
    //seta os valores nas Combo box parentesco do dependente
    private void preencherParentescos(){
        List<String> parentescos = new ArrayList<>();
        
        parentescos.add("Filho(a)");
        parentescos.add("Conjugue");
        parentescos.add("Pai");
        parentescos.add("Mãe");
        
        
        Collections.sort(parentescos);
        
        cbxParentesco.getItems().addAll(parentescos);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarRendaMensal();
        configurarIdade();
        preencherParentescos();
        preencherTipo();
        preencherSexos();
        preencherClassificacao();
        btnFechar.setOnAction(e -> fechar());
        btnSalvar.setOnAction(e -> salvar());
        btnLimpar.setOnAction(e -> limpar());
        btnAddDependente.setOnAction(e -> adicionarDependentes());
        btnAddContato.setOnAction(e -> adicionarContatos());
    }    
    
}
