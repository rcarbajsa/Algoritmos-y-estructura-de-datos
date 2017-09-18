package aed.loops;

public class Utils {
	public static int maxNumRepeated(Integer[] l, Integer elem) {
		int contador = 0;
		for (int i = 0; i < l.length; i++) {
			for (int j = i; j < l.length && elem.equals(l[j]); j++) {
				if (contador <= j - i) {
					contador++;
				}
			}
		}
		return contador;
	}
}