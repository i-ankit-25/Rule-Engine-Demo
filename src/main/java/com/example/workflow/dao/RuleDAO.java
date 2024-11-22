package com.example.workflow.dao;

import com.example.workflow.model.Rule;
import com.example.workflow.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RuleDAO {

    private final RuleRepository ruleRepository;

    @Autowired
    public RuleDAO(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public List<Rule> getRulesByRoleAndRegion(String role, String region) {
        List<Rule> allRules = ruleRepository.findAll();
        return allRules.stream()
                .filter(rule -> rule.getRuleExpression().contains("role == \"" + role + "\"") &&
                        rule.getRuleExpression().contains("region == \"" + region + "\""))
                .collect(Collectors.toList());
    }
}
