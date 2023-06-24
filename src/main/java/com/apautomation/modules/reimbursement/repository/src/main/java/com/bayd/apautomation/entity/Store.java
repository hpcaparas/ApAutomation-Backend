package com.bayd.apautomation.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Stores", schema = "apautomation")
@Data
@AllArgsConstructor
@Builder
@ToString
public class Store extends AbstractEntity implements Serializable {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "storeid")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID storeid;
    @Column(name = "name")
    private String name;

    public Store() {
        this.storeid = UUID.randomUUID();
    }

}
