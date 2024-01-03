package simplepdl.manip;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import petrinet.*;
import simplepdl.*;

public class PDL2NET {

	private static Map <String, Place> placeMap = new HashMap<>();
	private static Map <String, Transition> workDefMap = new HashMap<>();
	private static Map <String, Transition> transitionMap = new HashMap<>();
	private static Map <String, Place> ressourceMap = new HashMap<>();
	
	public static void main(String[] args) {
		
		// Charger le package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		SimplepdlPackage processPackageInstance = SimplepdlPackage.eINSTANCE;
		PetrinetPackage petriPackageInstance = PetrinetPackage.eINSTANCE;
		
		// Enregistrer l'extension ".xmi" comme devant Ãªtre ouverte Ã 
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
		
		// CrÃ©er un objet resourceSetImpl qui contiendra une ressource EMF (le modÃ¨le)
		ResourceSet resSet = new ResourceSetImpl();

		// DÃ©finir la ressource (le modÃ¨le)
		URI modelURI1 = URI.createURI("Process2.xmi");
		Resource processResource = resSet.getResource(modelURI1,true);
		URI modelURI2 = URI.createURI("net2.xmi");
		Resource petriResource = resSet.createResource(modelURI2);
		
		// La fabrique pour fabriquer les Ã©lÃ©ments de SimplePDL
	    SimplepdlFactory myProcessFactory = SimplepdlFactory.eINSTANCE;
	    PetrinetFactory myPetriFactory = PetrinetFactory.eINSTANCE;

		// CrÃ©er un Ã©lÃ©ment Process
	    simplepdl.Process process = (simplepdl.Process) processResource.getContents().get(0);
		PetriNet petri = myPetriFactory.createPetriNet();
		petri.setName(process.getName());
		
		// Ajouter le Process dans le modÃ¨le
		petriResource.getContents().add(petri);
		
		// Traiter les WorkDefinitions
		for (Object o : process.getProcesselements()) {
			if (o instanceof WorkDefinition) {
				
				// Prendre la WorkDefinition actuelle
				WorkDefinition wd = (WorkDefinition) o;
				
				// Création des placeMap
				// Place Ready
				Place placeReady = myPetriFactory.createPlace();
				placeReady.setName(wd.getName() + "_Ready");
				placeReady.setToken(1);
				
				// Place Started
				Place placeMaptarted = myPetriFactory.createPlace();
				placeMaptarted.setName(wd.getName() + "_Started");
				placeMaptarted.setToken(0);
				
				// Place Running
				Place placeRunning = myPetriFactory.createPlace();
				placeRunning.setName(wd.getName() + "_Running");
				placeRunning.setToken(0);
				
				// Place Finished
				Place placeFinished = myPetriFactory.createPlace();
				placeFinished.setName(wd.getName() + "_Finished");
				placeFinished.setToken(0);
				
				// Ajouter les placeMap à aux éléments de la petrinet
				petri.getElements().add(placeReady);
				petri.getElements().add(placeMaptarted);
				petri.getElements().add(placeRunning);
				petri.getElements().add(placeFinished);
				
				// Ajouter les placeMap à la liste des placeMap
				placeMap.put(placeMaptarted.getName(), placeMaptarted);
				placeMap.put(placeRunning.getName(), placeRunning);
				placeMap.put(placeFinished.getName(), placeFinished);
				placeMap.put(placeReady.getName(), placeReady);
				
				// Création des transitions
				// Transition Start
				Transition transitionStart = myPetriFactory.createTransition();
				transitionStart.setName(wd.getName() + "_Start");
				workDefMap.put(wd.getName(), transitionStart);
				transitionMap.put(transitionStart.getName(), transitionStart);
				
				// Transition Finish
				Transition transitionFinish = myPetriFactory.createTransition(); 
				transitionFinish.setName(wd.getName() + "_Finish");
				transitionMap.put(transitionFinish.getName(), transitionFinish);
				
				// Ajouter les transitions aux éléments de la petrinet
				petri.getElements().add(transitionStart);
				petri.getElements().add(transitionFinish);
				
				// Création des arcs
				Arc workDefReady2Start = myPetriFactory.createArc();
				Arc workDefStart2Started = myPetriFactory.createArc();
				Arc workDefStart2Running = myPetriFactory.createArc();
				Arc workDefRunning2Finish = myPetriFactory.createArc();
				Arc workDefFinish2Finished = myPetriFactory.createArc();
				
				// Ajouter les arcs aux éléments de la petrinet
				petri.getElements().add(workDefReady2Start);
				petri.getElements().add(workDefStart2Started);
				petri.getElements().add(workDefStart2Running);
				petri.getElements().add(workDefRunning2Finish);
				petri.getElements().add(workDefFinish2Finished);
				
				// Ajuster la source et la cible des arcs, le type, la direction et le poids
				// L'arc de Ready à Start
				workDefReady2Start.setSource(placeReady);
				workDefReady2Start.setTarget(transitionStart);
				workDefReady2Start.setType(ArcKind.NORMAL);
				workDefReady2Start.setWeight(1);

				// L'arc de Start à Started
				workDefStart2Started.setSource(transitionStart);
				workDefStart2Started.setTarget(placeMaptarted);
				workDefStart2Started.setType(ArcKind.NORMAL);
				workDefStart2Started.setWeight(1);

				// L'arc de Start à Running
				workDefStart2Running.setSource(transitionStart);
				workDefStart2Running.setTarget(placeRunning);
				workDefStart2Running.setType(ArcKind.NORMAL);
				workDefStart2Running.setWeight(1);

				// L'arc de Running à Finish
				workDefRunning2Finish.setSource(placeRunning);
				workDefRunning2Finish.setTarget(transitionFinish);
				workDefRunning2Finish.setType(ArcKind.NORMAL);
				workDefRunning2Finish.setWeight(1);

				// L'arc de Finish à Finished
				workDefFinish2Finished.setSource(transitionFinish);
				workDefFinish2Finished.setTarget(placeFinished);
				workDefFinish2Finished.setType(ArcKind.NORMAL);
				workDefFinish2Finished.setWeight(1);
			}
		}

		// Traiter les ressources
		for (Object o : process.getProcesselements()) {
			if (o instanceof Ressource) {
				Ressource res = (Ressource) o;
				Place placeRessource = myPetriFactory.createPlace(); petri.getElements().add(placeRessource);
				placeRessource.setName(res.getName());
				placeRessource.setToken(res.getQuantity());
				ressourceMap.put(res.getName(), placeRessource);
			}
		}
		
		// Traiter l'allocation des ressources
		for (Object o : process.getProcesselements()) {
			if (o instanceof WorkDefinition) {
				WorkDefinition wd = (WorkDefinition) o;
				Transition tr = workDefMap.get(wd.getName());
				for (RessourceAllocation allocation : wd.getOccurences()) {
					Arc arcAllocate = myPetriFactory.createArc(); 
					petri.getElements().add(arcAllocate);
					arcAllocate.setWeight(allocation.getOccurence());
					arcAllocate.setTarget(tr);
					arcAllocate.setSource(ressourceMap.get(allocation.getRessource().getName()));
				}
			}
		}
		
		// Traiter les WorkSequences
		for (Object o : process.getProcesselements()) {
			if (o instanceof WorkSequence) {
				WorkSequence ws = (WorkSequence) o;
				Arc a = myPetriFactory.createArc(); petri.getElements().add(a);
				a.setType(ArcKind.READ);
				String sourceArc;
				String destinationArc;
				if (ws.getLinkType() == WorkSequenceType.START_TO_START || ws.getLinkType() == WorkSequenceType.START_TO_FINISH)  {
					sourceArc = "_Started";
				} else {
					sourceArc = "_Finished";
				}
				
				if (ws.getLinkType() == WorkSequenceType.START_TO_START || ws.getLinkType() == WorkSequenceType.FINISH_TO_START)  {
					destinationArc = "_Start";
				} else {
					destinationArc = "_Finish";
				}
				a.setSource(placeMap.get(ws.getPredecessor().getName() + sourceArc));
				a.setTarget(transitionMap.get(ws.getSuccessor().getName() + destinationArc));
			}
		}
		
		// Sauver la ressource
	    try {
	    	petriResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



