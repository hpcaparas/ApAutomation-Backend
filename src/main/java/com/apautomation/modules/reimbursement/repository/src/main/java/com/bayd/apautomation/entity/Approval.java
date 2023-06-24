package com.bayd.apautomation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Approvals", schema = "apautomation")
@Data
@AllArgsConstructor
@Builder
@ToString
public class Approval extends AbstractEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "appid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID appid;
    @Column(name = "status")
    private String status;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "comments")
    private String comments;
    @Column(name = "approved_on")
    private Date approvedOn;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Bill.class)
    @JoinColumn(name = "billid")
    private Bill approvalbill;
    public Approval() {
        this.appid = UUID.randomUUID();
    }

}
