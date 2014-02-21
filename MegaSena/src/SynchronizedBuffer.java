// Fig. 23.19: SynchronizedBuffer.java
// SynchronizedBuffer sincroniza acesso a um unico inteiro compartilhado.

public class SynchronizedBuffer implements Buffer
{
   private int buffer = -1; // compartilhado pelas threads producer e consumer
   private boolean occupied = false; // contagem de buffers ocupados
   
   // coloca o valor no buffer
   public synchronized void set( int value )
   {
      // enquanto nao houver posicoes vazias, coloca a thread em estado de espera
      while ( occupied ) 
      {
         // envia informacoes de thread e de buffer para a saida, entao espera
         try 
         {
            System.out.println( "Produtor tenta escrever." );
            displayState( "Buffer cheio. Produtor espera." );
            wait();
         } // fim try
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // fim catch
      } // fim while
        
      buffer = value; // configura novo valor de buffer
        
      // indica que a produtora nao pode armazenar outro valor
      // ate que a consumidora recupere valor atual de buffer
      occupied = true;
        
      displayState( "Produtor escreve " + buffer );
      
      notify(); // instrui a thread em espera a entrar no estado executavel
   } // fim metodo set; libera o bloqueio em SynchronizedBuffer 
    
   // retorna valor do buffer
   public synchronized int get()
   {
      // enquanto os dados nao sao lidos, coloca thread em estado de espera
      while ( !occupied )
      {
         // envia informacoes de thread e de buffer para a saida, entao espera
         try 
         {
            System.out.println( "Consumidor tenta ler." );
            displayState( "Buffer vazio. Consumidor espera." );
            wait();
         } // fim try
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // fim catch
      } // fim while

      // indica que a produtora pode armazenar outro valor 
      // porque a consumidora acabou de recuperar o valor do buffer
      occupied = false;

      int readValue = buffer; // armazena valor em buffer
      displayState( "Consumidor le " + readValue );
      
      notify(); // instrui a thread em espera a entrar no estado executave

      return readValue;
   } // fim metodo get; libera bloqueio em SynchronizedBuffer 
    
   // exibe operacao atual e o estado de buffer
   public void displayState( String operation )
   {
      System.out.printf( "%-40s%d\t\t%b\n\n", operation, buffer, 
         occupied );
   } // fim metodo displayState
} // fim classe SynchronizedBuffer
