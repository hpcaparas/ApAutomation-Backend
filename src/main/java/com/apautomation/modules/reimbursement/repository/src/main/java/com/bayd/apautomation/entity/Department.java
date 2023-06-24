package com.bayd.apautomation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Departments", schema = "apautomation")
@Data
@AllArgsConstructor
@Builder
@ToString
public class Department extends AbstractEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "deptid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID deptid;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "userdepartments")
    List<User> users;
    public Department() {
        this.deptid = UUID.randomUUID();
    }

}
