
Feature: controlling
Scenario: controlling order
  Given there are 12 cucumbers      # <start> replaced with 12
  When I eat 5 cucumbers            # <eat> replaced with 5
  Then I should have 7 cucumbers    # <left> replaced with 7

