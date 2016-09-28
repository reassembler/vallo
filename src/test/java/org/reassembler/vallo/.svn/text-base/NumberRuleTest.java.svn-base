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

public class NumberRuleTest extends TestCase {
    public void testNumberValidation() {
        String stringRules = "type:number; min:3; max:18;";
        String goodInt = "12.3";
        String badInt = "21";
        String badInt2 = "-1";
        
        assertFalse(Vallo.isValid(badInt, stringRules));
        assertFalse(Vallo.isValid(badInt2, stringRules));
        assertFalse(Vallo.isValid("0", stringRules));
        
        assertTrue(Vallo.isValid(goodInt, stringRules));  
        
        stringRules = "type:number; min:10000.123; max:10000000.1233333;";
        assertTrue(Vallo.isValid("50000", stringRules));
        assertFalse(Vallo.isValid("5", stringRules));
        assertFalse(Vallo.isValid("10000.122", stringRules));
        assertFalse(Vallo.isValid("not a number", stringRules));
        
        assertTrue(Vallo.isValid("5000", "type:number; min:5000; max:5001;"));
        assertTrue(Vallo.isValid("5000", "type:number; min:5000; max:5000;"));
        assertTrue(Vallo.isValid("5001", "type:number; min:5000; max:5001;"));
        assertFalse(Vallo.isValid("5003", "type:number; min:5000; max:5001;"));
        
        assertFalse(Vallo.isValid("5003", "type:number; equals:5000;"));
        assertTrue(Vallo.isValid("5003", "type:number; equals:5003;"));
    }
    
    public void testIntValidator() {
        String stringRules = "type:number; min:3; max:18;";
        String goodInt = "12";
        String badInt = "21";
        String badInt2 = "-1";
        
        assertFalse(Vallo.isValid(badInt, stringRules));
        assertFalse(Vallo.isValid(badInt2, stringRules));
        assertFalse(Vallo.isValid("0", stringRules));
        
        assertTrue(Vallo.isValid(goodInt, stringRules));       
    }
    
    public void testMinLessThanMax() {
        try {
            RuleFactory.createRule("test_num:number; min:13; max:12;");
            assertFalse(true);
        }
        catch (IllegalStateException e) {
            assertTrue(true);
        }
    }
    
    public void testIntCreation() {
        Rule r = RuleFactory.createRule("test_num:int; min:3; max:12;");
        
        assertFalse(r.isValid("11.3"));
        assertTrue(r.isValid("12"));
    }
    public void testFloatCreation() {
        Rule r = RuleFactory.createRule("test_num:float; min:3; max:12;");
        
        assertTrue(r.isValid("11.3"));
    }
}
