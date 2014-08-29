package Datos;

import Entidades.Rubro;
import Entidades.Material;
import java.sql.ResultSet;
import java.util.*;


public class RubroDB extends AccesoDatos {

public RubroDB() throws Exception{}

      public List getRubrosConSubrubros() throws Exception
      {
        List<Rubro>  listaRub = new ArrayList();
        List<Rubro>  listaSubRub ;

       ResultSet resultado = EjecutarQuery("select * from rubros where idrubropadre is null order by idRubro " );
       while (resultado.next())
       {
           Rubro rub = new Rubro();
           rub.setIdRubro(resultado.getString(1));
           rub.setDescRubro(resultado.getString(2));

           listaSubRub = buscaSubrubros(resultado.getString(1));
           rub.setSubrubros(listaSubRub);

           listaRub.add(rub);
        } 
            closeCon();
           return listaRub;
      }
              
              
         /*metodo que busca los hijos (subrubros) de un rubro dado*/ 
         public List buscaSubrubros(String idRubro) throws Exception{
            List<Rubro>  listaSub = new ArrayList();
            List<Rubro>  listaSubR;
            ResultSet resultado = EjecutarQuery("select r.idRubro,r.descRubro,r.idRubroPadre from rubros r inner join rubros r1 on r.idRubroPadre = r1.idrubro and r.idRubroPadre = " + idRubro );
            while (resultado.next()){
                Rubro rub = new Rubro();
                rub.setIdRubro(resultado.getString(1));
                rub.setDescRubro(resultado.getString(2));

                listaSubR = buscaSubrubros(resultado.getString(1));
                rub.setSubrubros(listaSubR);
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
            System.out.println(listaMat);
            return listaMat;
	} 
         
}