Laboratorium II - Architektura warstwowa, EntityManager oraz testy

Uwaga! Do wykonania zadan konieczne jest zaimplementowanie modelu obiektowo-relacyjnego bazy z Laboratorium I !

1. [ x ] Korzystajac z przykladowego kodu dla encji AddressEntity utworz warstwy dostepu do danych (Repository, Service, mappery, TOsy, opcjonalnie RESTy - dla chetnych) dla encji PatientEntity. Spelnione maja byc nasteoujace wymagania:
   - [ x ] TO pacjenta ma miec liste wizyt ktore sie odbyly
   - [ x ] kazda wizyta ma miec informacje o czasie (daty), imie i nazwisko lekarza oraz liste typow (z encji MedicalTreatment)
   - [ x ] rozszerz encje PatientEntity o jedno dowolne pole innego typu niz String, odwzoruj je w TO.

2. [ x ] Korzystajac z przykladowych insertow w pliku data.sql uzupelnij encje pacjenta, doktorow oraz wizyt danymi testowymi

3. [ x ] Korzystajac z przykladowych testow dla encji Address, napisz testy do serwisu (uwaga! serwisu, nie DAO!) pacjenta:
   - [ x ] test usuwajacy pacjenta sprawdza czy usuniete zostaly wszystkie wizyty (kaskada) i czy nie zostali usunieci doktorzy
   - [ x ] pobranie pacjenta po ID powinno zwrocic strukture TO-sow odpowiadajaca wczesniejszym zalozeniom. W asercjach sprawdz poprawnosc odczytu dodanego przez Ciebie pola z punktu pierwszego

4. [ x ] Dodaj metode w PatientDao, ktora na podstawie parametrow wejsciowych:
   - [ x ] ID pacjenta, ID doktora, data wizyty, opis wizyty, 
         utworzy nowa encje wizyty i doda ja do pacjenta w jednym wywolaniu - kaskadowy update pacjenta (merge).
   - [ x ] Napisz test do tej metody (Dao)
