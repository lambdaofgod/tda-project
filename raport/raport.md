# Jakub Bartczuk
# Topologiczna analiza danych a redukcja wymiaru

W projekcie zajmujemy się metodami pochodzącymi z topologii algebraicznej \(grupy homologii\) w kontekście oceny metod nieliniowej redukcji wymiaru.

W projekcie erlangeńskim Felixa Kleina geometria jest badaniem własności, które są niezmiennicze względem grup przekształceń - geometria afiniczna zajmuje się niezmiennikami \(odwracalnych\) przekształceń afinicznych itd.  
Topologia może być w tym kontekście rozpatrywana jako geometria, której grupą przekształceń są homeomorfizmy \(tzn ciągłe bijekcje, których funkcje odwrotne są ciągłe\).

Jednym z poddziałów topologii jest topologia algebraiczna, która zajmuje się niezmiennikami, które są pewnymi algebrami - grupami w przypadku tzw. grupy podstawowej, albo grupami abelowymi/modułami w przypadku homologii \(faktycznie są one niezmiennikami ogólniejszych przekształceń, tj równoważności homotopijnych\).

Metody oparte na topologii pasują do 'manifold learning' jako metody wyliczania niezmienników niskowymiarowych rozmaitości na których leżą dane.

W projekcie wykorzystujemy niezmienniki topologiczne do oceny nieliniowych algorytmów redukcji wymiaru. Konkretnie stosujemy metodę trwałych homologii. Daje ona pewne wskazówki, które metody mogą nadawać się do wizualizacji danych, a które mogą zostać zdyskwalifikowane jako zaburzające charakterystykę danych.

# Wykorzystane metody

Wśród niezmienników topologicznych szczególnie łatwe do wyliczenia są grupy homologii.  
Szczegóły definicji można odnaleźć np [tu](https://www.math.upenn.edu/~zvihr/courses/AppliedAT.pdf), paragrafy 4 i 7.  
W przypadku wyliczania homologii rozmaitości z chmury punktów \(próbek\) wykorzystujemy kompleksy Vietorisa-Ripsa oraz kompleksy świadków.

W skrócie proces wyliczania homologii rozmaitości z chmury punktów wygląda następująco:

* Wyznaczenie kompleksu \(strumienia sympleksów skierowanego przez parametr $$t$$\)

* Wyliczenie homologii dla odpowiednich wartości $$t$$

Wyznaczenie kompleksu Vietorisa-Ripsa dla zbioru danych 

$$X = \{x_i\}_{i \leq N}$$,

$$n$$ \(numDivisions\),

$$t_{max} $$ \(maxFiltrationValue\) polega na

* wyliczeniu $$t$$-tego kompleksu \(nerwu pokrycia $$K(x_i, t)$$  zbioru $$X$$\) dla $$t \in \{0, \frac{t_{max}}{n}, ...,  t_{max}\}$$
* wyliczeniu homologii dla powyższych kompleksów.

Parametryzując cykle reprezentujące grupy homologii w odpowiednich wymiarach $$t$$ otrzymujemy tzw. kod kreskowy, np dla zbioru 'domku': 
![zbioru z obrazka](https://github.com/appliedtopology/javaplex/raw/master/reports/javaplex_tutorial/houseFig.png)

Otrzymujemy kod kreskowy

![](/houseHomology.png)

![](/houseHomology1.png)

Co interpretuje się następująco: dla $$t = 2$$ Przestrzeń staje się spójna, oraz pojawia się 'dziura' (reprezentująca grupę homologii w wymiarze 1).

W notebooku [javaplex](https://github.com/lambdaofgod/tda-project/blob/master/notebooks/javaplex.ipynb) znajdują się dalsze przykłady (wykorzystujące chmurę punktów z torusa) oraz definicja wyboru 'punktów orientacyjnych'

## Implementacja

Do obliczeń korzystających z metod topologicznych wykorzystujemy bibliotekę [javaplex](https://github.com/appliedtopology/javaplex "javaplex").  

Implementacje algorytmów redukcji wymiaru pochodzą z biblioteki [smile](http://haifengl.github.io/smile/ "smile").

Wszystkie analizy przeprowadzone są w notebookach Jupytera używających [kernela Scali](https://github.com/alexarchambault/jupyter-scala).

# Opis danych
Wykorzystujemy dwa zestawy danych, których własności badane są odpowiednio w pracach 

1. [On the non-linear statistics of range image patches](https://pdfs.semanticscholar.org/dde7/e2562efd53a7137535aec9389d252bafeb25.pdf)

2. [On the Local Behavior of Spaces of Natural Images](http://math.uchicago.edu/~shmuel/AAT-readings/Data%20Analysis%20/mumford-carlsson%20et%20al.pdf)

Dane z obu zestawów składają się z 15 tysięcy przykładów odpowiednio 25 i 9-wymiarowych obrazków. 

Dane z zestawu **1** mają kształt okręgu.

Zestaw **2** ma kształt 3 okręgów połączonych parami w 2 punktach.

# Opis wyników
### 1
[notebook](https://github.com/lambdaofgod/tda-project/blob/master/notebooks/Range%20image%20patches.ipynb)
* Sprawdzamy metody Isomap, LLE odwzorowując dane w przestrzeń 2-wymiarową (najniższego wymiaru, w którą da się zanurzyć okrąg). Dopasowujemy też sieć Kohonena.
* Wybieramy 1000 punktów żeby przyspieszyć działanie metod redukcji wymiaru (sprawdzamy, że istotnie owa próba ma taką charakterystykę jak dane).
* 
    * Okazuje się, że zastosowane metody uczą się odwzorowań, które dość dobrze zachowują kształt.
    * LLE wydaje się wypadać trochę gorzej, bo nietrywialny 1-cykl pojawia się dużo później niż w oryginalnej przestrzeni.
    * Zachowanie SOM jest zależne od rozmiaru kraty, ale od kraty 20 x 20 wyniki są porównywalne z Isomap.

### 2
[notebook](https://github.com/lambdaofgod/tda-project/blob/master/notebooks/Natural%20image%20patches.ipynb)
* Liczymy homologie zbioru danych. Strukturę opisaną wyżej reprezentuje 5 cykli w wymiarze 1.
* Sprawdzamy metody Isomap, LLE i KPCA (z jądrem wielomianowym 2 stopnia) odwzorowując dane w przestrzeń 3-wymiarową (w 2 wymiarach nie da się zanurzyć okręgów o podanym wcześniej położeniu).
* Wybieramy 1000 punktów żeby przyspieszyć działanie metod redukcji wymiaru (sprawdzamy, że istotnie owa próba ma taką charakterystykę jak dane - pojawił się jednak pewien szum).
* 
    * Dane zredukowane przez Isomap zachowują homologie. 
    * LLE ma z tym problem, co widać na kodach kreskowych dla dwóch przykładowych parametrów $$k$$ (ilości sąsiadów użytych do liniowego przybliżenia).
    * KPCA z jądrem wielomianowym podobnie jak LLE traci informację o 1-wymiarowych cyklach.

# Wnioski końcowe, perspektywy

Metody topologicznej analizy danych mogą być wykorzystywane jako uzupełnienie błędu rekonstrukcji w przypadku nieliniowych metod redukcji wymiaru. Licząc niezmienniki przed i po zastosowaniu algorytmu możemy sprawdzić, czy topologiczne własności zbioru danych zostały zachowane. W przypadku redukcji wymiaru do 2 lub 3 otrzymujemy informację, czy zastosowany algorytm nie 'oszukuje' nas, prezentując zbiór o innej charakterystyce niż wyjściowe dane.

Pewnym rozwinięciem projektu mogłoby być sprawdzenie własności metod będących rozwinięciem sieci Kohonena, np. [sieci reprezentujących topologię](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.107.4723&rep=rep1&type=pdf) czy metody neural gas.

Patrząc wstecz, wybór biblioteki Smile był mało fortunny. Jest ona bardzo nierówna, bo mając bardzo szybkie pewne metody (drzewa losowe i boosting) używa podstawowej Javy do implementacji pewnych algorytmów, m. in. Isomap i LLE.

Projekt możnaby ulepszyć korzystając z implementacji LLE/Isomap opartej o wydajną reprezentację wektorów, np. korzystając z biblioteki [breeze](https://github.com/scalanlp/breeze) lub [nd4j](http://nd4j.org/).

Narzędzia topologii obliczeniowej z javaplexa korzystają z kompleksów Vietorisa-Ripsa (niektóre z ulepszonych wersji), którego konstrukcja ma wykładniczą złożoność obliczeniową względem ilości punktów. Istnieją pewne sposoby konstrukcji kompleksów, tzw. $$\alpha$$-kompleksów, o wielomianowej złożoności. Poza tym istnieją metody oparte na dyskretnej teorii Morse'a, które służą upraszczaniu kompleksów. Niestety większość bibliotek do TDA nie zawiera tych metod, a zawierające je biblioteki napisane są w C++, co nastręcza wielu problemów w przejściu od obliczeń do wyników prezentowalnych w notebookach.

Kody kreskowe są problematyczne jeśli chodzi o precyzyjne określenie podobieństwa trwałych homologii. Co prawda na kodzie kreskowym ustalonego wymiaru jest określona metryka 'bottleneck', ale nietrywialne jest pytanie, jak należy składać te metryki dla różnych wymiarów, oraz jak usuwać szum (krótkie kody).

## Źródła

Boissonnat, Chazal, Yvinec [Geometric and Topological Inference](http://geometrica.saclay.inria.fr/team/Fred.Chazal/papers/CGLcourseNotes/main.pdf)

Adams, Tausz [Javplex tutorial](http://www.math.colostate.edu/~adams/research/javaplex_tutorial.pdf)

Edelsbrunner, Harer **Computational Topology: An Introduction**

