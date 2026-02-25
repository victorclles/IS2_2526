package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.Cliente;
import es.unican.is2.SegurosCommon.DataAccessException;
import es.unican.is2.SegurosCommon.OperacionNoValida;
import es.unican.is2.SegurosCommon.Seguro;

public class GestionSeguros {
	private IClientesDAO clientesDao;
	private IGestionSeguros segurosDao;
	private IInfoSeguros infoSegurosDao;
	
	public GestionSeguros(IClientesDAO clientesDao, IGestionSeguros segurosDao, IInfoSeguros infoSegurosDao) {
		this.clientesDao = clientesDao;
		this.segurosDao = segurosDao;
		this.infoSegurosDao = infoSegurosDao;
	}
	
	
	/**
	 * Persiste un nuevo cliente
	 * @param c Cliente que desea persistir
	 * @return El cliente persitido
	 * 		   null si no se persiste porque ya existe
	  * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		clientesDao.nuevoCliente(c);
	}
	
	/**
	 * Elimina el cliente cuyo dni se indica
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El cliente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValida si el cliente existe 
	 *         pero tiene seguros a su nombre
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente bajaCliente(String dni) throws OperacionNoValida,DataAccessException {
		clientesDao.bajacliente(dni);
	}
	
	/**
	 * Agrega un nuevo seguro al cliente cuyo dni se indica.
	 * @param s Seguro que desea agregar
	 * @param dni DNI del cliente
	 * @return El seguro agregado
	 * 		   null si no se agrega porque no se encuentra el cliente
	 * @throws OperacionNoValida si el seguro ya existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		segurosDao.nuevoSeguro(s, dni);
	}
	
	/**
	 * Elimina el seguro cuya matricula se indica y 
	 * que pertenece al cliente cuyo dni se indica
	 * @param matricula Identificador del seguro a eliminar
	 * @param dni DNI del propietario del seguro
 	 * @return El seguro eliminado
 	 *         null si el seguro o el cliente no existen
 	 * @throws OperacionNoValida si el seguro no pertenece al dni indicado
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		segurosDao.bajaSeguro(matricula, dni);
	}

	/**
	 * Agrega o modifica el conductor adicional al seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @param conductor Nombre del conductor adicional a agregar
 	 * @return El seguro modificado
 	 *         null si el seguro no existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException;
}
