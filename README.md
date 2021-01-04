![Test](https://github.com/Gruppe-21/21_final/workflows/test/badge.svg)

```java
 * @author Hildibjørg Didriksen,
 * @author Marcus Rappenborg Kjærsgaard
 * @author Frederik Lundsbjerg
 * @author Tobias Nyholm Maneschijn
 * @author Rasmus Nylander
 * @author Troels Christoffersen
 * @author Gülsen
```

# :moneybag: CDIO del 3 :money_with_wings:
Dette er vores repo til del 3 af CDIO :)

## Trello boards
[<img src="https://www.shareicon.net/data/2016/10/18/844179_trello-icon_512x512.png" width="25"/> Agile Board](https://trello.com/b/xdOw33v4)
[<img src="https://cdn.iconscout.com/icon/free/png-512/trello-6-569395.png" width="28"/> Rapport Board](https://trello.com/b/qTeQJrhg)

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
