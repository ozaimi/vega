package vega.model;

@lombok.Setter
@lombok.Getter
public class ManualMerge {
    private Long id;
    private Long version;
    private String sourcePortfolio;
    private String targetPortfolio;

}
