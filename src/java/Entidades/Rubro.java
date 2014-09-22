package Entidades;

import Datos.RubroDB;
import java.util.ArrayList;
import java.util.List;

public class Rubro { 
    private String idRubro;
    private String descRubro;
    private String idUnidadMedida;
    private List<Rubro> subrubros ;
    private List<Material> materiales ;
    private List<ManoDeObra> manoDeObra ;
    private float cantPresRub;
 
    public Rubro (){}
	
	
    public Rubro(String idRubro, String descRubro) {
        this.idRubro = idRubro;
        this.descRubro = descRubro;
        this.subrubros = new ArrayList();
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
    
    public String getIdUnidadMedida() {
        return idUnidadMedida;
    }
    
    public float getCantPresRub() {
        return cantPresRub;
    }

    public void setCantPresRub(float cantPresRub) {
        this.cantPresRub = cantPresRub;
    }
    
    public void setIdUnidadMedida(String idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }
    
    public List getSubrubros() {
        return subrubros;
    }

    public void setSubrubros (List subrubros) {
        this.subrubros = subrubros;
    }
    
        public List getMateriales() {
        return materiales;
    }

    public void setMateriales (List materiales) {
        this.materiales = materiales;
    }
    
        public List getManoDeObra() {
        return manoDeObra;
    }

    public void setManoDeObra (List manoDeObra) {
        this.manoDeObra = manoDeObra;
    }
    
    
    public void addSubrubro(Rubro rubro)
    {
        this.subrubros.add(rubro);
    }
 
        public Rubro getRubroPadre(){ 
            Rubro rPadre = null;
            try{
                    RubroDB rDB = new RubroDB();
                    rPadre = rDB.getRubroPadre(idRubro); //TODO: revisar
            }
            catch(Exception e){}
            return rPadre;
	}
        
        public Rubro getRubro(String idR){ 
		Rubro rub = null;
		try{
			RubroDB rDB = new RubroDB();
			rub = rDB.getRubro(idR); //TODO: revisar
		}
		catch(Exception e){}
                return rub;
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
