import java.util.Scanner;

import data.DataStoreFactory;
import data.DataStoreInterface;
import logic.ResourceManager;
import ui.EmergencyUI;

public class EMSMain {
	
	public static void main(String[] args) {
		
		// read user option for data store
		String datastoreOption = "";
		System.out.println("What type of data store? (M / D / F)");
		Scanner userInput = new Scanner(System.in);
		if(userInput.hasNextLine()) {
			datastoreOption = userInput.nextLine();
		}

		// create data store object user chooses
		DataStoreInterface datastore = DataStoreFactory.getDatastore(datastoreOption);
		
		// create resource manager object
		ResourceManager resourceManager = new ResourceManager(datastore);

		// create emergency ui object
		EmergencyUI ui = new EmergencyUI();
		ui.setResourceManager(resourceManager);
		
		// let ui start
		new Thread(ui).start();
		
		// dynamically change data store option
		while(true) {
			if(userInput.hasNextLine()) {
				datastoreOption = userInput.nextLine();
			}
			
			datastore = DataStoreFactory.getDatastore(datastoreOption);
			
			resourceManager.setDatastore(datastore);
		}
	}
	
}
