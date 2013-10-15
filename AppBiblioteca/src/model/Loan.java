package model;

public class Loan {

	private int isbn;
	private int title;
	private int name;
	private int cpf;
	private int returndate;

	public Loan() {
	}

	public void insertTo(TableType tab, Integer ref) {
		switch (tab) {
		case ISBN: {
			isbn = ref;
			break;
		}
		case NAME: {
			name = ref;
			break;
		}
		case CPF: {
			cpf = ref;
			break;
		}
		case TITLE: {
			title = ref;
			break;
		}
		case RETURNDATE: {
			returndate = ref;
			break;
		}
		default: {
			break;
		}
		}
	}

	public Integer obtainTo(TableType tab) {
		switch (tab) {
		case NAME: {
			return name;
		}
		case ISBN: {
			return isbn;
		}
		case TITLE: {
			return title;
		}
		case RETURNDATE: {
			return returndate;
		}
		case CPF: {
			return cpf;
		}
		default: {
			return null;
		}
		}
	}
}
