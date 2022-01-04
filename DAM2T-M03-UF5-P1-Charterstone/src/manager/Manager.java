package manager;
import java.util.ArrayList;

import dao.Reader;
import dao.Writer;
import exceptions.LogicExceptions;
import exceptions.ExecutionExceptions;
import model.Jugador;
import model.Localizacion;

public class Manager {
	private static Manager manager;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Localizacion> localiaciones;
	private Reader reader;
	private Writer writer;
	private Jugador ultimoJugador;
	private boolean first;

	private Manager () {
		this.jugadores = new ArrayList<>();
		this.localiaciones = new ArrayList<>();
		this.first = false;
	}
	
	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	public void init() {
		try {
			this.reader = new Reader("files/entrada.txt");
			this.writer = new Writer("files/salida.txt");
			readFile();
		} catch (ExecutionExceptions e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				this.reader.closeFile();
				this.writer.closeFile();
			} catch (ExecutionExceptions e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void readFile() throws ExecutionExceptions {
		String line;
		readJugadors();
		readLocalizaciones();
		while ((line = reader.read()) != null) {
			try {
				gestionarAccion(line.split(" "));
			} catch (LogicExceptions e) {
				this.writer.write("ERROR: " + line + " --> " + e.getMessage());
			}
		}
	}

	private void readJugadors() throws ExecutionExceptions {
		String line;
		while (!(line = reader.read()).equals("##")) {
			this.jugadores.add(new Jugador(line.split(" ")));
		}
	}
	
	private void readLocalizaciones() throws ExecutionExceptions {
		String line;
		while (!(line = reader.read()).equals("##")) {
			this.localiaciones.add(new Localizacion(line.split(" ")));
		}
	}

	private void gestionarAccion(String[] split) throws LogicExceptions, ExecutionExceptions {
		if (!first && !split[0].equals("I")) throw new LogicExceptions(LogicExceptions.ERROR_ACCION);
		switch (split[0]) {
			case "I":
				checkArgumentos(split,1);
				iniciarPartida();
				first = true;
				break;
			case "M":
				if (this.ultimoJugador != null && 
						this.ultimoJugador.getJugador().replace("#","").equals(split[1])) 
					throw new LogicExceptions(LogicExceptions.ERROR_TURNO_REPETIDO);
				checkArgumentos(split,3);
				move(split);
				break;
			case "S":
				checkArgumentos(split, 1);
				showPuntos();
				break;
			case "R":
				checkArgumentos(split,2);
				recoger(split);
				break;
			case "L":
				checkArgumentos(split,1);
				verLocalizaciones();
				break;
			default:
				throw new LogicExceptions(LogicExceptions.ERROR_ACCION);
		}
	}
	
	private void verLocalizaciones() throws ExecutionExceptions {
		for (Localizacion l : this.localiaciones) {
			writer.write("\t" + l.toString());
		}
	}

	private void recoger(String[] split) {
		for (Localizacion l : this.localiaciones) {
			if (l.getJugador() != null && 
					l.getJugador().getColor()
					.equals(this.jugadores.get(Integer.parseInt(split[1])-1).getColor()))
				l.setJugador(null);
		}
	}

	private void showPuntos() throws ExecutionExceptions {
		for (Jugador j : this.jugadores) {
			writer.write("\t" + j.toString());
		}
	}

	private void move(String[] split) throws NumberFormatException, LogicExceptions {
		Localizacion localizacion = this.localiaciones.get(Integer.parseInt(split[2])-1);
		Jugador jugador = this.jugadores.get(Integer.parseInt(split[1])-1);
		checkPersonajesJugados(jugador.getColor());
		if (localizacion.checkMateriales(jugador.getMateriales())) {
			jugador.removeGastado(localizacion.getRecursosAGastar());
			jugador.addObtenido(localizacion.getRecursos());
			localizacion.setJugador(jugador);
			this.ultimoJugador = jugador;
			throw new LogicExceptions(LogicExceptions.NO_ERROR);
		}
	}

	private void checkPersonajesJugados(String color) throws LogicExceptions {
		int usados = 0;
		for (Localizacion l : this.localiaciones) {
			if (l.getJugador() != null && l.getJugador().getColor().equals(color)) usados++;
		}
		if (usados >= 2) throw new LogicExceptions(LogicExceptions.ERROR_PERSONAJES_INSUFICIENTES);
	}

	public void checkArgumentos(String[] split, int requerido) throws LogicExceptions {
		if (split.length != requerido) throw new LogicExceptions(LogicExceptions.ERROR_ARGUMENTOS);
	}

	private void iniciarPartida() throws ExecutionExceptions {
		this.writer.write("INICIANDO PARTIDA");
	}
	
}
