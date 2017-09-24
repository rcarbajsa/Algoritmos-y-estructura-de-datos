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
	
	public void ordenarArr() {
		System.out.println(database.toString());
		boolean bool = false;
		String begin;
		int value = 0;
		while(bool) {
			begin = database.get(database.size() - 1 - value).getMatricula();
			for (int i = 0; i<database.size();i++) {
				if(begin.compareTo(database.get(i).getMatricula()) >= 0) {
					database.add(value, database.get(i));
					value ++;
				}
			}
			value ++;
		}
		System.out.println(database.toString());
	}
	
	/**
	 * Adds a new calificacion to the database.
	 * @throws CalificacionAlreadyExistsException
	 * if a calificacion with the same matricula already existed
	 * in the database.
	 */
	public void addCalificacion(String nombre, String matricula, int nota)
			throws CalificacionAlreadyExistsException{
		
	}

	/**
	 * Modifies the nota for a calificacion with the given matricula
	 * (there can never be more than calificacion for a given matricula).
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public void updateNota(String matricula, int nota)
			throws InvalidMatriculaException{
		
	}

	/**
	 * Deletes the calificacion for the student with the given matricula.
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public void deleteCalificacion(String matricula)
			throws InvalidMatriculaException{
		
	}

	/**
	 * Returns the calificacion for the student with the given matricula.
	 * @return the calificacion for the student with the given matricula.
	 * @throws InvalidMatriculaExcepcion if there is no calificacion for
	 * the specified matricula.
	 */
	public Calificacion getCalificacion(String matricula)
			throws InvalidMatriculaException{
		
	}

	/**
	 * Returns a SORTED indexed list with the calificaciones in the
	 * database, where the
	 * sorting order is given by the Comparator cmp. Note that calificaciones
	 * should be sorted from smaller to bigger values (using cmp).
	 * @return a sorted list of the calificacions in the database.
	 */
	IndexedList<Calificacion> getCalificaciones(Comparator<Calificacion> cmp);

	/**
	 * Returns a list with the calificacions that have a nota greater
	 * or equal to the notaMinima argument.
	 * Note that there is no requirement that the returned
	 * list has to be sorted. This method should NOT remove calificaciones
	 * from the database.
	 */
	public IndexedList<Calificacion> getAprobados(int notaMinima){
		IndexedList<Calificacion> aprobados = new ArrayIndexedList<Calificacion>();
		for(int i = 0; i< database.size(); i++) {
			if(database.get(i).getNota() >= notaMinima) {
				
			}
		}
	}

}
