package aed.compress;

import es.upm.aedlib.Position;
import es.upm.aedlib.Entry;
import es.upm.aedlib.tree.*;
import es.upm.aedlib.priorityqueue.*;

public class Huffman {
	private BinaryTree<Character> huffmanTree;

	public Huffman(String text) {
		this.huffmanTree = constructHuffmanTree(text);
	}

	private BinaryTree<Character> constructHuffmanTree(String text) {
		PriorityQueue<Integer, BinaryTree<Character>> Q = new SortedListPriorityQueue<Integer, BinaryTree<Character>>();
		int[] arr = countChars(text);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				BinaryTree<Character> T = new AttachableLinkedBinaryTree<Character>();
				Q.enqueue(arr[i], T);
			}
		}
		Entry<Integer, BinaryTree<Character>> left;
		Entry<Integer, BinaryTree<Character>> right;
		while (Q.size() > 1) {
			left = Q.dequeue();
			right = Q.dequeue();
			AttachableLinkedBinaryTree<Character> T = new AttachableLinkedBinaryTree<Character>();
			T.addRoot((char)32);
			T.attach(T.root(), left.getValue(), right.getValue());
			Q.enqueue(left.getKey(), T);
			Q.enqueue(right.getKey(), T);
		}
		huffmanTree = Q.first().getValue();
		return Q.first().getValue();
	}

	public static int[] countChars(String text) {
		int[] resultado = new int[256];
		for (int i = 0; i < text.length(); i++) {
			resultado[text.charAt(i)]++;
		}
		return resultado;
	}
}