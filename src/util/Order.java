package util;
import java.util.Arrays;

public class Order {

	/*
	 * El método de ordenación por selección consiste en repetir los siguientes
	 * pasos: Se busca el elemento más pequeño del array y se coloca en la primera
	 * posición. Entre los restantes, se busca el elemento más pequeño y se coloca
	 * en la segunda posición. Entre los restantes se busca el elemento más pequeño
	 * y se coloca en la tercera posición.
	 */

	// Tiempo de ejecución O(n2)
	public static void seleccion(int a[]) {
		for (int i = 0; i < a.length - 1; i++) { // Voy a recorrer todo el arreglo
			int menor = a[i]; // Tomo como menor el primero y guardo el valor y la posicion en el que esta
			int pos_menor = i;
			for (int y = i + 1; y < a.length; y++) {// Empiezo a recorrer el arreglo
				if (a[y] < menor) {// si encuentro un menor guardo el valor menor y la posicion del menor
					menor = a[y];
					pos_menor = y;
				}
				if (pos_menor != i) { // Si resulta que encontre un menor por lo tanto cambio la posicion. pongo el
										// valor menor primero.
					int aux = a[i];
					a[i] = a[pos_menor];
					a[pos_menor] = aux;
				}
			}
		}
	}

	/*
	 * Consiste en comparar pares de elementos adyacentes en un array y si están
	 * desordenados intercambiarlos hasta que estén todos ordenados. El elemento
	 * mayor sube como una burbuja hacia la posición más alta.
	 */
	// Tiempo de ejecución O(n2)
	public static void burbujeo(int a[]) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int y = i + 1; y < a.length; y++) {
				if (a[i] > a[y]) {
					int aux = a[i];
					a[i] = a[y];
					a[y] = aux;
				}
			}
		}
	}

	/*
	 * Funcionamiento del método MergeSort es el siguiente: - Si la longitud del
	 * array es menor o igual a 1 entonces ya está ordenado. - El array a ordenar se
	 * divide en dos mitades de tamaño similar. - Cada mitad se ordena de forma
	 * recursiva aplicando el método MergeSort. - A continuación las dos mitades ya
	 * ordenadas se mezclan formando una secuencia ordenada.
	 */
	
	public static void mergesort(int a[]) {
		mergesort(a, 0, a.length-1);
	}
	
	private static void mergesort(int a[], int izq, int der) {
		if (izq < der) {
			int medio = (izq + der) / 2;
			mergesort(a, izq, medio);
			mergesort(a, medio + 1, der);
			merge(a, izq, medio, der);
		}
	}

	/*
	El método ordena un array a de enteros desde la posición izq hasta la posición der. 
	En la primera llamada al método recibirá los valores izq = 0, der = ELEMENTOS-1. 
	Primero se calcula el elemento central medio. A continuación la primera parte del array, 
	desde izq hasta medio y la segunda parte del array, desde medio+1 hasta der, 
	se mezclan mediante llamadas recursivas al método mergesort. 
	La recursión termina cuando izq == der, es decir, cuando un subarray contiene solamente un elemento. 
	La operación principal de mezcla la realiza el método merge. 
	*/
	// Tiempo de ejecución O(n log n)
	private static void merge(int a[], int izq, int medio, int der) {
		int i, j, k;
		int[] b = new int[a.length]; // array auxiliar
		for (i = izq; i <= der; i++) { // copia ambas mitades en el array auxiliar
			b[i] = a[i];
		}

		i = izq;
		j = medio + 1;
		k = izq;

		while (i <= medio && j <= der) { // copia el siguiente elemento más grande
			if (b[i] <= b[j]) {
				a[k] = b[i];
				i++;
			}
			else {
				a[k] = b[j];
				j++;
			}
			k++;
		}

		while (i <= medio) { // copia los elementos que quedan de la
			a[k] = b[i]; // primera mitad (si los hay)
			k++;
			i++;
		}
		while (j <= der) {
			a[k] = b[j];
			k++;
			j++;
		}
		
	}
	
	/*
	Quicksort
		
		Es el algoritmo de ordenación más rápido.
		Se basa en la técnica divide y vencerás, que consiste en ir subdividiendo el array en arrays más pequeños, y ordenar éstos. 
		Para hacer esta división, se toma un valor del array como pivote, y se mueven todos los elementos menores que este pivote a su izquierda,
		y los mayores a su derecha. 
		A continuación se aplica el mismo método a cada una de las dos partes en las que queda dividido el array.
		Después de elegir el pivote se realizan dos búsquedas:
			Una de izquierda a derecha, buscando un elemento mayor que el pivote
			Otra de derecha a izquierda, buscando un elemento menor que el pivote.
			Cuando se han encontrado los dos elementos anteriores, se intercambian, y se sigue realizando la búsqueda hasta que las dos búsquedas 
			se encuentran.
		Suponiendo que tomamos como pivote el primer elemento, el método Quicksort que implementa este algoritmo de ordenación para ordenar 
		un array de enteros se presenta a continuación. 
		
		Los parámetros izq y der son el primer y último elemento del array a tratar en cada momento.
		El método ordena un array A de enteros desde la posición izq hasta la posición der. 
		
		En la primera llamada recibirá los valores izq = 0, der = ELEMENTOS-1.
		
		En el peor caso, cuando el pivote es el elemento menor del array el tiempo de ejecución del método Quicksort es O(n2).
		En general el tiempo medio de ejecución del Quicksort es O(n log n).
		
		No requiere memoria adicional
		
	*/
	
	public static void quicksort(int a[]) {
		quicksort(a, 0, a.length-1);
	}
	
	private static void quicksort(int A[], int izq, int der) {
		int pivote = A[izq]; // tomamos primer elemento como pivote
		int i = izq; // i realiza la búsqueda de izquierda a derecha
		int j = der; // j realiza la búsqueda de derecha a izquierda
		int aux;

		while (i < j) { // mientras no se crucen las búsquedas
			while (A[i] <= pivote && i < j) {
				i++; // busca elemento mayor que pivote
			}
			while (A[j] > pivote ) {
				j--; // busca elemento menor que pivote
			}
			if (i < j) { // si no se han cruzado
				aux = A[i]; // los intercambia
				A[i] = A[j];
				A[j] = aux;
			}
		}

		A[izq] = A[j]; // se coloca el pivote en su lugar de forma que tendremos
		A[j] = pivote; // los menores a su izquierda y los mayores a su derecha

		if (izq < j - 1) {
			quicksort(A, izq, j - 1); // ordenamos subarray izquierdo
		}
		if (j + 1 < der) {
			quicksort(A, j + 1, der); // ordenamos subarray derecho
		}
		

	}
	
//	 The sorting algorithm is a Dual-Pivot Quicksort
//    * by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This algorithm
//    * offers O(n log(n)) performance on all data sets, and is typically
//    * faster than traditional (one-pivot) Quicksort implementations

	public static void arraysSort(int[] a) {
		Arrays.sort(a);
	}

}
