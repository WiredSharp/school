package wiredsharp.study.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.core.Response;

import wiredsharp.study.model.Student;

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
         return ErrorResponse.NotFound("no matching student");
      } else {
         return Response.ok(wiredsharp.study.api.model.Student.from(student)).build();
      }
   }
}
