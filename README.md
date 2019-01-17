# Text Match Engine
This is an exercise for Schibsted

## Previous requirements
* Java 8 (1.8.0_181)
* Maven (apache-maven-3.5.3)

## How to build
* Run command ```mvn clean package```

## How to run
* Run command ```java -jar ./target/TextMatchEngine.jar com.schibsted.engine.TextMatchEngine ${directoryContainingTextFiles}```
    * Example: ``` java -jar ./target/TextMatchEngine.jar com.schibsted.engine.TextMatchMain ./src/test/java/resources/ ```

## Example
```AR0FVFWM17QHV2J:match-text-engine feguerra (master)$java -jar ./target/TextMatchEngine.jar com.schibsted.engine.TextMatchMain ./src/test/java/resources/
Reading on directory ./src/test/java/resources/
4 files read in directory ./src/test/java/resources/
search> hola
test1.txt : 100,00%
test3.txt : 100,00%
test2.txt : 0,00%
test4.txt : 0,00%
search> hola chau
test3.txt : 100,00%
test1.txt : 50,00%
test2.txt : 50,00%
test4.txt : 0,00%
search> hola chau jaja
test3.txt : 66,67%
test1.txt : 33,33%
test2.txt : 33,33%
test4.txt : 0,00%
search> :quit
Bye!
```

## Author
fernandowguerra@gmail.com
