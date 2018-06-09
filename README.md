# WdSI - Kółko i krzyżyk

Projekt z przedmiotu **Wstęp do Sztucznej Inteligencji**

## Opis projektu

Posiadamy końcowy kompletny zbiór danych przedstawiający 
wszystkie możliwe kombinacje na planszy zakończenia gry kółko i krzyżyk. Założeniem
jest, że 'x' rozpoczął grę pierwszy. Koncepcją docelową jest zwycięstwo 'x'.


### Zbiór danych

Dane reprezentowane są w następujący sposób (reprezentacja wiersza danych):
```
  1. top-left-square: {x,o,b}
  2. top-middle-square: {x,o,b} 
  3. top-right-square: {x,o,b} 
  4. middle-left-square: {x,o,b} 
  5. middle-middle-square: {x,o,b} 
  6. middle-right-square: {x,o,b} 
  7. bottom-left-square: {x,o,b} 
  8. bottom-middle-square: {x,o,b} 
  9. bottom-right-square: {x,o,b} 
  10. Class: {positive,negative}
```
Charakterystyka danych:

| Dana  | Wartość |
| ------------- | ------------- |
| Charakterystyka zbioru  | Wielowymiarowy |
| Charakterystyka atrybutu  | Kategoryzowany  |
| Zadania związane  | Klasyfikacja  |
| Liczba instancji  | 958  |
| Liczba atrybutów  | 9  |
| Brakujące wartości  | 0  |
| Dziedzina  | Gra  |
| Liczba klas  | 2 (wygrana/przegrana)  |
| Bilans danych  | 625-wygrana, 333-przegrana  |

### Założenia
* osiągnięcie skuteczności na poziomie 75%
* zastosowanie NN (neural network)


### Wykorzystane języki/narzędzia

* Java 1.8
* [DeepLearning4j](https://deeplearning4j.org/)-  biblioteka


### Kolejne kroki

1. Zamiana reprezentacji końcowych danych z:
    ```
    x,x,x,x,o,o,x,o,o,positive
    x,x,x,x,o,o,o,x,o,positive
    x,x,x,x,o,o,o,o,x,positive
    x,x,o,x,x,o,o,b,o,negative
    x,x,o,x,x,o,b,o,o,negative
    x,x,o,x,x,b,o,o,o,negative
    ```
    na
    ```
    2,2,2,2,3,3,2,3,3,1
    2,2,2,2,3,3,3,2,3,1
    2,2,2,2,3,3,3,3,2,1
    2,2,3,2,2,3,3,4,3,0
    2,2,3,2,2,3,4,3,3,0
    2,2,3,2,2,4,3,3,3,0
    ```
    gdzie:
    ```
    x = 2;
    o = 3;
    b = 4;
    przegrana = 0
    wygrana = 1
    ```
  
2. Podział 958 danych na 2 kubki: 

    ```
     - - - - - - - -         - - - - - - - -   
    |               |       |               |               
    |      34%      |       |      66%      |
    |   przegrana   |       |    wygrana    |
    |               |       |               |
     - - - - - - - -         - - - - - - - - 
     
    Z powyższych 2 kubków losujemy po ok. 34% danych przegranych i 66% przegranych
    i rozdzielamy do 10 kubków. 
    
                 - - - - - - - - - - -  
                |           |         |             
        10x *   |    34%    |   66%   |    
                | przegrana | wygrana |   
                |           |         |            
                 - - - - - - - - - - -  
                     
    Ostatecznie bierzemy kolejno każdy z kubków i przekazujemy jeden 
    do testu, a pozostałe dziewięć do treningu i tak 10 razy w pętli,
    tak aby każdy z nich był w teście, a pozostałe w treningu. 
    Wszystko to jest wykonywane jeszcze 30x w pętli i wyniki ostatecznie
    są uśredniane. 
    ```

3. Główne wykorzystanie biblioteki DL4j
    ```
    MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                    .iterations(10)
                    .activation(Activation.SIGMOID)
                    .weightInit(WeightInit.XAVIER)
                    .learningRate(0.1)
                    .regularization(true).l2(0.0001)
                    .list()
                    .layer(0, new DenseLayer.Builder().nIn(FEATURES_COUNT).nOut(8)
                            .build())
                    .layer(1, new DenseLayer.Builder().nIn(8).nOut(8)
                            .build())
                    .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                            .activation(Activation.SOFTMAX)
                            .nIn(8).nOut(CLASSES_COUNT).build())
                    .backprop(true).pretrain(false)
                    .build();
    ```

4. Ostateczne rezultaty <br /><br />
    [results.txt](https://github.com/wilq96/tictactoemachinelearning/blob/master/src/main/resources/results.txt) - ostateczne wyniki dostępne tutaj, poniżej przykład jednej z sekcji
    ``` 
        Examples labeled as 0 classified by model as 0: 29 times
        Examples labeled as 0 classified by model as 1: 4 times
        Examples labeled as 1 classified by model as 1: 62 times
        
        
        ==========================Scores========================================
         # of classes:    2
         Accuracy:        0,9579
         Precision:       0,9697
         Recall:          0,9394
         F1 Score:        0,9688
        ========================================================================
        
       ----------------------------------
        Macierz pomyłek:
        
         Predicted:         0      1
         Actual:
       0  0          |      19     14
       1  1          |       0     62
       
       -----------------------------------
        
        
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                              ----- RESULTS -----                               
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        Accuracy: 0.7907719298245615
        Precision: 0.7970452829180267
        Recall: 0.7835516454871293
        F1 score: 0.8240154937403639
        
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    ```
    **Dokładność jaką otrzymujemy jest na poziomie ok. 80%**

## Wykonali

* Michał Wilk
* Kamil Postrożny

Politechnika Krakowska wydział Fizyki, Matematyki i Informatyki - Informatyka niestacjonarne
