package wiredsharp.study.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import wiredsharp.study.api.model.ErrorResponse;
import wiredsharp.study.model.School;
import wiredsharp.study.model.Student;

@ApplicationScoped
@Path("api/schools")
public class SchoolResource {

   private final Logger Logger = org.jboss.logging.Logger.getLogger(SchoolResource.class);

   @GET
   public Uni<Response> getAll() {
      return School.<School>listAll(Sort.ascending("name")).map(SchoolResponse::Create);
   }

   
   @GET
   @Path("{id:\\d+}")
   public Uni<Response> getById(Long id) {
      return School.<School>findById(id).map(SchoolResponse::Create);
   }

   @POST
   @Path("{id:\\d+}")
   public Uni<Response> enroll(@PathParam("id") Long id, @RequestBody StudentRequest request, UriInfo uriInfo) {
      return School.<School>findById(id).chain(school -> {
         if (null == school) return Uni.createFrom().item(ErrorResponse.notFound("school #" + id + " does not exist"));
         try {
            return school.enroll(request).map(student -> StudentResponse.created(uriInfo, student));
         } catch (Exception e) {
            Logger.error("unable to enroll student " + request + " in " + school, e);
            return Uni.createFrom().item(ErrorResponse.serverError("unable to enroll student " + request + " in " + school));
         }
      });
   }

   @DELETE
   @Path("{schoolId:\\d+}/student/{studentId:\\d+}")
   public Uni<Response> revoke(@PathParam("schoolId") Long schoolId, @PathParam("studentId") Long studentId) {
      return School.<School>findById(schoolId).chain(school -> {
         if (null == school) return Uni.createFrom().item(ErrorResponse.notFound("school #" + schoolId + " does not exist"));
         return school.revoke(studentId).map(isDeleted -> {
            Logger.info(isDeleted ? "student #" + studentId + " revoked" : "no match for student id " + studentId);
            return Response.noContent().build();
         });
      });
   }
}

