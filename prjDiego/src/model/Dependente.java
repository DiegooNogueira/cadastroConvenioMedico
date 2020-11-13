
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dependente")
public class Dependente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;
    
    @Column(name = "idade", nullable = false)
    private int idade;
    
    @Column(name = "sexo", length = 1, nullable = false)
    private char sexo;
    
    @Column(name = "parentesco", length = 30, nullable = false)
    private String parentesco;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente titular;

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Parentesco: " + parentesco;
    }
    
    
    public Dependente() {
    }

    public Dependente(String nome, int idade, char sexo, String parentesco) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.parentesco = parentesco;     
    }

    public Dependente(Long id, String nome, int idade, char sexo, String parentesco, Cliente titular) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.parentesco = parentesco;
        this.titular = titular;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Cliente getCliente() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
    
}
