package edu.eci.pdsw.test;

import java.util.List;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

/**
 *
 * @author fchaves
 * 
 * 
 * /**
 * 
 * Calculo Multa:
 * 
 * Frontera:
 * CF1: Multas a devoluciones hechas en la fecha exacta (multa 0).
 * 
 * Clases de equivalencia:
 * CE2: El item despues de prestado no debe estar disponible, y debe estar asignado al cliente
 * CE3: La multa debe ser cero si el item se entrega antes de la fecha limite
 * 
 */
 

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        ServiciosAlquiler sa=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

     @Test
    public void CF1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        
        Item i1=new Item(new TipoItem(0, "Pelicula"), 44, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",3842,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(44);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 3842, item, 5);
        
        assertEquals("Se calcula correctamente la multa (0) "
                + "cuando la devolucion se realiza el dia limite."
                ,0, sa.consultarMultaAlquiler(sa.consultarItemsCliente(3842).get(0).getId(), java.sql.Date.valueOf("2005-12-25")));
                
    }
       
   
    @Test
    public void CE2Test() throws ExcepcionServiciosAlquiler {
        ServiciosAlquiler sa=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
        Cliente cliente = new Cliente("FUlano", 0, "456", "456", "7890");
        sa.registrarCliente(cliente);
        Item it = new Item(new TipoItem(0, "PrueTIpItem"), 1038, "INtento", "Intento Descipcion Descripcion", java.sql.Date.valueOf("2005-12-28"), 3, "DVD", "Prueba Intento");
        sa.registrarItem(it);
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 0, it, 2);
        try {
            assertTrue("Consultar si esta el item disponible", sa.consultarItemsDisponibles().size() == 0);
        } catch (ExcepcionServiciosAlquiler e) {
            
        }
        List<ItemRentado> lista = sa.consultarItemsCliente(0);
        assertTrue("El item rentado es el mismo item que se registro", lista.get(0).getItem().toString().equals(it.toString()));
    }
    
    @Test
    public void CE3Test() {
            ServiciosAlquiler sa=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
            Cliente cliente = new Cliente("Intento2", 5678, "11112", "987654", "34560987");
        try {
            sa.registrarCliente(cliente);
        } catch (ExcepcionServiciosAlquiler ex) {
            fail("Error al ingresar el Cliente");
        }
            Item item = new Item(new TipoItem(0, "INtento TipoItem"), 9938, "INtento", "INtento Descripcion", java.sql.Date.valueOf("2999-12-31"), 3, "BLURAY", "Intento Genero");
        try {
            sa.registrarItem(item);
        } catch (ExcepcionServiciosAlquiler ex) {
            fail("Error al  Ingresar el Item");
        }
        try {
            sa.registrarAlquilerCliente(java.sql.Date.valueOf("2017-03-03"), 5678, item, 10);
        } catch (ExcepcionServiciosAlquiler ex) {
            fail("Error al Ingresar AlquilerCliente");
        }
        try {
            assertEquals("Comprobando si la multa es igual a 0", 0, sa.consultarMultaAlquiler(sa.consultarItemsCliente(5678).get(0).getId(), java.sql.Date.valueOf("2098-03-10")));
        } catch (ExcepcionServiciosAlquiler ex) {
            fail("Error al comprobar la multa  usando consultarMultaAlquiler  -  " + ex.getMessage());
        }
        
        
    }
    @Test
    public void emptyDB() {
        qt().forAll(integers().allPositive()).check((id) -> {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(id);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
             return r;
        });
    }

}
