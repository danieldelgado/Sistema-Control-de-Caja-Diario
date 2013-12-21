package co.tecnomati.java.controlcaja.dominio;
// Generated 21/12/2013 09:49:52 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Concepto generated by hbm2java
 */
public class Concepto  implements java.io.Serializable {


     private Integer codigoConcepto;
     private String descripcion;
     private Boolean estado;
     private Set<Comprobanteconcepto> comprobanteconceptos = new HashSet<Comprobanteconcepto>(0);

    public Concepto() {
    }

    public Concepto(String descripcion, Boolean estado, Set<Comprobanteconcepto> comprobanteconceptos) {
       this.descripcion = descripcion;
       this.estado = estado;
       this.comprobanteconceptos = comprobanteconceptos;
    }
   
    public Integer getCodigoConcepto() {
        return this.codigoConcepto;
    }
    
    public void setCodigoConcepto(Integer codigoConcepto) {
        this.codigoConcepto = codigoConcepto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Set<Comprobanteconcepto> getComprobanteconceptos() {
        return this.comprobanteconceptos;
    }
    
    public void setComprobanteconceptos(Set<Comprobanteconcepto> comprobanteconceptos) {
        this.comprobanteconceptos = comprobanteconceptos;
    }




}


