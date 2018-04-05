
package modelo;

import java.awt.Image;

public class Producto {
   
    private Integer cod;
    private String nombre;
    private Double precio;
    private String proveedor;
    private String imagen;
    private Image imagen2;
    private Integer cat;
    private String oferta;

    public Producto() { }

    public Producto(Integer cod, String nombre, Double precio, String proveedor, String imagen, 
            Image imagen2, Integer cat, String oferta) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
        this.imagen = imagen;
        this.imagen2 = imagen2;
        this.cat = cat;
        this.oferta = oferta;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }
    
    public Integer getCat() {
        return cat;
    }
    
    public String getOferta() {
        return oferta;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
      
    public Image getImagen2() {
        return imagen2;
    }

    public void setImagen2(Image imagen) {
      this.imagen2 = imagen2;
    }   
      
    public void setCat(Integer cat) {
      this.cat = cat;
    }     
    
    public void setOferta(String oferta) {
      this.oferta = oferta;
    }     
}
