
public class Client {
	
	private int cpf;
	private int name;
	private int country;
	private int state;
	private int city;
	private int neighborhood;
	private int street;
	private int number;
	private int cep;
	
	public Client() {
	}
	
	public void insertTo(TableType tab, Integer ref) {
		switch (tab) {
		case CEP:{
			cep = ref;
			break;
		}
		case CITY:{
			city = ref;
			break;
		}
		case COUNTRY:{
			country = ref;
			break;
		}
		case CPF:{
			cpf = ref;
			break;
		}
		case NAME:{
			name = ref;
			break;
		}
		case NEIGHBORHOOD:{
			neighborhood = ref;
			break;
		}
		case NUMBER:{
			number = ref;
			break;
		}
		case STATE:{
			state = ref;
			break;
		}
		case STREET:{
			street = ref;
			break;
		}
		default:{
			break;
		}
	}
}	
	public Integer obtainTo(TableType tab) {
		switch (tab) {
		case CEP:{
			return cep;
		}
		case CITY:{
			return city;
		}
		case COUNTRY:{
			return country;
		}
		case CPF:{
			return cpf;
		}
		case NAME:{
			return name;
		}
		case NEIGHBORHOOD:{
			return neighborhood;
		}
		case NUMBER:{
			return number;
		}
		case STATE:{
			return state;
		}
		case STREET:{
			return street;
		}
		default:{
			return null;
		}
	}
	}
}
