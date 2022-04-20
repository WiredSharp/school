package wiredsharp.study.api.model;

public class Student {

   public Long id;
   public String name;

   public String getPath() { return "/api/students/" + id; }

   public Student(Long id, String name) {
      super();
      this.id = id;
      this.name = name;
   }

   public static Student from(wiredsharp.study.model.Student student) {
      return new Student(student.id, student.name);
   }
}
