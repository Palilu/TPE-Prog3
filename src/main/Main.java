package main;

import util.*;
import tree.*;

public class Main {

	public static void main(String[] args) {

		Timer reloj = new Timer();
		reloj.start();
		double tiempo = reloj.stop();
		System.out.println("Prueba time: " + tiempo);
		
		reloj = new Timer();
		reloj.start();
		for(int j = 0; j <9999; j++) {
			int N = 50;
			int[] a = new int[N];
			for(int i = 0; i <N; i++) {
				int randomValue = (int) (Math.random()*1000);
				a[i] = randomValue;
			}
			Order.seleccion(a);
		}
		tiempo = reloj.stop();
		System.out.println("Ordenamiento por burbujeo tardo: " + tiempo);		
		
		reloj = new Timer();
		reloj.start();
		for(int j = 0; j <9999; j++) {
			int N = 50;
			int[] a = new int[N];
			for(int i = 0; i <N; i++) {
				int randomValue = (int) (Math.random()*1000);
				a[i] = randomValue;
			}
			Order.burbujeo(a);
		}
		tiempo = reloj.stop();
		System.out.println("Ordenamiento por burbujeo tardo: " + tiempo);
		
		
		reloj = new Timer();
		reloj.start();
		for(int j = 0; j <9999; j++) {
			int N = 50;
			int[] a = new int[N];
			for(int i = 0; i <N; i++) {
				int randomValue = (int) (Math.random()*1000);
				a[i] = randomValue;
			}
			Order.mergesort(a);
		}
		tiempo = reloj.stop();
		System.out.println("Ordenamiento por mergesort tardo: " + tiempo);		
		
		reloj = new Timer();
		reloj.start();
		for(int j = 0; j <9999; j++) {
			int N = 50;
			int[] a = new int[N];
			for(int i = 0; i <N; i++) {
				int randomValue = (int) (Math.random()*1000);
				a[i] = randomValue;
			}
			Order.quicksort(a);			
		}
		tiempo = reloj.stop();
		System.out.println("Ordenamiento por quicksort tardo: " + tiempo);
		
		reloj = new Timer();
		reloj.start();
		for(int j = 0; j <9999; j++) {
			int N = 50;
			int[] a = new int[N];
			for(int i = 0; i <N; i++) {
				int randomValue = (int) (Math.random()*1000);
				a[i] = randomValue;
			}
			Order.arraysSort(a);
		}
		tiempo = reloj.stop();
		System.out.println("Ordenamiento por Arrays.sort(a) tardo: " + tiempo);
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>  Prubas sobre métodos del arbol <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		Tree tree = new Tree();
		
		System.out.println("--------->>La raiz es " + tree.getRoot() );
		System.out.println("--------->>Cantidad de elementos en el arbol " + tree.size() );
		
		int valor_buscar = 33;
		if (tree.hasElem(valor_buscar)) {
			System.out.println("--------->>Se encuentra el elemento " + valor_buscar + " =)  " );
		}else{
			System.out.println("--------->>No esta el " + valor_buscar + " =(  " );
		}
		
		for(int i = 0; i <50; i++) {
			int randomValue = (int) (Math.random()*100);
			tree.insert(randomValue);//No entra valores duplicados. Porque va por la rama si es mayor o menor. Nunca se analiza el igual
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> PRE ORDEN <<<<<<<<<<<<<<<<<<<");
		tree.printPreOrder();
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> EN ORDEN <<<<<<<<<<<<<<<<<<<<");
		tree.printInOrder();
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> POS ORDEN <<<<<<<<<<<<<<<<<<<");
		tree.printPosOrder();
		
		System.out.println("--------->>La raiz es " + tree.getRoot() );
		
		System.out.println("--------->>Máximo elemento del árbol " + tree.getMaxElem() );
		
		System.out.println("--------->>Mínimo elemento del árbol " + tree.getMinElem() );
		
		System.out.println("--------->>Cantidad de elementos en el árbol " + tree.size() );
		
		System.out.println("--------->>Border " + tree.getBorder() );
		
		System.out.println("--------->>Altura = " + tree.getHeight() );
		
		System.out.println("--------->>Elementos en Nivel 0 " + tree.getElemAtLevel(0) );
		System.out.println("--------->>Elementos en Nivel 1 " + tree.getElemAtLevel(1) );
		System.out.println("--------->>Elementos en Nivel 2 " + tree.getElemAtLevel(2) );
		System.out.println("--------->>Elementos en Nivel 3 " + tree.getElemAtLevel(3) );
		System.out.println("--------->>Elementos en Nivel 4 " + tree.getElemAtLevel(4) );
		System.out.println("--------->>Elementos en Nivel 5 " + tree.getElemAtLevel(5) );
		
		System.out.println("--------->>Elementos en Nivel 6 " + tree.getElemAtLevel(6) );
		System.out.println("--------->>Elementos en Nivel 7 " + tree.getElemAtLevel(7) );
		System.out.println("--------->>Elementos en Nivel 8 " + tree.getElemAtLevel(8) );
		System.out.println("--------->>Elementos en Nivel 9 " + tree.getElemAtLevel(9) );
		System.out.println("--------->>Elementos en Nivel 10 " + tree.getElemAtLevel(10) );
		System.out.println("--------->>Elementos en Nivel 11 " + tree.getElemAtLevel(11) );
		
		System.out.println("--------->>Rama mas larga  " + tree.getLongestBranch() );
		
		
		if (tree.hasElem(valor_buscar)) {
			System.out.println("--------->>Se encuentra el elemento " + valor_buscar + " =)  y se va a eliminar" );
		}else{
			System.out.println("--------->>No esta el " + valor_buscar + " =(  " );
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> EN ORDEN <<<<<<<<<<<<<<<<<<<<");
		tree.printInOrder();
		
		Integer eliminar_valor = tree.getElemAtLevel(5).get(2);
		System.out.println("Se elimina el valor = " + eliminar_valor );
		tree.delete(eliminar_valor);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>> EN ORDEN <<<<<<<<<<<<<<<<<<<<");
		tree.printInOrder();
		
				
	}

}
