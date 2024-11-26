package com.example.workflow;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleResponse {

    private String attribute;
    private String action;
    private String section;

    public RuleResponse(String attribute, String action, String section) {
        this.attribute = attribute;
        this.action = action;
        this.section = section;
    }
}

