package org.atlanmod.vce.emfviews.helper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ModelHelper {
	 
	 /**
	   * Navigate through a given resource (representing a model) and print out its class name and attributes
	   * @param Resource resource
	   */
	  public static void printResource(Resource resource) 
	  {
		  List <EObject> elements = resource.getContents();
		  for (Iterator<EObject> iter = elements.iterator() ; iter.hasNext();) {
		    	EObject element = iter.next();
			    EClass modelClass = element.eClass();
			    System.out.println(modelClass.getName());
			    for (Iterator<EAttribute> iterAttr = modelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
			    	EAttribute elementAttribute = (EAttribute) iterAttr.next();
			    	
			    	Object elementAttributeValue = element.eGet(elementAttribute);
			    	String attrName = elementAttribute.getName();
			    	System.out.println(" " + attrName + ": " + elementAttributeValue);
			    	if (element.eIsSet(elementAttribute)) {
			    		System.out.println();
			    	} else {
			    		System.out.println(" (default)");
			    	}
			    }
		    }
	  }
	  
	  /**
	   * Receives a resource (model) and copy all its contents to a new path
	   * @param resource resource to be stored in the new path
	   * @param uriPath path 
	   */
	  public static void serializeResource(Resource resource, URI uriPath) 
	  {
		  ResourceSet resSet = new ResourceSetImpl();
		  resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new  XMIResourceFactoryImpl());
		  Resource resCopy = resSet.createResource(uriPath);
		  
		  try{
			  //copy everything from the given resource
			  resCopy.getContents().add(resource.getContents().get(0));
			  resCopy.save(null);
		  }
		  catch (IOException e) {e.printStackTrace();}		  
	  }
}
