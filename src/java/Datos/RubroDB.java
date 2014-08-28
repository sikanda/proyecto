package Datos;

import Entidades.Rubro;
import java.sql.ResultSet;
import java.util.*;


public class RubroDB extends AccesoDatos {

public RubroDB() throws Exception{}

       
          public List getRubrosConSubrubros() throws Exception{
             List listaRub = new ArrayList();
              List<Rubro> listaSubRub; // = new ArrayList();
              List<Rubro> listaSubSub; // = new ArrayList();
              List<Rubro> listaSubSubSub; // = new ArrayList();
              List<Rubro> listaSubSubSubSub; // = new ArrayList();
            
            ResultSet resultado = EjecutarQuery("select * from rubros where idrubropadre is null order by idRubro " );
            while (resultado.next()){
                Rubro rub = new Rubro();
                rub.setIdRubro(resultado.getString(1));
                rub.setDescRubro(resultado.getString(2));
                //rub.setIdRubroPadre(resultado.getString(3));//idPadre no va mas
                rub.setMateriales(rub.getMateriales());
                //resultado tiene nivel 1 '003'
                listaSubRub = buscaSubrubros(resultado.getString(1));
                
                //listaSubRub tiene nivel 2 '003001'
                for(int i=0; i< listaSubRub.size(); i++) {
            
                //listaSubSub tiene nivel 3 '003001001'
                 listaSubSub = buscaSubrubros(listaSubRub.get(i).getIdRubro());
              
                    for(int j=0; j< listaSubSub.size(); j++) {
                      //listaSubSub tiene nivel 3 '003001001'
                    listaSubSubSub = buscaSubrubros(listaSubSub.get(j).getIdRubro());

                                 for(int k=0; k< listaSubSubSub.size(); k++) {

                                 //listaSubSubSubSub tiene nivel 4 '003001001001'    
                                 listaSubSubSubSub = buscaSubrubros(listaSubSubSub.get(k).getIdRubro());
                                 listaSubSubSub.get(k).setSubrubros(listaSubSubSubSub);  
                               //System.out.println (listaSubSubSub.get(k).getIdRubro());
                                }
                    
                  listaSubSub.get(j).setSubrubros(listaSubSubSub);     
              //   System.out.println (listaSubSub.get(j).getIdRubro());
                    }
              
              listaSubRub.get(i).setSubrubros(listaSubSub); 
             // System.out.println (listaSubRub.get(i).getIdRubro());
         }
               
                rub.setSubrubros(listaSubRub);
                listaRub.add(rub);
            }

            closeCon();
            return listaRub;
	}
       
          
         /*metodo que busca los hijos (subrubros) de un rubro dado*/ 
         public List buscaSubrubros(String idRubro) throws Exception{
            List listaSub = new ArrayList();
            ResultSet resultado = EjecutarQuery("select r.idRubro,r.descRubro,r.idRubroPadre from rubros r inner join rubros r1 on r.idRubroPadre = r1.idrubro and r.idRubroPadre = " + idRubro );
            while (resultado.next()){
                Rubro rub = new Rubro();
                rub.setIdRubro(resultado.getString(1));
                rub.setDescRubro(resultado.getString(2));
               // rub.setIdRubroPadre(resultado.getString(3));
                listaSub.add(rub);
            }
            //closeCon();
            return listaSub;
	}
         
         
         
        /*metodo que busca los rubros padre*/ 
         public List buscaRubrosPadre() throws Exception{
            List listaSub = new ArrayList();
            ResultSet resultado = EjecutarQuery("select * from rubros where idrubropadre is null order by idRubro " );
            while (resultado.next()){
                Rubro rub = new Rubro();
                rub.setIdRubro(resultado.getString(1));
                rub.setDescRubro(resultado.getString(2));
              //  rub.setIdRubroPadre(resultado.getString(3));
                listaSub.add(rub);
            }
//            closeCon();
            return listaSub;
	} 
         
         
         
   ///no se si servira
//          public Rubro getSoloRubro(String idRubro) throws Exception
//    {
//        Rubro prov = new Rubro();
//        ResultSet resultado = EjecutarQuery("SELECT * FROM rubros WHERE idRubro = " + idRubro);
//
//        while (resultado.next())
//        {
//            prov.setIdRubro(idRubro);
//            prov.setDescRubro(resultado.getString(2));
//            prov.setIdRubroPadre(resultado.getString(3));
//        }
//        resultado.close();
//        return prov;
//    }
      

}