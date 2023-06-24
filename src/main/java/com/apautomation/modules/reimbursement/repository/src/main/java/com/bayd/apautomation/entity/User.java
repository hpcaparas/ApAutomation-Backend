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
@Table(name = "Users", schema = "apautomation")
@Data
@AllArgsConstructor
@Builder
@ToString
public class User extends AbstractEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "userid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID userid;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "isverified")
    private Boolean isverified;

    @OneToMany(mappedBy = "billuser")
    private List<Bill> bills;

    @ManyToMany
    @JoinTable(
            name = "user_department",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "deptid"))
    private List<Department> userdepartments;

    public User() {
        this.userid = UUID.randomUUID();
    }

}
