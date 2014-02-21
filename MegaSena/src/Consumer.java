// Fig. 23.8: Consumer.java
// O método run de Consumer itera dez vezes lendo um valor do buffer.
import java.util.Random;

public class Consumer implements Runnable 
{ 
   private static Random generator = new Random();
   private Buffer sharedLocation; // referência a objeto compartilhado

   // construtor
   public Consumer( Buffer shared )
   {
      sharedLocation = shared;
   } // fim construtor Consumer 

   // lê o valor do sharedLocation dez vezes e soma os valores
   public void run()
   {
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) 
      {
         // dorme de 0 a 3 segundos, lê o valor do buffer e adiciona a soma
         try 
         {
            Thread.sleep( generator.nextInt( 3000 ) );    
            sum += sharedLocation.get();
         } // fim try
         // se a thread adormecida é interrompida, imprime rastreamento de pilha
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // fim catch
      } // fim for

      System.out.printf( "\n%s %d.\n%s\n", 
         "Consumidor lê valores totalizando", sum, "Terminando Consumidor." );
   } // fim método run
} // fim classe Consumer


