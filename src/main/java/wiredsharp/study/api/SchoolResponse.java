package wiredsharp.study.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.Response;

import io.quarkus.runtime.annotations.RegisterForReflection;
import wiredsharp.study.api.model.ErrorResponse;
import wiredsharp.study.model.School;

@RegisterForReflection
public class SchoolResponse {
   public List<wiredsharp.study.api.model.School> schools;
   
   public SchoolResponse(Iterable<School> schools) {
      this.schools = StreamSupport.stream(schools.spliterator(), false)
         .map(wiredsharp.study.api.model.School::from)
         .collect(Collectors.toList());
   }

   public static Response Create(Iterable<wiredsharp.study.model.School> schools) {
      return Response.ok(new SchoolResponse(schools)).build();
   }

   
   public static Response Create(wiredsharp.study.model.School school) {
      if (school == null) {
         return ErrorResponse.notFound("no matching school");
      } else {
         return Response.ok(wiredsharp.study.api.model.School.from(school)).build();
      }
   }
}
