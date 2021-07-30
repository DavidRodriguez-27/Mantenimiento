package arreglos;

import java.io.*;

import clases.Persona;

import java.util.ArrayList;

public class ArregloPersonas {
	
	//  Atributo privado
	private ArrayList <Persona> per;
	//  Constructor
    public ArregloPersonas() {
    	per = new ArrayList <Persona> ();
    	cargarPersonas();
    }
	//  Operaciones públicas básicas
	public void adicionar(Persona x) {
		per.add(x);
		grabarPersonas();
	}
	public int tamaño() {
		return per.size();
	}
	public Persona obtener(int i) {
		return per.get(i);
	}
	public Persona buscar(int codigo) {
		Persona x;
		for (int i=0; i<tamaño(); i++) {
			x = obtener(i);
			if (x.getCodigo() == codigo)
				return x;
		}
		return null;
	}
	public Persona buscar(String dni) {
		Persona x;
		for (int i=0; i<tamaño(); i++) {
			x = obtener(i);
			if (x.getDni().equals(dni))
				return x;
		}
		return null;
	}
	public void eliminar(Persona x) {
		per.remove(x);
		grabarPersonas();
	}
	public void actualizarArchivo() {
		grabarPersonas();
	}
	private void grabarPersonas() {
		try {
			PrintWriter pw;
			String linea;
			Persona x;
			pw = new PrintWriter(new FileWriter("personas.txt"));
			for (int i=0; i<tamaño(); i++) {
				x = obtener(i);
				linea = x.getCodigo() + ";" +
					    x.getNombre() + ";" +
						x.getDni() + ";" +
						x.getPeso() + ";" +
						x.getEstatura() + ";" +
						x.getEstado();
				pw.println(linea);
			}
			pw.close();
		}
		catch (Exception e) {
		}
	}
	private void cargarPersonas() {
		try {
			BufferedReader br;
			String linea, nombre, dni;
			String[] s;
			int codigo, estado;
			double peso, estatura;
			br = new BufferedReader(new FileReader("personas.txt"));
			while ((linea = br.readLine()) != null) {
				s = linea.split(";");
				codigo = Integer.parseInt(s[0].trim());
				nombre = s[1].trim();
				dni = s[2].trim();
				peso = Double.parseDouble(s[3].trim());
				estatura = Double.parseDouble(s[4].trim());
				estado = Integer.parseInt(s[5].trim());
				adicionar(new Persona(codigo, nombre, dni, peso, estatura, estado));
			}
			br.close();
		}
		catch (Exception e) {
		}
	}
	//  Operaciones públicas complementarias
	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 1001;
		else
			return obtener(tamaño()-1).getCodigo() + 1;		
	}
	
}