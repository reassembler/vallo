/*
 *  Copyright 2008 David Petersheim 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 *  
 */
package org.reassembler.vallo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Vallo {
    private Map rules = new LinkedHashMap();
    static Properties creditCardConstraints = new Properties();
    
    static {
        creditCardConstraints.setProperty("amex", "lengths:[15], prefixes:[34, 37]");
        creditCardConstraints.setProperty("china-union-pay", "lengths:[16-19], prefixes[622126-622925]");
        creditCardConstraints.setProperty("diners", "lengths:[14, 16], prefixes:[300-305, 36, 54, 55]");
        creditCardConstraints.setProperty("discover", "lengths:[16], prefixes:[6011, 60112-60114, 601174, 601177-601179, 601186-601199, 622126-622925, 644-649, 65]");
        creditCardConstraints.setProperty("jcb", "lengths:[16], prefixes:[3528-3589]");
        creditCardConstraints.setProperty("laser-debit", "lengths:[16-19], prefixes:[6304, 6706, 6771, 6709]");
        creditCardConstraints.setProperty("maestro-debit", "lengths:[12-19], prefixes:[5018,5020,5038,6304,6759,6761]");
        creditCardConstraints.setProperty("mastercard", "lengths:[16], prefixes:[51-55]");
        creditCardConstraints.setProperty("solo", "lengths:[16, 18, 19], prefixes:[6334, 6767]");
        creditCardConstraints.setProperty("switch", "lengths:[16, 18, 19], prefixes:[4903,4905,4911,4936,564182,633110,6333,6759]");
        creditCardConstraints.setProperty("visa", "lengths:[13, 16], prefixes:[4]");
        creditCardConstraints.setProperty("visa-electron", "lengths:[16], prefixes:[417500,4917,4913,4508,4844]");
    }

    public static boolean isValid(String value, String ruleString) {
        return test(value, ruleString).isValid();
    }

    public static Result test(String value, String ruleString) {
        return test(value, ruleString, null);
    }

    public static Result test(String value, String ruleString, String message) {
        // all values are required
        if (value == null) {
            Result r = new Result();
            r.addFailure("value was null");
            return r;
        }

        Rule rule = RuleFactory.createRule(ruleString, message);

        return rule.test(value);
    }

    public static Result test(Map values, Map ruleDefinitions) {
        Result r = new Result();

        Iterator it = ruleDefinitions.keySet().iterator();
        while (it.hasNext()) {
            String ruleName = (String) it.next();

            Object or = ruleDefinitions.get(ruleName);
            Rule rule = null;
            if (or instanceof String) {
                rule = RuleFactory.createRule((String) or,
                        "no message provided");
            }
            else if (or instanceof Rule) {
                rule = (Rule) or;
            }
            else {
                throw new IllegalArgumentException(
                        "object must be a Rule or a String defining the rule");
            }

            String val = null;

            Object o = values.get(ruleName);
            if (o instanceof String) {
                val = (String) o;
            }
            else if (o instanceof String[]) {
                String[] vals = (String[]) o;
                if (vals.length > 0) {
                    val = vals[0];
                }
            }

            if ((val == null || val.trim().length() == 0)) {
                if (!rule.isGroupOptional()) {
                    String msg = rule.getMessage();
                    msg = msg != null ? msg : "'" + ruleName
                            + "' is a required value";
                    r.addFailure(msg);
                }
                continue;
            }

            Result result = rule.test(val);

            r.addFailures(result.getFailures());
        }

        return r;
    }

    /**
     * @crap
     */
    public static Result testTypeMatchesAccount(String type, String account) {
        Properties ccConstraints = creditCardConstraints;
        Result re = new Result();
        
        String constraintDefinition = ccConstraints.getProperty(type);
        
        if (constraintDefinition == null) {
            re.addFailure("Invalid credit card type: '" + type + "'");
            return re;
            // EARLY OUT
        }
        
        Map constraintMap = CcConstraintParser.parseConstraint(constraintDefinition);
        constraintMap.put("type", type);
        
        return testTypeToAccount(constraintMap, account);
    }

    /**
     * @crap
     */
    private static Result testTypeToAccount(Map c, String account) {
        String type = (String) c.get("type");
        Result r = new Result();
        boolean lengthOk = false;
        int []lengths = (int []) c.get("lengths");
        for (int i = 0; i < lengths.length; i++) {
            int length = lengths[i];
            
            if (account.length() == length) {
                lengthOk = true;
                break;
            }
        }
        
        if (!lengthOk) {
            r.addFailure("Invalid account length for type(" + type + "): " + account);
        }
        
        boolean prefixOk = false;
        String []prefixes = (String[]) c.get("prefixes");
        for (int i = 0; i < prefixes.length; i++) {
            String prefix = prefixes[i];
            if (prefix.indexOf("-") != -1) {
                // range
                String []parts = prefix.split("-");
                int start = Integer.parseInt(parts[0].trim());
                int stop= Integer.parseInt(parts[1].trim());
                for (int j = start; j <= stop; j++) {
                    if (account.startsWith(String.valueOf(j))) {
                        prefixOk = true;
                        break;
                    }
                }
            }
            else {
                if (account.startsWith(prefix)) {
                    prefixOk = true;
                    break;
                }
            }
        }
        
        if (!prefixOk) {
            r.addFailure("Invalid account prefix for type(" + type + "): " + account);
        }
        
        return r;
    }

    public static boolean isValidTypeMatchesAccount(String type, String account) {
        return Vallo.testTypeMatchesAccount(type, account).isValid();
    }

    public void addRule(String ruleDefinition) {
        addRule(ruleDefinition, null);
    }

    public void addRule(String ruleDefinition, String message) {
        Rule r = RuleFactory.createRule(ruleDefinition, message);
        if (this.rules.put(r.getDefinition().getName(), r) != null) {
            throw new IllegalArgumentException("duplicate rule found for rule: '" 
                    + r.getDefinition().getName() + "'");
        }
    }

    public Result test(Map values) {
        return Vallo.test(values, this.rules);
    }

    public Map getRules() {
        return this.rules;
    }

    /**
     * Completely craptaculous. This method probably needs to be its own class.
     * 
     * @crap
     */
    public static Result testCreditCardDate(String month, String year, String message) {
        Result r = new Result();
        
        try {
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);
            
            Calendar c = GregorianCalendar.getInstance();
            
            int cm = c.get(Calendar.MONTH) + 1;
            int cy = c.get(Calendar.YEAR);
            
            if (y > cy) {
                return r;
                // EARLY OUT
            }
            
            if (y == cy) {
                if (m < cm) {
                    if (message == null) {
                        r.addFailure("card is expired, month test failed");
                    }
                    else {
                        r.addFailure(message);
                    }
                }
                 
                return r;
                // EARLY OUT
            }
            
            
            if (y < cy) {
                if (message == null) {
                    r.addFailure("card is expired, year test failed");
                }
                else {
                    r.addFailure(message);
                }
                
                return r;
                // EARLY OUT
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            if (message == null) {
                r.addFailure(e.toString());
            }
            else {
                r.addFailure(message);
            }
        }
            
            
        return r;
    }
}
