package aed.compress;

import es.upm.aedlib.Position;
import es.upm.aedlib.tree.*;

public class AttachableLinkedBinaryTree<E> extends LinkedBinaryTree<E> implements AttachableBinaryTree<E> {

	public void attach(Position<E> pos, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		this.insertLeft(pos, leftTree.root().element());
		this.insertRight(pos, rightTree.root().element());
		insertarDerecha(rightTree.root(), this.right(pos), rightTree);
		insertarIzquierda(leftTree.root(), this.left(pos), leftTree);
	}

	public void insertarIzquierda(Position<E> padre, Position<E> cursor, BinaryTree<E> tree) {
		Position<E> actual = cursor;
		if (tree.hasLeft(padre)) {
			this.insertLeft(cursor, tree.left(padre).element());
			cursor = this.left(cursor);
			insertarIzquierda(tree.left(padre), cursor, tree);
		}
		if (tree.hasRight(padre)) {
			this.insertRight(actual, tree.right(padre).element());
			cursor = this.right(actual);
			insertarIzquierda(tree.right(padre), cursor, tree);
		}

	}

	public void insertarDerecha(Position<E> padre, Position<E> cursor, BinaryTree<E> tree) {
		Position<E> actual = cursor;
		if (tree.hasRight(padre)) {
			this.insertRight(cursor, tree.right(padre).element());
			cursor = this.right(cursor);
			insertarDerecha(tree.right(padre), cursor, tree);
		}
		if (tree.hasLeft(padre)) {
			this.insertLeft(actual, tree.left(padre).element());
			cursor = this.left(actual);
			insertarDerecha(tree.left(padre), cursor, tree);
		}

	}

}