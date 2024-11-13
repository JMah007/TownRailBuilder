# TownRailBuilder

Base Java Project
=================

This is a [Gradle][]-based Java project structure. Provided you have the [OpenJDK][] installed, the `gradlew` script will take care of all other dependencies.

[Java]: https://docs.oracle.com/javase/tutorial/
[Gradle]: https://gradle.org/
[OpenJDK]: https://adoptium.net/temurin/releases/


## Running

To run the code (for debugging purposes), invoke the `gradlew` script with a `run` argument:

```
./gradlew run
```

If you run into permission problems:

```
bash gradlew run
```


## File Description
simoutput DOT file is an output file that can be converted into a graph. It contains all the towns and rails that exist where the first list represents the towns which which can be converted into nodes. The second list contains the railways connecting the towns and the format determines what type of rail it is (double, single or constructing). To convert this file into a graph using GraphViz, after running the program and stopping it, enter this command "neato simoutput.dot -Tpdf -o simoutput.pdf". A file simoutput of type Avast HTML Document will be created which you can open and visualise the towns and rails as a graph.

Assignment2OOSE.drawio and RailStateChart.drawio are the UML and state diagrams for the program

