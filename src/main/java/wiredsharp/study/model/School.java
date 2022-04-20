package wiredsharp.study.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import wiredsharp.study.api.SchoolResource;
import wiredsharp.study.api.StudentRequest;

@Entity
@Table(name="schools")
public class School extends PanacheEntity {

   private final org.jboss.logging.Logger Logger = org.jboss.logging.Logger.getLogger(SchoolResource.class);

   public String name;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "school")
   public Set<Student> students;

   public Uni<Student> enroll(StudentRequest request) {
      Student student = new Student();
      student.name = request.name;
      student.school = this;
      this.students.add(student);
      return student.persistAndFlush();
   }

   public Uni<Boolean> revoke(Long studentId) {
      return Student.delete("id", studentId).map(id -> id > 0);
   }

   @Override
   public String toString() {
      return "School " + name;
   }
}
