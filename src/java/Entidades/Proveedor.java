package Entidades;

import Datos.ProveedorDB;
import java.util.ArrayList;

public class Proveedor { 
    private Integer idProveedor;
    private String nombreProv;
    private String apellidoProv;
    private String direProv;
    private String emailProv;
    private String telProv;
 
    public Proveedor (){}
	
	public Proveedor(int id, String nombre, String apellido, String direccion,
			String telefono, String mail) {
		super();
		this.idProveedor = id;
		this.nombreProv = nombre;
		this.apellidoProv = apellido;
		this.direProv = direccion;
		this.telProv = telefono;
		this.emailProv = mail;
	}
        
        
    public Proveedor (String nom,String ape,String dir,String mail,String tel) 
    {  
        boolean rta = true;
        try{
            ProveedorDB EDB = new ProveedorDB();
            idProveedor = EDB.getIdProveedor();
            }
        catch(Exception e)
            {rta = false;}
        if (rta)
        {
		this.nombreProv = nom;
		this.apellidoProv = ape;
		this.direProv = dir;
		this.telProv = tel;
		this.emailProv = mail;
        }
     }

  
        //TODO: faltan todos los otros metodos
         public boolean update(){
        boolean rta = true;
        ProveedorDB UDB = null;
        try{
                UDB = new ProveedorDB();
        }
        catch (Exception e){
            rta = false;
        }
        if(rta){
                rta = UDB.update(this);
        }
        return rta;
    }

     public boolean save(){
        boolean rta = true;
        ProveedorDB UDB = null;
        try{
                UDB = new ProveedorDB();
        }
        catch (Exception e){
            rta = false;
        }
        if(rta){
                rta = UDB.save(this);
        }
        return rta;
    }
        
        public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getApellidoProv() {
        return apellidoProv;
    }

    public void setApellidoProv(String apellidoProv) {
        this.apellidoProv = apellidoProv;
    }

    public String getDireProv() {
        return direProv;
    }

    public void setDireProv(String direProv) {
        this.direProv = direProv;
    }

    public String getEmailProv() {
        return emailProv;
    }

    public void setEmailProv(String emailProv) {
        this.emailProv = emailProv;
    }

    public String getTelProv() {
        return telProv;
    }

    public void setTelProv(String telProv) {
        this.telProv = telProv;
    }
}
