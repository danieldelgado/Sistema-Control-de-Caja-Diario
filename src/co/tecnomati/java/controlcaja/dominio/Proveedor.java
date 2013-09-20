package co.tecnomati.java.controlcaja.dominio;
// Generated 14/09/2013 06:55:51 by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * Proveedor generated by hbm2java
 */
public class Proveedor  implements java.io.Serializable {


     private Integer idProveedor;
     private long cuit;
     private String razonSocial;
     private String domicilio;
     private Integer estado;

    public Proveedor() {
    }

	
    public Proveedor(long cuit) {
        this.cuit = cuit;
    }
    public Proveedor(long cuit, String razonSocial, String domicilio, Integer estado) {
       this.cuit = cuit;
       this.razonSocial = razonSocial;
       this.domicilio = domicilio;
       this.estado = estado;
    }
   
    public Integer getIdProveedor() {
        return this.idProveedor;
    }
    
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
    public long getCuit() {
        return this.cuit;
    }
    
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
    public String getRazonSocial() {
        return this.razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }




}

