package co.tecnomati.java.controlcaja.dominio;
// Generated 31/01/2014 14:12:22 by Hibernate Tools 3.2.1.GA



/**
 * Comprobanteconcepto generated by hbm2java
 */
public class Comprobanteconcepto  implements java.io.Serializable {


     private Integer id;
     private Concepto concepto;
     private Comprobante comprobante;
     private Double monto;
     private Boolean estado;

    public Comprobanteconcepto() {
    }

    public Comprobanteconcepto(Concepto concepto, Comprobante comprobante, Double monto, Boolean estado) {
       this.concepto = concepto;
       this.comprobante = comprobante;
       this.monto = monto;
       this.estado = estado;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Concepto getConcepto() {
        return this.concepto;
    }
    
    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
    public Comprobante getComprobante() {
        return this.comprobante;
    }
    
    public void setComprobante(Comprobante comprobante) {
        this.comprobante = comprobante;
    }
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }




}


