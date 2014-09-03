package Entidades;

import Datos.RubroDB;
import java.util.List;

public class Rubro { 
    private String idRubro;
    private String descRubro;
    private String idUnidadMedida;
    private List<Rubro> subrubros ;
    private List<Material> materiales ;
    private List<ManoDeObra> manoDeObra ;
 
    public Rubro (){}
	
	
    public Rubro(String idRubro, String descRubro) {
        this.idRubro = idRubro;
        this.descRubro = descRubro;
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
    
        public void addHijo(Rubro padre, Rubro hijo)
    {
        padre.subrubros.add(hijo);
    }
     
      	public String getIdRubroPadre(){ 
		String idPadre = null;
		try{
			RubroDB rDB = new RubroDB();
			idPadre = rDB.getRubroPadre(idRubro); //TODO: revisar
		}
		catch(Exception e){}
                return idPadre;
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
