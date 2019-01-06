# Online Library


## Ce face

- Scopul aplicatiei este de a gestiona cartile dintr-o biblioteca
- ofera posibilitatea unui utilizator de a imprumita sau de a descarca o carte valabila in baza de date a bibliotecii 
- Un utilizator poate sa isi creeze un cont
- acest cont dadui acces la baza de date a bibliotecii
- Dupa crearea unui cont si autentificarea unui utilizator
- acesta va vedea lista cu cartile bibliotecii intr-un tabel
- cartile putand fi sortate in ordine alfabetica si cautate dupa nume prin intermediul un textfield. 
- Campurile tabelului contin titlul, autorul, si numarul de carti disponibile in biblioteca
- Prin selectarea unui rand din tabel, utilizatorul va deschide pagina de detalii a cartii selectate, 
care va contine pe langa titlu, autor si cantitate si descrierea cartii, 
un buton de download a cartii in caz ca exista in format pdf, 
un buton de imprumut pentru cartea fizica sau un buton de returnare in caz ca utilizatorul a imprumutat-o deja
- Paginile afisate dupa autentificare contin un meniu pentru ca utilizatorul sa poata selecta pagina cu tabelul 
cartilor sau o pagina personala unde poate vedea un tabel cu cartile ce le-a imprumutat
- Pe aceasta pagina utilizatorul poate returna cartile
-Pe fiecare pagina mai exista si un buton de logout, ca utilizatorul sa se poata intoarce la pagina de login

##Tehnologii
- Thymeleaf pentru partea de view
- spring pentru partea de backend
- hibernate pentru persistenta si legatura cu baza de date 
- mysql pentru baza de date
- Aplicatia va rula pe serverul de aplicatii Tomcat.


