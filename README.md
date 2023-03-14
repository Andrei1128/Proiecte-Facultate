# Proiecte realizate in cadrul facultatii
## E-meditatii
Este un program implementant in  java ce permite creare de conturi pentru elevi respectiv profesori cu scopul de a realiza o conexiune intre acestia pentru stabilirea datelor necesare unei meditatii.

Programul permite : 
- modificarea profilului (nume,prenume,poza,materii preferate/predate)
- trimiterea de cereri de meditatii profesorilor
- acceptarea/refuzarea cererilor in cazul profesorilor
- anularea intalnirilor 
- vizualizarea intalnirilor
- acordarea de review in cazul terminarii meditatiei
- printarea reviewurilor primite in cazul profesorilor

## Proiecte Geometrie Computationala
*Proiectele sunt implementate in java si dispun de GUI si de un canvas pentru reprezentarea vizuala a structurilor si rezultatelor acestora.

*Pasii algoritmilor sunt prezentati pe scurt.
### PSLG (planar straight line-graph)
Programul permite determinarea fetei in care se afla un punct intr-o structura conexa de tip PSLG utilizand metoda lespezilor.

#### Pasii algoritmului :
1. ordonam varfurile de jos in sus si le renumerotam
2. reorietam laturile a.i. sa fie orientate de jos in sus
3. Pentru fiecare varf vi formez 2 multimi:
- A(i) = laturile ce intra in vi, ordonate in sens direct trigonometric
- B(i) = laturile ce ies din vi, ordonate invers trigonometric
4. pentru i de la 1 la n inlocuim A(i) cu B(i)
5. localizam punctul:
- localizare pe verticala => pct-ul se afla in lespedea L
- localizare pe orizontala in cadrul lespezii => pct-ul se afla in fata X
### Jarvis si Graham
Este un program ce permite alegerea algoritmului utilizat (Jarvis sau Graham) pentru determinarea infasuratorii convexe a structurii formate din punctele date.
#### Pasii algoritmului lui Jarvis:
1. ordonam punctele dupa abscisa
2. luam primul punct P1 si alegem P2 a.i. panta lui P1P2 sa fie maxima
3. se repeta pasul 2 "inaintand" punctele pana cand Pk = An (D(AiP1P2)>0 atunci P2=Ai)
#### Pasii algoritmului lui Graham :
1. se afla si translateaza structura in punctul de greutate
2. ordonam punctele in jurul originii
3. avem o lista dubla inlantuita, se merge prin lista si se sterg punctele cu panta negativa
4. se repeta pasul 3 pana cand lista nu se mai modifica (raman doar punctele ce formeaza frontiera structurii)
### Arbore2D si Limitare
Programul permite alegerea algoritmului utilizat pentru a determina cate puncte se afla intr-un dreptunghi dat de noi.
#### Pasii algoritmului de localizare prin includere-excludere :
1. se creaza 2 liste de puncte ordonate dupa x respectiv y
2. creem o matrice patratica de dim L1+1 pe care o completam astfel:
- pe prima coloana punem 0
- for j = 2, L1+1:
  - cautam elem j-1 din lista L1 si L2 si ii atribuim lui k indexul acestuia
  - for i = 1, k  => M[i][j] = M[i][j-1]
  - for i = k+1, L1+1  => M[i][j] = M[i][j-1]-1
3. nr de puncte in ABCD = QB-QA-QC+QD (QE=M[i][j], i=xE y=yE)
#### Pasii algoritmului de localizare utlizand un arbore bidimensional:
1. se creaza 2 liste sortate dupa x respectiv y
2. Alegem puctul de abcisa medie si ii atribuim taietura "verticala"
3. Acum avem prima lista "impartita" in 2 , elem din stanga abscisei din cele din dreapta
4. Luam punctele din prima jumatate si le punem intr-o noua lista in ordinea din L2
5. Din noua lista luam ordonata medie, ii atribuim taietura "orizontala" si setam punctul de stanga a abscisei medie de mau sus acest punct(in cazul in care noua lista este goala setam punctul de stanga null)
6. Analog se face pentru partea dreapta, pt punctele ce sunt ordonata medie se inlocuieste listele,taieturile
7. Se adauga dreptunghiul si se cauta in arbore punctele ce se afla in interiorul acestuia
## Proiecte Limbaje formale si compilatoare
### Proiectul 1
Simuleaza un automat finit si permite: 
- analizarea unui cuvant(daca este acceptat de automat sau nu)
- verificarea daca automatul este determinist sau nu
- analizarea daca o stare data este accesibila sau nu
### Proiectul 2
Programul simuleaza o gramatica independenta de context (GIC) pe care se pot aplica cele 3 leme de pompare.
### Proiectul 3
Reprezinta algoritmul CYK (algoritm de parsare pentru gramatici independente de context).

Algoritm de programare dinamica pentru a determina daca un sir de caractere apartine limbajului unei gramatici.
## Solitaires
Program implementat in C++ ce reprezinta un joc cu carti de tip solitaires.

Au fost utilizate predominant stive ca colectii de date.

Programul nu dispune de GUI.
## Proiect Assembly
Permite desenarea in oglindita in 4 cadrane utilizand multiple culori.
