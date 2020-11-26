![Test](https://github.com/Gruppe-21/21_del2/workflows/test/badge.svg)

```java
 * @author Hildibjørg Didriksen,
 * @author Marcus Rappenborg Kjærsgaard
 * @author Frederik Lundsbjerg
 * @author Tobias Nyholm Maneschijn
 * @author Rasmus Nylander
 * @author Troels Christoffersen
```

# CDIO del 3
Dette er vores repo til del 3 af CDIO :)


## Project board (Code only)
https://github.com/Gruppe-21/21_del3/projects/2?fullscreen=true

## Trello boards
[Agile Board](https://trello.com/b/5o6evYm7)
[Rapport Board](https://trello.com/b/Ndx97ZVg)

## Overleaf Link + Git Repository
- https://github.com/Gruppe-21/CDIO-3-Rapport
- https://www.overleaf.com/project/5fa03f64efe54a000131cc69 


## Localisation
Adding new languages are done in file lang.xml in the Maven resources directory.
```xml 
    <locales>
        <locale lang="en_US" name="English">
            <sentence label="sentence">This is a sentence.</sentence>
        </locale>
    </locales>
```

### using in code
#### setting the locale (ex. to "en_US")
```java 
Localisation.getInstance().setCurrentLocale(String locale)
```

#### Getting a string in the current language
```java 
Localisation.getInstance().getStringValue(String label)
```

#### Getting all the locales(languages) as a string array
```java 
Localisation.getInstance().getAllLocales()
```


## Customising the board
The squares in the game board can be customised in resources/boards/main_board.xml. 
Available squares are:
- StartSquare
- PropertySquare
- GoToPrisonSquare
- PrisonSquare
- ChanceSquare
- FreeParkingSquare
```xml 
    <board>
        <StartSquare label="go"/>
        <PrisonSquare/>
        <GoToPrisonSquare/>
        <FreeParkingSquare/>
        <ChanceSquare/>
        <PropertySquare label="burgerjoint" price="1" color="green"/>
    </board>
```
### Loading boards
#### Squares(a board) can be loaded with
```java 
 BoardLoader.loadBoard("main_board");
```
