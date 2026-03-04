package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.Cliente;
import es.unican.is2.SegurosCommon.DataAccessException;
import es.unican.is2.SegurosCommon.IClientesDAO;
import es.unican.is2.SegurosCommon.IGestionClientes;
import es.unican.is2.SegurosCommon.IGestionSeguros;
import es.unican.is2.SegurosCommon.IInfoSeguros;
import es.unican.is2.SegurosCommon.ISegurosDAO;
import es.unican.is2.SegurosCommon.OperacionNoValida;
import es.unican.is2.SegurosCommon.Seguro;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {
	private IClientesDAO clientesDao;
	private ISegurosDAO segurosDao;
	
	public GestionSeguros(IClientesDAO clientesDao, ISegurosDAO segurosDao) {
		this.clientesDao = clientesDao;
		this.segurosDao = segurosDao;
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
		return clientesDao.creaCliente(c);
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
		return clientesDao.eliminaCliente(dni);
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
		Cliente cliente = clientesDao.cliente(dni);
		if (cliente == null) {
			return null;
		}
		
		Seguro seguro = segurosDao.creaSeguro(s);
		if (seguro == null) {
			throw new OperacionNoValida("Seguro already exists.");
		}
		cliente.getSeguros().add(seguro);
		clientesDao.actualizaCliente(cliente);
		
		return seguro;
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
		Cliente cliente = clientesDao.cliente(dni);
		if (cliente == null) {
			return null;
		}
		Seguro seguro = segurosDao.seguroPorMatricula(matricula);
		if (seguro == null) {
			return null;
		}
		if (!cliente.getSeguros().contains(seguro)) {
			throw new OperacionNoValida("Client does not contain that");
		}
		
		return seguro;
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
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro seguro = segurosDao.seguroPorMatricula(matricula);
		if (seguro == null) {
			return null;
		}
		
		seguro.setConductorAdicional(conductor);
		return seguro;
	}


	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		return clientesDao.cliente(dni);
	}


	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		Seguro seguro = new Seguro();
		seguro.setMatricula(matricula);
		return segurosDao.creaSeguro(seguro);
	}
}
