import java.util.ArrayList;


public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Database bd = new Database();
		ClientRegister reg = new ClientRegister();
		reg.setTo(TableType.CEP, "88000-000");
		reg.setTo(TableType.CITY, "Floripa");
		reg.setTo(TableType.COUNTRY, "BR");
		reg.setTo(TableType.CPF, "123456789");
		reg.setTo(TableType.NAME, "Marcio");
		reg.setTo(TableType.NEIGHBORHOOD, "Trindade");
		reg.setTo(TableType.NUMBER, "10A");
		reg.setTo(TableType.STATE, "SC");
		reg.setTo(TableType.STREET, "Rozalia");
		
		bd.addRegister(reg);
		reg = new ClientRegister();
		reg.setTo(TableType.CEP, null);
		reg.setTo(TableType.CITY, "Floripa");
		reg.setTo(TableType.COUNTRY, "BR");
		reg.setTo(TableType.CPF, "123456780");
		reg.setTo(TableType.NAME, "Sagaz");
		reg.setTo(TableType.NEIGHBORHOOD, "Trindade");
		reg.setTo(TableType.NUMBER, null);
		reg.setTo(TableType.STATE, "SC");
		reg.setTo(TableType.STREET, "Rozalia");
		bd.addRegister(reg);
		//bd.removeRegister(reg);
		
		reg = new ClientRegister();
		reg.setTo(TableType.CEP, "88000-000");
		reg.setTo(TableType.CITY, "Floripa");
		reg.setTo(TableType.COUNTRY, null);
		reg.setTo(TableType.CPF, "123456780");
		reg.setTo(TableType.NAME, "Sagaz");
		reg.setTo(TableType.NEIGHBORHOOD, "Trindade");
		reg.setTo(TableType.NUMBER, null);
		reg.setTo(TableType.STATE, "SC");
		reg.setTo(TableType.STREET, "Rozalia");
		
		ArrayList<Register> ret = bd.searchFor(reg);
	}

}
