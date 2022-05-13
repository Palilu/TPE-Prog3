package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

public class Tree {
	
	private TreeNode root;
	
	public Tree() {
		this.root = null;
	}

	/**
	 * Devuelve la raíz del árbol
	 */
	public String getRootValue() {
		if (!isEmpty()) {
			return root.getKey();
		}else {
			return null;
		}
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
			insert(this.root, genre, book);
		}
	}

	public void insert(TreeNode node, String genre, Book book) {
		if (node == null) {
			TreeNode newNode = new TreeNode(genre);
			root = newNode;
		} else {
			this.doInsert(this.root, genre, book);
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
			insert(node.getLeft(), genre, book);
		} else {
			// Sino... es mayor, lo agrego a la derecha.
			insert(node.getRight(), genre, book);
		}
	}
	
//	//Eliminar un elemento
//	public boolean delete(Integer valor) {
//		if (!this.isEmpty()) {
//			if (this.root.getKey() == valor) {//Si lo que voy a eliminar es la raiz
//				return this.delete(this.root);
//			}
//			return this.delete(this.root,this.root, valor);//Si no es la raiz paso un padre y el nodo
//		}
//		return false;
//	}
	
//	private boolean delete(TreeNode padre, TreeNode nodo, Integer valor) {
//		if (nodo == null) {
//			return false;
//		}
//		if (valor > nodo.getKey()) { //Va por derecha
//			return delete(nodo, nodo.getRight(), valor);
//		}else {
//			if (valor < nodo.getKey()) { //Va por izquierda
//				return delete(nodo, nodo.getLeft(), valor);
//			}else {
//				if (this.isLeaf(nodo)) {
//					// es hoja
//					return this.deleteHoja(padre, valor);
//				}else {
//					if (nodo.getLeft() == null && nodo.getRight() != null) {
//						//uno solo hijo derecho
//						return this.deleteNodoDeUnHijo(padre,nodo.getRight(),valor);
//					}
//					else {
//						if (nodo.getLeft() != null && nodo.getRight() == null) {
//							//uno solo hijo izquierdo
//							return this.deleteNodoDeUnHijo(padre,nodo.getLeft(),valor);
//						}else {
//							//Si no es la raiz
//							if (nodo.getLeft() != null && nodo.getRight() != null) {
//								TreeNode aux = getNodoMaxElem(nodo.getLeft());//Del izquierdo tomo el mayor
//								delete(aux.getKey()); //Elimino el valor mayor encontrado
//								if (padre.getLeft() != null && padre.getLeft().getKey() == valor) {//Si es el hijo izquierdo el que voy a eliminar
//									aux.setLeft(nodo.getLeft());
//									aux.setRight(nodo.getRight());
//									padre.setLeft(aux);
//								}
//
//								if (padre.getRight() != null && padre.getRight().getKey() == valor) {//Si es el hijo derecho el que voy a eliminar
//									aux.setLeft(nodo.getLeft());
//									aux.setRight(nodo.getRight());
//									padre.setRight(aux);
//								}
//
//							}
//							return true;
//						}
//					}
//				}
//			}
//		}
//	}
	
//	private boolean deleteNodoDeUnHijo(TreeNode padre, TreeNode nodo, Integer valor) {
//		if (padre.getLeft() != null && padre.getLeft().getKey() == valor) {//Si es el hijo izquierdo el que voy a eliminar
//			padre.setLeft(nodo);
//			return true;
//		}
//		if (padre.getRight() != null && padre.getRight().getKey() == valor) { //Si es el hijo derecho el que voy a eliminar
//			padre.setRight(nodo);
//			return true;
//		}
//		return false;
//	}

//	private boolean deleteHoja(TreeNode padre, Integer valor) {
//		if (padre.getLeft() != null && padre.getLeft().getKey() == valor) { //Si es el hijo izquierdo el que voy a eliminar
//			padre.setLeft(null);
//			return true;
//		}
//		if (padre.getRight() != null && padre.getRight().getKey() == valor) { //Si es el hijo derecho el que voy a eliminar
//			padre.setRight(null);
//			return true;
//		}
//		return false;
//	}

//	private boolean delete(TreeNode raiz) {
//		if (raiz.getLeft() == null && raiz.getRight() != null) {//Si tengo un solo nodo y es el derecho
//			this.root = raiz.getRight();
//		}
//		if (raiz.getLeft() != null && raiz.getRight() == null) {//Si tengo un solo nodo y es el izquierdo
//			this.root = raiz.getLeft();
//		}
//		if (raiz.getLeft() != null && raiz.getRight() != null) {//Si tengo ambos hijos
//			TreeNode aux = getNodoMaxElem(raiz.getLeft());
//			delete(aux.getKey()); //Elimino el valor mayor encontrado
//			aux.setRight(raiz.getRight());
//			aux.setLeft(raiz.getLeft());
//			this.root = aux;
//		}
//		return false;
//	}
	
//	//Mayor elemento del arbol
//	public Integer getMaxElem() {
//		return getMaxElem(this.root);
//	}
	
//	private Integer getMaxElem(TreeNode n) {
//		if (isEmpty()) return -1;
//		if (n.getRight() == null) {
//			return n.getKey();
//		}else {
//			return getMaxElem(n.getRight());
//		}
//	}
	
//	//Mayor Nodo
//	private TreeNode getNodoMaxElem(TreeNode n) {
//		if (isEmpty()) return null;
//		if (n == null || n.getRight() == null) {
//			return n;
//		}else {
//			return getNodoMaxElem(n.getRight());
//		}
//	}
	
//	//Menor elemento del arbol
//	public Integer getMinElem() {
//		return getMinElem(this.root);
//	}
	
//	private Integer getMinElem(TreeNode n) {
//		if (isEmpty()) return -1;
//		if (n.getLeft() == null) {
//			return n.getKey();
//		}else {
//			return getMinElem(n.getLeft());
//		}
//	}
	
	//Menor Nodo 
	/*private Nodo getNodoMinElem(Nodo n) {
		if (isEmpty()) return null;
		if (n == null || n.getLeft() == null) {
			return n;
		}else {
			return getNodoMinElem(n.getLeft());
		}
	}*/
	
	//Imprimir de la forma pre roden
//	public void printPreOrder() {
//	    this.printPreOrder(this.root);
//	    System.out.println("");
//	    System.out.println("-----------------------------------------------------------------");
//	}
	
//	private void printPreOrder(TreeNode treeNode) {
//	    if (treeNode != null) {
//	        treeNode.printValue();
//	        printPreOrder(treeNode.getLeft());
//	        printPreOrder(treeNode.getRight());
//	    }
//	}

	//Imprimir de la forma in roden
//	public void printInOrder() {
//	    this.printInOrder(this.root);
//	    System.out.println("");
//	    System.out.println("-----------------------------------------------------------------");
//	}
	
//	private void printInOrder(TreeNode n) {
//	    if (n != null) {
//	    	printInOrder(n.getLeft());
//	        n.printValue();
//	        printInOrder(n.getRight());
//	    }
//	}
	
	//Imprimir de la forma pos roden
//	public void printPosOrder() {
//	    this.printPosOrder(this.root);
//	    System.out.println("");
//	    System.out.println("-----------------------------------------------------------------");
//	}
//
//	private void printPosOrder(TreeNode n) {
//	    if (n != null) {
//	    	printPosOrder(n.getLeft());
//	    	printPosOrder(n.getRight());
//	        n.printValue();
//	    }
//	}
//
//	public int size() {
//		return size(this.root);
//	}
//
//	private int size (TreeNode n) {
//		if (n == null) {
//			return 0;
//		}
//		if (this.isLeaf(n)) {
//			return 1;
//		}else {
//			return 1 + size(n.getLeft()) + size(n.getRight()) ;
//		}
//	}
//
//	public List<Integer> getBorder(){
//		return getBorder(this.root);
//	}
//
//	private List<Integer> getBorder(TreeNode n){
//		ArrayList<Integer> aux = new ArrayList<Integer>();
//		if (n != null) {
//			if (isLeaf(n)) {
//				aux.add(n.getKey());
//			}else {
//				aux.addAll(getBorder(n.getLeft()));
//				aux.addAll(getBorder(n.getRight()));
//			}
//		}
//		return aux;
//	}
//
//	public int getHeight() {
//		return getHeight(this.root);
//	}
//
//	private int getHeight(TreeNode n) {
//		if (isLeaf(n)) {
//			return 0;
//		}else {
//			int alturaIzq = 0;
//			int alturaDer = 0;
//			if (n.getLeft() != null && n.getRight() == null) {
//				alturaIzq = 1 + getHeight(n.getLeft());
//			}else {
//				if (n.getLeft() == null && n.getRight() != null) {
//					alturaDer = 1 + getHeight(n.getRight());
//				}else {
//					if (n.getLeft() != null && n.getRight() != null) {
//						alturaIzq = 1 + getHeight(n.getLeft());
//						alturaDer = 1 + getHeight(n.getRight());
//					}
//				}
//			}
//			return alturaIzq > alturaDer ? alturaIzq : alturaDer;
//		}
//
//	}
//
//	public List<Integer> getElemAtLevel(int nivel) {
//		return getElemAtLevel(this.root,0, nivel);
//	}
//
//	private List<Integer> getElemAtLevel(TreeNode n, int nivelActual, int nivel){
//		ArrayList<Integer> aux = new ArrayList<Integer>();
//		if (n != null) {
//			if (nivelActual == nivel) {
//				aux.add(n.getKey());
//			}else {
//				aux.addAll(getElemAtLevel(n.getLeft(),nivelActual+1,nivel));
//				aux.addAll(getElemAtLevel(n.getRight(),nivelActual+1,nivel));
//			}
//		}
//		return aux;
//	}
//
//	public List<Integer> getLongestBranch() {
//		return getLongestBranch(this.root);
//	}
//
//	private List<Integer> getLongestBranch(TreeNode n) {
//		ArrayList<Integer> auxI = new ArrayList<Integer>();
//		ArrayList<Integer> auxD = new ArrayList<Integer>();
//		if (isLeaf(n)) {
//			auxI.add(n.getKey());
//			auxD.add(n.getKey());
//		}else {
//			if (n.getLeft() != null && n.getRight() == null) {
//				auxI.add(n.getKey());
//				auxI.addAll(getLongestBranch(n.getLeft()));
//			}else {
//				if (n.getLeft() == null && n.getRight() != null) {
//					auxD.add(n.getKey());
//					auxD.addAll(getLongestBranch(n.getRight()));
//				}else {
//					if (n.getLeft() != null && n.getRight() != null) {
//						auxI.add(n.getKey());
//						auxD.add(n.getKey());
//						auxI.addAll(getLongestBranch(n.getLeft()));
//						auxD.addAll(getLongestBranch(n.getRight()));
//					}
//				}
//			}
//		}
//		return auxI.size() > auxD.size() ? auxI : auxD;
//
//	}
}
