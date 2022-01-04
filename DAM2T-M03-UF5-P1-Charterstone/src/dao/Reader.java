package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ExecutionExceptions;

public class Reader {
	private BufferedReader bf;
	private FileReader rd;
	
	public Reader(String file) throws ExecutionExceptions {
		try {
			this.rd = new FileReader(file);
			this.bf = new BufferedReader(this.rd);
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_FICHERO);
		}
	}
	
	public String read() throws ExecutionExceptions {
		try {
			return this.bf.readLine();
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_LECTURA);
		}
	}

	public void closeFile() throws ExecutionExceptions {
		try {
			this.bf.close();
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_CIERRE);
		}
	}

	public int readChar() throws ExecutionExceptions {
		try {
			return this.rd.read();
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_LECTURA);
		}
	}
}
