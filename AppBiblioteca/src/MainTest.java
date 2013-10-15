import java.util.ArrayList;
import java.util.Vector;

import control.Biblioteca;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Biblioteca bi = new Biblioteca();
		ArrayList<String> novoUsuario;
		System.out.println("incluirNovoUsuario --------------------------------");
		for (int i= 0;i<20;i++){
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
		
		System.out.println("pesquisarUsuário -----------------------------");
		for (int i= 0;i<20;i++){
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
		
		System.out.println("alteraUsuário -----------------------------");
		for (int i= 0;i<20;i++){
			ArrayList<String> alteraUsuario = new ArrayList<String>();
			alteraUsuario.add(String.valueOf(i));
			alteraUsuario.add("nomeAlterado"+i);
			alteraUsuario.add("enderecoAlterado"+i);
			try {
				bi.alterarUmUsuario(alteraUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarUsuárioAlterado -----------------------------");
		for (int i= 0;i<20;i++){
			String nome = new String("nomeAlterado"+i);
			try {
				ArrayList<String> ret = bi.pesquisarUsuário(nome);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("excluirUsuário -----------------------------");
		for (int i= 10;i<20;i++){
			String nome = new String("nomeAlterado"+i);
			try {
				bi.excluirUmUsuario(nome);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarUsuárioExcluido -----------------------------");
		for (int i= 0;i<20;i++){
			String nome = new String("nomeAlterado"+i);
			try {
				ArrayList<String> ret = bi.pesquisarUsuário(nome);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println("Não encontrado = "+nome);
			}
		}
		
		ArrayList<String> novoLivro;
		System.out.println("incluirNovoLivro --------------------------------");
		for (int i= 0;i<20;i++){
			novoLivro = new ArrayList<String>();
			novoLivro.add(String.valueOf(i));
			novoLivro.add("title"+i);
			novoLivro.add("publisher"+i);
			novoLivro.add("author,"+i);
			try {
				bi.incluirNovoLivro(novoLivro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarLivro -----------------------------");
		for (int i= 0;i<20;i++){
			String isbn = new String(String.valueOf(i));
			try {
				ArrayList<String> ret = bi.pesquisarLivros(isbn);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("alteraLivro -----------------------------");
		for (int i= 0;i<20;i++){
			ArrayList<String> alterarLivro = new ArrayList<String>();
			alterarLivro.add(String.valueOf(i));
			alterarLivro.add("titleAlterado"+i);
			alterarLivro.add("publisherAlterado"+i);
			alterarLivro.add("authorAlterado,"+i);
			try {
				bi.alterarUmLivro(alterarLivro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarLivroAlterado -----------------------------");
		for (int i= 0;i<20;i++){
			String isbn = new String(String.valueOf(i));
			try {
				ArrayList<String> ret = bi.pesquisarLivros(isbn);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("excluirLivro -----------------------------");
		for (int i= 10;i<20;i++){
			String isbn = new String(String.valueOf(i));
			try {
				bi.excluirUmLivro(isbn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarUsuárioExcluido -----------------------------");
		for (int i= 0;i<20;i++){
			String isbn = new String(String.valueOf(i));
			try {
				ArrayList<String> ret = bi.pesquisarLivros(isbn);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println("Não encontrado isbn = "+isbn);
			}
		}
	
		Vector<String[]> EmprestarLivro;
		System.out.println("EmprestarLivro --------------------------------");
		EmprestarLivro = new Vector<String[]>();
		for (int i= 0;i<20;i++){
			String[] dado = {String.valueOf(i),"nome"+i,String.valueOf(i)+String.valueOf(i),"title"+i,"15/10/2013,"+i};
			EmprestarLivro.add(dado);
			try {
				bi.emprestarLivros(EmprestarLivro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarLivroEmprestados -----------------------------");
		for (int i= 0;i<20;i++){
			String isbn = new String(String.valueOf(i)+String.valueOf(i));
			try {
				ArrayList<String> ret = bi.pesquisarLivroEmprestado(isbn);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println("Não encontrado isbn = "+isbn);
			}
		}
		
		Vector<String[]> DevolverLivro;
		System.out.println("DevolverLivro --------------------------------");
		DevolverLivro = new Vector<String[]>();
		for (int i= 10;i<20;i++){
			String[] dado = {String.valueOf(i),"nome"+i,String.valueOf(i)+String.valueOf(i),"title"+i,"15/10/2013,"+i};
			DevolverLivro.add(dado);
			try {
				bi.devolverLivros(DevolverLivro);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("pesquisarLivroDevolvidos -----------------------------");
		for (int i= 0;i<20;i++){
			String isbn = new String(String.valueOf(i)+String.valueOf(i));
			try {
				ArrayList<String> ret = bi.pesquisarLivroEmprestado(isbn);
				for(String str:ret){
					System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println("Não encontrado isbn = "+isbn);
			}
		}
	}
	

}
