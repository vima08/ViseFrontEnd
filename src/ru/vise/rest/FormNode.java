package ru.vise.rest;

import java.util.ArrayList;
import java.util.List;

public class FormNode {
    private String id;
    private String name;
    private String attr_id;
    private String type;
    private String title;
    private List<String> options;
    private String value;
    private String placeholder;

    public FormNode(){
        options = new ArrayList<String>();
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public List<String> getOptions() {
        return options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
