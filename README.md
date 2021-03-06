# Loan Broker
## How to Demo:

Projektet er meget nemt at demo. Nedenfor er en kort vejledning i de forskellige steps:
1. Clone projektet
2. Kør build_deploy.sh -Vent til det er færdigt (Der skal stå "#badassmicrofish (build) ready in...")
3. Kør start_consumers.sh

<p>Projektet er nu kørende lokalt på en Payara Micro server. Alle moduler er blevet deployet, og web-serveren kan findes på "http://localhost:8080"</p>

**For at stoppe serveren igen, lukkes build_deploy vinduet (ctrl-c) og jobbet afsluttes.** 

#### REST-API
Ønsker man at POST'e en ny besked til Api'et kan det gøres via f.eks. Postman:
<br>
http://localhost:8080/loanbroker-receiver/api/quote/send/?ssn=101190-2277&amount=120000&duration=5


#### RabbitMQ
Indtil videre bruger projektet følgende køer i RabbitMq.
<br>
http://datdb.cphbusiness.dk:15672/#/queues/%2F/kkc-receiver
<p>Det er her beskeder bliver oprettet når man kalder REST servicen's POST metode, og det er den samme kø som bliver brugt af loanbroker.enricher til at hente beskeder.</p>
<br>
http://datdb.cphbusiness.dk:15672/#/queues/%2F/kkc-enricher-credit
<br>
<p>Denne kø som bliver brugt af loanbroker.enricher.credit, til at sende de berigede beskeder videre til.</p>
<br>
http://datdb.cphbusiness.dk:15672/#/queues/%2F/kkc-enricher-bank
<br>
<p>Denne kø som bliver brugt af loanbroker.enricher.bank til at sende de berigede beskeder videre til.</p>


#### Yderligere beskrivelse af setup kommer...


## Beskrivelse af de forskellige moduler:

### systemintegration.loanbroker
Dette modul kan betegnes som hoved-modulet, i maven kaldt "Parent projekt". Formålet med dette er at vi kan samle de dependencies (afhægigheder), som bruges af alle moduler, ét sted og dermed undgå, at hvert enkelt modul skal hente hente de samme jar filer. Vi kan på denne måde styre vores systems dependencies centralt, så hvis vi ønsker at bruge en anden version af en bestemt dependency, skal det kun ændres ét sted. Hvert modul har dog stadig sin egen pom.xml, hvilket gør det muligt at hente modul specifikke dependencies til det projekt, hvis det er nødvendigt.  
<br>
En anden fordel ved at have et hoved-modul, er at vi blot behøver at kører vores "build" kommando én gang, og så sørger maven for at bygge det ene projekt, samt **alle** under modulerne. *INDSÆT sreen dump* 
<br><br>
Projektet består blot af en pom.xml fil og et bash script. Pom filen håndtere blandt andet:
1. Properties
2. Dependencies
3. Modules

<br>
Bash scriptet bruges til at bygge hoved-modulet og alle under modulerne. (Når dette script har kørt, bliver der genereret en "Über jar" under mappen *systemintegration.loanbroker\loanbroker-receiver\target*. Ved eksekvering af denne, opstartes en Payara micro server lokalt, hvor alle moduler er deployet og kan tilgåes på localhost:8080.  


### systemintegration.endpoint
Modulet er tiltænkt at fungere som en form for klient til de steder i vores system, som har behov for at bruge RabbitMQ køerne. Formålet med dette modul er at, hvis du enten er "producer" eller "consumer" er koden for at forbinde til en kø den samme, og derfor kan vi generalisere det i denne klasse. 
<br><br>
Modulet indeholder tre klasser:
1. Endpoint - Er selve kø'en og forbindelsen til denne
2. Producer - Er klassen som bruges for at tilføje beskeder på kø'en
3. Consumer - Er klassen som bruges til at læse fra køen

### systemintegration.receiver
Modulet er loanbroker systemets modtager modul, som har til formål at modtage request's fra klienten. Receiveren sørger for at sende de modtagne request fra klienten til besked systemets kø.
<br>
Klasen importere endpoint modulet, som receiveren bruger til at initializere en ny instans af producer klassen, og derefter sender den et byte [] til produceren's sendMessageBasic() metode.


## Ordliste:
Når vi bruger betegenelsen **modul**, mener vi: Den enkelte komponent i vores system. Dvs. hvert enkel maven projekt, f.eks. "loanbroker-endpoint" betegner vi som et modul. I maven er et projekt og et modul i princippet det samme, men med en forskel: Når man opretter et modul, skal man angive hvilket "parent projekt" det tilhører.
