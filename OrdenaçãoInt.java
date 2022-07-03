package ProjetoGAP;

/* Os algoritmos de ordenação estão recebendo uma matriz como parâmetro, porém
 * apenas a primeira posição da matriz é levada em consideração na hora da 
 * ordenação, a segunda posição é apenas o índice da linha para que o arquivo
 * possa ser gravado corretamente acessando a linha por esse índice, na classe
 * principal, quando ocorrem as trocas, toda a linha da matriz é trocada para
 * preservar o índice da do campo em questão. */

public class OrdenaçãoInt extends FormataArquivo {

    public static void insertionSort (int[][] array) {

        int[] key;
        int i;
    
        for (int j = 1; j < array.length; j++) {
            key = array[j];
            for (i = j - 1; (i >= 0) && (array[i][0] > key[0]); i--) {

                array[i + 1] = array[i];
            }
            array[i + 1] = key;
        }
    }

    public static void selectionSort (int[][] array) {

        for (int i = 0; i < array.length - 1; i++) {

            int menor = i;

            for (int j = menor + 1; j < array.length; j++) {
                if (array[j][0] < array[menor][0]) {
                    menor = j;
                }
            }
            if (menor != i) {
                swap(array, i, menor);;
            }
        }
    }

    public static void bubbleSort (int array[][]) {

        boolean troca = true;
        while (troca) {
            troca = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i][0] > array[i + 1][0]) {
                    swap(array, i, i+1);
                    troca = true;
                }
            }
        }
    }
    
    public static void quickSort (int[][] array, int inicio, int fim) {

        if (inicio < fim) {

            int posicaoPivo = particionar(array, inicio, fim);
            quickSort(array, inicio, posicaoPivo - 1);
            quickSort(array, posicaoPivo + 1, fim);
        }
    }
    private static int particionar (int[][] array, int inicio, int fim) {
        
        int[] pivo = array[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (array[i][0] <= pivo[0]) {
                i++;

            } else if (pivo[0] < array[f][0]) {
                f--;

            } else {
                swap(array, i, f);
                i++;
                f--;
            }
        }
        array[inicio] = array[f];
        array[f] = pivo;
        return f;
    }

    public static void quicksortMedianaDe3(int[][] array, int inicio, int fim) {  

        if (inicio < fim) {

            int q = particionarMed3(array, inicio, fim);
            quicksortMedianaDe3(array, inicio, q - 1);
            quicksortMedianaDe3(array, q + 1, fim);            
        }
    }    
    private static int particionarMed3(int[][] array, int inicio, int fim) {

        //procura a mediana entre inicio, meio e fim
        int meio = (inicio + fim) / 2;
        int a = array[inicio][0];
        int b = array[meio][0];
        int c = array[fim][0];
        int medianaIndice;

        if (a < b) {
            if (b < c) {
                medianaIndice = meio;
            } else {                
                if (a < c) {
                    medianaIndice = fim;
                } else {
                    medianaIndice = inicio;
                }
            }
        } else {
            if (c < b) {
                medianaIndice = meio;
            } else {
                if (c < a) {
                    medianaIndice = fim;
                } else {
                    medianaIndice = inicio;
                }
            }
        }
        swap(array, medianaIndice, fim);
        
        int[] pivo = array[fim];
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            if (array[j][0] <= pivo[0]) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        //coloca o pivô na posição de ordenação
        swap(array, i + 1, fim);
        return i + 1; //retorna a posição do pivô
    }
  
    public static void mergeSort (int[][] array, int left, int right) {

        if (left < right) {
  
            int mid = (left + right) / 2;
  
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
  
            mesclar(array, left, mid, right);
        }
    }
    public static void mesclar (int array[][], int left, int mid, int right) {
  
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
    
        int leftArr[][] = new int[leftSize][2];
        int rightArr[][] = new int[rightSize][2];
    
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = array[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArr[j] = array[mid + 1 + j];
        }
        int i = 0, j = 0, k = left;
    
        while (i < leftSize && j < rightSize) {
            if (leftArr[i][0] <= rightArr[j][0]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            k++;
        }  
        while (i < leftSize) {
            array[k] = leftArr[i];
            i++;
            k++;
        }
    
        while (j < rightSize) {
            array[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void countingSort (int[][] array, int size) {

        int[][] arrayAux = new int[size + 1][2];
    
        int max = array[0][0];
        for (int i = 1; i < size; i++) {
            if (array[i][0] > max) {
            max = array[i][0];
            }
        }
        int[] count = new int[max + 1];
        
        for (int i = 0; i < size; i++) {
            count[array[i][0]]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }    
        for (int i = size - 1; i >= 0; i--) {
            arrayAux[count[array[i][0]] - 1] = array[i];
            count[array[i][0]]--;
        }    
        for (int i = 0; i < size; i++) {
            array[i] = arrayAux[i];
        }
    }

    public static void heapSort (int array[][]) {
  
        for (int i = (array.length / 2) - 1; i >= 0; i--) {

            agrupar(array, array.length, i);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            
            swap(array, 0, i);
  
            agrupar(array, i, 0);
        }
    } 
    public static void agrupar (int array[][], int n, int i) {

        int maior = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
        if (left < n && array[left][0] > array[maior][0]) {
            maior = left;
        }
        if (right < n && array[right][0] > array[maior][0]) {
            maior = right;
        }
        if (maior != i) {
            swap(array, i, maior);
            agrupar(array, n, maior);
        }
    }
    
    // método para trocar as posições do array nos algoritmos de ordenação
    private static void swap(int[][] array, int i, int j) {
        int[] aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    } 
}