package Datos;

import Entidades.Proveedor;
import java.sql.ResultSet;
import java.util.*;


public class ProveedorDB extends AccesoDatos {

public ProveedorDB() throws Exception{}

	//Todo: agregar metodos custom, if necessary
     public boolean save(Proveedor u){
        boolean rta = false;
		rta = EjecutarNonQuery("insert into proveedores (nombreProv, apellidoProv, direProv, emailProv, telProv)  VALUES ( '" + u.getNombreProv()  + "' , '" + u.getApellidoProv() + "' , '" + u.getDireProv() + "' , '" + u.getEmailProv() + "' , '" + u.getTelProv() +  "' )");
		if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }

  public boolean update(Proveedor u){
        boolean rta = false;
		rta = EjecutarNonQuery("UPDATE proveedores SET nombreProv = '" + u.getNombreProv() + "', apellidoProv = '" + u.getApellidoProv() + "', direProv = '" + u.getDireProv() + "', telProv = '" + u.getTelProv() + "', emailProv = '" + u.getEmailProv() + "' WHERE idProveedor = " + u.getIdProveedor());
                
         
	if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
  
   public boolean delete(int idP){
        boolean rta = false;
		rta = EjecutarNonQuery("delete from proveedores WHERE idProveedor = " + idP);
	if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
   
       public List getProveedores() throws Exception{
            List listaProv = new ArrayList();
            ResultSet resultado = EjecutarQuery("SELECT * FROM proveedores");
            while (resultado.next()){
                Proveedor prov = new Proveedor();
                prov.setIdProveedor(resultado.getInt(1));
                prov.setNombreProv(resultado.getString(2));
                prov.setApellidoProv(resultado.getString(3));
                prov.setDireProv(resultado.getString(4));
                prov.setEmailProv(resultado.getString(5));
                prov.setTelProv(resultado.getString(6));
            
                listaProv.add(prov);
            }
            closeCon();
            return listaProv;
	}
       
      public Proveedor getProveedor(int idProveedor) throws Exception
    {
        Proveedor prov = new Proveedor();
        ResultSet resultado = EjecutarQuery("SELECT * FROM proveedores WHERE idProveedor = " + idProveedor);

        while (resultado.next())
        {
            prov.setIdProveedor(idProveedor);
            prov.setNombreProv(resultado.getString(2));
            prov.setApellidoProv(resultado.getString(3));
            prov.setDireProv(resultado.getString(4));
            prov.setEmailProv(resultado.getString(5));
            prov.setTelProv(resultado.getString(6));

        }
        resultado.close();
        return prov;
    }
      
          public int getIdProveedor(){
        int rta = EjecutarQueryInt("SELECT MAX(idProveedor) FROM proveedores")+1;
        closeCon();
        return (rta);

    }
}