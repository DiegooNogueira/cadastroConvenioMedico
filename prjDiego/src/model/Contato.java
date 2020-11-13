
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
@Table(name = "contato")
public class Contato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tipo", length = 10, nullable = false)
    private String tipo;
    
    @Column(name = "classificacao", length = 15, nullable = false)
    private String classificacao;
    
    @Column(name = "contato", length = 30, nullable = false)
    private String contato;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Contato() {
    }

    @Override
    public String toString() {
        return "Classificação: " + classificacao + ", Contato: " + contato;
    }
    
    public Contato(String tipo, String classificacao, String contato) {
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.contato = contato;
    }

    public Contato(Long id, String tipo, String classificacao, String contato, Cliente cliente) {
        this.id = id;
        this.tipo = tipo;
        this.classificacao = classificacao;
        this.contato = contato;
        this.cliente = cliente;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente titular) {
        this.cliente = titular;
    }
    
}
