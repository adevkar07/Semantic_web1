import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;
public class Lab1p4 {
	static Model baseFriends = null;
	static String defaultNameSpace ="http://utdallas/semclass";
	static String personURI    = "http://utdallas/semclass";
	static String FullName     = "Dr Viera Chandler ";
	static String NamePrefix = "Dr";
	static String GivenName = "Viera";
	static String FamilyNAme = "Chandler";
	static String title = "South West Division President";
	static String company = "Allied Semantics";
	static String dob = "Jnuary 15, 1964";
	static String email ="vechandler@alliedsem.com";
	static String resourceID = "534772";
	  public static void main (String args[])throws Exception {
		  
		  org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
		// create an empty model
	        Model model = ModelFactory.createDefaultModel();

	        // create the resource
	        //   and add the properties cascading style
	        Resource vChandler = model.createResource(personURI)
		    		   .addProperty(VCARD.FN, FullName)
		    		   .addProperty(VCARD.N, 
		    				   model.createResource()
		    				   .addProperty(VCARD.Prefix, "Dr")
		    				   .addProperty(VCARD.Given, GivenName)
		    				   .addProperty(VCARD.Family, FamilyNAme))
		    		   .addProperty(VCARD.TITLE, title)
		    		   .addProperty(VCARD.ORG, company)
		    		   .addProperty(VCARD.BDAY, dob)
		    		   .addProperty(VCARD.EMAIL, email)
		    		   .addProperty(VCARD.UID, resourceID);
	        
	      //Write the model to file
	        try {
	   	     model.write(new BufferedWriter(new FileWriter("Lab1p2_acd170130.xml")), "RDF/XML-ABBREV");
	   	     model.write(new BufferedWriter(new FileWriter("Lab1p2_acd170130.ntp")), "N-TRIPLES");
	   	     model.write(new BufferedWriter(new FileWriter("Lab1p2_acd170130.n3")), "N3");
	   	     }catch(Exception e) {
	   	    	 
	   	     }
	        
	      //Using TDB persistence model
	        
	        Dataset dataset=TDBFactory.createDataset("MyDatabases/Dataset1");
	    	dataset.begin(ReadWrite.WRITE);
	    	try
	     	{
	    		baseFriends=dataset.getNamedModel("myrdf");
	    		baseFriends = ModelFactory.createOntologyModel();
	      		InputStream inFoafInstance = FileManager.get().open("acd170130_FOAFFriends.rdf.txt");
	      		baseFriends.read(inFoafInstance, defaultNameSpace);
	      		//baseFriends.write(System.out);	      	
	      		dataset.commit();
	      		dataset.end();
	          }
	          catch(Exception e)
	          {
	        	  System.out.println(e);
	          }
	     	
	    //	Write TDB model to file  
	    	 try {
	    			baseFriends.write(new BufferedWriter(new FileWriter("Lab1p4_acd170130.xml")), "RDF/XML-ABBREV");
		      		baseFriends.write(new BufferedWriter(new FileWriter("Lab1p4_acd170130.ntp")), "N-TRIPLES");
		      		baseFriends.write(new BufferedWriter(new FileWriter("Lab1p4_acd170130.n3")), "N3");
		   	     }catch(Exception e) {
		   	    	  System.out.println(e);
		   	     }		  		 		  
	  }
	  
	  }

