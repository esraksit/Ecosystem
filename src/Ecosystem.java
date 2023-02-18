import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;


class Animal
{
	
}

class Fish extends Animal
{
	
}

class Bear extends Animal
{
	
}

public class Ecosystem
{
   public static final int    LENGHT  = 1000;
   public static final int    FISH_NUM  = 100;
   public static final int    BEAR_NUM = 60;
   public static final double MOVE_CHANCE = 0.50; // 50% chance of moving.

   Random random = new Random();

   private Animal[] river;
   private int fishCount = 0;
   private int bearCount = 0;


   public Ecosystem()
   {
      river = new  Animal[LENGHT];

      // Add Fish and Bear to random locations
      while (fishCount < FISH_NUM)
      {
         fishCount += addNewAnimal (new Fish() );
      }

      
      while (bearCount < BEAR_NUM)
      {
         bearCount += addNewAnimal (new Bear() );
      }
   }

   
   private int addNewAnimal(Animal animal)
   {
      int add = 0;

      int totalAnimal = fishCount + bearCount;

      if (totalAnimal < LENGHT)
      {
         
         while (add == 0)
         {
            
            int x = random.nextInt (LENGHT);  
            

            if (river[x] == null)
            {
               river[x] = animal;
               add++;
            }
         }
      }

      return add;
   }

   @Override
   public String toString()
   {
      String str = "";

     
      
         for (int x = 0; x < LENGHT; x++)
         {
            if (river[x] == null)
            {
               str += ".";
            }
            else if (river[x] instanceof Fish)
            {
               str += "F";
            }
            else if (river[x] instanceof Bear)
            {
               str += "B";
            }
         }
         str += "\n";
      

      str += "Fish: " + fishCount + "      Bears: " + bearCount;

      return str;
   }

   public void move ()
   {
      
      
         for (int x = 0; x < LENGHT; x++)
         {
            if (river[x] != null)
            {
               double chance = random.nextDouble();

               if (chance < MOVE_CHANCE)
               {
                  int changeOfLocation = random.nextInt (3) - 1; // -1, 0 , +1
                  

                  int newLocation = x + changeOfLocation;
                  

                  

                  boolean canMove = false;
                  

                  if ((changeOfLocation != 0) && (newLocation >= 0) && (newLocation < LENGHT) )
                  {
                     canMove = true;
                  }		//arrayin dýþýna çýkýyor muyuz

                  

                  if ((canMove == true))
                  {
                     int finalLocation = x;
                    
                     
                     finalLocation = newLocation;

                    


                     if (river[finalLocation] == null) //gitceiðimiz yer boþsa
                     {
                        river[finalLocation] = river[x];
                        river[x]= null;
                     }
                     else if (river[x].getClass() == river[finalLocation].getClass() )//gitceðimiz yer doluysa ama ayný türse
                     {
                        
                        if (river[x].getClass().getName().equals ("Fish") == true)
                           fishCount += addNewAnimal (new Fish() );

                        if (river[x].getClass().getName().equals ("Bear") == true)
                           bearCount += addNewAnimal (new Bear() );

                     }
                     else if (river[x].getClass() != river[finalLocation].getClass() )//gitcrðimiz yerde farklý türse
                     {
                        
                        if (river[x].getClass().getName().equals ("Fish") == true)
                        {
                           if (fishCount > 0)
                           {
                              river[x] = null;
                              fishCount--;
                           }
                        }

                        if (river[finalLocation].getClass().getName().equals ("Fish") == true)
                        {
                           if (fishCount > 0)
                           {
                              river[finalLocation] = river[x];
                              fishCount--;
                           }
                        }
                     }

               }


               }

            }
         }

      

   }

   public void moveAndDisplay()
   {
      System.out.println (this);

      move();
   }

   public static void main (String[] args)
   {
      Ecosystem eco = new Ecosystem();
      Scanner sc  = new Scanner (System.in);

      

      Timer t = new Timer (1000, event -> eco.moveAndDisplay() );
      t.start();

      sc.nextLine();
   }


}
