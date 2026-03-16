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
	
	
	/// IGestionClientes
	
	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		return clientesDao.creaCliente(c);
	}
	
	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = clientesDao.cliente(dni);
		if (!cliente.getSeguros().isEmpty()) {
			throw new OperacionNoValida("Cliente has seguros.");
		}
		
		return clientesDao.eliminaCliente(dni);
	}
	
	
	/// IGestionSeguros
	
	@Override
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
	
	@Override
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
		cliente.getSeguros().remove(seguro);
		clientesDao.actualizaCliente(cliente);
		
		return segurosDao.eliminaSeguro(seguro.getId());
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro seguro = segurosDao.seguroPorMatricula(matricula);
		if (seguro == null) {
			return null;
		}
		
		seguro.setConductorAdicional(conductor);
		return segurosDao.actualizaSeguro(seguro);
	}

	
	/// IInfoSeguros
	
	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		return clientesDao.cliente(dni);
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		return segurosDao.seguroPorMatricula(matricula);
	}
}
