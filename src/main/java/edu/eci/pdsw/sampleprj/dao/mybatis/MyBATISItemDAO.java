/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author hcadavid
 */
public class MyBATISItemDAO implements ItemDAO{

    @Inject
    private ItemMapper itemMapper; 
    
    @Inject
    private TipoItemMapper tipoMapper;
        
    @Override
    public void save(Item it) throws PersistenceException{
        try{
            itemMapper.insertarItem(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }        
        
    }

    @Override
    public Item load(int id) throws PersistenceException {
        try{
            return itemMapper.consultarItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
        
        
    }
    
    @Override
    public List<ItemRentado> loadItemByCLient(int id) throws PersistenceException {
        return itemMapper.consultarItemCliente(id);
            
    }

    @Override
    public List<Item> consultarItemDis() throws PersistenceException {
        return itemMapper.consultarItemDis();
    }

    @Override
    public List<TipoItem> consultTipItems() throws PersistenceException {
        return tipoMapper.consultTipItems();
        
    }
    
    @Override
    public TipoItem consultTipItem(int id) throws PersistenceException {
        return tipoMapper.consultTipItem(id);
    }

    @Override
    public void devolucion(int itemid) throws PersistenceException {
        itemMapper.devolucion(itemid);
    }

    @Override
    public long consultarTarifaxDia(long idItem) throws PersistenceException {
        
        Item item=itemMapper.consultarItem((int)idItem);
        long rta=0;
        if(item!=null){
            rta= item.getTarifaxDia();
        }
        return rta;

    }

    @Override
    public void actualizarTarifaItem(long idItem, int tarifa) throws PersistenceException {
        itemMapper.actualizarTarifaItem(idItem, tarifa);
    }
    
    
}
