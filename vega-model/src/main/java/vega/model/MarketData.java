package vega.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@MappedSuperclass

public class MarketData extends BaseEntity{

    private long marketDataDefId;
    private BigDecimal value;
    private Date date;
}
