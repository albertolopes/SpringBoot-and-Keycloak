package com.beto.food.config;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import springfox.documentation.spring.web.plugins.Docket;

@Getter
public class DocketEvent extends ApplicationEvent {

    private final transient Docket docket;

    public DocketEvent(Object source, Docket docket) {
        super(source);
        this.docket = docket;
    }
}