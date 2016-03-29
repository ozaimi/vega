package vega.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Getter  @Setter
@Audited
@SequenceGenerator(name="user_gen", sequenceName="user_seq")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_gen")
    @Column(name = "id",nullable = false)
    private int id;

    @Version
    @Column(name = "version",nullable = false)
    private long version;

    @Column(name = "username",nullable = false,length = 50)
    private String userName;
}
