package com.example.workflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rule {

    @Id
    private String ruleId;
    private String ruleExpression;
    private String ruleAction;
    private String ruleSection;
    private String attribute;
}
