
package Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Datos.PresupuestoDB;

public class Presupuesto {
    
    private int idPresupuesto;
    private List<Rubro> rubros ;
    private Usuario usuario ;
    private Cliente cliente;
    private String observaciones;
    private Date fechaCreacion;
    
    
    public Presupuesto(){}
    //constructor: setea rubros vacio
      public Presupuesto (Usuario usr,String obs,Date fec, Cliente cli) 
    { 
        boolean rta = true;
        try{
            PresupuestoDB PDB = new PresupuestoDB();
            idPresupuesto = PDB.getIdPresupuesto();
            }
        catch(Exception e)
            {rta = false;}
        if (rta)
        {
            this.fechaCreacion = fec;
            this.observaciones = obs;
            this.usuario = usr;
            this.cliente = cli;
            this.rubros = new ArrayList();
        }
     }
    
     public void addRubroPres(Rubro rubro)
    {
        this.rubros.add(rubro);
    }
          
    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public List<Rubro> getRubros() {
        return rubros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public List devolverRubrosPresupuesto()
    {
        List lista = new ArrayList();
        int len;
        Rubro p,p1,p2 ;    
        for(Rubro r : rubros){
//            len = (r.getIdRubro()).length();
//            switch(len) {
//            case 3: //L1
//                lista.add(r);
//                break;  
//            case 6: //L2
                p = r.getRubro(r.getIdRubroPadre()); //TODO: REVISAR
    //            p.addHijo(p,r);
                lista.add(p);
//                break;
//             case 9: //L3
//                p = r.getRubro(r.getIdRubroPadre()); //r tiene L3, p tiene L2
//                p.addRubro(r); //p L2 agrega su hijo L3
//                p1 = p.getRubro(p.getIdRubroPadre()); //padre de p es p1 = L1
//                p1.addRubro(p); //p1 L1 agrega su hijo L2
//                lista.add(p1);  //agrego p1 L1 a la lista
//                break;
//             case 12: //L4
//                p = r.getRubro(r.getIdRubroPadre()); //r tiene L4, p tiene L3
//                p.addRubro(r); //p L3 agrega su hijo L4
//                p1 = p.getRubro(p.getIdRubroPadre()); //padre de p es p1 = L2
//                p1.addRubro(p); //p1 L2 agrega su hijo L3
//                p2 = p1.getRubro(p1.getIdRubroPadre()); //padre de p1 es p2 = L1
//                p2.addRubro(p1); //p2 L1 agrega su hijo L2 
//                lista.add(p2);  //agrego p2 L1 a la lista
//                break;
//                }
        }
        return lista;
    }
    
    
      public boolean save(){
        boolean rta = true;
        PresupuestoDB PDB = null;
        try{
                PDB = new PresupuestoDB();
        }
        catch (Exception e){
            rta = false;
        }
        if(rta){
                rta = PDB.save(this) ;
                //rta = PDB.savePresupuesto(this) ;
        }
        return rta;
    }
}
