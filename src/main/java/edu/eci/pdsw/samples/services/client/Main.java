/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.client;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
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
        ItemRentado r=erick.get(1);
        System.out.println("das"+r.getId());
        
        
        System.exit(0);
    }
    
}
