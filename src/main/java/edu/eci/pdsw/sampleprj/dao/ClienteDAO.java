/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface ClienteDAO {
    
    public void save(Cliente c) throws PersistenceException;
    
    public Cliente load(int id) throws PersistenceException;
    public List<Cliente> loadClient() throws PersistenceException ;
    public ItemRentado loadItemCliente(int id)throws PersistenceException ;
    public void registroAlquilerCliente(long clienteDocumen, int idItem,Date fechainicioRenta, Date fechafinrenta)throws PersistenceException ;
    public void vetarCliente(long id)throws PersistenceException;
 
    
}
