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

import java.util.HashMap;
import java.util.Map;


public class RuleFactory {
    private static Map typeTypeMap = new HashMap();
    
    static {
        typeTypeMap.put("string", StringRule.class);
        typeTypeMap.put("cc", LuhnNumberRule.class);
        typeTypeMap.put("luhn", LuhnNumberRule.class);
        typeTypeMap.put("date", DateRule.class);
        typeTypeMap.put("ssn", SsnRule.class);
        typeTypeMap.put("number", NumberRule.class);
        typeTypeMap.put("int", NumberRule.class);
        typeTypeMap.put("float", NumberRule.class);
        typeTypeMap.put("phone", PhoneRule.class);
        typeTypeMap.put("email", EmailRule.class);
        typeTypeMap.put("zip", PostalCodeRule.class);
        typeTypeMap.put("url", UrlRule.class);
    }
    
    public static Rule createRule(String ruleString) {
        return createRule(ruleString, "no message");
    }
    

    public static Rule createRule(String ruleString, String message) {
        if (ruleString == null || ruleString.trim().length() == 0) {
            throw new IllegalArgumentException("rules can not be empty");
        }
        
        RuleDefinition pr = RuleParser.parseRuleString(ruleString);
        
        String type = pr.getType();
        
        Rule rule = null;
        
        Class clazz = (Class) typeTypeMap.get(type);
        
        if (clazz == null) {
            throw new IllegalArgumentException("unknown type: '" + type + "'");
        }
        
        try {
            rule = (Rule) clazz.newInstance();
            rule.init(pr);
        }
        catch (Exception e) {
            throw new IllegalStateException("could not instantiate rule class: " + clazz, e);
        }
        
        rule.setMessage(message);
        
        return rule;
    }
}
