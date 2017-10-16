package aed.iteradores;

import es.upm.aedlib.positionlist.*;
import java.util.Iterator;

class Secretaria {
	private Iterable<AsignaturaAdmin> asignaturas;

	public Secretaria(Iterable<AsignaturaAdmin> asignaturas) {
		this.asignaturas = asignaturas;
	}

	private AsignaturaAdmin findAsignatura(String asignatura) {
		Iterator<AsignaturaAdmin> it = asignaturas.iterator();
		AsignaturaAdmin res = null;
		while (it.hasNext() && res == null) {
			AsignaturaAdmin admin = it.next();
			if (admin.getNombreAsignatura().equals(asignatura)) {
				res = admin;
			}
		}
		return res;
	}

	private AsignaturaAdmin getAsignatura(String asignatura) throws InvalidAsignaturaException {
		AsignaturaAdmin admin = findAsignatura(asignatura);
		if (admin == null)
			throw new InvalidAsignaturaException();
		else
			return admin;
	}

	public Iterable<String> matricular(String asignatura, Iterable<String> matriculas)
			throws InvalidAsignaturaException {
		return getAsignatura(asignatura).matricular(matriculas);
	}

	public Iterable<String> desMatricular(String asignatura, Iterable<String> matriculas)
			throws InvalidAsignaturaException {
		return getAsignatura(asignatura).desMatricular(matriculas);
	}

	public double notaMediaExpediente(String matricula) {
		double media = 0;
		int sig = 0;
		Iterator<AsignaturaAdmin> it = asignaturas.iterator();
		AsignaturaAdmin asig = null;
		while (it.hasNext()) {
			asig = it.next();
			try {
				if (asig.estaMatriculado(matricula) && asig.tieneNota(matricula)) {
					media += asig.getNota(matricula);
					sig++;
				}
			} catch (InvalidMatriculaException e) {
				// Asignatura que no tiene secretaria
			}
		}
		if (sig == 0) {
			return 0;
		} else {
			return (media /= sig);
		}
	}

	public String mejorNotaMedia() {
		double media = 0;
		String ret = null;
		Iterator<AsignaturaAdmin> it = asignaturas.iterator();
		AsignaturaAdmin asig = null;
		while (it.hasNext()) {
			asig = it.next();
			if (asig.notaMedia() > media) {
				media = asig.notaMedia();
				ret = asig.getNombreAsignatura();
			}
		}
		if (media == 0) {
			return null;
		}
		return ret;
	}

	public Iterable<Pair<String, Integer>> expediente(String matricula) throws InvalidMatriculaException {
		PositionList<Pair<String, Integer>> ret = new NodePositionList<Pair<String, Integer>>();
		Iterator<AsignaturaAdmin> it = asignaturas.iterator();
		AsignaturaAdmin asig = null;
		while (it.hasNext()) {
			try {
				asig = it.next();
				ret.addLast(new Pair<String, Integer>(asig.getNombreAsignatura(), asig.getNota(matricula)));
			} catch (Exception e) {
			}
		}
		return ret;
	}

	public Iterable<Pair<String, String>> asignaturasNoConflictivas() {
		PositionList<Pair<String, String>> ret = new NodePositionList<Pair<String, String>>();
		Iterator<AsignaturaAdmin> it1 = asignaturas.iterator();
		AsignaturaAdmin asig1 = null;
		AsignaturaAdmin asig2 = null;
		while (it1.hasNext()) {
			asig1 = it1.next();
			Iterator<AsignaturaAdmin> it2 = asignaturas.iterator();
			while (it2.hasNext()) {
				asig2 = it2.next();
				if ((asig1 == null || asig2 == null) || (!compartenAlumnos(asig1, asig2) && asig1 != asig2)) {
					ret.addLast(new Pair<String, String>(asig1.getNombreAsignatura(), asig2.getNombreAsignatura()));
				}
			}

		}
		return ret;
	}

	private boolean compartenAlumnos(AsignaturaAdmin a1, AsignaturaAdmin a2) {
		boolean bool = false;
		Iterator<String> matriculados1 = a1.matriculados().iterator();
		String asig1 = null;
		String asig2 = null;
		while (matriculados1.hasNext() && !bool) {
			asig1 = matriculados1.next();
			Iterator<String> it2 = a2.matriculados().iterator();
			while (!bool && it2.hasNext()) {
				asig2 = it2.next();
				if (asig1.equals(asig2)) {
					bool = true;
				}
			}
		}
		return bool;
	}
}
