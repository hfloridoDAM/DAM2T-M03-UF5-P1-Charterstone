package exceptions;

import java.util.Arrays;
import java.util.List;

public class ExecutionExceptions extends Exception {

	public static final int ERROR_FICHERO = 0;
	public static final int ERROR_LECTURA = 1;
	public static final int ERROR_ESCRITURA = 2;
	public static final int ERROR_CIERRE = 3;

	private int value;

	public ExecutionExceptions(int value) {
		this.value = value;
	}

	private List<String> message = Arrays.asList(
			"<< Error creando el fichero >>",
			"<< Error de lectura de fichero >>",
			"<< Error de escritura de fichero >>",
			"<< Error cerrando el fichero >>");

	@Override
	public String getMessage() {
		return message.get(value);
	}
}
