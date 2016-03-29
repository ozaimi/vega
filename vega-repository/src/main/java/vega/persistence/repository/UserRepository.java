package vega.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vega.model.User;

/*

drop sequence user_seq
create sequence user_seq start 100;
SELECT nextval('user_seq');
 create table REVINFO (
        REV INT PRIMARY KEY,
        REVTSTMP bigint
    );

     create table REVINFO (
        REV INT PRIMARY KEY,
        REVTSTMP bigint
    );

    create table audited_revision_entity (
        ID INT,
         userName character varying(255),
        timestamp bigint

    );



CREATE TABLE app_user_aud
(
  id bigint NOT NULL,
  username character varying(255) NOT NULL,
  version bigint NOT NULL,
  REV INT NOT NULL,
  REVTYPE INT NOT NULL,
  PRIMARY KEY(ID,REV)
)


delete from app_user_aud
drop table audited_revision_entity
commit

select * from audited_revision_entity
select * from app_user_aud

CREATE TABLE public.app_user
(
  id bigint NOT NULL,
  username character varying(255) NOT NULL,
  version bigint NOT NULL,
  CONSTRAINT app_user_pkey PRIMARY KEY (id)
)

drop table app_user
select * from app_user_aud
select * from app_user

select * from app_user_aud

 audited_revision_entity
        (timestamp, user, id)



 */

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public interface UserRepository  extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
