package wiredsharp.study.api.model;

public class Student {
   public String name;

   public Student(String name) {
      super();
      this.name = name;
   }

   public static Student from(wiredsharp.study.model.Student student) {
      return new Student(student.name);
   }
}
