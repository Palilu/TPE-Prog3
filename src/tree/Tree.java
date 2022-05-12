package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private Nodo root;
	
	public Tree() {
		this.root = null;
	}
	
	//Devuelve la raíz del árbol
	public Integer getRoot() {
		if (!isEmpty()) {
			return getRootTree();
		}else {
			return -1;
		}
	}
	
	private Integer getRootTree(){
		return this.root.getValue();
	}
	
	private boolean isHoja(Nodo n) {
		return n!=null && n.getLeft() == null && n.getRigth() == null;
	}

	//Consulta si existe un elemento en el arbol
	public boolean hasElem(Integer valor) {
		return hasElem(this.root, valor);
	}
	
	private boolean hasElem(Nodo nodo, Integer valor) {
		if (nodo == null) {
	        return false;
	    }
		if(nodo.getValue() == valor) {
			return true;
		}else {
			if (valor < nodo.getValue()) {
				return hasElem(nodo.getLeft(),valor);
			}else {
				return hasElem(nodo.getRigth(),valor);
			}	
		}
	}
	
	//Si está vacio el árbol
	public boolean isEmpty() {
		return this.root == null;
	}
	
	//Inserta un elemento en el árbol
	public void insert(Integer valor) {
		if (isEmpty()) {
			Nodo nuevo = new Nodo(valor);
			root = nuevo;
		}else {
			this.insert(this.root, valor);
		}
	}

	private void insert(Nodo nodo, Integer valor) {
		if (valor < nodo.getValue() ) { //va para la izquierda
			if (nodo.getLeft() == null) { //si es null inserto
				Nodo nuevo = new Nodo(valor);
				nodo.setLeft(nuevo);
			}else {
				insert(nodo.getLeft(),valor);//sino sigo buscando
			}
		}else {
			if (nodo.getValue() < valor) { //va para la derecha
				if (nodo.getRigth() == null) { //si es null inserto
					Nodo nuevo = new Nodo(valor);
					nodo.setRigth(nuevo);
				}else {
					insert(nodo.getRigth(),valor);//sino sigo buscando por la derecha
				}
			}
		}
	}
	
	//Eliminar un elemento
	public boolean delete(Integer valor) {
		if (!this.isEmpty()) {
			if (this.root.getValue() == valor) {//Si lo que voy a eliminar es la raiz
				return this.delete(this.root);
			}
			return this.delete(this.root,this.root, valor);//Si no es la raiz paso un padre y el nodo
		}
		return false;
	}
	
	private boolean delete(Nodo padre, Nodo nodo, Integer valor) {
		if (nodo == null) {
			return false;
		}
		if (valor > nodo.getValue()) { //Va por derecha
			return delete(nodo, nodo.getRigth(), valor);
		}else {
			if (valor < nodo.getValue()) { //Va por izquierda
				return delete(nodo, nodo.getLeft(), valor);
			}else {
				if (this.isHoja(nodo)) {
					// es hoja
					return this.deleteHoja(padre, valor);					
				}else {
					if (nodo.getLeft() == null && nodo.getRigth() != null) {
						//uno solo hijo derecho
						return this.deleteNodoDeUnHijo(padre,nodo.getRigth(),valor);
					}
					else {
						if (nodo.getLeft() != null && nodo.getRigth() == null) {
							//uno solo hijo izquierdo
							return this.deleteNodoDeUnHijo(padre,nodo.getLeft(),valor);
						}else {
							//Si no es la raiz
							if (nodo.getLeft() != null && nodo.getRigth() != null) {
								Nodo aux = getNodoMaxElem(nodo.getLeft());//Del izquierdo tomo el mayor
								delete(aux.getValue()); //Elimino el valor mayor encontrado
								if (padre.getLeft() != null && padre.getLeft().getValue() == valor) {//Si es el hijo izquierdo el que voy a eliminar
									aux.setLeft(nodo.getLeft());
									aux.setRigth(nodo.getRigth());
									padre.setLeft(aux);
								}
								
								if (padre.getRigth() != null && padre.getRigth().getValue() == valor) {//Si es el hijo derecho el que voy a eliminar
									aux.setLeft(nodo.getLeft());
									aux.setRigth(nodo.getRigth());
									padre.setRigth(aux);
								}
								
							}
							return true;
						}
					}
				}
			}
		}
	}
	
	private boolean deleteNodoDeUnHijo(Nodo padre, Nodo nodo, Integer valor) {
		if (padre.getLeft() != null && padre.getLeft().getValue() == valor) {//Si es el hijo izquierdo el que voy a eliminar 
			padre.setLeft(nodo);
			return true;
		}
		if (padre.getRigth() != null && padre.getRigth().getValue() == valor) { //Si es el hijo derecho el que voy a eliminar
			padre.setRigth(nodo);
			return true;
		}
		return false;
	}

	private boolean deleteHoja(Nodo padre, Integer valor) {
		if (padre.getLeft() != null && padre.getLeft().getValue() == valor) { //Si es el hijo izquierdo el que voy a eliminar
			padre.setLeft(null);
			return true;
		}
		if (padre.getRigth() != null && padre.getRigth().getValue() == valor) { //Si es el hijo derecho el que voy a eliminar
			padre.setRigth(null);
			return true;
		}
		return false;
	}

	private boolean delete(Nodo raiz) {	
		if (raiz.getLeft() == null && raiz.getRigth() != null) {//Si tengo un solo nodo y es el derecho
			this.root = raiz.getRigth();
		}
		if (raiz.getLeft() != null && raiz.getRigth() == null) {//Si tengo un solo nodo y es el izquierdo
			this.root = raiz.getLeft();
		}
		if (raiz.getLeft() != null && raiz.getRigth() != null) {//Si tengo ambos hijos
			Nodo aux = getNodoMaxElem(raiz.getLeft());
			delete(aux.getValue()); //Elimino el valor mayor encontrado
			aux.setRigth(raiz.getRigth());
			aux.setLeft(raiz.getLeft());
			this.root = aux;			
		}
		return false;
	}
	
	//Mayor elemento del arbol
	public Integer getMaxElem() {
		return getMaxElem(this.root);
	}
	
	private Integer getMaxElem(Nodo n) {
		if (isEmpty()) return -1;
		if (n.getRigth() == null) {
			return n.getValue();
		}else {
			return getMaxElem(n.getRigth());
		}
	}
	
	//Mayor Nodo 
	private Nodo getNodoMaxElem(Nodo n) {
		if (isEmpty()) return null;
		if (n == null || n.getRigth() == null) {
			return n;
		}else {
			return getNodoMaxElem(n.getRigth());
		}
	}
	
	//Menor elemento del arbol
	public Integer getMinElem() {
		return getMinElem(this.root);
	}
	
	private Integer getMinElem(Nodo n) {
		if (isEmpty()) return -1;
		if (n.getLeft() == null) {
			return n.getValue();
		}else {
			return getMinElem(n.getLeft());
		}
	}
	
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
	public void printPreOrder() {
	    this.printPreOrder(this.root);
	    System.out.println("");
	    System.out.println("-----------------------------------------------------------------");
	}
	
	private void printPreOrder(Nodo nodo) {
	    if (nodo != null) {
	        nodo.printValue();
	        printPreOrder(nodo.getLeft());
	        printPreOrder(nodo.getRigth());
	    }
	}

	//Imprimir de la forma in roden
	public void printInOrder() {
	    this.printInOrder(this.root);
	    System.out.println("");
	    System.out.println("-----------------------------------------------------------------");
	}
	
	private void printInOrder(Nodo n) {
	    if (n != null) {
	    	printInOrder(n.getLeft());
	        n.printValue();
	        printInOrder(n.getRigth());
	    }
	}
	
	//Imprimir de la forma pos roden
	public void printPosOrder() {
	    this.printPosOrder(this.root);
	    System.out.println("");
	    System.out.println("-----------------------------------------------------------------");
	}
	
	private void printPosOrder(Nodo n) {
	    if (n != null) {
	    	printPosOrder(n.getLeft());
	    	printPosOrder(n.getRigth());
	        n.printValue();
	    }
	}
	
	public int size() {
		return size(this.root);
	}
	
	private int size (Nodo n) {
		if (n == null) {
			return 0;
		}
		if (this.isHoja(n)) {
			return 1;
		}else {
			return 1 + size(n.getLeft()) + size(n.getRigth()) ;
		}
	}
	
	public List<Integer> getBorder(){
		return getBorder(this.root);
	} 
	
	private List<Integer> getBorder(Nodo n){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		if (n != null) {
			if (isHoja(n)) {
				aux.add(n.getValue());
			}else {
				aux.addAll(getBorder(n.getLeft()));
				aux.addAll(getBorder(n.getRigth()));
			}
		}
		return aux;
	}

	public int getHeight() {
		return getHeight(this.root);
	} 
	
	private int getHeight(Nodo n) {
		if (isHoja(n)) {
			return 0;
		}else {
			int alturaIzq = 0;
			int alturaDer = 0;
			if (n.getLeft() != null && n.getRigth() == null) {
				alturaIzq = 1 + getHeight(n.getLeft());
			}else {	
				if (n.getLeft() == null && n.getRigth() != null) {
					alturaDer = 1 + getHeight(n.getRigth());
				}else {
					if (n.getLeft() != null && n.getRigth() != null) {
						alturaIzq = 1 + getHeight(n.getLeft());
						alturaDer = 1 + getHeight(n.getRigth());						
					}	
				}
			}
			return alturaIzq > alturaDer ? alturaIzq : alturaDer;
		}		
	
	}
	
	public List<Integer> getElemAtLevel(int nivel) {
		return getElemAtLevel(this.root,0, nivel);
	}
	
	private List<Integer> getElemAtLevel(Nodo n, int nivelActual, int nivel){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		if (n != null) {
			if (nivelActual == nivel) {
				aux.add(n.getValue());			
			}else {
				aux.addAll(getElemAtLevel(n.getLeft(),nivelActual+1,nivel));
				aux.addAll(getElemAtLevel(n.getRigth(),nivelActual+1,nivel));
			}		
		}
		return aux;
	}
	
	public List<Integer> getLongestBranch() {
		return getLongestBranch(this.root);
	}
	
	private List<Integer> getLongestBranch(Nodo n) {
		ArrayList<Integer> auxI = new ArrayList<Integer>();
		ArrayList<Integer> auxD = new ArrayList<Integer>();
		if (isHoja(n)) {
			auxI.add(n.getValue());
			auxD.add(n.getValue());
		}else {
			if (n.getLeft() != null && n.getRigth() == null) {
				auxI.add(n.getValue());
				auxI.addAll(getLongestBranch(n.getLeft()));				
			}else {	
				if (n.getLeft() == null && n.getRigth() != null) {
					auxD.add(n.getValue());
					auxD.addAll(getLongestBranch(n.getRigth()));
				}else {
					if (n.getLeft() != null && n.getRigth() != null) {
						auxI.add(n.getValue());
						auxD.add(n.getValue());
						auxI.addAll(getLongestBranch(n.getLeft()));
						auxD.addAll(getLongestBranch(n.getRigth()));						
					}	
				}
			}			
		}
		return auxI.size() > auxD.size() ? auxI : auxD;
		
	}
}
