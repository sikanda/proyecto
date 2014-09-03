package Datos;

import Entidades.Rubro;
import Entidades.Material;
import Entidades.ManoDeObra;
import java.sql.ResultSet;
import java.util.*;


public class RubroDB extends AccesoDatos {

public RubroDB() throws Exception{}

      public List getRubrosConSubrubros() throws Exception
      {
        List<Rubro>  listaRub = new ArrayList();
        List<Rubro>  listaSubRub ;
        List<Material>  listaMat;
        List<ManoDeObra>  listaMO;

       ResultSet resultado = EjecutarQuery("select * from rubros where idrubropadre is null order by idRubro " );
       while (resultado.next())
       {
           Rubro rub = new Rubro();
           rub.setIdRubro(resultado.getString(1));
           rub.setDescRubro(resultado.getString(2));

           listaSubRub = buscaSubrubros(resultado.getString(1));
           rub.setSubrubros(listaSubRub);
           
            listaMat = getMaterialesEnRubro(resultado.getString(1));
            rub.setMateriales(listaMat);

            listaMO = getMOEnRubro(resultado.getString(1));
            rub.setManoDeObra(listaMO);

           listaRub.add(rub);
        } 
            closeCon();
           return listaRub;
      }
              
              
         /*metodo que busca los hijos (subrubros) de un rubro dado*/ 
         public List buscaSubrubros(String idRubro) throws Exception{
            List<Rubro>  listaSub = new ArrayList();
            List<Rubro>  listaSubR;
            List<Material>  listaMat;
            List<ManoDeObra>  listaMO;
            
            ResultSet resultado = EjecutarQuery("select r.idRubro,r.descRubro,r.idRubroPadre from rubros r inner join rubros r1 on r.idRubroPadre = r1.idrubro and r.idRubroPadre = " + idRubro );
            while (resultado.next()){
                Rubro rub = new Rubro();
                rub.setIdRubro(resultado.getString(1));
                rub.setDescRubro(resultado.getString(2));

                listaSubR = buscaSubrubros(resultado.getString(1));
                rub.setSubrubros(listaSubR);
                
                listaMat = getMaterialesEnRubro(resultado.getString(1));
                rub.setMateriales(listaMat);
                
                listaMO = getMOEnRubro(resultado.getString(1));
                rub.setManoDeObra(listaMO);
                
                listaSub.add(rub);
            }
            return listaSub;
	}
         
    
         /*metodo que busca los materiales para un id de rubro*/ 
         public List getMaterialesEnRubro(String idRubro) throws Exception{
            List listaMat = new ArrayList();
            ResultSet resultado = EjecutarQuery("select mr.idMaterial, mr.coefStdMat , m.descMaterial, m.idUnidadMedida from materialesrubro mr inner join  materiales m on mr.idMaterial = m.idMaterial where idrubro = " + idRubro );
            while (resultado.next()){
                Material mat = new Material();
                mat.setIdMaterial(resultado.getString(1));
                mat.setCoefStdMat(resultado.getFloat(2));
                mat.setDescMaterial(resultado.getString(3));
                mat.setIdUnidadMedida(resultado.getString(4));

                listaMat.add(mat);
            }
            return listaMat;
	} 
      
        /*metodo que busca la mano de obra para un id de rubro*/ 
         public List getMOEnRubro(String idRubro) throws Exception{
            List listaMO = new ArrayList();
            ResultSet resultado = EjecutarQuery("select mr.idManoDeObra, mr.coefStdMo , m.descManoDeObra, m.idUnidadMedida from manodeobrarubro mr inner join  manodeobra m on mr.idManoDeObra = m.idManoDeObra where idrubro = " + idRubro );
            while (resultado.next()){
                ManoDeObra mo = new ManoDeObra();
                mo.setIdManoDeObra(resultado.getString(1));
                mo.setCoefStdMO(resultado.getFloat(2));
                mo.setDescManoDeObra(resultado.getString(3));
                mo.setIdUnidadMedida(resultado.getString(4));

                listaMO.add(mo);
            }
            return listaMO;
	} 
         
           /*metodo que busca el padre directo para un idrubro*/ 
         public String getRubroPadre(String idRubro) throws Exception{
            String padre = new String();
            ResultSet resultado = EjecutarQuery("select idRubroPadre from rubros where idRubro = " + idRubro );
            while (resultado.next()){
                padre = resultado.getString(1);
            }
            return padre;
	} 
         
         
         ///Obtiene un objeto rubro a partir del idRubro
          public Rubro getRubro(String idRubro) throws Exception
    {
        Rubro r = new Rubro();
        ResultSet resultado = EjecutarQuery("SELECT idRubro, descRubro, idUnidadMedida FROM rubros WHERE idRubro = " + idRubro);

        while (resultado.next())
        {
            r.setIdRubro(idRubro);
            r.setDescRubro(resultado.getString(2));
            r.setIdUnidadMedida(resultado.getString(3));
        }
        resultado.close();
        return r;
    }	
}