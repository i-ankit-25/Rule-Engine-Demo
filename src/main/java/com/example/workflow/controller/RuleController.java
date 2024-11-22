package com.example.workflow.controller;

import com.example.workflow.RuleResponse;
import com.example.workflow.service.RuleService;
import com.example.workflow.service.NgrokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RuleController {

    private final RuleService ruleService;
    private final NgrokService ngrokService;

    @Autowired
    public RuleController(RuleService ruleService, NgrokService ngrokService) {
        this.ruleService = ruleService;
        this.ngrokService = ngrokService;
    }

    @GetMapping("/rules")
    public List<String> getAllRules() {
        return ruleService.getAllRules().stream()
                .map(rule -> rule.getRuleAction())
                .collect(Collectors.toList());
    }

    @GetMapping("/rules/filter")
    public List<RuleResponse> getFilteredRules(
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "region", required = false) String region) {
        return ruleService.evaluateRules(role, region);
    }

    @GetMapping("/ngrok-url")
    public String getNgrokUrl() {
        return "Your public Ngrok URL is: " + NgrokService.getPublicUrl();
    }
}
