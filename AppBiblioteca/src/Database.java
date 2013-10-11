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
	private Data<Client> tabClientes;
	private Client client;
	private Integer clientID;
	Set<Integer> idSet;

	// private Data<String> livrosISBN;

	public Database() {
		tabCEP = new Data<>();
		tabCyte = new Data<>();
		keyTabCPF = new Data<>();
		tabCountry = new Data<>();
		tabNumberStreet = new Data<>();
		tabNeighborhood = new Data<>();
		tabStreet = new Data<>();
		tabState = new Data<>();
		tabName = new Data<>();
		tabClientes = new Data<>();
	}

	public boolean addRegister(Register register) {
		if (register != null) {
			switch (register.getType()) {
			case CLIENT: {
				return addClientRegister((ClientRegister) register);
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
				return search((ClientRegister) register);
			}
			default:
				return null;
			}
		}
		return null;
	}

	private ArrayList<Register> search(ClientRegister register) {
		idSet = new LinkedHashSet<>(new ArrayList<Integer>());
		merceList(tabCEP.getLinkList(tabCEP.getKey(register.obtainTo(TableType.CEP))));
		merceList(tabCyte.getLinkList(tabCyte.getKey(register.obtainTo(TableType.CITY))));
		merceList(tabCountry.getLinkList(tabCountry.getKey(register.obtainTo(TableType.COUNTRY))));
		merceList(keyTabCPF.getLinkList(keyTabCPF.getKey(register.obtainTo(TableType.CPF))));
		merceList(tabName.getLinkList(tabName.getKey(register.obtainTo(TableType.NAME))));
		merceList(tabNeighborhood.getLinkList(tabNeighborhood.getKey(register.obtainTo(TableType.NEIGHBORHOOD))));
		merceList(tabNumberStreet.getLinkList(tabNumberStreet.getKey(register.obtainTo(TableType.NUMBER))));
		merceList(tabState.getLinkList(tabState.getKey(register.obtainTo(TableType.STATE))));
		merceList(tabStreet.getLinkList(tabStreet.getKey(register.obtainTo(TableType.STREET))));
		return createRegisterList();
	}

	private void merceList(ArrayList<Integer> linkList) {
		if (linkList != null){
			idSet.addAll(linkList);
		}
	}
	
	private ArrayList<Register> createRegisterList() {
		ArrayList<Register> regList = new ArrayList<>();
		for (Integer clientID: new ArrayList<>(idSet)){
			client = tabClientes.getContent(clientID);
			Register reg = new ClientRegister();
			reg.setTo(TableType.CEP, tabCEP.getContent(client.obtainTo(TableType.CEP)));
			reg.setTo(TableType.CITY, tabCyte.getContent(client.obtainTo(TableType.CITY)));
			reg.setTo(TableType.COUNTRY, tabCountry.getContent(client.obtainTo(TableType.COUNTRY)));
			reg.setTo(TableType.CPF, keyTabCPF.getContent(client.obtainTo(TableType.CPF)));
			reg.setTo(TableType.NAME, tabName.getContent(client.obtainTo(TableType.NAME)));
			reg.setTo(TableType.NEIGHBORHOOD, tabNeighborhood.getContent(client.obtainTo(TableType.NEIGHBORHOOD)));
			reg.setTo(TableType.NUMBER, tabNumberStreet.getContent(client.obtainTo(TableType.NUMBER)));
			reg.setTo(TableType.STATE, tabState.getContent(client.obtainTo(TableType.STATE)));
			reg.setTo(TableType.STREET, tabStreet.getContent(client.obtainTo(TableType.STREET)));
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
			clientID = tabClientes.insert(client);
			addData(tabCEP, register.obtainTo(TableType.CEP), TableType.CEP);
			addData(tabCyte, register.obtainTo(TableType.CITY), TableType.CITY);
			addData(tabCountry, register.obtainTo(TableType.COUNTRY),
					TableType.COUNTRY);
			addData(keyTabCPF, register.obtainTo(TableType.CPF), TableType.CPF);
			addData(tabName, register.obtainTo(TableType.NAME), TableType.NAME);
			addData(tabNeighborhood, register.obtainTo(TableType.NEIGHBORHOOD),
					TableType.NEIGHBORHOOD);
			addData(tabNumberStreet, register.obtainTo(TableType.NUMBER),
					TableType.NUMBER);
			addData(tabState, register.obtainTo(TableType.STATE),
					TableType.STATE);
			addData(tabStreet, register.obtainTo(TableType.STREET),
					TableType.STREET);
			return true;
		}
	}

	private void addData(Data<String> data, String obj, TableType tab) {
		if (obj != null) {
			if (!data.hasContent(obj)) {
				Integer id = data.insert(obj);
				client.insertTo(tab, id);
				data.insertLinkReference(id, clientID);
			} else {
				Integer id = data.getKey(obj);
				client.insertTo(tab, id);
				data.insertLinkReference(id, clientID);
			}
		}
	}

	private boolean removeClientRegister(ClientRegister register) {
		if (register.obtainTo(TableType.CPF) != null
				&& keyTabCPF.hasContent(register.obtainTo(TableType.CPF))) {
			clientID = keyTabCPF.getLinkList(
					keyTabCPF.getKey(register.obtainTo(TableType.CPF))).get(0);
			client = tabClientes.getContent(clientID);
			removeLinkReference(tabCEP, client.obtainTo(TableType.CEP));
			removeLinkReference(tabCyte, client.obtainTo(TableType.CITY));
			removeLinkReference(tabCountry, client.obtainTo(TableType.COUNTRY));
			removeLinkReference(keyTabCPF, client.obtainTo(TableType.CPF));
			removeLinkReference(tabName, client.obtainTo(TableType.NAME));
			removeLinkReference(tabNeighborhood,
					client.obtainTo(TableType.NEIGHBORHOOD));
			removeLinkReference(tabNumberStreet,
					client.obtainTo(TableType.NUMBER));
			removeLinkReference(tabState, client.obtainTo(TableType.STATE));
			removeLinkReference(tabStreet, client.obtainTo(TableType.STREET));
			keyTabCPF.remove(client.obtainTo(TableType.CPF));
			tabClientes.remove(clientID);
			return true;
		} else {
			return false;
		}
	}

	private void removeLinkReference(Data<String> data, Integer id) {
		data.removeLinkReference(id, clientID);
	}
}
