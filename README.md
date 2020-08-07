# Tech Challenge

This is a tech challenge for the Billforward.

# Run the application

## Maven run

    mvn exec:java -Dexec.mainClass=com.billforward.battleship.BattleShipGameRunner -Dexec.args="c:\dev\input.txt c:\dev\output.txt"

    
## Build jar and run

    #Create jar in the target folder
    mvn clean install
    
    #Run jar with arguments
    java -jar target\battleship-1.0-SNAPSHOT.jar c:\dev\input.txt c:\dev\output.txt

