/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.client;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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
        TipoItem tp=new TipoItem(12, "Las cosas de la vida");
        Item qqq=new Item(tp, 119090, "Algo", "Algo sobre algo", Date.from(Instant.EPOCH), 2000,"DVD", "pARA TODOS");
        
        Cliente p=new Cliente ("Erick",11039, "222121", "Calle 1","Erick@loca.com");
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarCliente(p); FUnciona pero para que no moleste x duplicados
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarAlquilerCliente(java.sql.Date.valueOf(LocalDate.of(2018, Month.JUNE, 12)), p.getDocumento(), qqq, 20);
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarDevolucion(0);
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarItem(qqq);
        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().vetarCliente(22, true);
        System.exit(0);
    }
    
}
