
package Datos;
import Entidades.Presupuesto;
import Entidades.Rubro;
import Entidades.ManoDeObra;
import Entidades.Material;
import java.util.Iterator;

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
        
        public boolean saveALLPres(Presupuesto p){ 
        boolean rta = false;
        boolean rta1,rta2,rta3,rta4 ;
       // Rubro r = new Rubro();
       java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
       String fechaCreac = sdf.format(p.getFechaCreacion());
        rta1 = EjecutarNonQuery("insert into presupuestos (observaciones, idUsuario, idCliente, fechaCreacion)  VALUES ( '" + p.getObservaciones()  + "' , " + p.getUsuario().getIdUsuario() + " , " + p.getCliente().getIdCliente()  + ", '" + fechaCreac +   "' )");
        
        int idPres = EjecutarQueryInt("SELECT MAX(idPresupuesto) FROM presupuestos");
        
           Iterator itRub = p.getRubros().iterator();
           while(itRub.hasNext())
           {
                Rubro  r = (Rubro)itRub.next();
                //OJO! esto sirve para rubros hoja... ni idea si vienen con los padres, hay q iterar en profundidad tb
                rta4 = EjecutarNonQuery("INSERT INTO rubrospresupuesto (idPresupuesto,idRubro) VALUES (  " + idPres + " , '" + r.getIdRubro() +  "' )");
                Iterator itMat = r.getMateriales().iterator();
                while(itMat.hasNext())
                {
                    Material  ma = (Material)itMat.next();
                    rta2 = EjecutarNonQuery("INSERT INTO materialespresupuesto (idPresupuesto,idRubro,idMaterial,cantPresMat)  VALUES ( " + idPres + " , '" + r.getIdRubro() + "' , '" + ma.getIdMaterial()  + "' , " +ma.getCantPres() +   " )");
                    if(rta2 && rta4 && rta1)
                    {
                         rta = commit();
                     }
                     if(!(rta2 && rta4 && rta1))
                     {
                         rollback();
                     }    
                 }
                Iterator itMo = r.getManoDeObra().iterator();
                while(itMo.hasNext())
                {
                 ManoDeObra  mo = (ManoDeObra)itMo.next();    
                 rta3 = EjecutarNonQuery("INSERT INTO manodeobrapresupuesto (idPresupuesto,idRubro,idManoDeObra,cantPresMO)  VALUES ( " + idPres + " , '" + r.getIdRubro() + "' , '" + mo.getIdManoDeObra()  + "' , " +mo.getCantPres() +   " )");
                    if(rta3 && rta4 && rta1)
                    {
                        rta = commit();
                    }
                    if(!(rta3 && rta4 && rta1))
                    {
                        rollback();
                    }
                }
           }       
        // si no tiene mat y mo no hace falta hacer commit?
        closeCon();
        return rta;
    }
        
}
