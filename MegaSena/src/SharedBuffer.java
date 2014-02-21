// Fig 23.20: SharedBufferTest2.java
// Aplicativo mostra duas threads que manipulam um buffer sincronizado.

public class SharedBuffer
{
   public static void main( String[] args )
   {

      // cria SynchronizedBuffer para armazenar ints
      Buffer compartilhado = new SynchronizedBuffer();

      Producer p = new Producer (compartilhado);
      Consumer c = new Consumer (compartilhado);

      System.out.printf( "%-40s%s\t\t%s\n%-40s%s\n\n", "Operacao", 
         "Buffer", "Ocupado", "---------", "------\t\t--------" );

      Thread t1 = new Thread(p);
      Thread t2 = new Thread(c);
      t1.start();
      t2.start();

   } // fim main
} // fim classe SharedBufferTest2



