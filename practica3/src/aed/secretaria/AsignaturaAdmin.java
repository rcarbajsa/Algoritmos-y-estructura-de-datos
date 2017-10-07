package aed.secretaria;


import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;


/**
 * Organizes the administration for an asignatura.
 * An asignatura has a name (e.g., "AED"), and the class
 * keeps track of matriculated alumnos, 
 * and of assigned notas for alumnos.
 */
public class AsignaturaAdmin {
	private String nombreAsignatura;
	private PositionList<Pair<String,Integer>> notas;

	/**
	 * Creates an asignatura administration object, 
	 * where the asignatura has a name (e.g. "AED"),
	 * and which keeps tracks of matriculated alumnos (their matriculas), 
	 * and assigned notas.
	 */
	public AsignaturaAdmin(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
		this.notas = new NodePositionList<Pair<String,Integer>>();
	}
	
	public Position<Pair<String,Integer>> buscarMatricula (String matricula){
		Position<Pair<String,Integer>> resultado=null;
		for(Position<Pair<String,Integer>> aux=notas.first();aux!=null;aux=notas.next(aux)) {
			if(aux.element().getLeft().equals(matricula)) {
				resultado=aux;
			}
		}
		return resultado;
		
	}

	/**
	 * Returns the asignatura name.
	 * @return the asignatura name.
	 */
	public String getNombreAsignatura() {
		return this.nombreAsignatura;
	}

	/**
	 * Adds a number of matriculas
	 * to the asignatura. Returns a list of the matriculas
	 * that were added,
	 * i.e., the list of matriculas which had not previously been added.
	 * @return a list with the matriculas added.
	 */
	public PositionList<String> matricular(PositionList<String> matriculas) {
		PositionList<String> ret = new NodePositionList<String>();
		for(Position<String> aux = matriculas.first();aux!=null;aux=matriculas.next(aux)) {
			if(!estaMatriculado(aux.element())){
				Pair<String, Integer> pair = new Pair<String, Integer> (aux.element(), null);
				ret.addLast(aux.element());
				notas.addLast(pair);
			}
		}
		return ret;
	}

	/**
	 * Removes a list of matriculas from the asignatura.
	 * Returns a list with the matriculas which were successfully
	 * removed. A matricula can be removed IF:
	 * i) the matricula was previously added and has not been removed since, AND
	 * ii) there is NO nota associated with the matricula.
	 * @return a list with the matriculas that were removed.
	 */
	
		public PositionList<String> desMatricular(PositionList<String> matriculas) {
			PositionList<String> ret = new NodePositionList<String>();
			//Bucle para añadir las matriculas al ret.Funciona
			for(Position<String> auxMat= matriculas.first();auxMat!=null;auxMat=matriculas.next(auxMat)) {
				if( estaMatriculado(auxMat.element())&&!tieneNotaAux(auxMat.element())) {
					ret.addLast(auxMat.element());
				}
			}
			System.out.println(ret.toString());
			
				//Bucle para eliminar las matriculas de notas. No funciona
				for(Position<String> aux= ret.first();aux!=null;aux=ret.next(aux)) {
					notas.remove(buscarMatricula(aux.element()));
					}
				return ret;
}

	public boolean estaMatriculado(String matricula) {
		boolean bool = false;
		for(Position<Pair<String, Integer>> cont= notas.first(); cont != null; cont = notas.next(cont)) {
			if(cont.element().getLeft().equals(matricula)) {
				bool = true;
			}
		}
		return bool;
	}

	public boolean tieneNotaAux(String matricula){
		if(estaMatriculado(matricula)==false) {
			return false;
		}
		boolean resultado=false;
		for(Position<Pair<String,Integer>> aux=notas.first();aux!=null;aux=notas.next(aux)) {
			if(aux.element().getLeft().equals(matricula)
					&& aux.element().getRight()!=null) {
				resultado=true;
			}
		}
		return resultado;
	}

	public boolean tieneNota(String matricula) throws InvalidMatriculaException {
		if(estaMatriculado(matricula)==false) {
			throw new InvalidMatriculaException();
		}
		boolean resultado=false;
		for(Position<Pair<String,Integer>> aux=notas.first();aux!=null;aux=notas.next(aux)) {
			if(aux.element().getLeft().equals(matricula)
					&& aux.element().getRight()!=null) {
				resultado=true;
			}
		}
		return resultado;
	}

	/**
	 * Returns the nota of a matricula.
	 * @return the nota of an matrciula.
	 * @throws InvalidMatriculaException if the matricula has no nota assigned,
	 * or the matricula has not been added to the asignatura (or was removed).
	 */
	public int getNota(String matricula) throws InvalidMatriculaException {
		if(estaMatriculado(matricula)==false) {
			throw new InvalidMatriculaException();
		}
		int ret = 0;
		for(Position<Pair<String, Integer>> cont= notas.first(); cont != null; cont = notas.next(cont)) {
			if(cont.element().getLeft().equals(matricula)){
				if(cont.element().getRight() != null) {
					ret = cont.element().getRight();
				}else {
					throw new InvalidMatriculaException();
				}
			}
		}
		return ret;
	}

	/**
	 * Assigns a nota for a matricula.
	 * @throws InvalidMatriculaException if the matricula has not
	 * been added to the asignatura (or was removed).
	 */
	public void setNota(String matricula, int nota) throws InvalidMatriculaException {
		if(estaMatriculado(matricula)==false) {
			throw new InvalidMatriculaException();
		}
		for(Position<Pair<String, Integer>> cont= notas.first(); cont != null; cont = notas.next(cont)) {
			if(cont.element().getLeft().equals(matricula)) {
				cont.element().setRight(nota);
			}
		}
	}

	/**
	 * Returns a list with the matriculas who has a nota between 
	 * the minimum nota minNota (including it) and the maximum nota maxNota
	 * (including it).
	 * @return a list with the matriculas
	 * with notas between (including) minNota...maxNota.
	 */
	public PositionList<String> alumnosEnRango(int minNota, int maxNota) {
		PositionList<String> ret = new NodePositionList<String>();
		for(Position<Pair<String, Integer>> cont= notas.first();cont != null; cont = notas.next(cont)) {
			if( cont.element().getRight() != null && minNota <= cont.element().getRight() && cont.element().getRight() <= maxNota) {
				ret.addLast(cont.element().getLeft());
			}
		}
		return ret;
	}

	/**
	 * Calculates the average grade of the notas in the asignatura.
	 * NOTE. Does not count alumnos (matriculas) that have not been assigned
	 * a nota.
	 * NOTE. The average grade for an empty set of notas is defined to be 0.
	 * @return the average grade of the asignatura.
	 */
	public double notaMedia() {
		double ret = 0;
		int num = 0;
		System.out.println(notas.toString());
		for(Position<Pair<String, Integer>> cont= notas.first(); cont != null; cont = notas.next(cont)) {
			if(cont.element().getRight() != null) {
				ret += cont.element().getRight();
				num ++;
			}
		}
		System.out.println(ret);
		if(num == 0) {
			return 0;
		}else {
			return ret/num;
		}
	}
}
