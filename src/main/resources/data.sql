INSERT INTO rules (rule_id, rule_expression, rule_action, rule_section, attribute)
VALUES
  ('R1', 'role.equalsIgnoreCase("buyer") && region.equalsIgnoreCase("UK")', 'view f1', 'Contact details', 'Contact Info'),
  ('R2', 'role.equalsIgnoreCase("seller") && region.equalsIgnoreCase("US")', 'view f2', 'Site details', 'Site Info'),
  ('R3', 'role.equalsIgnoreCase("buyer") && region.equalsIgnoreCase("US")', 'view f3', 'Links', 'Links Info'),
  ('R4', 'role.equalsIgnoreCase("buyer") && region.equalsIgnoreCase("US")', 'view document 1', 'Documents', 'Documents Info'),
  ('R5', 'role.equalsIgnoreCase("buyer")', 'view f6', 'Supplier details', 'Supplier Info'),
  ('R6', 'role.equalsIgnoreCase("seller") || region.equalsIgnoreCase("ROI")', 'view f5', 'Site details', 'Site Info')
ON CONFLICT (rule_id) DO NOTHING;
