
package Datos;
import Entidades.Presupuesto;

public class PresupuestoDB extends AccesoDatos {
    
    
    public PresupuestoDB() throws Exception{}
        public int getIdPresupuesto(){
        int rta = EjecutarQueryInt("SELECT MAX(idPresupuesto) FROM presupuestos")+1;
        closeCon();
        return (rta);
           }
        
 
     public boolean save(Presupuesto p){ 
        boolean rta = false;
           //TODO: revisar tipos y comillas
		rta = EjecutarNonQuery("insert into presupuestos (observaciones, idUsuario, idCliente, fechaCreacion)  VALUES ( '" + p.getObservaciones()  + "' , '" + p.getUsuario().getIdUsuario() + "' , '" + p.getCliente().getIdCliente()  + "' , '" + p.getFechaCreacion() +   "' )");
		if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }

  public boolean update(Presupuesto p){
        boolean rta = false;
        //TODO: revisar tipos y comillas
		rta = EjecutarNonQuery("UPDATE presupuestos SET idUsuario = '" + p.getUsuario().getIdUsuario() + "', observaciones = '" + p.getObservaciones()  + "', idCliente = '" + p.getCliente().getIdCliente()  + "', fechaCreacion = '" + p.getFechaCreacion() + "' WHERE idPresupuesto = " + p.getIdPresupuesto());
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
		rta = EjecutarNonQuery("delete from presupuestos WHERE idPresupuesto = " + idP);
	if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
   
   ///////////MANO DE OBRA PRESUPUESTO
        public boolean saveMOPres(int idPres, String idR, String idMo, float cant){ 
        boolean rta = false;
           //TODO: revisar tipos y comillas
		rta = EjecutarNonQuery("INSERT INTO manodeobrapresupuesto (idPresupuesto,idRubro,idManoDeObra,cantPresMO)  VALUES ( '" + idPres + "' , '" + idR + "' , '" + idMo  + "' , '" +cant +   "' )");
	  if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
        
    ///////////MATERIALES PRESUPUESTO  
        public boolean saveMAPres(int idPres, String idR, String idMa, float cant){ 
        boolean rta = false;
           //TODO: revisar tipos y comillas
		rta = EjecutarNonQuery("INSERT INTO materialespresupuesto (idPresupuesto,idRubro,idMaterial,cantPresMat)  VALUES ( '" + idPres + "' , '" + idR + "' , '" + idMa  + "' , '" +cant +   "' )");
		if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
        
    ///////////RUBRO PRESUPUESTO  
        public boolean saveRUBPres(int idPres, String idR){ 
        boolean rta = false;
           //TODO: revisar tipos y comillas
		rta = EjecutarNonQuery("INSERT INTO rubrospresupuesto (idPresupuesto,idRubro) VALUES (  '" + idPres + "' , '" + idR +  "' )");
		if(rta){
            rta = commit();
        }
        if(!rta){
            rollback();
        }
		closeCon();
        return rta;
    }
}
