package tests;

import ports.ControlePorta;

/**
 * @author fernando-pucci
 *
 *         Classe principal de testes para comunicação serial com o Arduino
 *
 */
public class SerialTester {

	public static void main(String[] args) {

		ControlePorta cp = new ControlePorta("/dev/ttyACM0", 9600);

		cp.enviaDados(args[0]);

		cp.close();

		System.out.println("OK");
		System.exit(0);
	}

}
