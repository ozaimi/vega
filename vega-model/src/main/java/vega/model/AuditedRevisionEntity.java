package vega.model;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@RevisionEntity(AuditingRevisionListener.class)
@Entity
public class AuditedRevisionEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 1L;

    private String userName;

    public String getUser() {

        return userName;
    }

    public void setUser(String userName) {

        this.userName = userName;
    }
}