package aed.actasnotas;
import es.upm.aedlib.indexedlist.*;

import java.util.Comparator;

import aed.actasnotas.*;

public class ActasNotasAED implements ActaNotas {
	private String nombre, matricula;
	private int nota;
	private IndexedList<Calificacion> database;
	
	public ActasNotasAED(String nombre, String matricula, int nota, IndexedList<Calificacion> database) {
			this.nombre = nombre;
			this.matricula = matricula;
			this.nota = nota;
			this.database = new ArrayIndexedList<Calificacion>();
	}
	
	private int buscarMatricula(String matricula) {
		int i=0;
		boolean aux=false;
		for( i=0;i<database.size()&&aux==false;i++) {
			if(database.get(i).getMatricula().equals(matricula)) {
		        aux=true;
				database.get(i).setNota(nota);
			}
		}
		if(!aux) {return -1;}
		else{return i;}
	}
	
	public void addCalificacion(String nombre, String matricula, int nota)
			throws CalificacionAlreadyExistsException{
		if(buscarMatricula(matricula)==-1) {
			Calificacion add = new Calificacion(nombre, matricula, nota);
			database.add(database.size(), add);
		}else {
			throw new CalificacionAlreadyExistsException();
		}
	}
	
	public void updateNota(String matricula, int nota)
			throws InvalidMatriculaException{
		if(buscarMatricula(matricula)==-1) {
			throw new InvalidMatriculaException(); 
		}else {
			database.get(buscarMatricula(matricula)).setNota(nota);
		}
	}

	
	public void deleteCalificacion(String matricula)
			throws InvalidMatriculaException{
		if(buscarMatricula(matricula)==-1) {
			throw new InvalidMatriculaException(); 
		}else {
			database.remove(database.get(buscarMatricula(matricula)));
		}
	}


	public Calificacion getCalificacion(String matricula)
			throws InvalidMatriculaException{
		if(buscarMatricula(matricula)==-1) {
			throw new InvalidMatriculaException(); 
		}else {
			return database.get(buscarMatricula(matricula));
		}
	}
	

	public IndexedList<Calificacion> getCalificaciones(Comparator<Calificacion> cmp){
		for(int i=0; i<database.size();i++) {
			for (int j=i+1; j<database.size();j++) {
				if(cmp.compare(database.get(i), database.get(j)) < 0) {
					database.add(i, database.get(j));
				}
			}
		}
		return database;
	}


	public IndexedList<Calificacion> getAprobados(int notaMinima){
		int cont =0;
		IndexedList<Calificacion> aprobados = new ArrayIndexedList<Calificacion>();
		for(int i = 0; i< database.size(); i++) {
			if(database.get(i).getNota() >= notaMinima) {
				aprobados.add(cont, database.get(i));
				cont++;
			}
		}
		return aprobados;
	}

}
