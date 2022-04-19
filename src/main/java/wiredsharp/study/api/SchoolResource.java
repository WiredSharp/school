package wiredsharp.study.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import wiredsharp.study.model.School;

@ApplicationScoped
@Path("api/schools")
public class SchoolResource {
   
   @GET
   public Uni<List<School>> getAll() {
      return School.listAll(Sort.ascending("name"));
   }
}

