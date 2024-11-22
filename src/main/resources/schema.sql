CREATE TABLE IF NOT EXISTS rules (
    rule_id VARCHAR(255) PRIMARY KEY,
    rule_expression TEXT,
    rule_action VARCHAR(255),
    rule_section VARCHAR(255),
    attribute VARCHAR(255)
);
