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

public class RuleFactoryTest extends TestCase {
    
    public void testCreateRule() {
        Rule rule = RuleFactory.createRule("age:number;", "number is invalid");
        assertTrue(rule instanceof NumberRule);
        
        rule = RuleFactory.createRule("age:int;", "number is invalid");
        assertTrue(rule instanceof NumberRule);
        
        rule = RuleFactory.createRule("zip_code:zip;", "invalid zip");
        assertTrue(rule instanceof PostalCodeRule);
        
        rule = RuleFactory.createRule("price:float;", "number is invalid");
        assertTrue(rule instanceof NumberRule);
        
        rule = RuleFactory.createRule("test; min:3;", "string is invalid");
        assertTrue(rule instanceof StringRule);
        
        rule = RuleFactory.createRule("home_phone:phone; name:test; ", "invalid phone number");
        assertTrue(rule instanceof PhoneRule);
        
        rule = RuleFactory.createRule("ccnumber:cc; name:test;", "card number is invalid");
        assertTrue(rule instanceof LuhnNumberRule);
        
        rule = RuleFactory.createRule("email:email; name:test;", "invalid email address");
        assertTrue(rule instanceof EmailRule);
        
        rule = RuleFactory.createRule("birthdate:date; format:M:d:yyy; name:test; when:past;", "invalid date");
        assertTrue(rule instanceof DateRule);
        
        rule = RuleFactory.createRule("social:ssn;", "invalid ssn");
        assertTrue(rule instanceof SsnRule);
    }
}
