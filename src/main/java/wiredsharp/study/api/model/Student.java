package wiredsharp.study.api.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Student {

   public Long id;
   public String school;
   public String name;

   public Student(Long id, String school, String name) {
      super();
      this.id = id;
      this.name = name;
      this.school = school;
   }

   public static Student from(wiredsharp.study.model.Student student) {
      return new Student(student.id, student.school.name, student.name);
   }
}
