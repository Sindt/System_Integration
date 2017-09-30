# Loan Broker

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
Bash scriptet bruges til at bygge hoved-modulet og alle under modulerne. (Når dette script har kørt, bliver der genereret en "Über jar" under mappen *systemintegration.loanbroker\loanbroker-receiver\target*. Ved eksekvering af denne, opstartes en Payara micro server, hvor alle moduler er deployet og kan tilgåes på localhost:8080.  


### systemintegration.endpoint
Modulet er tiltænkt at fungere som en form for klient til de steder i vores system, som har behov for at bruge RabbitMQ køerne. Formålet med dette modul er at, hvis du enten er "producer" eller "consumer" er koden for at forbinde til en kø deb samme, og derfor kan vi generalisere det i denne klasse. 
<br><br>
Modulet indeholder tre klasser:
1. Endpoint - Er selve kø'en og forbindelsen til denne
2. Producer
3. Consumer


## Ordliste:
Når vi bruger betegenelsen **modul**, mener vi: Den enkelte komponent i vores system. Dvs. hvert enkel maven projekt, f.eks. "loanbroker-endpoint" betegner vi som et modul. I maven er et projekt og et modul i princippet det samme, men med en forskel: Når man opretter et modul, skal man angive hvilket "parent projekt" det tilhører.
