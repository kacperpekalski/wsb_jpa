﻿Laboratorium III - JPQL

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie architektury warstwowej i testow z Laboratorium II !

Uzupelnij plik data.sql o dane niezbedne do realizacji nastepujacych zapytan:
1. [ x ] Znajdz pacjentow po nazwisku
2. [ x ] Znajdz wszystkie wizyty pacjenta po jego ID
3. [ x ] Znajdz pacjentow ktorzy mieli wiecej niz X wizyt (X jest parametrem wejsciowym)
4. [ x ] Znajdz pacjentow po dodanym przez Ciebie polu - nie wyszukuj wprost po wartosci, uzyj zapytania typu wieksze/mniejsze/pozniej/wczesniej/zawiera, w zaleznosci od wybranego typu zmiennej.

Napisz testy do zapytan w nastepujacej formie:
1. [ x ] do zapytania nr 1 - test DAO
2. [ x ] do zapytania nr 2 - test serwisu
3. [  ] do zapytania nr 3 - test DAO
4. [  ] do zapytania nr 4 - test DAO

   [  ] Do wybranej encji dodaj wersjonowanie, oraz napisz test (w DAO) sprawdzajacy rownolegla modyfikacje (OptimisticLock)