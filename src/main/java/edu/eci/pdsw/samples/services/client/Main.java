/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.client;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) throws ExcepcionServiciosAlquiler{
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(2));
        System.out.println("VER EL CLIENTE"+ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(1684264984));
      
        List<ItemRentado> erick=ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsCliente(22);
        //ItemRentado r=erick.get(1);
        System.out.println("Vamos1");
        List<TipoItem> tip=ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarTiposItem();
        //select tem.*  from VI_ITEMRENTADO tem  JOIN VI_ITEMS it where tem.CLIENTES_documento =#{idCLient}
        for(int y=0; y<erick.size();y++){
            System.out.println("Vamos2");
            ItemRentado q=erick.get(y);
            System.out.println("miremos"+ q.getId());
            Item s=q.getItem();
            System.out.println("que pasa"+s);
            //Falta arreglar porque de item manda null
            //System.out.println("MIremos"+ s.getNombre()+"MAS : "+s.getGenero());
            //System.out.println("miremos"+ q.getId());        
        }
        //System.out.println("das"+r.getId());
        for(int uu=0; uu<tip.size();uu++){
            TipoItem tr=tip.get(uu);
            System.out.println("Miremos los tipos"+tr.getDescripcion());
        
        }
        
        
        
        System.exit(0);
    }
    
}
