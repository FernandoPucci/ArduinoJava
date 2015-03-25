package tests;

import javax.swing.JOptionPane;

import ports.ControlePorta;
import util.Constantes;

/**
 * @author fernando-pucci
 *
 *         Classe principal de testes para comunicação serial com o Arduino
 *
 */
public class SerialTester {

	public static void main(String[] args) {

		ControlePorta cp = new ControlePorta(Constantes.SERIAL_PORT,
				Constantes.SERIAL_SPEED);

		do {

			String mensagem = JOptionPane.showInputDialog(null,
					"Digite mensagem:", "MENSAGEM",
					JOptionPane.INFORMATION_MESSAGE);

			cp.sendComando(mensagem);

		} while (JOptionPane.showConfirmDialog(null, "Deseja Continuar?",
				"Continuar?", JOptionPane.YES_NO_OPTION) == 0);
		cp.close();
		System.out.println("Finalizando . . .");
		System.exit(0);
	}

}
