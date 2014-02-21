/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Livro;
import model.Usuario;
import model.Database;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import model.BookRegister;
import model.ClientRegister;
import model.LoanRegister;
import model.Register;
import model.TableType;

/**
 *
 * @author Rodrigo
 */
public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private HashMap<String,Usuario> emprestimo;
    private Database dt;
    public Biblioteca(){
        livros = new ArrayList<Livro>();
        usuarios = new ArrayList<Usuario>();
        emprestimo =  new HashMap<String, Usuario>();
        dt = new Database();
    }
    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void cadastrarLivros(Livro livro) {
         livros.add(livro);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public ArrayList<String> pesquisarUsuário(String nome){
        //Pesquisa um usuario e retorna um array com os dados do usuario
        //[cpf, nome, endereco, endereço, livroemprestado(sim,nao)]
        ClientRegister rg = new ClientRegister();
        rg.setTo(TableType.NAME, nome);
        ArrayList<Register> list  = dt.searchFor(rg);
        ArrayList<String> returned = null;
        if (!list.isEmpty()){
            
            returned = new ArrayList<String>();
            returned.add(list.get(0).obtainTo(TableType.CPF));
            returned.add(list.get(0).obtainTo(TableType.NAME));
            returned.add(list.get(0).obtainTo(TableType.ADDRESS));
            return returned;   
        } 
        return returned;
    }
    
    public void incluirNovoUsuario(ArrayList<String> novoUsuario) throws Exception{
        //incluir um novo usuario
        //[cpf, nome, endereco]
        Register rg = new ClientRegister();
        rg.setTo(TableType.CPF, novoUsuario.get(0));
        rg.setTo(TableType.NAME, novoUsuario.get(1));
        rg.setTo(TableType.ADDRESS, novoUsuario.get(2));
        if (!dt.addRegister(rg)) {
            throw new Exception("Não foi possível inclui o Novo Usuário!");
        }
    }
    
    public void alterarUmUsuario(ArrayList<String> alteraUsuario) throws Exception{
        //alterar um usuario existente
        //[cpf, nome, endereco]
        Register rg = new ClientRegister();
        rg.setTo(TableType.CPF, alteraUsuario.get(0));
        rg.setTo(TableType.NAME, alteraUsuario.get(1));
        rg.setTo(TableType.ADDRESS, alteraUsuario.get(2));
        if (!dt.removeRegister(rg)) {
            throw new Exception("Não foi possível alterar o Usuário!");
        }
        if (!dt.addRegister(rg)) {
            throw new Exception("Não foi possível alterar o Usuário!");
        }
        
    }
    
    public void excluirUmUsuario(String excluiUsuario) throws Exception{
        //excluir um usuario existente
        Register rg = new ClientRegister();
        rg.setTo(TableType.NAME, excluiUsuario);
        ArrayList<Register> list  = dt.searchFor(rg);
        if (list.isEmpty() || dt.removeRegister(list.get(0))){
           throw new Exception("Não foi possível excluir o Usuário!");         
        }     
    }
    
    public void incluirNovoLivro(ArrayList<String> novoLivro){
    	//cadastrar um novo objeto livro
    	//[ISBN, Titulo, editora, autores]
    	Register rg = new BookRegister();
    	rg.setTo(TableType.ISBN, novoLivro.get(0));
    	rg.setTo(TableType.TITLE, novoLivro.get(1));
    	rg.setTo(TableType.PUBLISHER, novoLivro.get(2));
    	rg.setTo(TableType.AUTHOR, novoLivro.get(3));
    	dt.addRegister(rg);
    }

    public ArrayList<String> pesquisarLivros(String isbn){
        //Pesquisa um livro e retorna um array com os dados do livro
        //[ISBN, Titulo, editora, autores]
        Register rg = new BookRegister();
        rg.setTo(TableType.ISBN, isbn);
        ArrayList<Register> list  = dt.searchFor(rg);
        ArrayList<String> returned = null;
        if (!list.isEmpty()){
            returned = new ArrayList<String>();
            returned.add(list.get(0).obtainTo(TableType.ISBN));
            returned.add(list.get(0).obtainTo(TableType.TITLE));
            returned.add(list.get(0).obtainTo(TableType.PUBLISHER));
            returned.add(list.get(0).obtainTo(TableType.AUTHOR));
            return returned;   
        }        
        return returned;
    }
    
    public void alterarUmLivro(ArrayList<String> alterarLivro){
    	//alterar um objeto livro existente
    	//[ISBN, Titulo, editora, autores]
    	Register rg = new BookRegister();
    	rg.setTo(TableType.ISBN, alterarLivro.get(0));
    	dt.removeRegister(rg);
    	rg.setTo(TableType.TITLE, alterarLivro.get(1));
    	rg.setTo(TableType.PUBLISHER, alterarLivro.get(2));
    	rg.setTo(TableType.AUTHOR, alterarLivro.get(3));
    	dt.addRegister(rg);
    }
    
    public void excluirUmLivro(String isbn){
    	//exlui um objeto livro existente
    	//[ISBN, Titulo, editora, autores]
    	Register rg = new BookRegister();
    	rg.setTo(TableType.ISBN, isbn);
    	dt.removeRegister(rg);
    }
    
    public ArrayList<String> pesquisarLivroEmprestado(String isbn){
        //verifica se o livro está emprestado, caso esteva retorna
        //[cpf, nome, ISBN, titulo, datadevolucao]
        Register rg = new LoanRegister();
        rg.setTo(TableType.ISBN, isbn);
        ArrayList<Register> list  = dt.searchFor(rg);
        ArrayList<String> returned = null;
        if (!list.isEmpty()){
        	returned = new ArrayList<String>();
            returned.add(list.get(0).obtainTo(TableType.CPF));
            returned.add(list.get(0).obtainTo(TableType.NAME));
            returned.add(list.get(0).obtainTo(TableType.ISBN));
            returned.add(list.get(0).obtainTo(TableType.TITLE));
            returned.add(list.get(0).obtainTo(TableType.RETURNDATE));
            return returned;   
        }        
        return returned;
    }
    
    public void emprestarLivros(Vector<String[]> emprestarLivro){
        //revebe uma matriz com [cpf, nome, ISBN, titulo, datadevolucao]
        //para gravar na tabela de emprestimo
        Register rg = new LoanRegister();
        for (String [] data: emprestarLivro){
        	rg.setTo(TableType.CPF, data[0]);
        	rg.setTo(TableType.NAME, data[1]);
        	rg.setTo(TableType.ISBN, data[2]);
        	rg.setTo(TableType.TITLE, data[3]);
        	rg.setTo(TableType.RETURNDATE, data[4]);
        	dt.addRegister(rg);
        }
    }
    
    public void devolverLivros(Vector<String[]>  devolucao){
        //revebe uma matriz com [cpf, nome, ISBN, titulo, datadevolucao]
        //para retirar da tabela de emprestimo
    	Register rg = new LoanRegister();
        for (String [] data: devolucao){
        	rg.setTo(TableType.CPF, data[0]);
        	rg.setTo(TableType.NAME, data[1]);
        	rg.setTo(TableType.ISBN, data[2]);
        	rg.setTo(TableType.TITLE, data[3]);
        	rg.setTo(TableType.RETURNDATE, data[4]);
        	dt.removeRegister(rg);
        }
    }
    
    
    //ainda falta os metodos do cadastro de usuario
    //nao consegui terminar
    //acho que seria melhor passar os objetos pra tela principal e não array
    //como passei qualquer coisa altere
    

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public HashMap<String, Usuario> getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(HashMap<String, Usuario> emprestimo) {
        this.emprestimo = emprestimo;
    }
    
    
}
