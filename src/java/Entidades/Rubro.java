package Entidades;

import Datos.RubroDB;
import java.util.List;

public class Rubro { 
    private String idRubro;
    private String descRubro;
    private String idRubroPadre;
    private List subrubros;
 
    public Rubro (){}
	
	
    public Rubro(String idRubro, String descRubro, String idRubroPadre) {
        this.idRubro = idRubro;
        this.descRubro = descRubro;
        this.idRubroPadre = idRubroPadre;
    }

    public String getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(String idRubro) {
        this.idRubro = idRubro;
    }

    public String getDescRubro() {
        return descRubro;
    }
    
    public void setDescRubro(String descRubro) {
        this.descRubro = descRubro;
    }
    
    public void setIdRubroPadre(String idRubroPadre) {
        this.idRubroPadre = idRubroPadre;
    }

    public String getIdRubroPadre() {
        return idRubroPadre;
    }

    public List getSubrubros() {
        return subrubros;
    }

    public void setSubrubros (List subrubros) {
        this.subrubros = subrubros;
    }

  /*
        //TODO: faltan todos los otros metodos
         public boolean update(){
        boolean rta = true;
        UsuarioDB UDB = null;
        try{
                UDB = new UsuarioDB();
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
        UsuarioDB UDB = null;
        try{
                UDB = new UsuarioDB();
        }
        catch (Exception e){
            rta = false;
        }
        if(rta){
                rta = UDB.save(this);
        }
        return rta;
    }
    */
    
    
    		@Override
	public String toString() {
		return this.getDescRubro();
	}
     
}
