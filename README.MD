# Wikipedia Web Scraper

This Java project was created for the exam of "programming methodologies", course of the first year of computer science  @La Sapienza University in Rome.
The code is all in italian, i will release an english version in the near future.

## Request for the exam and scope of the code

The practical exam consist to write a web scraper for Wikipedia and build the genealogical tree for each dinasty of roman emperors listed in this [page](https://it.wikipedia.org/wiki/Imperatori_romani).
Most of the processes need be automatic.
<br/><br/>
## Progetto web scraper 
La repo contiene il codice scritto per l'esame di metodologie di programmazione del corso di informatica @La Sapienza di Roma.

La richiesta dell'esame è quella di creare un web scraper per ricostruire gli alberi genealogici degli imperatori romani presenti in questa [pagina](https://it.wikipedia.org/wiki/Imperatori_romani).
Il processo deve essere il più automatica possibile.

Tutto il codice è stato scritto da me, in totale autonomia.
L'ideazione e creazione è stata frutto di studio, riflessione ed approfondimento degli argomenti proposti durante il corso; in nessuna parte è stato fatto uso di codice proveniente da fonti esterne.
<br/><br/>
### Struttura /src

Nella prima fase ho scritto due librerie: una che permette lo scraping di grossa parte delle informazioni presenti nel box laterale di una qualsiasi pagina di Wikipedia (detto sinottico), la seconda che permette di creare un albero genealogico di qualsiasi classe implementi una interfaccia.

Nella seconda fase ho creato "iRomani", ovvero il programma che effettua lo scraping da Wikipedia e mostra gli alberi genealogici delle dinastie/periodo storico degli imperatori.
<br/><br/>
## File README

### 1) Sorgenti:
Tutti i sorgenti del progetto sono all'interno della cartella WikipediaWebScraper. Tutto il codice è stato scritto su Eclipse.


### 2)Tools necessari:
Tutte le librerie ed i tool necessari alla corretta esecuzione del programma sono presenti all'interno della cartella.
Le librerie sono all'interno della cartella WikipediaWebScraper/lib.

Per il corretto funzionamento di Selenium è necessario Chrome Versione 93.0.4577.82.
Tutti i test sono stati effettuati sulla versione 93.0.4577.82 (Build ufficiale) (a 64 bit).

Per utilizzare una versione precedente di Chrome è necessario sostituire il ChromeDriver all'interno della cartella /res, scaricando quella per la propria versione dal sito https://chromedriver.chromium.org/downloads ma non è garantito il corretto funzionamento.

Il codice è stato scritto con Java 15

### 3) Lancio del codice:
Il main del programma si trova all'interno della cartella src/iRomaniController/iRomani/iRomaniMain.java


### 4) Sorgenti:
Tutti i sorgenti del progetto sono all'interno della cartella WikipediaWebScraper.


### 5) Documentazione:
La relazione, l'uml delle classi ed il manuale della gui si trovano all'interno della cartella Documentazione.
