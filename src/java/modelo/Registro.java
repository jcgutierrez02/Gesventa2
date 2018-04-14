
package modelo;

public class Registro {
   
    private String nom_usu;
    private String nom_apell;
    private String usuario;
    private String clave;
   
    public Registro() { }

    public Registro(String nom_usu, String nom_apell, String usuario, String clave) {
        this.nom_usu = nom_usu;
        this.nom_apell = nom_apell;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getNom_apell() {
        return nom_apell;
    }

    public void setNom_apell(String nom_apell) {
        this.nom_apell = nom_apell;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
}   