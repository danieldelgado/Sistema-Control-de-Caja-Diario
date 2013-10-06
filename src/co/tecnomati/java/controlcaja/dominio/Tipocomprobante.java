package co.tecnomati.java.controlcaja.dominio;
// Generated 05/10/2013 20:21:41 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Tipocomprobante generated by hbm2java
 */
public class Tipocomprobante  implements java.io.Serializable {


     private Integer codigo;
     private Categoriacomprobante categoriacomprobante;
     private String formulario;
     private String referencia;
     private Long numeroSerieIzq;
     private Long numeroSerieDer;
     private Set<Comprobante> comprobantes = new HashSet<Comprobante>(0);

    public Tipocomprobante() {
    }

    public Tipocomprobante(Categoriacomprobante categoriacomprobante, String formulario, String referencia, Long numeroSerieIzq, Long numeroSerieDer, Set<Comprobante> comprobantes) {
       this.categoriacomprobante = categoriacomprobante;
       this.formulario = formulario;
       this.referencia = referencia;
       this.numeroSerieIzq = numeroSerieIzq;
       this.numeroSerieDer = numeroSerieDer;
       this.comprobantes = comprobantes;
    }
   
    public Integer getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public Categoriacomprobante getCategoriacomprobante() {
        return this.categoriacomprobante;
    }
    
    public void setCategoriacomprobante(Categoriacomprobante categoriacomprobante) {
        this.categoriacomprobante = categoriacomprobante;
    }
    public String getFormulario() {
        return this.formulario;
    }
    
    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }
    public String getReferencia() {
        return this.referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public Long getNumeroSerieIzq() {
        return this.numeroSerieIzq;
    }
    
    public void setNumeroSerieIzq(Long numeroSerieIzq) {
        this.numeroSerieIzq = numeroSerieIzq;
    }
    public Long getNumeroSerieDer() {
        return this.numeroSerieDer;
    }
    
    public void setNumeroSerieDer(Long numeroSerieDer) {
        this.numeroSerieDer = numeroSerieDer;
    }
    public Set<Comprobante> getComprobantes() {
        return this.comprobantes;
    }
    
    public void setComprobantes(Set<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }




}


