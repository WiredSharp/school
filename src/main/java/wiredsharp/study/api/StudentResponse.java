package wiredsharp.study.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.quarkus.runtime.annotations.RegisterForReflection;
import wiredsharp.study.api.model.ErrorResponse;
import wiredsharp.study.model.Student;

@RegisterForReflection
public class StudentResponse {
   public List<wiredsharp.study.api.model.Student> students;
   
   public StudentResponse(Iterable<Student> students) {
      this.students = StreamSupport.stream(students.spliterator(), false)
         .map(wiredsharp.study.api.model.Student::from)
         .collect(Collectors.toList());
   }

   public static Response Create(Iterable<wiredsharp.study.model.Student> students) {
      return Response.ok(new StudentResponse(students)).build();
   }
   
   public static Response Create(wiredsharp.study.model.Student student) {
      if (student == null) {
         return ErrorResponse.notFound("no matching student");
      } else {
         return Response.ok(wiredsharp.study.api.model.Student.from(student)).build();
      }
   }

   public static Response created(UriInfo uriInfo, Student enrolled) {
      return Response.created(uriInfo.getBaseUriBuilder().path("/api/students/" + enrolled.id).build()).build();
   }
}
