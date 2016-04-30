package rmi;

import java.rmi.ConnectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

/**
 * @author fernandopucci
 *
 *
 *Classe para testes do servidor RMI. 
 *
 *Implementacoes semelhantes devem estar implementadas em aplicacoes que desejem enviar comandos
 */
public class ClientMain {

	private void doTest() {
		try {
			// fire to localhost port 1099
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// search for myMessage service
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// call server's method
			String teste = JOptionPane.showInputDialog("Digita a mensagem: ",
					"Entrada");
			impl.sendCommand(teste);
			JOptionPane.showMessageDialog(null, "Mensagem Enviada", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ConnectException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível localizar o servidor RMI", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void gatheringInformation() {
		try {
			// fire to localhost port 1099
			Registry myRegistry = LocateRegistry.getRegistry(Message.HOST,
					Message.PORT);

			// search for myMessage service
			Message impl = (Message) myRegistry.lookup(Message.SERVICE_NAME);

			// call server's method
			String teste = JOptionPane.showInputDialog("Solicite a informação: ",
					"Entrada");
			
			String retorno = impl.getInformation(teste);
			JOptionPane.showMessageDialog(null, "Informação Solicitada: " +  retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (ConnectException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível localizar o servidor RMI", "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ClientMain main = new ClientMain();
//		main.doTest();
		main.gatheringInformation();
	
	}
}
