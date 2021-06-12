package com.user.Infrastructure.Entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;


@Table(name = "EVENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id ;

    @Column(name = "type")
    private String eventType;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "data")
    private String data;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

}
