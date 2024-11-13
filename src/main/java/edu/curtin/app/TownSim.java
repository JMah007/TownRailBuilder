package edu.curtin.app;

import edu.curtin.app.observer.OutputToFile;
import java.io.*;

public class TownSim
{
    public static void main(String[] args)
    {
        TownRailManager environment = new TownRailManager();
        OutputToFile observer1 = new OutputToFile();
        int dayNum = 0;

        //Add observer to obersver list
        environment.addObserver(observer1);

        TownsInput inp = new TownsInput();
          /*TownsInput inp = new TownsInput(123);  // Seed for the random number generator
         inp.setErrorProbability(0.5);*/

        try
        {
            while(System.in.available() == 0) // Each loop here generates a new day 
            {
                
                // Start of the day 
                System.out.println("---");
                System.out.println("Day " + dayNum + ":\n");

                // Do all the usual non message related daily updates
                environment.produceGoodsInTowns();
                environment.incrementAllRailDays();
                environment.transportAllGoodsInTown();
                
                String msg = inp.nextMessage();
                while(msg != null) // This loop generates all the tasks for the day
                {
                    
                    
                    if(environment.validateMessage(msg)){
                        System.out.println(msg);
                        environment.carryOutMessage(msg); // Do message related update

                    }
                    msg = inp.nextMessage();
                }

                environment.displayAllInfo();
                dayNum++;

                // Wait 1 second
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    throw new AssertionError(e);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error reading user input");
        }
    }
}