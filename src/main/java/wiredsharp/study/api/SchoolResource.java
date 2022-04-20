package wiredsharp.study.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import wiredsharp.study.model.School;

@ApplicationScoped
@Path("api/schools")
public class SchoolResource {
   
   @GET
   public Uni<Response> getAll() {
      return School.<School>listAll(Sort.ascending("name")).map(SchoolResponse::Create);
   }

   
   @GET
   @Path("{id:\\d+}")
   public Uni<Response> getById(Long id) {
      return School.<School>findById(id).map(SchoolResponse::Create);
   }
}

