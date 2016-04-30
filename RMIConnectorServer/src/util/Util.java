package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String getDataFormatada(Date date) {

		return new SimpleDateFormat(Constantes.DATA_FORMATO).format(date);
	}

}
