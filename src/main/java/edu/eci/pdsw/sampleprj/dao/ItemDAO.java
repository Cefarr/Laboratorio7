/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface ItemDAO {

    public void save(Item it) throws PersistenceException;
    
    public Item load(int id) throws PersistenceException;
    public List<ItemRentado> loadItemByCLient(int id) throws PersistenceException;
    public List<Item> consultarItemDis()throws PersistenceException;
    public List<TipoItem> consultTipItems()throws PersistenceException;
    public TipoItem consultTipItem(int id)throws PersistenceException;
    
}
