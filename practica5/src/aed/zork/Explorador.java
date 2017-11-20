package aed.zork;

import java.util.Iterator;

import es.upm.aedlib.positionlist.*;

public class Explorador {

	public static Pair<Object, PositionList<Lugar>> explora(Lugar inicialLugar) {
		PositionList<Lugar> caminos = new NodePositionList<Lugar>();
		return exploraRec(inicialLugar, caminos);
	}

	public static Pair<Object, PositionList<Lugar>> exploraRec(Lugar actual, PositionList<Lugar> caminos) {
		if (actual.tieneTesoro()) {
			caminos.addLast(actual);
			Pair<Object, PositionList<Lugar>> res = new Pair<Object, PositionList<Lugar>>(actual.getTesoro(), caminos);
			return res;
		} else if (actual.sueloMarcadoConTiza()) {
			return null;
		} else {
			actual.marcaSueloConTiza();
			Pair<Object, PositionList<Lugar>> p = null;
			Iterator<Lugar> it = actual.caminos().iterator();
			boolean bool = false;
			while (it.hasNext() && !bool) {
				Lugar nuevo = it.next();
				p = exploraRec(nuevo, caminos);
				if (p != null) {
					bool = true;
					caminos.addFirst(actual);
				}
			}
			return p;
		}
	}
}