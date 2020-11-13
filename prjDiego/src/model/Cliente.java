
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;
    
    @Column(name = "dataNascimento", length = 30, nullable = false)
    private String dataNasc;
    
    @Column(name = "sexo", length = 1, nullable = false)
    private char sexo;
    
    @Column(name = "rendaMensal", nullable = false)
    private Double rendaMensal;

    public Cliente() {
    }

    public Cliente(String nome, String dataNasc, char sexo, Double rendaMensal) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.rendaMensal = rendaMensal;
    }

    public Cliente(Long id, String nome, String dataNasc, char sexo, Double rendaMensal) {
        this.id = id;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.rendaMensal = rendaMensal;
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

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }
    
    
    
}
