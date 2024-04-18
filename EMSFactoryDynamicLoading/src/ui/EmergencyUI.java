package ui;

import java.util.Random;

import logic.ResourceManager;

public class EmergencyUI implements Runnable {

	private ResourceManager resourceManager;

	// simulate ui actions on UI
	public void run() {
		Random rand = new Random();
		
		while(true) {

		    int action = rand.nextInt((4 - 1) + 1) + 1;
		    
		    if(action == 1) {
		    	System.out.println("User updates map");
		    	resourceManager.updateMap();
		    } else if(action == 2) {
		    	System.out.println("User reports accident");
		    	resourceManager.reportAccident();
		    } else if(action == 3) {
		    	System.out.println("User allocates emergency resources");
		    	resourceManager.allocateEmergencyResources();
		    }
		    
		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    
		}
	}

	public void setResourceManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;		
	}

}
