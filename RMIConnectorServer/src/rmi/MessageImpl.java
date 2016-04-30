package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import javax.rmi.CORBA.Util;

import ports.ControlePorta;
import util.Constantes;

/**
 * @author fernandopucci
 *
 *
 *         Classe que Implementa a mensagem de comando recebida, deve chamar os
 *         comandos seriais do Arduino
 */
public class MessageImpl extends UnicastRemoteObject implements Message,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -447359723460304664L;

	private ControlePorta cp = null;

	public MessageImpl() throws RemoteException {

		try {
			
			if (cp == null) {
				cp = new ControlePorta(Constantes.SERIAL_PORT,
						Constantes.SERIAL_SPEED);
			}
			
		} catch (Exception ex) {
			System.out.println("Erro ao iniciar porta ["
					+ Constantes.SERIAL_PORT + "] a ["
					+ Constantes.SERIAL_SPEED + "] bps");

		}

	}

	@Override
	public void sendCommand(String command) throws RemoteException {

		try {

			cp.sendComando(command);
			
			System.out.println(new Date(System.currentTimeMillis())
					+ " - Comando Recebido: " + command);

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		}
	}

	@Override
	public String getInformation(String info) throws RemoteException {

		// TODO: fazer metodo que conecta ao arduino e busca e
		// retornatemperatura atual
		cp.getInformation();
		return  cp.getInformation() + "°C - "+ util.Util.getDataFormatada(new Date(System.currentTimeMillis()));
	}
}
