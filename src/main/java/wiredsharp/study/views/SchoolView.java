package wiredsharp.study.views;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Location;
import io.quarkus.qute.TemplateInstance;

@Path("/schools")
@ApplicationScoped
public class SchoolView {

   @CheckedTemplate
   public static class Templates {
      @Location("index.html")
      public static native TemplateInstance index(); 
   }


   @GET
   @Produces(MediaType.TEXT_HTML)
   public TemplateInstance index() {
      return Templates.index();
   }
}
