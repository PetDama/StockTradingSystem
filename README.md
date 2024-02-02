# StockTradingSystem

**Singleton**
  Am ales sa folosesc patternul Singleton pentru clasele UserManager si AuthenticationManager deoarece acestea gestioneaza starea autentificarii si a utilizatorilor in intreaga aplicatie. Prin asigurarea ca exista o singura instanta a acestor clase, elimin riscul de creare a unor obiecte multiple care ar putea duce la comportamente nedorite in sistem.

**Factory Method**
 Am ales Factory Method pentru crearea actiunilor in cadrul clasei 'StockFactory' - abstractizez procesul de creare a actiunilor si permit subclaselor sa fie instantiate individual si sa implementez diverse tipuri de actiuni.

**Observer**
 Observer este implementat pentru functionalitatea de istoric al tranzactiilor din clasa 'User'. Notific automat componentele aplicatiei despre orice modificare in istoricul tranzactiillor unui user.

**Strategy**
 Patternul Strategy l-am folosit in cadrul clasei StockFactory pentru a permite diverse strategii de creare a actiunilor. Asa, pot extinde functionalitatea de creare a actiunilor prin adaugarea de noi subclase care sa defineasca moduri diferite de creare a actiunilor, in functie de nevoile noastre specifice.

 **MVC Model**
   Desi partial, arhitectura sistemului se bazeaza in mare parte pe modelul MVC. Clasele User si Portfolio pot fi considerate ca modele, gestionand datele si logica de afaceri. Clasa main actinoeaza ca un controler.
