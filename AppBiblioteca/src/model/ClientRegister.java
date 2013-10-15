package model;

public class ClientRegister extends Register {

	private String cpf;
	private String name;
	private String country;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private String number;
	private String cep;
	private String address;

	public ClientRegister() {
		setType(RegisterType.CLIENT);
	}

	@Override
	public void setTo(TableType tab, String value) {
		switch (tab) {
		case CEP: {
			cep = value;
			break;
		}
		case CITY: {
			city = value;
			break;
		}
		case COUNTRY: {
			country = value;
			break;
		}
		case CPF: {
			cpf = value;
			break;
		}
		case NAME: {
			System.out.println("name" + value);
			name = value;
			break;
		}
		case NEIGHBORHOOD: {
			neighborhood = value;
			break;
		}
		case NUMBER: {
			number = value;
			break;
		}
		case STATE: {
			state = value;
			break;
		}
		case STREET: {
			street = value;
			break;
		}
		case ADDRESS: {
			address = value;
			break;
		}
		default: {
			break;
		}
		}
	}

	@Override
	public String obtainTo(TableType tab) {
		switch (tab) {
		case CEP: {
			return cep;
		}
		case CITY: {
			return city;
		}
		case COUNTRY: {
			return country;
		}
		case CPF: {
			return cpf;
		}
		case NAME: {
			System.out.println("name ret" + name);
			return name;
		}
		case NEIGHBORHOOD: {
			return neighborhood;
		}
		case NUMBER: {
			return number;
		}
		case STATE: {
			return state;
		}
		case STREET: {
			return street;
		}
		case ADDRESS: {
			return address;
		}
		default: {
			return null;
		}
		}
	}

}
