package org.atlanmod.emfviews.helper;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class ViewHelper {
	 
	 /**
	   * Navigate through a given list of view's virtual elements and print out its class name and attributes
	   * @param List <EObject> vElements
	   */
	  public static void printView(List <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
		    	EObject vElement = iter.next();
			    EClass vElementModelClass = vElement.eClass();
			    System.out.println(vElementModelClass.getName());
			    for (Iterator<EAttribute> iterAttr = vElementModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
			    	EAttribute vElementAttribute = (EAttribute) iterAttr.next();
			    	
			    	Object elementAttributeValue = vElement.eGet(vElementAttribute);
			    	String attrName = vElementAttribute.getName();
			    	System.out.println(" " + attrName + ": " + elementAttributeValue);
			    	if (vElement.eIsSet(vElementAttribute)) {
			    		System.out.println();
			    	} else {
			    		System.out.println(" (default)");
			    	}
			    }
		    }
	  }
	  
	  /**
	   * Navigate through a given list of view's virtual elements and print out its class name and attributes
	   * @param List <EObject> vElements
	   */
	  public static void printWeavingModel(TreeIterator <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements ; iter.hasNext();) {
		    	EObject vElement = iter.next();
			    EClass vElementModelClass = vElement.eClass();
			    System.out.println(vElementModelClass.getName());
			    for (Iterator<EAttribute> iterAttr = vElementModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
			    	EAttribute vElementAttribute = (EAttribute) iterAttr.next();
			    	
			    	Object elementAttributeValue = vElement.eGet(vElementAttribute);
			    	String attrName = vElementAttribute.getName();
			    	System.out.println(" " + attrName + ": " + elementAttributeValue);
			    	if (vElement.eIsSet(vElementAttribute)) {
			    		System.out.println();
			    	} else {
			    		System.out.println(" (default)");
			    	}
			    }
		    }
	  }
}
