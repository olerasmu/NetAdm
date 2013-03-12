package asTwo;
import java.io.InputStream;
import java.util.Iterator;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.compose.Intersection;
import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelGraphInterface;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RSIterator;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.VCARD;


public class SemanticApplication {
	/**
	 The application takes a string as input (name of CNMP MIB object) and tries to find the 
	 corresponding SNMP MIB object name with the help of the ontology. 
	 It then takes this name, issues an snmpget command to one SNMP agent and prints the result on the command line.
	 */

	
	 public static void printStatements(Model m, Resource s, Property p, Resource o) {
	        for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
	            Statement stmt = i.nextStatement();
	            System.out.println("");
	            System.out.println(" - " + PrintUtil.print(stmt));
	            
	            Resource res = stmt.getResource();
	        

	        }
	        
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting application");
		String[] cnmpObjects = {"X5178","X3125","X4912","X5982","X1234","X6742"};
		
		Model schema = FileManager.get().loadModel("sematicweb-2013.owl");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, schema);
		
		Resource res = infmodel.getResource("http://www.item.ntnu.no/fag/ttm4128/sematicweb-2013#X5982");
		printStatements(infmodel, res, OWL.equivalentClass, null);
		
	
		
	}

}
