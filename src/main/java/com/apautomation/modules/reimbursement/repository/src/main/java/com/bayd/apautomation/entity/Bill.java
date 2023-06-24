package com.bayd.apautomation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Bills", schema = "apautomation")
@Data
@AllArgsConstructor
@Builder
@ToString
public class Bill extends AbstractEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "billid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID billid;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "tax")
    private Float tax;
    @Column(name = "deptname")
    private String deptname;
    @Column(name = "filename")
    private String filename;
    @Column(name = "billdate")
    private Date billdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User billuser;
    @OneToMany(mappedBy = "approvalbill")
    private List<Approval> approvals;
    public Bill() {
        this.billid = UUID.randomUUID();
    }

}
