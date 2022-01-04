package dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.ExecutionExceptions;

public class Writer {
	private BufferedWriter bf;
	
	
	public Writer(String file) throws ExecutionExceptions {
		try {
			FileWriter fw = new FileWriter(file);
			this.bf = new BufferedWriter(fw);
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_FICHERO);
		}
	}
	
	public void write (String linea) throws ExecutionExceptions {
		try {
			this.bf.append(linea+"\n");
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_ESCRITURA);
		}
	}

	public void closeFile() throws ExecutionExceptions {
		try {
			this.bf.close();
		} catch (IOException e) {
			throw new ExecutionExceptions(ExecutionExceptions.ERROR_CIERRE);
		}
	}
}
