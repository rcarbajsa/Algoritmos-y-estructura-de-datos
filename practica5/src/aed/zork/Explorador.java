package aed.zork;

import es.upm.aedlib.positionlist.*;

public class Explorador {

	public static Pair<Object, PositionList<Lugar>> explora(Lugar inicialLugar) {
		//inicialLugar.printLaberinto();
		PositionList<Lugar> caminos = new NodePositionList<Lugar>();
		caminos.addLast(inicialLugar);
		Object tesoro = null;
		return explora(inicialLugar,tesoro,caminos);
	}

	public static Pair<Object, PositionList<Lugar>> explora(Lugar actual, Object tesoro, PositionList<Lugar> cam) {
		if(actual.sueloMarcadoConTiza()) {
			return explora(actual.caminos().iterator().next(),tesoro,cam);
		}
		else {
			actual.marcaSueloConTiza();
			if(actual.tieneTesoro()) {
				tesoro=actual.getTesoro();
				Pair<Object, PositionList<Lugar>> res = new Pair<Object, PositionList<Lugar>>(tesoro, cam);
				return res;
			}
			else {
				return explora(actual.caminos(),tesoro,cam);
				
			}

		}


	}


	
	public static Pair<Object, PositionList<Lugar>> explora(Iterable<Lugar> actual,Object tesoro,PositionList<Lugar> cam){
		if(actual.iterator().hasNext()) {
			cam.addLast(actual.iterator().next());
			return explora(actual.iterator().next(),tesoro,cam);
		}	
		else {
			return null;
		}
	}
}