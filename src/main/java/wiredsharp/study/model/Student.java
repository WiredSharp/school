package wiredsharp.study.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Table(name="students")
public class Student extends PanacheEntity {

   public String name;
   
   @ManyToOne
   @JoinColumn(name="school_id", nullable = false)
   @JsonIgnore()
   public School school;
}
