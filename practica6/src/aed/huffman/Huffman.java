package aed.huffman;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.*;

/**
 * Defines metodos for Huffman encoding of text strings.
 */
public class Huffman {
	private LinkedBinaryTree<Character> huffmanTree;

	public Huffman(LinkedBinaryTree<Character> huffmanTree) {
		this.huffmanTree = huffmanTree;
	}

	public Huffman(CharCode[] paths) {
		this.huffmanTree = new LinkedBinaryTree<Character>();
		huffmanTree.addRoot((char) 32);
		for (int i = 0; i < paths.length; i++) {
			String cursor = paths[i].getCode();
			Character letra = paths[i].getCh();
			Position<Character> pos = huffmanTree.root();
			if (cursor.length() == 1) {
				Position<Character> raiz = huffmanTree.root();
				if (cursor.charAt(0) == '1') {
					huffmanTree.insertRight(raiz, letra);
				} else {
					huffmanTree.insertLeft(raiz, letra);
				}
			} else {
				for (int j = 0; j < cursor.length(); j++) {
					if (cursor.charAt(j) == '1') {
						if (!huffmanTree.hasRight(pos)) {
							huffmanTree.insertRight(pos, (char) 32);
						}
						pos = huffmanTree.right(pos);
						if (j + 1 == cursor.length()) {
							huffmanTree.set(pos, letra);
						}
					} else {
						if (!huffmanTree.hasLeft(pos)) {
							huffmanTree.insertLeft(pos, (char) 32);
						}
						pos = huffmanTree.left(pos);
						if (j + 1 == cursor.length()) {
							huffmanTree.set(pos, letra);
						}
					}
				}
			}
		}
	}

	public String encode(String text) {
		String encodeStr = "";
		for (int i = 0; i < text.length(); i++) {
			encodeStr += findCharacterCode(text.charAt(i), huffmanTree, huffmanTree.root());
		}
		return encodeStr;
	}

	private String findCharacterCode(Character ch, BinaryTree<Character> tree, Position<Character> pos) {
		String line = "";
		for (Position<Character> v : tree.children(pos)) {
			if (v.element().equals(ch)) {
				while (!tree.isRoot(v)) {
					Position<Character> w = tree.parent(v);
					if (tree.left(w).equals(v)) {
						line = "0" + line;
					} else {
						line = "1" + line;
					}
					v = w;
				}
				return line;
			} else if (tree.isInternal(v)) {
				line += findCharacterCode(ch, tree, v);
			} else {
				pos = tree.root();
			}
		}
		return line;
	}

	public String decode(String codigo) {
		String res = "";
		Position<Character> cursor = huffmanTree.root();
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i) == '1') {
				cursor = huffmanTree.right(cursor);
				if (huffmanTree.isExternal(cursor)) {
					res += cursor.element();
					cursor = huffmanTree.root();
				}
			} else {
				cursor = huffmanTree.left(cursor);
				if (huffmanTree.isExternal(cursor)) {
					res += cursor.element();
					cursor = huffmanTree.root();
				}
			}
		}
		return res;
	}
}
