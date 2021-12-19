package it.codeland.academy.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Locale;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Holamundo {

  @Inject
  @Named("jcr:title")
  @Required
  private String title;

  @Inject
  @Named("jcr:description")
  private String description;

  @PostConstruct
  protected void init() {
    if (description != null && !description.isEmpty()) {
      this.description = this.description.toUpperCase(Locale.ROOT);
    } else {
      this.description = "Not Available";
    }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
