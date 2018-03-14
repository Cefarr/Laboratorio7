package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;
import static junit.runner.Version.id;

/**
 * 
 * @author hcadavid
 */
@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

    
    private static final int mula_Diaria=5000;
    
    @Inject
    private ItemDAO daoItem;
        
    @Inject 
    private ClienteDAO daoCliente;
    
    @Override
    public int valorMultaRetrasoxDia(int itemId)throws ExcepcionServiciosAlquiler {
        try {
            return (int)daoItem.load(itemId).getTarifaxDia();
        } catch (PersistenceException ex) {
             throw new ExcepcionServiciosAlquiler("Error al consultar Cliente"+ itemId );
        }
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.load((int)docu);
        } catch (PersistenceException ex) {
    
            throw new ExcepcionServiciosAlquiler("Error al consultar Cliente"+ docu );
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        //falta arreglar el mapper cliente y este y todos los demas metodos
        //select * from VI_ITEMRENTADO WHERE CLIENTES_documento=22
        //select it.*  from VI_ITEMRENTADO tem  JOIN VI_ITEMS it where tem.CLIENTES_documento=22
        try {
            return daoItem.loadItemByCLient((int) idcliente);
        } catch (PersistenceException ex) {
    
            throw new ExcepcionServiciosAlquiler("Error al consultar el item del Cliente"+ idcliente );
        }
        
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.loadClient();
        } catch (PersistenceException ex) {
    
            throw new ExcepcionServiciosAlquiler("Error al consultar el item del Cliente");
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        try {
            return daoItem.consultarItemDis();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item ");
      
                  }
        }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        try{
            long mulTotal=0;
            ItemRentado r=daoCliente.loadItemCliente(iditem);
            if(r==null){
                throw new ExcepcionServiciosAlquiler("EL Item no esta agregado");
            }else{
                LocalDate fechMinEntrega=r.getFechafinrenta().toLocalDate();
                LocalDate fechEntrega=fechaDevolucion.toLocalDate();
                long dRetraso=ChronoUnit.DAYS.between(fechMinEntrega,fechEntrega);
                
                if(dRetraso>0){
                    mulTotal=dRetraso*mula_Diaria;
                }            
            }
            return mulTotal;

        }catch(PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("EL alquiler del item no pudo ser revisado", ex);
           
        }
        
        
        
        
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        
        try {
        return daoItem.consultTipItem(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item ");
      
                  }
    }
    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.consultTipItems();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los item ");
                  }
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            LocalDate fechinic=date.toLocalDate();
            LocalDate fechfin=fechinic.plusDays(numdias);
            Date fechini1=Date.valueOf(fechinic);
            Date fecfin=Date.valueOf(fechfin);
            daoCliente.registroAlquilerCliente(docu, item.getId(), fechini1,fecfin);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registral el alquiler del cliente "+ docu,ex);
                  }        
        
    }

    @Override
    public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.save(p);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar al cliente");
                  }
    }
    @Override
    public void registrarDevolucion(int iditem) throws ExcepcionServiciosAlquiler {
        try {
            daoItem.devolucion(iditem);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar al registrar la devolucion");
                  }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            daoItem.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar al registrar un item");
                  }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.vetarCliente(docu);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar al vetar al cliente");
                  }
        
    }
}
