package aed.find;

import es.upm.aedlib.tree.Tree;
import es.upm.aedlib.Position;

public class Find {

	public static void find(String fileName, Tree<String> directory) {

		if (fileName.equals(directory.root().element())) {
			Printer.enableOutput();
			String line = null;
			line = fileName;
			Printer.println("/" + line);
		} else {
			voidfindInPos(directory.root(), fileName, directory);
		}
	}

	private static void voidfindInPos(Position<String> cursor, String fileName, Tree<String> directory) {
		for (Position<String> v : directory.children(cursor)) {
			if (v.element().equals(fileName)) {
				String line = "";
				Printer.enableOutput();
				line = v.element();
				v = directory.parent(v);
				while (!directory.isRoot(v)) {
					line = v.element() + "/" + line;
					v = directory.parent(v);
				}
				line = "/" + directory.root().element() + "/" + line;
				Printer.println(line);
			} else if (directory.isInternal(v)) {
				voidfindInPos(v, fileName, directory);
			}
		}
	}
}
