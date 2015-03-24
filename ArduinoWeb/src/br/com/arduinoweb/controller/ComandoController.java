package br.com.arduinoweb.controller;

import java.rmi.ConnectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.com.arduinoweb.model.BaseController;
import br.com.arduinoweb.util.ConstantsCommands;
import rmi.Message;

public class ComandoController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119981071538645130L;

	// public static int valorR;
	// public static int valorG;
	// public static int valorB;

	public String getTemperature() throws Exception {

		String retorno = null;

		try {
			// configura servicos RMI
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// conecta ao servico RMI
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// chama metodo do RMI Server

			retorno = impl.getInformation(ConstantsCommands.CMD_TOKEN + "temp");

			System.out.println(ConstantsCommands.CMD_MSG_SNT);

		} catch (ConnectException e) {
			throw new Exception("Não foi possível localizar o servidor RMI");

		} catch (Exception e) {
			throw e;
		}

		return retorno;

	}

	public void sendCommand(String cmd) throws Exception {

		try {
			// configura servicos RMI
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// conecta ao servico RMI
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// chama metodo do RMI Server
			impl.sendCommand(buildCommand(cmd, null));

			System.out.println(ConstantsCommands.CMD_MSG_SNT);

		} catch (ConnectException e) {
			throw new Exception("Não foi possível localizar o servidor RMI");

		} catch (Exception e) {
			throw e;
		}

	}

	public void sendCommand(String cmd, int value) throws Exception {

		try {
			// configura servicos RMI
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// conecta ao servico RMI
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// chama metodo do RMI Server
			impl.sendCommand(buildCommand(cmd, value));

			System.out.println("Mensagem Enviada");

		} catch (ConnectException e) {
			throw new Exception("Não foi possível localizar o servidor RMI");

		} catch (Exception e) {
			throw e;
		}

	}

	public void sendCommand(String cmd, int redValue, int greenValue,
			int blueValue) throws Exception {

		try {
			// configura servicos RMI
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// conecta ao servico RMI
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// chama metodo do RMI Server
			// impl.sendCommand(buildCommand(cmd, valorR, valorG, valorB));
			impl.sendCommand(buildCommand(cmd, redValue, greenValue, blueValue));

			System.out.println("Mensagem Enviada");

		} catch (ConnectException e) {
			throw new Exception("Não foi possível localizar o servidor RMI");

		} catch (Exception e) {
			throw e;
		}

	}

	private String buildCommand(String command, int... value) {

		StringBuilder sb = new StringBuilder();

		sb.append(ConstantsCommands.CMD_TOKEN);
		sb.append(command);

		if (value != null && value.length == 1) {

			// sb.append(ConstantsCommands.CMD_TOKEN);
			sb.append(preencheZeroEsquerda(value[0]));

		} else if (value != null && value.length == 3) {

			for (int i = 0; i < value.length; i++) {
				// sb.append(ConstantsCommands.CMD_TOKEN);
				sb.append(i == 0 ? ConstantsCommands.CMD_RED_TOKEN
						: i == 1 ? ConstantsCommands.CMD_GREEN_TOKEN
								: i == 2 ? ConstantsCommands.CMD_BLUE_TOKEN
										: "--");
				sb.append(preencheZeroEsquerda(value[i]));
			}

		}

		System.out.println(">> " + sb.toString());

		return sb.toString();
	}

	private String preencheZeroEsquerda(int valor) {

		return valor < 10 ? ("00" + valor) : valor < 100 ? ("0" + valor) : "" + valor;

	}

	// public int getValorR() {
	// return valorR;
	// }

	// public void setValorR(int valorR) {
	// ComandoController.valorR = valorR;
	// }

	// public int getValorG() {
	// return valorG;
	// }

	// public void setValorG(int valorG) {
	// ComandoController.valorG = valorG;
	// }

	// public int getValorB() {
	// return valorB;
	// }

	// public void setValorB(int valorB) {
	// ComandoController.valorB = valorB;
	// }

}
