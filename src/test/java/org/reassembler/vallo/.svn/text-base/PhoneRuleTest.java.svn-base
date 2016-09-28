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

public class PhoneRuleTest extends TestCase {
    public void testPhoneRule() {
        String stringRules = "type:phone; format:us;";
        String good = "502/5855*961";
        String local = "585-5961";
        String bad2 = "585-51";
        String tooBig = "123123123123123585-51";
        
        Rule rule = RuleFactory.createRule(stringRules, "failure message");
        
        assertTrue(rule.isValid(good));
        assertFalse(rule.isValid(local));
        assertFalse(rule.isValid(bad2));
        assertFalse(rule.isValid(tooBig));
        
        stringRules = "type:phone; format:us.local;";
        rule = RuleFactory.createRule(stringRules, "failure message");
        
        assertTrue(rule.isValid(good));
        assertTrue(rule.isValid(local));
        assertFalse(rule.isValid(bad2));
        assertFalse(rule.isValid(tooBig));
        
        
        stringRules = "type:phone;";
        rule = RuleFactory.createRule(stringRules, "failure message");
        
        assertTrue(rule.isValid(good));
        assertFalse(rule.isValid(local));
        assertFalse(rule.isValid(bad2));
        assertFalse(rule.isValid(tooBig));
    }

}
