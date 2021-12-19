package it.codeland.academy.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SliderModel {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private List<String> slides = new ArrayList<>();

    @SlingObject
    private Resource componentResource;

    @PostConstruct
    protected void init(){


    }

    public List<String> getList() {
        try {
            Resource items = componentResource.getChild("items");
            if (items != null) {
                for (Resource slide: items.getChildren()) {
                    slides.add(slide.getValueMap().get("label", String.class));
                }
            }
        } catch (Exception e) {
            log.error("********** {}", e);
        }
        return slides;
    }

    public void setList(List<String> list) {
        this.slides = list;
    }
}
