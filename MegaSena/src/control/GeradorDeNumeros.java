package control;

import java.util.Vector;

public class GeradorDeNumeros extends Thread {
	Vector<int[]> buffer = new Vector<>();
	public void run() {
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
								if (setBuffer(dez)){
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
	}


private boolean setBuffer(int[] dez){
	System.out.println(dez[0]+" "+dez[1]+" "+dez[2]+" "+dez[3]+" "+dez[4]+" "+dez[5]);
	return false;
}
}