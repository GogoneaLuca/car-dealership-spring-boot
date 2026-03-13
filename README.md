# EliteAuto - Dealership Management System

O aplicație web full-stack, de nivel enterprise, dezvoltată pentru gestionarea completă a unui parc auto: inventar vehicule, gestiunea angajaților și procesarea tranzacțiilor financiare.

##  Tehnologii Utilizate
* **Backend:** Java 21, Spring Boot 3 (Spring Web, Spring Data JPA)
* **Bază de Date:** Microsoft SQL Server (Găzduită în **Microsoft Azure Cloud**)
* **Frontend:** Thymeleaf, Bootstrap 5, HTML5/CSS3 (Design modern, UI/UX premium)
* **Arhitectură:** MVC (Model-View-Controller)

##  Funcționalități Principale & Decizii de Arhitectură
* **Arhitectură Hibridă de Date:** Am utilizat ORM (Spring Data JPA / Hibernate) pentru operațiile CRUD standard (adăugare/editare mașini, angajați) pentru viteză și securitate împotriva SQL Injection.
* **SQL Nativ pentru Analytics:** Am implementat rapoarte de business intelligence folosind exclusiv interogări SQL Native complexe (`JOIN`-uri multiple, subcereri imbricate, `GROUP BY`, `HAVING`) pentru a extrage metrici de performanță (ex: Mărci Premium, Top Angajați, Clienți VIP).
* **Gestiune Securizată a Echipei:** Sistem de administrare a personalului cu funcționalități complete, protejat prin validări de sesiune la nivel de Controller.
* **Procesare Tranzacții:** Logică tranzacțională pentru asocierea vehiculelor, clienților și agenților, actualizând automat disponibilitatea stocului.

##  Cum se rulează local
*Notă: În producție, baza de date a rulat pe un server Microsoft Azure. Pentru rularea locală, urmați pașii:*
1. Clonează acest repository.
2. Rulează scriptul `export_baza_date.sql` (inclus în rădăcina proiectului) în instanța ta de SQL Server pentru a genera schema și datele de test.
3. Actualizează fișierul `application.properties` cu user-ul și parola serverului tău local de baze de date.
4. Rulează aplicația (`mvn spring-boot:run`) și accesează `http://localhost:8080`.