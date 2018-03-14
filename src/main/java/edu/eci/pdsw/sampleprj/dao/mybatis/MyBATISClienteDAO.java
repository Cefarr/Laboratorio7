/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2098325
 */
public class MyBATISClienteDAO implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente c) throws PersistenceException {
        clienteMapper.agregarCliente(c.getDocumento(),c.getNombre(),c.getTelefono(),c.getDireccion(),c.getEmail(),c.isVetado());
        
    }

    @Override
    public Cliente load(int id) throws PersistenceException {
        return clienteMapper.consultarCliente(id);    
    }
    @Override
    public List<Cliente> loadClient() throws PersistenceException {
        return clienteMapper.consultarClientes();
        
    }

    @Override
    public ItemRentado loadItemCliente(int id) throws PersistenceException {
        return clienteMapper.loadItemClien(id);
       
    }

    @Override
    public void registroAlquilerCliente(long clienteDocumen, int idReg, Date fechainicioRenta, Date fechafinrenta) throws PersistenceException {
           int iditem=clienteMapper.getNetval();
            clienteMapper.agregarItemRentadoACliente(iditem,(int)clienteDocumen, idReg, fechainicioRenta, fechafinrenta); 
    }

    @Override
    public void vetarCliente(long id) throws PersistenceException {
        clienteMapper.vetarCliente((int)id);
    }
    
}
