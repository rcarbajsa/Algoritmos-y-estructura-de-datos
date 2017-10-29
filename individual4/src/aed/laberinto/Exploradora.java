package aed.laberinto;

import java.util.Iterator;

import es.upm.aedlib.lifo.*;

public class Exploradora {

	/**
	 * Busca un tesoro en el laberinto, empezando en el lugar inicial: inicialLugar.
	 * 
	 * @return un Objeto tesoro encontrado, o null, si ningun tesoro existe en la
	 *         parte del laberinto que es alcanzable desde la posicion inicial.
	 */
	public static Object explora(Lugar inicialLugar) {
		LIFO<Lugar> faltaPorExplorar = new LIFOList<Lugar>();
		faltaPorExplorar.push(inicialLugar);
		boolean bool = false;
		Object tesoro = null;
		while (!(faltaPorExplorar.isEmpty()) && !bool) {
			Lugar casilla = faltaPorExplorar.pop();
			if (casilla.tieneTesoro()) {
				casilla.marcaSueloConTiza();
				bool = true;
				tesoro = casilla.getTesoro();
			} else {
				casilla.marcaSueloConTiza();
				Iterator<Lugar> itCaminos = casilla.caminos().iterator();
				while (itCaminos.hasNext() && !bool) {
					casilla = itCaminos.next();
					if (casilla.sueloMarcadoConTiza() == false) {
						faltaPorExplorar.push(casilla);
					}
				}
			}
		}
		return tesoro;
	}
}
