package aed.mergelists;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import java.util.Comparator;

import aed.mergelists.*;

public class MergeLists {
	public static <E> PositionList<E> merge(final PositionList<E> l1, final PositionList<E> l2,
			final Comparator<E> comp) {
		PositionList<E> lista = new NodePositionList<E>();
		Position<E> p1 = l1.first();
		Position<E> p2 = l2.first();
		for (int i = 0; i < l1.size() + l2.size(); i++) {
			if (i < l1.size()) {
				lista.addLast(p1.element());
				p1 = l1.next(p1);
			} else {
				lista.addLast(p2.element());
				p2 = l2.next(p2);
			}
		}
		E temp;
		for (Position<E> p3 = lista.first(); p3 != null && lista.next(p3) != null; p3 = lista.next(p3)) {
			for (Position<E> p4 = lista.next(p3); p4 != null; p4 = lista.next(p4)) {
				if (comp.compare(p3.element(), p4.element()) > 0) {
					temp = p3.element();
					lista.set(p3, p4.element());
					lista.set(p4, temp);
				}
			}
		}
		return lista;
	}

	public static <E> IndexedList<E> merge(final IndexedList<E> l1, final IndexedList<E> l2, final Comparator<E> comp) {
		IndexedList<E> lista = new ArrayIndexedList<E>();
		for (int i = 0; i < l1.size() + l2.size(); i++) {
			if (i < l1.size()) {
				lista.add(i, l1.get(i));
			} else {
				lista.add(i, l2.get(i - l1.size()));
			}
		}
		E temp;
		for (int i = 0; i < lista.size() - 1; i++) {
			for (int j = i + 1; j < lista.size(); j++) {
				if (comp.compare(lista.get(i), lista.get(j)) > 0) {
					temp = lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
}
