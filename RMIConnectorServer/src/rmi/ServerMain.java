package rmi;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author fernandopucci
 *
 */
/**
 * @author fernandopucci
 *
 *Classe principal com parametros para inicializacao do servidor RMI 
 */
public class ServerMain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5423045442849530914L;

	private void startServer() {
		try {
			// create on port 1099
			Registry registry = LocateRegistry.createRegistry(Message.PORT);

			// create a new service named myMessageMain
			registry.rebind(Message.SERVICE_NAME, new MessageImpl());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Servidor ATIVO!");
	}

	public static void main(String[] args) {
		ServerMain main = new ServerMain();
		main.startServer();
	}

}
