import java.util.ArrayList;

import control.Biblioteca;
import model.ClientRegister;
import model.Database;
import model.Register;
import model.TableType;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Biblioteca bi = new Biblioteca();
		ArrayList<String> novoUsuario = new ArrayList<String>();
		for (int i= 1;i<20;i++){
			novoUsuario = new ArrayList<String>();
			novoUsuario.add(String.valueOf(i));
			novoUsuario.add("nome"+i);
			novoUsuario.add("endereco"+i);
			try {
				bi.incluirNovoUsuario(novoUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i= 1;i<20;i++){
			String nome = new String("nome"+i);
			try {
				ArrayList<String> ret = bi.pesquisarUsuário(nome);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}
