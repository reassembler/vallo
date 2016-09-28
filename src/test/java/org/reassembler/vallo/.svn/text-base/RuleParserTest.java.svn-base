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

import java.util.Map;

import junit.framework.TestCase;

public class RuleParserTest extends TestCase {
    public void testParseRuleString() {
        String ruleString = "uName:s; min:3; max:10; regex:[a-zA-Z]+[^\\;]+;";
        Map c = RuleParser.parseRuleString(ruleString).getParams();
        assertEquals(3, c.size());
        
        ruleString = "name; min:3;";
        c = RuleParser.parseRuleString(ruleString).getParams();
        assertEquals(1, c.size());
        
        ruleString = "name; min:3; ";
        c = RuleParser.parseRuleString(ruleString).getParams();
        assertEquals(1, c.size());
        
        ruleString = "name; min:3; regex:^\\;;";
        c = RuleParser.parseRuleString(ruleString).getParams();
        assertEquals(2, c.size());
        
        ruleString = "abit:string; min: 3;        regex :^\\;;";
        c = RuleParser.parseRuleString(ruleString).getParams();
        assertEquals(2, c.size());
    }
    
    public void testParseInvalidRules() {
        try {
            RuleParser.parseRuleString("Employee_Id_Cancel:regex:[0-9]{6};");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
System.out.println(e);            
        }

    }
}
