# Loan Broker

## Beskrivelse af de forskellige moduler:

### systemintegration.loanbroker
Dette modul kan betegnes som hoved-modulet, i maven kaldt "Parent projekt". Formålet med dette er at vi kan samle de dependencies (afhægigheder), som bruges af alle moduler, ét sted og dermed undgå, at hvert enkelt modul skal hente hente de samme jar filer. Vi kan på denne måde styre vores systems dependencies centralt, så hvis vi ønsker at bruge en anden version af en bestemt dependency, skal det kun ændres ét sted. 
<br>
En anden fordel ved at have et hoved-modul, er at vi blot behøver at kører vores "build" kommando én gang, og så sørger maven for at bygge det ene projekt, samt **alle** under modulerne. *INDSÆT sreen dump* 
<br>
Projektet består blot af en pom.xml fil, som håndtere blandt andet:
1. Properties - 



Om du er en producent eller en forbruger, er koden for at forbinde til en kø det samme, derfor kan vi generalisere det i denne klasse.


## Ordliste:
Når vi bruger betegenelsen **modul**, mener vi: Den enkelte komponent i vores system. Dvs. hvert enkel maven projekt, f.eks. "loanbroker-endpoint" betegner vi som et modul. I maven er et projekt og et modul i princippet det samme, men med en forskel: Når man opretter et modul, skal man angive hvilket "parent projekt" det tilhører.
