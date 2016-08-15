package vega.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vega.model.PortfolioRoutingRule;

/*


create sequence ptf_routing_rule_seq 100;

CREATE TABLE ptf_routing_rule
(
  id bigint NOT NULL,
  source character varying(50) NOT NULL,
  backend_reference varying(50) NOT NULL,
  target character varying(50) NOT NULL,
  CONSTRAINT ptf_routing_rule_pkey PRIMARY KEY (id)
)


CREATE TABLE ptf_routing_rule_aud
(
  id bigint NOT NULL,
  source character varying(50) NOT NULL,
  target character varying(50) NOT NULL,
  REV INT NOT NULL,
  REVTYPE INT NOT NULL,
  PRIMARY KEY(ID,REV)
)



 */


@Repository
@Transactional(propagation= Propagation.REQUIRED)
public interface PortfolioRoutingRepository extends CrudRepository<PortfolioRoutingRule,Long> {
}
