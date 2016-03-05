package vega.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
@Getter  @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private int id;

    @Version
    @Column(name = "version",nullable = false)
    private long version;

    @Column(name = "name",nullable = false,length = 50)
    private String name;
}
