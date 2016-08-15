package vega.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ptf_routing_rule")
@Audited
@SequenceGenerator(name="ptf_routing_rule", sequenceName="ptf_routing_rule_seq")
public class PortfolioRoutingRule {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ptf_routing_rule")
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "source",nullable = false,length = 50)
    private String source;

    @Column(name = "backendReference",nullable = false,length = 50)
    private String backendReference;

    @Column(name = "target",nullable = false,length = 50)
    private String target;
}
