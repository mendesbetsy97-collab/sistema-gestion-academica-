package co.edu.sena.sgac.model;
public class Estudiante {
    private int id; private String documento, nombres, apellidos, correo, programa;
    public Estudiante() {}
    public Estudiante(int id, String documento, String nombres, String apellidos, String correo, String programa) { this.id=id; this.documento=documento; this.nombres=nombres; this.apellidos=apellidos; this.correo=correo; this.programa=programa; }
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getDocumento(){return documento;} public void setDocumento(String v){documento=v;}
    public String getNombres(){return nombres;} public void setNombres(String v){nombres=v;}
    public String getApellidos(){return apellidos;} public void setApellidos(String v){apellidos=v;}
    public String getCorreo(){return correo;} public void setCorreo(String v){correo=v;}
    public String getPrograma(){return programa;} public void setPrograma(String v){programa=v;}
}
