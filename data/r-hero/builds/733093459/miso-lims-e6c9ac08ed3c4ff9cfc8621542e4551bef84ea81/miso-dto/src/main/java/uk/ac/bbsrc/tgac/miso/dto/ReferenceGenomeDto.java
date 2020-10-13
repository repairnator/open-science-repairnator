package uk.ac.bbsrc.tgac.miso.dto;

public class ReferenceGenomeDto {

  private Long id;
  private String alias;
  private Long defaultScientificNameId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public Long getDefaultScientificNameId() {
    return defaultScientificNameId;
  }

  public void setDefaultScientificNameId(Long defaultScientificNameId) {
    this.defaultScientificNameId = defaultScientificNameId;
  }

}
