
package modelo;

public class Proveedor {
    private String cif;
    private String nom_emp;
    private String direccion;
    private String poblacion;

    public Proveedor() {}
     
    public Proveedor(String cif, String nom_emp, String direccion, String poblacion) {
        this.cif = cif;
        this.nom_emp = nom_emp;
        this.direccion = direccion;
        this.poblacion = poblacion;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNom_emp() {
        return nom_emp;
    }

    public void setNom_emp(String nom_emp) {
        this.nom_emp = nom_emp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
      
}
