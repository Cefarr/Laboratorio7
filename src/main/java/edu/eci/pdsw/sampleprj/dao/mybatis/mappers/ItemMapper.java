package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("itemId") int id);
    
    public void insertarItem(@Param ("item")Item it);
    public List<ItemRentado> consultarItemCliente(@Param("idCLient")int idClinet);    
    public List<Item> consultarItemDis();    
        
}
