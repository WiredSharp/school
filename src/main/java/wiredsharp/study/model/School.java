package wiredsharp.study.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Table(name="schools")
public class School extends PanacheEntity {

   public String name;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "school")
   public Set<Student> students;
}
