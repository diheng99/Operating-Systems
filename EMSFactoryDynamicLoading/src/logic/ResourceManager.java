package logic;
import data.DataStoreInterface;

public class ResourceManager {

	private DataStoreInterface datastore;

	public ResourceManager(DataStoreInterface datastore) {
		this.datastore = datastore;
	}

	public void updateMap() {
		datastore.retrieveMap();
		
		System.out.println("ResourceManager: updating map ...\n");
	}

	public void reportAccident() {
		System.out.println("ResourceManager: checking accident report ...");

		datastore.createAccidentRecord();
	}

	public void allocateEmergencyResources() {
		
		System.out.println("ResourceManager: finding available reosurces ...");
		
		datastore.dispatchMedicalTeam();
		
	}

	public void setDatastore(DataStoreInterface datastore) {
		this.datastore = datastore;
	}
	
	
}
