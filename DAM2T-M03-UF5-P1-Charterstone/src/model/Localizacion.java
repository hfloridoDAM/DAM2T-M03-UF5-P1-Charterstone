package model;

import exceptions.LogicExceptions;

public class Localizacion {
	private String necesario;
	private int numNecesario;
	private String obtenido;
	private int numObtenido;
	private Jugador jugador;

	public Localizacion(String[] line) {
		this.necesario = line[1];
		this.numNecesario = Integer.parseInt(line[2]);
		this.obtenido = line[4];
		this.numObtenido = Integer.parseInt(line[5]);
	}
	
	public boolean checkMateriales(String enviado) throws LogicExceptions {
		
		if (enviado.contains(necesario) && getMateriales(necesario, enviado) >= numNecesario) {
			return true;
		}
		throw new LogicExceptions(LogicExceptions.ERROR_MATERIALES_INSUFICIENTES);
	}
	
	private int getMateriales(String material, String enviado) {
		boolean encontrado = false;
		for (String s : enviado.split(" ")) {
			if (encontrado) return Integer.parseInt(s);
			if (s.equals(material)) encontrado = true;
		}
		return 0;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}
	
	public String getObtenido() {
		return this.obtenido;
	}

	public String getRecursos() {
		return this.obtenido + " " + this.numObtenido;
	}

	public String getRecursosAGastar() {
		return this.necesario + " " + this.numNecesario;
	}

	@Override
	public String toString() {
		return "Localizacion [necesario=" + necesario + ", numNecesario="
				+ numNecesario + ", obtenido=" + obtenido + ", numObtenido="
				+ numObtenido + ", jugador=" + (jugador != null? jugador.getColor(): "ninguno") + "]";
	}
}
