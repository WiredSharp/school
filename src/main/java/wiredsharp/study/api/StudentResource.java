package wiredsharp.study.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import wiredsharp.study.model.Student;

@ApplicationScoped
@Path("api/students")
public class StudentResource {
   @GET
   public Uni<Response> getAll() {
      return Student.<Student>listAll(Sort.ascending("name")).map(StudentResponse::Create);
   }

   
   @GET
   @Path("{id:\\d+}")
   public Uni<Response> getById(@PathParam("id") Long id) {
      return Student.<Student>findById(id).map(StudentResponse::Create);
   }
}

