package com.example.workflow.controller;

import com.example.workflow.RuleResponse;
import com.example.workflow.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RuleController {

    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/rules")
    public List<RuleResponse> getAllRules() {
        return ruleService.getAllRules().stream()
                .map(rule -> new RuleResponse(rule.getAttribute(), rule.getRuleAction(), rule.getRuleSection()))
                .collect(Collectors.toList());
    }

    @GetMapping("/rules/filter")
    public List<RuleResponse> getFilteredRules(
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "region", required = false) String region) {
        return ruleService.evaluateRules(role, region);
    }

}
