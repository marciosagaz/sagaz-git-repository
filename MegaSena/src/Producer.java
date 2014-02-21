// Fig. 23.7: Producer.java
// O método run de Producer armazena os valores de 1 a 10 no Buffer.
import java.util.Random;

public class Producer implements Runnable 
{
   private Buffer buffer;

   // construtor
   public Producer( Buffer buf )
   {
      buffer = buf;
   }

   // armazena os valores de  1 a 10 em sharedLocation
   public void run()
   {
		int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5;
		while (A < 55) { // A,B,C,D,E,F GERAM AS DEZENAS
			B = A + 1;
			while (B < 56) {
				C = B + 1;
				while (C < 57) {
					D = C + 1;
					while (D < 58) {
						E = D + 1;
						while (E < 59) {
							F = E + 1;
							while (F <= 59) {
								int[] dez = new int[6];
								dez[0] = A;
								dez[1] = B;
								dez[2] = C;
								dez[3] = D;
								dez[4] = E;
								dez[5] = F;
								if (buffer.set(dez)) {
									try {
										wait();
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								F++;
							}
							E++;
						}
						D++;
					}
					C++;
				}
				B++;
			}
			A++;
		}
   } // fim método run run
} // fim classe Producer

