package org.atlanmod.emfviews.extra;

import org.atlanmod.emfviews.core.ViewResource;
import org.atlanmod.emfviews.core.ViewpointResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

public class EmfViewsFactory extends ResourceFactoryImpl {

  @Override
  public Resource createResource(URI uri) {
    if (uri.fileExtension().equals("eview")) {
      return new ViewResource(uri);
    } else if (uri.fileExtension().equals("eviewpoint")) {
      return new ViewpointResource(uri);
    } else {
      return null;
    }
  }
}
