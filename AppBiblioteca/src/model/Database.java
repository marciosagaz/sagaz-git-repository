package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Database {
	private Data<String> keyTabCPF;
	private Data<String> tabCEP;
	private Data<String> tabNumberStreet;
	private Data<String> tabStreet;
	private Data<String> tabNeighborhood;
	private Data<String> tabCyte;
	private Data<String> tabState;
	private Data<String> tabCountry;
	private Data<String> tabName;
	private Data<String> tabAddress;
	private Data<Client> tabClientes;
	private Client client;

	private Data<String> keyTabISBN;
	private Data<String> tabAuthor;
	private Data<String> tabPublished;
	private Data<String> tabTitle;
	private Data<Book> tabBook;
	private Book book;

	private Data<String> keyLoanCPF;
	private Data<String> tabLoanName;
	private Data<String> tabLoanISBN;
	private Data<String> tabLoanTitle;
	private Data<String> tabLoanReturndate;
	private Data<Loan> tabLoan;
	private Loan loan;

	private Integer currentId;
	private Set<Integer> idSet;

	// private Data<String> livrosISBN;

	public Database() {
		tabCEP = new Data<String>();
		tabCyte = new Data<String>();
		keyTabCPF = new Data<String>();
		tabCountry = new Data<String>();
		tabNumberStreet = new Data<String>();
		tabNeighborhood = new Data<String>();
		tabStreet = new Data<String>();
		tabState = new Data<String>();
		tabName = new Data<String>();
		tabAddress = new Data<String>();
		tabClientes = new Data<Client>();

		keyTabISBN = new Data<String>();
		tabAuthor = new Data<String>();
		tabPublished = new Data<String>();
		tabTitle = new Data<String>();
		tabBook = new Data<Book>();

		keyLoanCPF = new Data<String>();
		tabLoanName = new Data<String>();
		tabLoanISBN = new Data<String>();
		tabLoanTitle = new Data<String>();
		tabLoanReturndate = new Data<String>();
		tabLoan = new Data<Loan>();
	}

	public boolean addRegister(Register register) {
		if (register != null) {
			switch (register.getType()) {
			case CLIENT: {
				return addClientRegister((ClientRegister) register);
			}
			case BOOK: {
				return addBookRegister((BookRegister) register);
			}
			case LOAN: {
				return addLoanRegister((LoanRegister) register);
			}
			default:
				return false;
			}
		}
		return false;
	}

	public boolean removeRegister(Register register) {
		if (register != null) {
			switch (register.getType()) {
			case CLIENT: {
				return removeClientRegister((ClientRegister) register);
			}
			case BOOK: {
				return removeBookRegister((BookRegister) register);
			}
			case LOAN: {
				return removeLoanRegister((LoanRegister) register);
			}
			default:
				return false;
			}
		}
		return false;
	}

	public ArrayList<Register> searchFor(Register register) {
		if (register != null) {
			switch (register.getType()) {
			case CLIENT: {
				return searchClient((ClientRegister) register);
			}
			case BOOK: {
				return searchBook((BookRegister) register);
			}
			case LOAN: {
				return searchLoan((LoanRegister) register);
			}
			default:
				return null;
			}
		}
		return null;
	}

	private ArrayList<Register> searchClient(ClientRegister register) {
		idSet = new LinkedHashSet<Integer>(new ArrayList<Integer>());
		merceList(tabCEP.getLinkList(tabCEP.getKey(register
				.obtainTo(TableType.CEP))));
		merceList(tabCyte.getLinkList(tabCyte.getKey(register
				.obtainTo(TableType.CITY))));
		merceList(tabCountry.getLinkList(tabCountry.getKey(register
				.obtainTo(TableType.COUNTRY))));
		merceList(keyTabCPF.getLinkList(keyTabCPF.getKey(register
				.obtainTo(TableType.CPF))));
		merceList(tabName.getLinkList(tabName.getKey(register
				.obtainTo(TableType.NAME))));
		merceList(tabAddress.getLinkList(tabAddress.getKey(register
				.obtainTo(TableType.ADDRESS))));
		merceList(tabNeighborhood.getLinkList(tabNeighborhood.getKey(register
				.obtainTo(TableType.NEIGHBORHOOD))));
		merceList(tabNumberStreet.getLinkList(tabNumberStreet.getKey(register
				.obtainTo(TableType.NUMBER))));
		merceList(tabState.getLinkList(tabState.getKey(register
				.obtainTo(TableType.STATE))));
		merceList(tabStreet.getLinkList(tabStreet.getKey(register
				.obtainTo(TableType.STREET))));
		return createClientRegisterList();
	}

	private ArrayList<Register> searchBook(BookRegister register) {
		idSet = new LinkedHashSet<Integer>(new ArrayList<Integer>());
		merceList(keyTabISBN.getLinkList(keyTabISBN.getKey(register
				.obtainTo(TableType.ISBN))));
		merceList(tabAuthor.getLinkList(tabAuthor.getKey(register
				.obtainTo(TableType.AUTHOR))));
		merceList(tabPublished.getLinkList(tabPublished.getKey(register
				.obtainTo(TableType.PUBLISHER))));
		merceList(tabTitle.getLinkList(tabTitle.getKey(register
				.obtainTo(TableType.TITLE))));
		return createBookRegisterList();
	}

	private ArrayList<Register> searchLoan(LoanRegister register) {
		idSet = new LinkedHashSet<Integer>(new ArrayList<Integer>());
		merceList(keyLoanCPF.getLinkList(keyLoanCPF.getKey(register
				.obtainTo(TableType.CPF))));
		merceList(tabLoanName.getLinkList(tabLoanName.getKey(register
				.obtainTo(TableType.NAME))));
		merceList(tabLoanISBN.getLinkList(tabLoanISBN.getKey(register
				.obtainTo(TableType.ISBN))));
		merceList(tabLoanTitle.getLinkList(tabLoanTitle.getKey(register
				.obtainTo(TableType.TITLE))));
		merceList(tabLoanReturndate.getLinkList(tabLoanReturndate
				.getKey(register.obtainTo(TableType.RETURNDATE))));
		return createLoanRegisterList();
	}

	private void merceList(ArrayList<Integer> linkList) {
		if (linkList != null) {
			idSet.addAll(linkList);
		}
	}

	private ArrayList<Register> createClientRegisterList() {
		ArrayList<Register> regList = new ArrayList<Register>();
		for (Integer cliID : new ArrayList<Integer>(idSet)) {
			client = tabClientes.getContent(cliID);
			Register reg = new ClientRegister();
			reg.setTo(TableType.CEP,
					tabCEP.getContent(client.obtainTo(TableType.CEP)));
			reg.setTo(TableType.CITY,
					tabCyte.getContent(client.obtainTo(TableType.CITY)));
			reg.setTo(TableType.COUNTRY,
					tabCountry.getContent(client.obtainTo(TableType.COUNTRY)));
			reg.setTo(TableType.CPF,
					keyTabCPF.getContent(client.obtainTo(TableType.CPF)));
			reg.setTo(TableType.NAME,
					tabName.getContent(client.obtainTo(TableType.NAME)));
			reg.setTo(TableType.ADDRESS,
					tabAddress.getContent(client.obtainTo(TableType.ADDRESS)));
			reg.setTo(TableType.NEIGHBORHOOD, tabNeighborhood.getContent(client
					.obtainTo(TableType.NEIGHBORHOOD)));
			reg.setTo(TableType.NUMBER, tabNumberStreet.getContent(client
					.obtainTo(TableType.NUMBER)));
			reg.setTo(TableType.STATE,
					tabState.getContent(client.obtainTo(TableType.STATE)));
			reg.setTo(TableType.STREET,
					tabStreet.getContent(client.obtainTo(TableType.STREET)));
			regList.add(reg);
		}
		return regList;
	}

	private ArrayList<Register> createBookRegisterList() {
		ArrayList<Register> regList = new ArrayList<Register>();
		for (Integer ID : new ArrayList<Integer>(idSet)) {
			book = tabBook.getContent(ID);
			Register reg = new BookRegister();
			reg.setTo(TableType.ISBN,
					keyTabISBN.getContent(book.obtainTo(TableType.ISBN)));
			reg.setTo(TableType.TITLE,
					tabTitle.getContent(book.obtainTo(TableType.TITLE)));
			reg.setTo(TableType.PUBLISHER,
					tabPublished.getContent(book.obtainTo(TableType.PUBLISHER)));
			reg.setTo(TableType.AUTHOR,
					tabAuthor.getContent(book.obtainTo(TableType.AUTHOR)));
			regList.add(reg);
		}
		return regList;
	}

	private ArrayList<Register> createLoanRegisterList() {
		ArrayList<Register> regList = new ArrayList<Register>();
		for (Integer cliID : new ArrayList<Integer>(idSet)) {
			loan = tabLoan.getContent(cliID);
			Register reg = new LoanRegister();
			reg.setTo(TableType.CPF,
					keyLoanCPF.getContent(loan.obtainTo(TableType.CPF)));
			reg.setTo(TableType.NAME,
					tabLoanName.getContent(loan.obtainTo(TableType.NAME)));
			reg.setTo(TableType.ISBN,
					tabLoanISBN.getContent(loan.obtainTo(TableType.ISBN)));
			reg.setTo(TableType.TITLE,
					tabLoanTitle.getContent(loan.obtainTo(TableType.TITLE)));
			reg.setTo(TableType.RETURNDATE, tabLoanReturndate.getContent(loan
					.obtainTo(TableType.RETURNDATE)));
			regList.add(reg);
		}
		return regList;
	}

	private boolean addClientRegister(ClientRegister register) {
		if (register.obtainTo(TableType.CPF) != null
				&& keyTabCPF.hasContent(register.obtainTo(TableType.CPF))) {
			return false;
		} else {
			client = new Client();
			currentId = tabClientes.insert(client);
			addClientData(tabCyte, register.obtainTo(TableType.CITY),
					TableType.CITY);
			addClientData(tabCountry, register.obtainTo(TableType.COUNTRY),
					TableType.COUNTRY);
			addClientData(keyTabCPF, register.obtainTo(TableType.CPF),
					TableType.CPF);
			addClientData(tabName, register.obtainTo(TableType.NAME),
					TableType.NAME);
			addClientData(tabAddress, register.obtainTo(TableType.ADDRESS),
					TableType.ADDRESS);
			addClientData(tabNeighborhood,
					register.obtainTo(TableType.NEIGHBORHOOD),
					TableType.NEIGHBORHOOD);
			addClientData(tabNumberStreet, register.obtainTo(TableType.NUMBER),
					TableType.NUMBER);
			addClientData(tabState, register.obtainTo(TableType.STATE),
					TableType.STATE);
			addClientData(tabStreet, register.obtainTo(TableType.STREET),
					TableType.STREET);
			return true;
		}
	}

	private boolean addBookRegister(BookRegister register) {
		if (register.obtainTo(TableType.ISBN) != null
				&& keyTabISBN.hasContent(register.obtainTo(TableType.ISBN))) {
			return false;
		} else {
			book = new Book();
			currentId = tabBook.insert(book);
			addBookData(tabAuthor, register.obtainTo(TableType.AUTHOR),
					TableType.AUTHOR);
			addBookData(tabPublished, register.obtainTo(TableType.PUBLISHER),
					TableType.PUBLISHER);
			addBookData(tabTitle, register.obtainTo(TableType.TITLE),
					TableType.TITLE);
			addBookData(keyTabISBN, register.obtainTo(TableType.ISBN),
					TableType.ISBN);
			return true;
		}
	}

	private boolean addLoanRegister(LoanRegister register) {
		if (register.obtainTo(TableType.CPF) != null
				&& keyLoanCPF.hasContent(register.obtainTo(TableType.CPF))) {
			return false;
		} else {
			loan = new Loan();
			currentId = tabLoan.insert(loan);
			addLoanData(keyLoanCPF, register.obtainTo(TableType.CPF),
					TableType.CPF);
			addLoanData(tabLoanISBN, register.obtainTo(TableType.ISBN),
					TableType.ISBN);
			addLoanData(tabLoanName, register.obtainTo(TableType.NAME),
					TableType.NAME);
			addLoanData(tabLoanTitle, register.obtainTo(TableType.TITLE),
					TableType.TITLE);
			addLoanData(tabLoanReturndate,
					register.obtainTo(TableType.RETURNDATE),
					TableType.RETURNDATE);
			return true;
		}
	}

	private void addClientData(Data<String> data, String obj, TableType tab) {
		if (obj != null) {
			if (!data.hasContent(obj)) {
				Integer id = data.insert(obj);
				client.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			} else {
				Integer id = data.getKey(obj);
				client.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			}
		}
	}

	private void addBookData(Data<String> data, String obj, TableType tab) {
		if (obj != null) {
			if (!data.hasContent(obj)) {
				Integer id = data.insert(obj);
				book.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			} else {
				Integer id = data.getKey(obj);
				book.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			}
		}
	}

	private void addLoanData(Data<String> data, String obj, TableType tab) {
		if (obj != null) {
			if (!data.hasContent(obj)) {
				Integer id = data.insert(obj);
				loan.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			} else {
				Integer id = data.getKey(obj);
				loan.insertTo(tab, id);
				data.insertLinkReference(id, currentId);
			}
		}
	}

	private boolean removeClientRegister(ClientRegister register) {
		if (register.obtainTo(TableType.CPF) != null
				&& keyTabCPF.hasContent(register.obtainTo(TableType.CPF))) {
			currentId = keyTabCPF.getLinkList(
					keyTabCPF.getKey(register.obtainTo(TableType.CPF))).get(0);
			client = tabClientes.getContent(currentId);
			removeLinkReference(tabCEP, client.obtainTo(TableType.CEP));
			removeLinkReference(tabCyte, client.obtainTo(TableType.CITY));
			removeLinkReference(tabCountry, client.obtainTo(TableType.COUNTRY));
			removeLinkReference(keyTabCPF, client.obtainTo(TableType.CPF));
			removeLinkReference(tabName, client.obtainTo(TableType.NAME));
			removeLinkReference(tabAddress, client.obtainTo(TableType.ADDRESS));
			removeLinkReference(tabNeighborhood,
					client.obtainTo(TableType.NEIGHBORHOOD));
			removeLinkReference(tabNumberStreet,
					client.obtainTo(TableType.NUMBER));
			removeLinkReference(tabState, client.obtainTo(TableType.STATE));
			removeLinkReference(tabStreet, client.obtainTo(TableType.STREET));
			keyTabCPF.remove(client.obtainTo(TableType.CPF));
			tabClientes.remove(currentId);
			return true;
		} else {
			return false;
		}
	}

	private boolean removeBookRegister(BookRegister register) {
		if (register.obtainTo(TableType.ISBN) != null
				&& keyTabISBN.hasContent(register.obtainTo(TableType.ISBN))) {
			currentId = keyTabISBN.getLinkList(
					keyTabISBN.getKey(register.obtainTo(TableType.ISBN)))
					.get(0);
			book = tabBook.getContent(currentId);
			removeLinkReference(tabAuthor, book.obtainTo(TableType.CEP));
			removeLinkReference(tabPublished, book.obtainTo(TableType.CITY));
			removeLinkReference(tabTitle, book.obtainTo(TableType.COUNTRY));
			removeLinkReference(keyTabISBN, book.obtainTo(TableType.ISBN));
			keyTabISBN.remove(book.obtainTo(TableType.ISBN));
			tabBook.remove(currentId);
			return true;
		} else {
			return false;
		}
	}

	private boolean removeLoanRegister(LoanRegister register) {
		if (register.obtainTo(TableType.CPF) != null
				&& keyLoanCPF.hasContent(register.obtainTo(TableType.CPF))) {
			currentId = keyLoanCPF.getLinkList(
					keyLoanCPF.getKey(register.obtainTo(TableType.CPF))).get(0);
			loan = tabLoan.getContent(currentId);
			removeLinkReference(tabLoanISBN, loan.obtainTo(TableType.ISBN));
			removeLinkReference(tabLoanName, loan.obtainTo(TableType.NAME));
			removeLinkReference(tabLoanReturndate,
					loan.obtainTo(TableType.RETURNDATE));
			removeLinkReference(tabLoanTitle, loan.obtainTo(TableType.TITLE));
			removeLinkReference(keyLoanCPF, loan.obtainTo(TableType.CPF));
			keyLoanCPF.remove(loan.obtainTo(TableType.CPF));
			tabLoan.remove(currentId);
			return true;
		} else {
			return false;
		}
	}

	private void removeLinkReference(Data<String> data, Integer id) {
		data.removeLinkReference(id, currentId);
	}
}
