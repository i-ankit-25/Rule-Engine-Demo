package com.example.workflow.service;

import com.example.workflow.RuleResponse;
import com.example.workflow.dao.RuleDAO;
import com.example.workflow.model.Rule;
import org.apache.commons.jexl3.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {

    private final RuleDAO ruleDAO;

    @Autowired
    public RuleService(RuleDAO ruleDAO) {
        this.ruleDAO = ruleDAO;
    }

    public List<Rule> getAllRules() {
        return ruleDAO.getAllRules();
    }

    public List<RuleResponse> evaluateRules(String role, String region) {
        List<Rule> allRules = ruleDAO.getAllRules();
        List<RuleResponse> matchedActionsWithSection = new ArrayList<>();

        // Create a JEXL engine
        JexlEngine jexl = new JexlBuilder().create();
        JexlContext context = new MapContext();

        // Set the role and region in the JEXL context
        context.set("role", role);
        context.set("region", region);

        for (Rule rule : allRules) {
            try {
                JexlExpression expression = jexl.createExpression(rule.getRuleExpression());
                Object result = expression.evaluate(context);

                if (result instanceof Boolean && (Boolean) result) {
                    matchedActionsWithSection.add(new RuleResponse(rule.getAttribute(), rule.getRuleAction(), rule.getRuleSection()));
                }
            } catch (Exception e) {
                System.err.println("Error evaluating rule expression: " + rule.getRuleExpression());
                e.printStackTrace();
            }
        }

        return matchedActionsWithSection;
    }
}
