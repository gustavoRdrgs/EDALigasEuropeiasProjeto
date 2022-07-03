package ProjetoGAP;

/* os métodos de ordenação estão recebendo matrizes juntamente do índice da coluna para
 * ser ordenada, no nosso caso, a coluna 7 que corresponde aos estádios. Quando ocorrerem
 * as trocas a linha inteira da matriz será trocada */

public class OrdenaçãoStr {
    
    public static void insertionSort (String[][] array, int index) {

        String key;
        int i;
    
        for (int j = 1; j < array.length; j++) {

            key = array[j][index];
            for (i = j - 1; (i >= 0) && (array[i][index].compareToIgnoreCase(key) > 0); i--) {

                array[i + 1][index] = array[i][index];
            }
            array[i + 1][index] = key;
        }
    }

    public static void selectionSort (String[][] array, int index) {

        for (int i = 0; i < array.length - 1; i++) {

            int menor = i;
            String menorStr = array[i][index];
            for (int j = menor + 1; j < array.length; j++) {
                if (array[j][index].compareToIgnoreCase(menorStr) < 0) {
                    menorStr = array[j][index];
                    menor = j;
                }
            }
            if (menor != i) {
                swap(array, i, menor);
            }
        }
    }

    public static void bubbleSort (String array[][], int index) {

        for (int i = 0; i < array.length-1; i++) {
            //System.out.println(i);
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j][index].compareToIgnoreCase(array[j + 1][index]) > 0) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    public static void quickSort (String[][] array, int inicio, int fim, int index) {

        if (inicio < fim) {

            int posicaoPivo = particionar(array, inicio, fim, index);
            quickSort(array, inicio, posicaoPivo - 1, index);
            quickSort(array, posicaoPivo + 1, fim, index);
        }
    }
    private static int particionar (String[][] array, int inicio, int fim, int index) {
        
        String[] pivo = array[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (array[i][index].compareToIgnoreCase(pivo[index]) <= 0) {
                i++;

            } else if (pivo[index].compareToIgnoreCase(array[f][index]) < 0) {
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

    public static void quicksortMedianaDe3(String[][] array, int inicio, int fim, int index) {  

        if (inicio < fim) {
            int q = particionarMed3(array, inicio, fim, index);
            quicksortMedianaDe3(array, inicio, q - 1, index);
            quicksortMedianaDe3(array, q + 1, fim, index);            
        }
    }    
    private static int particionarMed3(String[][] array, int inicio, int fim, int index) {

        //procura a mediana entre inicio, meio e fim
        int meio = (inicio + fim) / 2;
        String a = array[inicio][index];
        String b = array[meio][index];
        String c = array[fim][index];
        int medianaIndice;

        if (a.compareToIgnoreCase(b) < 0) {
            if (b.compareToIgnoreCase(c) < 0) {
                medianaIndice = meio;
            } else {                
                if (a.compareToIgnoreCase(c) < 0) {
                    medianaIndice = fim;
                } else {
                    medianaIndice = inicio;
                }
            }
        } else {
            if (c.compareToIgnoreCase(b) < 0) {
                medianaIndice = meio;
            } else {
                if (c.compareToIgnoreCase(a) < 0) {
                    medianaIndice = fim;
                } else {
                    medianaIndice = inicio;
                }
            }
        }
        swap(array, medianaIndice, fim);
        String pivo = array[fim][index];
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            if (array[j][index].compareToIgnoreCase(pivo) <= 0) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        //coloca o pivô na posição de ordenação
        swap(array, i + 1, fim);
        return i + 1; //retorna a posição do pivô
    }

    public static void mergeSort (String array[][], int left, int right, int index) {

        if (left < right) {
  
            int mid = (left + right) / 2;
  
            mergeSort(array, left, mid, index);
            mergeSort(array, mid + 1, right, index);
  
            mesclar(array, left, mid, right, index);
        }
    }
    public static void mesclar (String array[][], int left, int mid, int right, int index) {
  
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
    
        String leftArr[][] = new String[leftSize][];
        String rightArr[][] = new String[rightSize][];
    
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = array[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArr[j] = array[mid + 1 + j];
        }
        int i = 0, j = 0, k = left;
    
        while (i < leftSize && j < rightSize) {
            if (leftArr[i][index].compareToIgnoreCase(rightArr[j][index]) <= 0) {
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

    public static void heapSort (String array[][], int index) {
  
        for (int i = array.length / 2 - 1; i >= 0; i--) {

            agrupar(array, array.length, i, index);
        }
        for (int i = array.length - 1; i >= 0; i--) {

            swap(array, 0, i);  
            agrupar(array, i, 0, index);
        }
    } 
    public static void agrupar (String array[][], int n, int i, int index) {

        int maior = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
        if (left < n && array[left][index].compareToIgnoreCase(array[maior][index]) > 0) {
            maior = left;
        }
        if (right < n && array[right][index].compareToIgnoreCase(array[maior][index]) > 0) {
            maior = right;
        }
        if (maior != i) {
            swap(array, i, maior);
            agrupar(array, n, maior, index);
        }
    }

    // método para trocar as posições do array tipo String nos algoritmos de ordenação
    private static void swap(String[][] array, int i, int j) {
        String aux[] = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
}