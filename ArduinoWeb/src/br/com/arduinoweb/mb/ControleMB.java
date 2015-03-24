package br.com.arduinoweb.mb;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.push.annotation.Singleton;

import br.com.arduinoweb.controller.ComandoController;
import br.com.arduinoweb.controller.MessagesController;
import br.com.arduinoweb.util.ConstantsCommands;

@ManagedBean(name = "controleMB")
@Singleton
public class ControleMB extends MessagesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374969072811010227L;

	private static final String LED_RED = "sldRed";
	private static final String LED_GREEN = "sldGreen";
	private static final String LED_BLUE = "sldBlue";

	private String entrada;
	private String temperatura;
	//
	private static ComandoController comandoController = null;
	//
	private static int valorR = 0;
	private static int valorG = 0;
	private static int valorB = 0;
	//
	private static int valorMotor = 0;
	private static boolean lampada = false;

	private Map<String, Integer> mapaLed = null;

	public ControleMB() {
		init();
	}

	private void init() {

		if (comandoController == null) {
			comandoController = new ComandoController();
		}

		updateTemperatura();
		
		if (mapaLed == null) {
			mapaLed = new TreeMap<String, Integer>();
			mapaLed.put(LED_RED, valorR);
			mapaLed.put(LED_GREEN, valorG);
			mapaLed.put(LED_BLUE, valorB);
		}

	}

	public void updateTemperatura() {

		try {

			temperatura =  comandoController.getTemperature();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					error("Erro!", e.getMessage()));

		}

	}

	public String sendMensagem() {

		try {
			// System.out.println("##### " + entrada);
			comandoController.sendCommand(ConstantsCommands.CMD_MSG_TOKEN
					+ entrada);
			entrada = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					error("Erro!", e.getMessage()));

		}
		return "index.jsp";
	}

	public void changeLampada() {

		lampada = lampada == true ? false : true;

		System.out.println("changeLampada: " + lampada);
		try {
			comandoController.sendCommand(ConstantsCommands.CMD_LAMPADA_TOKEN
					+ lampada);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					error("Erro!", e.getMessage()));

		}
	}

	public void changeLeds(ValueChangeEvent event) {

		try {

			System.out.println("changeLEDs: " + event.getComponent().getId()
					+ ", " + event.getNewValue());

			mapaLed.put(event.getComponent().getId(), (int) event.getNewValue());

			// varreMapa();
			comandoController.sendCommand(ConstantsCommands.CMD_LED_TOKEN,
					mapaLed.get(LED_RED), mapaLed.get(LED_GREEN),
					mapaLed.get(LED_BLUE));
			// comandoController.sendCommand(ConstantsCommands.CMD_MOTOR_TOKEN,
			// valorMotor);

			entrada = null;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					error("Erro!", e.getMessage()));

		}
	}

	public void changeMotor(ValueChangeEvent event) {

		try {

			valorMotor = (int) event.getNewValue();

			System.out.println("changeMotor: " + valorMotor);

			comandoController.sendCommand(ConstantsCommands.CMD_MOTOR_TOKEN,
					valorMotor);

			entrada = null;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					error("Erro!", e.getMessage()));

		}
	}

	/**
	 * Apenas para efeito de TESTE
	 */
	private void varreMapa() {

		System.out.println();
		for (Map.Entry<String, Integer> e : mapaLed.entrySet()) {

			System.out.println(e.getKey() + ", " + e.getValue());

		}
	}

	// GETTERS E SETTERS
	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public ComandoController getComandoController() {
		return comandoController;
	}

	public void setComandoController(ComandoController comandoController) {
		this.comandoController = comandoController;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public int getValorR() {
		return valorR;
	}

	public void setValorR(int valorR) {
		this.valorR = valorR;
	}

	public int getValorG() {
		return valorG;
	}

	public void setValorG(int valorG) {
		this.valorG = valorG;
	}

	public int getValorB() {
		return valorB;
	}

	public void setValorB(int valorB) {
		this.valorB = valorB;
	}

	public int getValorMotor() {
		return valorMotor;
	}

	public void setValorMotor(int valorMotor) {
		this.valorMotor = valorMotor;
	}

	public boolean isLampada() {
		return lampada;
	}

	public void setLampada(boolean lampada) {
		this.lampada = lampada;
	}

}
