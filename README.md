### Projeto EDA - Ligas Europeias (GAP)

#####  _Arthur Lincoln da Paz, Gustavo Genésio Rodrigues e Philipe de Oliveira Tavares_

---

##### Orientações sobre o projeto:

- Dentro do arquivo zipado há uma pasta chamada ***ProjetoDeEda***, dentro dessa pasta há o package do nosso projeto, intitulado 'ProjetoGAP';
- O arquivo ***"matches.csv"*** deverá ser colocado dentro da pasta ***ProjetoDeEda***;
- Dentro do nosso pacote, o arquivo java onde se encontra nossa classe principal é o intitulado 'ProjetoEDA', é ele que deverá ser executado, os demais
arquivos só têm funções que utilizamos na classe principal, tais como as funções dos algoritmos de ordenação;
- Haverá um delay para cada transformação de arquivo, isso antes das ordenações, para o usuário conseguir acompanhar quais arquivos estão sendo criados;
- Há um ***menu*** para a criação dos arquivos que serão ordenados. 
- No menu, primeiro o usuário irá escolher a coluna (attendance, data, venue) que irá ordenar, após isso, terá que selecionar o algoritmo de ordenação que irá ser utilizado;
- Quando selecionar o algoritmo de ordenação, os arquivos do médio, pior e melhor caso correspondentes à aquela coluna e algoritmo serão criados altomaticamente.
O médio caso será a entrada normal do arquivo, o melhor caso será o arquivo já ordenado e o pior caso será o arquivo ordenado, porém em ordem decrescente.
- Quando for selecionado a opção de gerar os arquivos com o algoritmo de ordenação **QuickSort**, serão criados os arquivos ordenados com o próprio **QuickSort** e o **QuickSortMedianaDeTres**;
- Após, selecionar no menu o algoritmo para o ordenação, o tempo para gerar o arquivo irá aparecer no terminal do usuário.



##### Tabela de tempo (em milissegundos) da ordenação pelo público pagante (attendance):

|       Algoritmos       | Pior caso | Caso Médio | Melhor Caso |
| :--------------------: | :-------: | :--------: | :---------: |
|    **Bubble Sort**     |   2236    |    2948    |      0      |
|   **Insertion Sort**   |   1354    |    649     |      1      |
|   **Selection Sort**   |    844    |    620     |     588     |
|     **Quick Sort**     |    420    |     52     |     716     |
| **Quick Sort (Med 3)** |    204    |     82     |     97      |
|     **Merge Sort**     |    54     |     74     |     70      |
|     **Heap Sort**      |    11     |     20     |     23      |
|    **Couting Sort**    |    39     |     6      |     15      |



##### Tabela de tempo (em milissegundos) da ordenação pela data:

|       Algoritmos       | Pior caso | Caso Médio | Melhor Caso |
| :--------------------: | :-------: | :--------: | :---------: |
|    **Bubble Sort**     |   2227    |    1943    |      1      |
|   **Insertion Sort**   |   1333    |    572     |      0      |
|   **Selection Sort**   |    607    |    407     |     479     |
|     **Quick Sort**     |    564    |     6      |     236     |
| **Quick Sort (Med 3)** |    16     |     4      |      5      |
|     **Merge Sort**     |    27     |     32     |     42      |
|     **Heap Sort**      |    10     |     20     |     16      |
|    **Couting Sort**    |    61     |     44     |     70      |



##### Tabela de tempo (em milissegundos) da ordenação pelo local (venue):

|       Algoritmos       | Pior caso | Caso Médio | Melhor Caso |
| :--------------------: | :-------: | :--------: | :---------: |
|    **Bubble Sort**     |  122636   |   125903   |   141213    |
|   **Insertion Sort**   |   91793   |   28934    |     15      |
|   **Selection Sort**   |   48348   |   36055    |    35591    |
|     **Quick Sort**     |   6749    |    861     |     261     |
| **Quick Sort (Med 3)** |    444    |    365     |     325     |
|     **Merge Sort**     |    86     |     95     |     50      |
|     **Heap Sort**      |    33     |    162     |     100     |

Obs: O counting sort não funciona para ordenação de strings, pois ele usa o conteúdo a ser ordenado como índices de um array auxiliar para contar quantos números iguais à aquele índice há no array a ser ordenado e interar sobre aquela posição, e a partir disso a ordenação ser feita.

[^Criadores]: Arthur, Gustavo e Philipe (Estrutura de Dados 2022.1)
