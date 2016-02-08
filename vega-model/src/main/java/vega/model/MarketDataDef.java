package vega.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MarketDataDef extends BaseEntity{
    public String description;
}
