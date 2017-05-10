package com.codecool.shop.controller;

import org.thymeleaf.templateresolver.TemplateResolver;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class ThymeleafController {

    private final ThymeleafTemplateEngine thymeleafEngine;

    public ThymeleafController() {
        TemplateResolver thymeleafResolver = new TemplateResolver();
        this.thymeleafEngine = new ThymeleafTemplateEngine(thymeleafResolver);
    }

    public ThymeleafTemplateEngine getThymeleafEngine() {
        return this.thymeleafEngine;
    }
}
