package model;

public class Jugador {
	private String jugador;
	private String color;
	private int puntuacion;
	private int madera;
	private int carbon;
	private int trigo;
	private int monedas;

	public Jugador(String[] split) {
		this.jugador = split[0];
		this.color = split[1];
		this.carbon = Integer.parseInt(split[2]);
		this.trigo = Integer.parseInt(split[3]);
		this.madera = Integer.parseInt(split[4]);
		this.puntuacion = 0;
		this.monedas = 0;
	}

	public String getColor() {
		return color;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public int getMonedas() {
		return monedas;
	}

	public int getMadera() {
		return madera;
	}

	public int getCarbon() {
		return carbon;
	}

	public int getTrigo() {
		return trigo;
	}
	
	public String getJugador() {
		return this.jugador;
	}

	public String getMateriales() {
		return "M " + this.madera + " C " + this.carbon + " T " + this.trigo + " X " + this.monedas;
	}
	
	public void addObtenido(String obtenido) {
		if (obtenido.split(" ")[0].equals("M")) this.madera += Integer.parseInt(obtenido.split(" ")[1]);
		else if (obtenido.split(" ")[0].equals("T")) this.trigo += Integer.parseInt(obtenido.split(" ")[1]);
		else if (obtenido.split(" ")[0].equals("C")) this.carbon += Integer.parseInt(obtenido.split(" ")[1]);
		else if (obtenido.split(" ")[0].equals("P")) this.puntuacion += Integer.parseInt(obtenido.split(" ")[1]);
	}

	@Override
	public String toString() {
		return "Jugador [color=" + color + ", puntuacion=" + puntuacion
				+ ", madera=" + madera + ", carbon=" + carbon + ", trigo="
				+ trigo + ", monedas=" + monedas + "]";
	}

	public void removeGastado(String gastado) {
		if (gastado.split(" ")[0].equals("M")) this.madera -= Integer.parseInt(gastado.split(" ")[1]);
		else if (gastado.split(" ")[0].equals("T")) this.trigo -= Integer.parseInt(gastado.split(" ")[1]);
		else if (gastado.split(" ")[0].equals("C")) this.carbon -= Integer.parseInt(gastado.split(" ")[1]);
		else if (gastado.split(" ")[0].equals("P")) this.puntuacion -= Integer.parseInt(gastado.split(" ")[1]);
	}

}
