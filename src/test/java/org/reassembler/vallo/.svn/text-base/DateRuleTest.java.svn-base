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

import junit.framework.TestCase;

public class DateRuleTest extends TestCase {
    public void testValid() {
        String ruleS = "type:date; format: MM/dd/y;";
        
        Rule rule = RuleFactory.createRule(ruleS);
        
        assertTrue(rule.isValid("11/22/2008"));        
        assertTrue(rule.isValid("1/22/08"));        
        
        
        assertFalse(rule.isValid("/22/08"));        
    } 
    
     public void testValidWhen() {
        String ruleS = "birthday:date; format: MM/dd/y; when:past;";
        Rule r = RuleFactory.createRule(ruleS);
        
        assertTrue(r.isValid("1/1/1965"));
        assertFalse(r.isValid("1/1/2100"));
        
        ruleS = "type:date; format: MM/dd/y; when:future;";
        r = RuleFactory.createRule(ruleS);
        assertTrue(r.isValid("1/1/2100"));
        assertFalse(r.isValid("1/1/1965"));
        
        try {
            ruleS = "type:date; format: MM/dd/y; when:another;";
            r = RuleFactory.createRule(ruleS);
            r.isValid("1/1/2100");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {}
    } 
}
