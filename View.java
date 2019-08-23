package by.myJavaCourses.view;

import java.util.logging.Logger;

public class View {
	 private static final Logger log = Logger.getLogger("View.class");

	    public static void messageError(){
	      
	        log.info("The Way does not exist!!!");
	      
	    }

	    public static void messageSuccess() {
	        
	       log.info("The way exists, step by step !!!");
	       
	        
	    }
}
