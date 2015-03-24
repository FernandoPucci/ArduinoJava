package rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author fernandopucci
 *
 *Interface que determina quais metodos serão expostos no servidor RMI
 *
 */
public interface Message extends Remote{

	void sendCommand(String command) throws RemoteException;
	String getInformation(String info) throws RemoteException;

	/**
	 * Nome do servico
	 */
	public static final String SERVICE_NAME = "rmiConnector";
	
	/**
	 * Endereco da maquina servidora
	 */
	public static final String HOST = "127.0.0.1";
	
	/**
	 * Porta do serviço RMI da maquina servidora
	 */
	public static final int PORT = 1099;
}
