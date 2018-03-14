package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idCli")int id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param idTemR
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idItemRen")int idTemR,@Param("idCli") int id, 
            @Param("idItem") int idit, 
            @Param("fecIni") Date fechainicio,
            @Param("fecFin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return x    
     */
    public List<Cliente> consultarClientes();
    public ItemRentado loadItemClien(@Param("idItem") int id);
    
    public void agregarCliente(@Param("idCie") long docu,@Param("name") String nombre,@Param("tel") String telefono,@Param ("direc")String dirrecion,@Param("emal") String email, @Param("bool")Boolean boll);
    public int getNetval();
    public void vetarCliente(@Param("idClient") int id)throws PersistenceException;
    
}
