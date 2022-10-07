# GameOfLife
 The GameOfLife repository is the implementation of the "Game of life" game providing the game functionalitites and present the results in a GUI view.
 
 
## Used tools
The following tools are used to accomplish the goal of this project:
- Code and Version control (Git / Github desktop).
- Java language (Java).
- IntelliJ IDEA Community Edition (IntelliJ).
- Javac (Java compiler)


## Project installation
- First, get/install the latest long time supported version of Java from their [website](https://www.oracle.com/java/technologies/downloads/). The used version of Java sdk is 18 for this project.
- Second, close the repository.


## Starting the program
### Run the program in IntelliJ
1. Create a new maven project in IntelliJ IDE.
2. Under the tab "Run", open "Edit configurations".
3. In the "Program arguments" field, add three values.
3.1 First argument: The grid rows/columns number, (adding 10, means the grid will be 10x10).
3.2 Second argument: The number of the Random cells to start the game with.
3.3 Third argument: The number of the produced generations of the game.
4. Go to GameStartup class.
5. Run the main method.

### Run the program in Console:
1. Go to the path "src/main/java".
2. You will be in the "org folder".
3. Open the command prompt
4. Go to the same path as in step "1" (<project-folder>/src/main/java).
5. Run the command `javac org\peAccounting\startup\GameStartup.java`
6. After the compilation is succeded, run the following command `java org\peAccounting\startup\GameStartup.java <gridSize> <RandomNumbers> <numberOfGenerations>` where `gridSize` will be a number deciding the grid size (10 = 10x10), `RandomNumbers` is the number of the randomized cells, and `numberOfGenerations` is the number of generation will be produced.
 7. The GUI view will pop up presenting the results.
