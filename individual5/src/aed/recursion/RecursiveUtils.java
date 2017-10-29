package aed.recursion;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;

public class RecursiveUtils {
	public static int power(int a, int n) {
		if (n == 0) {
			return 1;
		}
		return (a * power(a, n - 1));
	}

	public static <E> boolean allNonNull(PositionList<E> l) {
		if (l.first() != null) {
			Position<E> a = l.first();
			return allNonNullRec(l, a);
		}
		return true;

	}

	private static <E> boolean allNonNullRec(PositionList<E> l, Position<E> pos) {
		if (l.next(pos) == null && pos.element() != null) {
			return true;
		}
		if (pos.element() == null) {
			return false;
		} else {
			pos = l.next(pos);
			return allNonNullRec(l, pos);
		}
	}

	public static <E> int countNonNull(PositionList<E> l) {
		if (l.first() != null) {
			Position<E> a = l.first();
			int res = 0;
			return countNonNullRec(l, a, res);
		}
		return 0;
	}

	private static <E> int countNonNullRec(PositionList<E> l, Position<E> pos, int res) {
		if (pos.element() != null) {
			res++;
		}
		if (l.next(pos) == null) {
			return res;
		}
		pos = l.next(pos);
		return countNonNullRec(l, pos, res);
	}

}
