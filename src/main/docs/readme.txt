#DPO - Adventura
#Dominik Veselý (Veseldom) cvičení L 12:45

# Jak Spustit
./adventure - spusti konzolovou verzi hry ovladanou pres terminal
./test - spusti automatickou verzi hry s uspesnym koncem
./failtest - spusti automatickou verzi hry se spatnym koncem

# Obsah balicku

*.sh - spousti aplikaci
pom.xml - maven soubor
/src/main - kompletni zdrojove kody k aplikaci vcetne assetu pro maven
/src/test - unit testy pouzite behem vývoje
/diagrams/ - doprovodné diagramy k mému návrhu

# Návrh a Popis

Z jednotlivých grafů ve složce diagrams je vidět o jakých třídách mluvím. Moje adventura je velice komplexní a rozšiřitelná. Základem celé hry jsou ukoly které se musí splnit díky rozsaáhlým možnostem ukolů umožnuje hra opravdu spoustu variací a možností jak ji hrát 

* TalkQuest - ukol pri kterem se musi promluvit s osobou
* DestinationQuest - ukol pri kterem se musi dojit do urcite lokace
* UseQuest- Podtřída DestinationQuestu musi se pouzit nejaky predmet (v urcite lokaci)
* GatherQuest - ukolem je sebrat x kusů přemětu y
* Deliverable - to samé co u Gather ale musíme poté promluvit s osobout

Díky těmto questům jsme schopni vytvořit opravdu velký příběh a k tomu nám pomůžu příkazy (jejich názvy jsou dost samovysvětlující a jako argument berou kontext)

pick,drop,go,talk,use

K ověření toho jestli osoba mluví, či obejt jde zvednout používám Behaviours a jejich implementace. 

Hra se nenačítá z žádného souboru (nebylo vyžadováno) a zatím je vytvořena pomocí Gamebuilderu. 

Aplikace je relativně komplexní a dost pravděpodobně za hranicemi zadání, ale chtěl jsem to udělat pořádně a myslím, že s komplexitou pomohou přiložené diagramy a jednoduchy kod

Přibalil jsem zdrojové kódy rovnou ke hre abyste mohl zkontrolovat / pripadne upravit atd.

# Použíté technologie a vzory

Snažil jsem se dodržovat všechny správné návyky OOP 
Využívám co nejvíce výhod OOP (dedičnost, polymorfismus, zapouzdření) viz diagramy a kod

# vzory (na které si takto vzpomenu)

* Command Pattern - pro jednotlivé příkazy
* Factory Pattern - vrací příkazy na základě vstupu
* Singleton Pattern - Využitý pro facady a Hru 
* Strategy Pattern - využit pro jednotlivá chování (Talkable...)
* Facade Pattern - není tolik využitý ale spíše připravený pro rozšíření
* MVC Pattern - pro celou strukturu aplikace
* Builder Pattern - pro vytvoření hotové hry

A to je asi tak vše co mě napadá. 

# Unit Testy 

/src/test/java - Unit testy z hlavního adresare zpustime "mvn test"

# Statistiky projektu

-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Java                            43            524            295           1274
XML                              1              1              0             46
Bourne Shell                     4              4              1             20
-------------------------------------------------------------------------------
SUM:                            48            529            296           1340
-------------------------------------------------------------------------------










