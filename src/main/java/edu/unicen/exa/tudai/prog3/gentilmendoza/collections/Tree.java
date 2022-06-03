package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.Collections;
import java.util.List;

public class Tree {
	
	private TreeNode root;
	
	public Tree() {
		this.root = null;
	}

	/**
	 * @param key La clave a buscar en el árbol
	 * @return Retorna un booleano indicando si la clave existe en el árbol.
	 */
	public boolean containsKey(String key) {
		return containsKey(this.root, key);
	}
	
	private boolean containsKey(TreeNode node, String key) {
		// Si llegamos a un nodo vacio es que no está.
		if (node == null) {
	        return false;
	    }
		// Comparamos el valor objetivo contra el valor del nodo.
		int compare = key.compareTo(node.getKey());
		if(compare == 0) {
			// Si son iguales el valor existe.
			return true;
		} else if (compare < 0) {
			// Si es menor, existe si existe en la rama izquierda.
			return containsKey(node.getLeft(), key);
		} else {
			// Sino, existe si existe en la rama derecha.
			return containsKey(node.getRight(), key);
		}
	}

	/**
	 * @return Retorna un booleano indicando si el árbol está vacío.
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * @param book El libro a insertar.
	 */
	public void insert(Book book) {
		// Pará cada género del libro.
		for (String genre : book.getGenres()) {
			// Agrego el libro en el índice de géneros.
			if (isEmpty()) {
				// Si es el primer elemento en ser agregado. Agrego
				this.root = new TreeNode(genre, book);
			} else {
				// Si no es el primer elemento recorro para agregarlo.
				doInsert(this.root, genre, book);
			}
		}
	}

	private void doInsert(TreeNode node, String genre, Book book) {
		// Comparamos el valor objetivo contra el valor del nodo.
		int compare = genre.compareTo(node.getKey());
		if (compare == 0) {
			// Si el género es igual a la clave, lo agregamos en este nodo.
			node.addBook(book);
		} else if (compare < 0) {
			// Si es menor, lo agrego a la izquierda.
			if (node.getLeft() == null) {
				node.setLeft(new TreeNode(genre, book));
			} else {
				doInsert(node.getLeft(), genre, book);
			}
		} else {
			// Sino... es mayor, lo agrego a la derecha.
			if (node.getRight() == null) {
				node.setRight(new TreeNode(genre, book));
			} else {
				doInsert(node.getRight(), genre, book);
			}
		}
	}
	
	public List<Book> get(String genre) {
		return doGet(root, genre);
	}

	private List<Book> doGet(TreeNode node, String genre) {
		if (node == null) {
			return Collections.emptyList();
		}
		int compare = genre.compareTo(node.getKey());
		if (compare == 0) {
			return node.getBooks();
		} else if (compare < 0) {
			// Si es menor, lo busco a la izquierda.
			return doGet(node.getLeft(), genre);
		} else {
			// Sino... es mayor, lo busco a la derecha.
			return doGet(node.getRight(), genre);
		}
	}
}
