package ProjetoGAP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class ProjetoEDA {

    public static void main(String[] args) throws IOException {

        // arquivo a ser trabalhado
        String matchesPath = "matches.csv";

        // variável que recebe a quantidade de linhas do arquivo 
        int nLines = (int) FormataArquivo.contarLinhas(matchesPath);
        int aux = 0; // variável auxiliar feita para controlar as linhas da matriz

        String[][] matches = new String[nLines][];
        try (BufferedReader br = new BufferedReader(new FileReader(matchesPath))) {
            
            // variável que recebe toda a linha do arquivo
            String line = br.readLine();

            // enquanto houverem linhas a serem lidas, os tratamentos continuarão sendo feitos
            while (line != null) {

                // é gravado na linha da matriz todas as posições referentes as colunas do arquivo
                matches[aux] = FormataArquivo.corrigirVirgulas(line);
                aux++; // variável que controla a linha da matriz a ser gravada é incrementada
                line = br.readLine(); // variável que controla se ainda linhas a serem lidas é atualizada
            }
            System.out.println("O arquivo matches_T1 será gravado\n");

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        try (PrintWriter writerMatchesT1 = new PrintWriter(new File("matches_T1.csv"))) {

            // objeto da classe StringBuilder para ser concatenado tudo referente ao novo arquivo
            StringBuilder strb1 = new StringBuilder();

            // laço que percorre todas as posições da matriz
            for (int i = 0; i < matches.length; i++) {
                for (int j = 0; j < 34; j++) {
                    if ((j <= 8) || (j >= 12 && j <= 13) || (j == 33) || (j == 31)) {

                        // o strb1 tem seu conteúdo concatenado mais uma vírgula para o CSV
                        strb1.append(matches[i][j] + ",");
                    }
                }
                // quebra de linha no arquivo
                if (i < matches.length - 1) {
                    strb1.append("\n");
                }
            }
            // delay no programa para que possa ser melhor acompanhado a execução e a criação dos arquivos
            PausaCodigo.pausarCodigo3Sgnds();

            // conteúdo do nosso sb é gravado no arquivo
            writerMatchesT1.write(strb1.toString());
            writerMatchesT1.close();

            System.out.println("\nO arquivo matches_T1 foi gravado no caminho: ProjetoDeEda/matches_T1.csv\n");
            PausaCodigo.pausarCodigo1Sgnd(); // delay de transição para o próximo arquivo

        } catch (FileNotFoundException e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }
        
        String[][] matches_T1 = new String[nLines-1][]; // matriz para colocar os dados de matches_T1
        String[] formattedFullDates = new String[nLines-1]; // array para guardar as datas formatadas
        String lineIndices = null; // variável para adicionar o campo 'full_date' aos índices dos dados

        // arquivo a ser trabalhado
        String matches_T1Path = "matches_T1.csv";
        int aux2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(matches_T1Path))) {

            // lê a primeira linha do arquivo, a que corresponde aos índices das colunas
            String line = br.readLine();

            // salva esses índices numa nova variável e adiciona o novo campo 'full_date'
            lineIndices = line.concat("full_date");

            /* grava a segunda linha do arquivo onde estão as datas para que elas possam ser formatadas,
            o laço então começa a ler o arquivo a partir da linha onde estão os dados*/ 
            line = br.readLine();

            while (line != null) {

                // chama a o método corrigirVirgulas para reorganizar as linhas
                matches_T1[aux2] = FormataArquivo.corrigirVirgulas(line);

                // chama o método responsável por formatar a data
                formattedFullDates[aux2] = FormataArquivo.formarDataCompleta(matches_T1[aux2]);

                // grava na linha da matriz os dados antigos mais a coluna da data formatada
                formattedFullDates[aux2] = line.concat(formattedFullDates[aux2]);

                aux2++; // variável que controla a linha da matriz a ser gravada é incrementada
                line = br.readLine(); // variável que controla se ainda linhas a serem lidas é atualizada
            }
            System.out.println("O arquivo matches_T2 será gravado\n");
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        try (PrintWriter writerMatchesT2 = new PrintWriter(new File("matches_T2.csv"))) {

            // objeto da classe StringBuilder para ser concatenado tudo referente ao novo arquivo
            StringBuilder strb2 = new StringBuilder();

            strb2.append(lineIndices+"\n"); // guarda no arquivo a linha dos índices

            // laço que percorre as posições do array com as linhas do novo arquivo
            for (int i = 0; i < formattedFullDates.length; i++) {

                // o strb2 tem seu conteúdo concatenado com a linha do arquivo mais uma quebra de linha
                strb2.append(formattedFullDates[i]);
                if (i < formattedFullDates.length-1) {
                    strb2.append("\n");
                }
            }
            // delay no programa para que possa ser melhor acompanhado a execução e a criação dos arquivos
            PausaCodigo.pausarCodigo3Sgnds();

            // o conteúdo é gravado no arquivo
            //System.out.println(strb2.toString());
            writerMatchesT2.write(strb2.toString());
            writerMatchesT2.close();

            System.out.println("\nO arquivo matches_T2 foi gravado no caminho: ProjetoDeEDA/matches_T2.csv\n");
            PausaCodigo.pausarCodigo1Sgnd(); // delay de transição para o próximo arquivo

        } catch (FileNotFoundException e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        // arquivo a ser lido
        String matches_T2Path = "matches_T2.csv";

        // dimensiona a matriz com a quantidade de partidas da Premier League
        int linesPL = FormataArquivo.contarLinhasPL(matches_T2Path);
        String[] matches_F1 = new String[linesPL];
        int aux3 = 0; // variável que controla a linha da matriz

        try (BufferedReader br = new BufferedReader(new FileReader(matches_T2Path))) {

            String line = br.readLine();

            while (line != null) {

                // se a linha conter "Premier League ela é salva no Array"
                if (line.contains("Premier League")) {

                    matches_F1[aux3] = line;
                    aux3++;
                }
                line = br.readLine(); // lê a próxima linha
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        try (PrintWriter writerMatchesF1 = new PrintWriter(new File("matches_F1.csv"))) {

            System.out.println("O arquivo matches_F1 será gravado\n");
            
            // StringBuilder que terá todas as partidas da premier league antes de ser gravada no arquivo
            StringBuilder strb3 = new StringBuilder();
            strb3.append(lineIndices+"\n"); // adiciona a linha dos indices

            for (int i = 0; i < matches_F1.length; i++) {

                strb3.append(matches_F1[i]+"\n");
            }
            // pausa no código para transição
            PausaCodigo.pausarCodigo3Sgnds();

            writerMatchesF1.write(strb3.toString());
            writerMatchesF1.close();

            System.out.println("\nO arquivo matches_F1 foi gravado no caminho: ProjetoDeEDA/matches_F1.csv\n");
            PausaCodigo.pausarCodigo1Sgnd(); // delay de transição para o próximo arquivo

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        // variável recebe o número de partidas da Premier League com mais de 20k pessoas
        int linesPLM20k = FormataArquivo.contarLinhasMaisQue20K(matches_F1);
        int aux4 = 0;
        String[] matchesF2 = new String[linesPLM20k]; // array que guardará essas partidas

        try (PrintWriter writerMatchesF2 = new PrintWriter(new File("matches_F2.csv"))) {

            System.out.println("O arquivo matches_F2 será gravado\n");
            
            StringBuilder strb4 = new StringBuilder();
            strb4.append(lineIndices+"\n");

            // laço que percorrerá o número de partidas com mais de 20k de público
            for (int i = 0; i < matches_F1.length; i++) {

                /* se o retorno da função formatarPublico for maior que 20000, seu conteúdo é 
                adicionado no StringBuilder*/
                if (FormataArquivo.formatarInteiros(matches_F1[i], 6) > 20000) {
                    strb4.append(matches_F1[i]+"\n");
                    matchesF2[aux4] = matches_F1[i];
                    aux4++;
                }
            }
            PausaCodigo.pausarCodigo3Sgnds();

            writerMatchesF2.write(strb4.toString());
            writerMatchesF2.close();
            
            System.out.println("\nO arquivo matches_F2 foi gravado no caminho: ProjetoDeEDA/matches_F2.csv\n");
            PausaCodigo.pausarCodigo1Sgnd(); // delay de transição para o próximo arquivo

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
        }

        Scanner sc = new Scanner(System.in);
        int ordenar = -1;
        // laço do menu para decidir por qual coluna o arquivo será ordenado
        while (ordenar != 0) {

            System.out.println("Escolha por qual coluna deseja ordenar\n");
            System.out.println("Digite 1 para ordenar pelo campo 'venue'\nDigite 2 para ordenar pelo campo 'attendance'");
            System.out.println("Digite 3 para ordenar pelo campo 'full_date'\n\nDigite 0 para sair");
            ordenar = sc.nextInt();

            int ordenaçãoVenue = -1;
            int ordenaçãoAttend = -1;
            int ordenaçãoDate = -1;

            switch (ordenar) {

                case 1:
                // laço para decidir qual algoritmo de ordenação será usado
                while (ordenaçãoVenue != 0) {

                    // essa matriz receberá o conteúdo de matches_T2 e servirá como o médio caso
                    // o melhor caso será ela própria após ser ordenada
                    String[][] venueMDC = new String[formattedFullDates.length][];
                    for (int i = 0; i < venueMDC.length; i++) {
                        venueMDC[i] = FormataArquivo.corrigirVirgulas(formattedFullDates[i]);
                    }
                    /* o pior caso será essa, ela será o inverso de 'venueMDC', essa transformação
                    ocorrerá dentro dos blocos 'try' */
                    String[][] venuePRC = new String[formattedFullDates.length][];

                    System.out.println("Escolha qual algoritmo de ordenação deseja usar\n");
                    System.out.println("Digite 1 para Insertion Sort\nDigite 2 para Selection Sort\nDigite 3 para Bubble Sort");
                    System.out.println("Digite 4 para Quick Sort\nDigite 5 para Merge Sort\nDigite 6 para Heap Sort");
                    System.out.println("\nDigite 0 para sair");
                    ordenaçãoVenue = sc.nextInt();

                    switch (ordenaçãoVenue) {
                        case 1:
                        try (PrintWriter venuesInsertionSortMDC = new PrintWriter(new File("matches_t2_venues_insertionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Insertion Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o insertion sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.insertionSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");


                            venuesInsertionSortMDC.write(sb.toString());
                            venuesInsertionSortMDC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_insertionSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesInsertionSortMLC = new PrintWriter(new File("matches_t2_venues_insertionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o insertion sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.insertionSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venuesInsertionSortMLC.write(sb.toString());
                            venuesInsertionSortMLC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_insertionSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesInsertionSortPRC = new PrintWriter(new File("matches_t2_venues_insertionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o insertion sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.insertionSort(venuePRC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesInsertionSortPRC.write(sb.toString());
                            venuesInsertionSortPRC.close();
                            System.out.println("O arquivo 'matches_t2_venues_insertionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;

                        case 2:
                        try (PrintWriter venuesSelectionSortMDC = new PrintWriter(new File("matches_t2_venues_selectionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Selection Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o selection sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.selectionSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesSelectionSortMDC.write(sb.toString());
                            venuesSelectionSortMDC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_selectionSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesSelectionSortMLC = new PrintWriter(new File("matches_t2_venues_selectionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o selection sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.selectionSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venuesSelectionSortMLC.write(sb.toString());
                            venuesSelectionSortMLC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_selectionSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesSelectionSortPRC = new PrintWriter(new File("matches_t2_venues_selectionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o selection sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.selectionSort(venuePRC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesSelectionSortPRC.write(sb.toString());
                            venuesSelectionSortPRC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_selectionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;

                        case 3:
                        try (PrintWriter venuesBubbleSortMDC = new PrintWriter(new File("matches_t2_venues_bubbleSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Bubble Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Bubble sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.bubbleSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesBubbleSortMDC.write(sb.toString());
                            venuesBubbleSortMDC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_bubbleSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesBubbleSortMLC = new PrintWriter(new File("matches_t2_venues_bubbleSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Bubble sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.bubbleSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venuesBubbleSortMLC.write(sb.toString());
                            venuesBubbleSortMLC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_bubbleSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesBubbleSortPRC = new PrintWriter(new File("matches_t2_venues_bubbleSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o bubble sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.bubbleSort(venuePRC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesBubbleSortPRC.write(sb.toString());
                            venuesBubbleSortPRC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_bubbleSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;

                        case 4:
                        try (PrintWriter venuesInsertionSortMDC = new PrintWriter(new File("matches_t2_venues_quickSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quickSort(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesInsertionSortMDC.write(sb.toString());
                            venuesInsertionSortMDC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_quickSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesInsertionSortMLC = new PrintWriter(new File("matches_t2_venues_quickSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quickSort(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venuesInsertionSortMLC.write(sb.toString());
                            venuesInsertionSortMLC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_quickSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venuesInsertionSortPRC = new PrintWriter(new File("matches_t2_venues_quickSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quickSort(venuePRC, 0, venuePRC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venuesInsertionSortPRC.write(sb.toString());
                            venuesInsertionSortPRC.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_quickSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_quickSortMedianaDe3_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort Mediana de 3 serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort Mediana de 3 está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quicksortMedianaDe3(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_quickSortMedianaDe3_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_quickSortMedianaDe3_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort Mediana de 3 está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quicksortMedianaDe3(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_quickSortMedianaDe3_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_quickSortMedianaDe3_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort Mediana de 3 está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.quicksortMedianaDe3(venuePRC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("O arquivo 'matches_t2_venues_quickSortMedianaDe3_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;

                        case 5:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_mergeSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Merge Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Merge Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.mergeSort(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_mergeSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_mergeSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Merge Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.mergeSort(venueMDC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_mergeSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_mergeSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Merge Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.mergeSort(venuePRC, 0, venueMDC.length-1, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_mergeSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;

                        case 6:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_heapSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Heap Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Heap Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.heapSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_heapSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_heapSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Heap Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.heapSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venueMDC.length; i++) {
                                for (int j = 0; j < venueMDC[i].length; j++) {
                                    sb.append(venueMDC[i][j]+",");
                                }
                                if (i != venueMDC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = venuePRC.length-1;
                            for (int i = 0; i < venueMDC.length; i++) {
                                venuePRC[i] = venueMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_heapSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_venues_heapSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Heap Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < venuePRC.length; i++) {
                                for (int m = 0; m < venuePRC[i].length; m++) {
                                    sb.append(venuePRC[i][m]+",");
                                }
                                if (i != venuePRC.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoStr.heapSort(venueMDC, 7);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_venues_heapSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        break;    
                    }
                }
                break;

                case 2:
                // laço para decidir qual algoritmo de ordenação será usado
                while (ordenaçãoAttend != 0) {

                    /* essa matriz receberá o conteúdo de 'attendance' e o índice da linha,
                    o conteúdo será usado nas ordenações e o índice nas gravações dos arquivos,
                    servirá para ser acessado o índice correto a ser adicionado na próxima linha */

                    // ela será o médio caso e depois o melhor caso após ser ordenada
                    int[][] attendanceMDC = new int[formattedFullDates.length][2];
                    for (int i = 0; i < formattedFullDates.length; i++) {
                        attendanceMDC[i][0] = FormataArquivo.formatarInteiros(formattedFullDates[i], 6);
                        attendanceMDC[i][1] = i;
                    }
                    /* essa será o inverso de 'attendanceMDC' e servirá como o pior caso, essa
                    transformação ocorrerá dentro dos blocos 'try' */
                    int[][] attendancePRC = new int[formattedFullDates.length][];

                    System.out.println("Escolha qual algoritmo de ordenação deseja usar\n");
                    System.out.println("Digite 1 para Insertion Sort\nDigite 2 para Selection Sort\nDigite 3 para Bubble Sort");
                    System.out.println("Digite 4 para Quick Sort\nDigite 5 para Merge Sort\nDigite 6 para Heap Sort");
                    System.out.println("Digite 7 para Counting Sort\n\nDigite 0 para sair");
                    ordenaçãoAttend = sc.nextInt();

                    switch (ordenaçãoAttend) {

                        case 1:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_insertionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Insertion Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Insertion Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_insertionSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_insertionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Insertion Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_insertionSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_insertionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Insertion Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(attendancePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_insertionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 2:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_selectionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Selection Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Selection Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_selectionSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_selectionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Selection Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_selectionSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_selectionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Selection Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(attendancePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_selectionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 3:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_bubbleSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Bubble Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Bubble Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_bubbleSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_bubbleSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Bubble Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_bubbleSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_bubbleSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Bubble Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(attendancePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_bubbleSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 4:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(attendancePRC, 0, attendancePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSortMedianaDe3_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort Mediana de 3 serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort Mediana de 3 está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSortMedianaDe3_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSortMedianaDe3_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort Mediana De 3 está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSortMedianaDe3_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_quickSortMedianaDe3_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort Mediana De 3 está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(attendancePRC, 0, attendancePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_quickSortMedianaDe3_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 5:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_mergeSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Merge Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Merge Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_mergeSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_mergeSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Merge Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(attendanceMDC, 0, attendanceMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_mergeSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_mergeSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Merge Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(attendancePRC, 0, attendancePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_mergeSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 6:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_heapSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Heap Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Heap Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_heapSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_heapSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Heap Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(attendanceMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_heapSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_heapSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Heap Sort está sendo ordenado");
                            
                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(attendancePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_heapSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 7:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_countingSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Counting Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Counting Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(attendanceMDC, attendanceMDC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_countingSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_countingSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Counting Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(attendanceMDC, attendanceMDC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendanceMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = attendancePRC.length-1;
                            for (int i = 0; i < attendanceMDC.length; i++) {
                                attendancePRC[i] = attendanceMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_countingSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_attendance_countingSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Counting Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[attendancePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(attendancePRC, attendancePRC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_countingSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;
                        
                    }
                }
                break; 

                case 3:
                // laço para decidir qual algoritmo de ordenação será usado
                while (ordenaçãoDate != 0) {

                    // essa array receberá as datas no formato DD/MM/AAAA
                    String[] fullDates = new String[formattedFullDates.length];
                    for (int i = 0; i < fullDates.length; i++) {
                        fullDates[i] = FormataArquivo.formarDataCompleta(matches_T1[i]);
                    }

                    /* essa matriz receberá o conteúdo de 'full_date' e o índice da linha,
                    o conteúdo será usado nas ordenações e o índice nas gravações dos arquivos,
                    servirá para ser acessado o índice correto a ser adicionado na próxima linha */

                    // ela será o médio caso e depois o melhor caso após ser ordenada
                    int[][] dateMDC = new int[formattedFullDates.length][2];
                    for (int i = 0; i < formattedFullDates.length; i++) {
                        dateMDC[i][0] = FormataArquivo.formarDataInt(fullDates[i]);
                        dateMDC[i][1] = i;
                    }
                    /* essa será o inverso de 'dateMDC' e servirá como o pior caso, essa
                    transformação ocorrerá dentro dos blocos 'try' */
                    int[][] datePRC = new int[formattedFullDates.length][];

                    System.out.println("Escolha qual algoritmo de ordenação deseja usar\n");
                    System.out.println("Digite 1 para Insertion Sort\nDigite 2 para Selection Sort\nDigite 3 para Bubble Sort");
                    System.out.println("Digite 4 para Quick Sort\nDigite 5 para Merge Sort\nDigite 6 para Heap Sort");
                    System.out.println("Digite 7 para Counting Sort\n\nDigite 0 para sair");
                    ordenaçãoDate = sc.nextInt();

                    switch (ordenaçãoDate) {

                        case 1:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_insertionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Insertion Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Insertion Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_full_date_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_insertionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Insertion Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_attendance_full_date_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_insertionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Insertion Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.insertionSort(datePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_insertionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 2:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_selectionSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Selection Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Selection Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_selectionSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_selectionSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Selection Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_selectionSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_selectionSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Selection Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.selectionSort(datePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_selectionSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 3:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_bubbleSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Bubble Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Bubble Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_bubbleSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_bubbleSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Bubble Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_bubbleSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_bubbleSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Bubble Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.bubbleSort(datePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_bubbleSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 4:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quickSort(datePRC, 0, datePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSortMedianaDe3_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Quick Sort Mediana de 3 serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Quick Sort Mediana de 3 está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSortMedianaDe3_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSortMedianaDe3_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Quick Sort Mediana De 3 está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSortMedianaDe3_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_quickSortMedianaDe3_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Quick Sort Mediana De 3 está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.quicksortMedianaDe3(datePRC, 0, datePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_quickSortMedianaDe3_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 5:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_mergeSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Merge Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Merge Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_mergeSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_mergeSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Merge Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(dateMDC, 0, dateMDC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_mergeSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_mergeSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Merge Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.mergeSort(datePRC, 0, datePRC.length-1);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_mergeSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 6:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_heapSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Heap Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Heap Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_heapSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_heapSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Heap Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(dateMDC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_heapSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_heapSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Heap Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.heapSort(datePRC);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_heapSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;

                        case 7:
                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_countingSort_medioCaso.csv"))) {
                            System.out.println("\nOs arquivos de ordenação do Counting Sort serão criados");
                            StringBuilder sb = new StringBuilder();

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[i]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            System.out.println("\nO médio caso para o Counting Sort está sendo ordenado");
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(dateMDC, dateMDC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_countingSort_medioCaso.csv' foi criado");
                                
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_countingSort_melhorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();

                            System.out.println("\nO melhor caso para o Counting Sort está sendo ordenado");

                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(dateMDC, dateMDC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[dateMDC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            int j = datePRC.length-1;
                            for (int i = 0; i < dateMDC.length; i++) {
                                datePRC[i] = dateMDC[j]; j--;
                            }

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_countingSort_melhorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        try (PrintWriter venues = new PrintWriter(new File("matches_t2_full_date_countingSort_piorCaso.csv"))) {
                            StringBuilder sb = new StringBuilder();
                            System.out.println("\nO pior caso para o Counting Sort está sendo ordenado");

                            sb.append(lineIndices+"\n");
                            for (int i = 0; i < formattedFullDates.length; i++) {
                                sb.append(formattedFullDates[datePRC[i][1]]);
                                if (i != formattedFullDates.length-1) {
                                    sb.append("\n");
                                }
                            }
                            long tempo = System.currentTimeMillis();
                            OrdenaçãoInt.countingSort(datePRC, datePRC.length);
                            System.out.println("\nA ordenação ocorreu em " + (System.currentTimeMillis() - tempo) + " milissegundos");

                            venues.write(sb.toString());
                            venues.close();
                            System.out.println("\nO arquivo 'matches_t2_full_date_countingSort_piorCaso.csv' foi criado");
                        
                        } catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage() + "\nOcorreu um erro com o arquivo");
                        }
                        System.out.println("_________________________________________________________________________________");

                        break;
                    }
                }
                break;
            }
        }
        sc.close();    
    }
}