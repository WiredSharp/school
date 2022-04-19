package wiredsharp.study.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import wiredsharp.study.model.School;

public class SchoolResponse {
   public List<School> schools;
   
   public SchoolResponse(Iterable<School> schools) {
      this.schools = StreamSupport.stream(schools.spliterator(), false).collect(Collectors.toList());
   }

   public static SchoolResponse Create(Iterable<School> schools) {
      return new SchoolResponse(schools);
   }
}
