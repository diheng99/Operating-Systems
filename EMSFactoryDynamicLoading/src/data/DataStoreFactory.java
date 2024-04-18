package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataStoreFactory {
	// store data store configuration
	private final static Map<String, String> configuration = new HashMap<String, String>();
	
	// load data store configuration during system startup
	static {
		try {
			
			Scanner configurationReader = new Scanner(new File("configuration.txt"));
			while(configurationReader.hasNextLine()) {
				String line  = configurationReader.nextLine();
				String[] parts = line.split("=");
		    	configuration.put(parts[0], parts[1]);				
			}
			configurationReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DataStoreInterface getDatastore(String datastoreOption) {
		DataStoreInterface datastore = null;
		
		try {
			String classname = configuration.get(datastoreOption);

			Class datastoreImpl = ClassLoader.getSystemClassLoader().loadClass(classname);
			
			datastore = (DataStoreInterface)datastoreImpl.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return datastore;
	}
	
}
