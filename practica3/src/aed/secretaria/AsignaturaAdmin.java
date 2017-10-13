package aed.secretaria;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class AsignaturaAdmin {
	private String nombreAsignatura;
	private PositionList<Pair<String, Integer>> notas;

	public AsignaturaAdmin(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
		this.notas = new NodePositionList<Pair<String, Integer>>();
	}

	public Position<Pair<String, Integer>> buscarMatricula(String matricula) {
		Position<Pair<String, Integer>> resultado = null;
		for (Position<Pair<String, Integer>> aux = notas.first(); aux != null; aux = notas.next(aux)) {
			if (aux.element().getLeft().equals(matricula)) {
				resultado = aux;
			}
		}
		return resultado;

	}

	public String getNombreAsignatura() {
		return this.nombreAsignatura;
	}

	public PositionList<String> matricular(PositionList<String> matriculas) {
		PositionList<String> ret = new NodePositionList<String>();
		for (Position<String> aux = matriculas.first(); aux != null; aux = matriculas.next(aux)) {
			if (!estaMatriculado(aux.element())) {
				Pair<String, Integer> pair = new Pair<String, Integer>(aux.element(), null);
				ret.addLast(aux.element());
				notas.addLast(pair);
			}
		}
		return ret;
	}

	public PositionList<String> desMatricular(PositionList<String> matriculas) {
		PositionList<String> ret = new NodePositionList<String>();
		for (Position<String> auxMat = matriculas.first(); auxMat != null; auxMat = matriculas.next(auxMat)) {
			if (estaMatriculado(auxMat.element()) && !tieneNotaAux(auxMat.element())) {
				ret.addLast(auxMat.element());
			}
		}
		for (Position<String> aux = ret.first(); aux != null; aux = ret.next(aux)) {
			notas.remove(buscarMatricula(aux.element()));
		}
		return ret;
	}

	public boolean estaMatriculado(String matricula) {
		boolean bool = false;
		for (Position<Pair<String, Integer>> cont = notas.first(); cont != null; cont = notas.next(cont)) {
			if (cont.element().getLeft().equals(matricula)) {
				bool = true;
			}
		}
		return bool;
	}

	public boolean tieneNotaAux(String matricula) {
		if (estaMatriculado(matricula) == false) {
			return false;
		}
		boolean resultado = false;
		for (Position<Pair<String, Integer>> aux = notas.first(); aux != null; aux = notas.next(aux)) {
			if (aux.element().getLeft().equals(matricula) && aux.element().getRight() != null) {
				resultado = true;
			}
		}
		return resultado;
	}

	public boolean tieneNota(String matricula) throws InvalidMatriculaException {
		if (estaMatriculado(matricula) == false) {
			throw new InvalidMatriculaException();
		}
		boolean resultado = false;
		for (Position<Pair<String, Integer>> aux = notas.first(); aux != null; aux = notas.next(aux)) {
			if (aux.element().getLeft().equals(matricula) && aux.element().getRight() != null) {
				resultado = true;
			}
		}
		return resultado;
	}

	public int getNota(String matricula) throws InvalidMatriculaException {
		if (estaMatriculado(matricula) == false) {
			throw new InvalidMatriculaException();
		}
		int ret = 0;
		for (Position<Pair<String, Integer>> cont = notas.first(); cont != null; cont = notas.next(cont)) {
			if (cont.element().getLeft().equals(matricula)) {
				if (cont.element().getRight() != null) {
					ret = cont.element().getRight();
				} else {
					throw new InvalidMatriculaException();
				}
			}
		}
		return ret;
	}

	public void setNota(String matricula, int nota) throws InvalidMatriculaException {
		if (estaMatriculado(matricula) == false) {
			throw new InvalidMatriculaException();
		}
		for (Position<Pair<String, Integer>> cont = notas.first(); cont != null; cont = notas.next(cont)) {
			if (cont.element().getLeft().equals(matricula)) {
				cont.element().setRight(nota);
			}
		}
	}

	public PositionList<String> alumnosEnRango(int minNota, int maxNota) {
		PositionList<String> ret = new NodePositionList<String>();
		for (Position<Pair<String, Integer>> cont = notas.first(); cont != null; cont = notas.next(cont)) {
			if (cont.element().getRight() != null && minNota <= cont.element().getRight()
					&& cont.element().getRight() <= maxNota) {
				ret.addLast(cont.element().getLeft());
			}
		}
		return ret;
	}

	public double notaMedia() {
		double ret = 0;
		int num = 0;
		for (Position<Pair<String, Integer>> cont = notas.first(); cont != null; cont = notas.next(cont)) {
			if (cont.element().getRight() != null) {
				ret += cont.element().getRight();
				num++;
			}
		}
		if (num == 0) {
			return 0;
		} else {
			return ret / num;
		}
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 8a2b9313bc4cbad42825a9cf1b6dda538420de0b
