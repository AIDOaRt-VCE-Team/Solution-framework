package org.atlanmod.emfviews.experiments;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.core.Viewpoint;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class CreateView {
  static String root = new File("../").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(root + relativePath);
  }

  public static void main(String[] args) throws IOException {
	
    //Create basic resources to deal with EMF reflective API 
    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    map.put("xmi", new XMIResourceFactoryImpl());
    map.put("ecore", new EcoreResourceFactoryImpl());
    map.put("uml", new Uml EcoreResourceFactoryImpl());

    //Create EMF Resources and register packages for the main used metamodels
    ResourceSet rs = new ResourceSetImpl();
    EPackage SysML = (EPackage) rs.getResource(URI.createFileURI("http://www.eclipse.org/papyrus/sysml/1.6/SysML"), true).getContents().get(0);    
    EPackage.Registry.INSTANCE.put(SysML.getNsURI(), SysML);

    Resource book = rs.getResource(resourceURI(""), true);
    Resource publ = rs.getResource(resourceURI("/models/Basic/Publication.xmi"), true);

    // 1. Build viewpoint weaving model
    VirtualLinksFactory vLinksFactory = VirtualLinksFactory.eINSTANCE;
    WeavingModel viewpointWeavingModel = vLinksFactory.createWeavingModel();
    viewpointWeavingModel.setName("publicationsAndBooks");

    ConcreteConcept source;
    {
      ContributingModel cm = vLinksFactory.createContributingModel();
      viewpointWeavingModel.getContributingModels().add(cm);
      cm.setURI("http://publication");
      ConcreteConcept cc = vLinksFactory.createConcreteConcept();
      cm.getConcreteElements().add(cc);
      cc.setPath("Publication");
      source = cc;
    }

    ConcreteConcept target;
    
    ConcreteElement nbPages;
    {
      ContributingModel contributingModel = vLinksFactory.createContributingModel();
      viewpointWeavingModel.getContributingModels().add(contributingModel);
      contributingModel.setURI("http://book");
      ConcreteConcept cConcept = vLinksFactory.createConcreteConcept();
      contributingModel.getConcreteElements().add(cConcept);
      cConcept.setPath("Chapter");
      target = cConcept;
      ConcreteElement cElement = vLinksFactory.createConcreteElement();
      contributingModel.getConcreteElements().add(cElement);
      cElement.setPath("Chapter.nbPages");
      nbPages = cElement;
    }

    {
      VirtualAssociation vAssociation = vLinksFactory.createVirtualAssociation();
      viewpointWeavingModel.getVirtualLinks().add(vAssociation);
      vAssociation.setName("bookChapters");
      vAssociation.setUpperBound(-1);
      vAssociation.setSource(source);
      vAssociation.setTarget(target);
    }

    {
      Filter fi = vLinksFactory.createFilter();
      viewpointWeavingModel.getVirtualLinks().add(fi);
      fi.setName("nbPages");
      fi.setTarget(nbPages);
    }

    // 2. Build viewpoint
    Map<String, EPackage> contributingModels = Map.ofEntries(
            Map.entry("book", Book),
            Map.entry("publ", Publ)
            );
    Viewpoint viewpoint = new Viewpoint(contributingModels, viewpointWeavingModel);

    // 3. Build view weaving model
    WeavingModel viewWeavingModel = vLinksFactory.createWeavingModel();
    viewWeavingModel.setName("publicationsAndBooks");

    {
      ContributingModel cm = vLinksFactory.createContributingModel();
      viewWeavingModel.getContributingModels().add(cm);
      cm.setURI("http://publication");
      ConcreteConcept cc = vLinksFactory.createConcreteConcept();
      cm.getConcreteElements().add(cc);
      cc.setPath(publ.getURIFragment(publ.getContents().get(0)));
      source = cc;
    }

    {
      ContributingModel cm = vLinksFactory.createContributingModel();
      viewWeavingModel.getContributingModels().add(cm);
      cm.setURI("http://book");
      ConcreteConcept cc = vLinksFactory.createConcreteConcept();
      cm.getConcreteElements().add(cc);
      cc.setPath(book.getURIFragment(book.getContents().get(0).eContents().get(0)));
      target = cc;
    }

    {
      VirtualAssociation va = vLinksFactory.createVirtualAssociation();
      viewWeavingModel.getVirtualLinks().add(va);
      va.setName("bookChapters");
      va.setSource(source);
      va.setTarget(target);
    }

    // 4. Build view
    View view = new View(viewpoint, Arrays.asList(book, publ), viewWeavingModel);

    // 5. Navigate the new association in the view
    EObject vpubl = view.getVirtualContents().get(1);
    System.out.println(vpubl.eGet(vpubl.eClass().getEStructuralFeature("title")));
  }
}