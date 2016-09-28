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

import java.io.IOException;

import junit.framework.TestCase;

public class StringRuleTest extends TestCase {
    public void testStringValidator() throws IOException {
        String goodName = "dave";
        String badName = "dave1";
        String noName = null;
        String stringRules = "type:string; min:3; max:10; regex:[a-zA-Z]+;";
        
        assertFalse(Vallo.isValid(badName, stringRules));
        assertFalse(Vallo.isValid(noName, stringRules));
        
        assertTrue(Vallo.isValid(goodName, stringRules));
        
        assertFalse(Vallo.isValid("passwith!$!#$!specials", "specials; regex:[^~!@#$%\\^()_+\\\\|:\\'\"/?><.,`];"));
        
        assertFalse(Vallo.isValid("notequal", "cat; equals:equal;"));
        assertTrue(Vallo.isValid("notequal", "cat; equals:notequal;"));
        
        
        assertTrue(Vallo.isValid("12345", "category; min:5; max:5;"));
        
        assertTrue(Vallo.isValid("\\12345", "cat; min:5; regex:^\\\\[0-9]+;"));
    }
    
    public void testOneOf() {
        String ruleS = "choice; regex:(one|two|three|ten|orange);";
        Rule r = RuleFactory.createRule(ruleS);
        String []goods = {
                "one",
                "two",
                "three",
                "ten",
                "orange",
        };
        
        String []bads = {
                "ione",
                "itwo",
                "ithree",
                "tien",
                "oriange",
        };
        
        
        for (int i = 0; i < goods.length; i++) {
            String val = goods[i];
            assertTrue(r.isValid(val));
        }
        
        for (int i = 0; i < bads.length; i++) {
            String val = bads[i];
            assertFalse(r.isValid(val));
        }
    }
  
}
