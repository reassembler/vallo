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

public class RuleTest extends TestCase {
    public void testMessages() {
        // string rule message
        String msg = "invalid name format";
        Rule r = RuleFactory.createRule("name; max:3;", msg);
        Result rt = r.test("sdasdasdasdasdddasd");
        Failure f = (Failure) rt.getFailures().get(0);
        assertEquals(msg, f.getMessage());
        
        // ssn rule msg
        r = RuleFactory.createRule("test_:ssn;", null);
        rt = r.test("sdasdasdasdasdddasd");
        f = (Failure) rt.getFailures().get(0);
        assertNotNull(f.getMessage());
        
        
        // date rule message
        msg = "invalid date was supplied";
        r = RuleFactory.createRule("test_:date;", msg);
        rt = r.test("sdasdasdasdasdddasd");
        f = (Failure) rt.getFailures().get(0);
        assertEquals(msg, f.getMessage());
        
        
        // email rule
        msg = "invalid email was supplied";
        r = RuleFactory.createRule("test_:email;", msg);
        rt = r.test("sdasdasdasdasdddasd");
        f = (Failure) rt.getFailures().get(0);
        assertEquals(msg, f.getMessage());
        
        // luhn rule
        msg = "invalid cc number supplied";
        r = RuleFactory.createRule("test_:cc;", msg);
        rt = r.test("sdasdasdasdasdddasd");
        f = (Failure) rt.getFailures().get(0);
        assertEquals(msg, f.getMessage());
        
        // number rule
        msg = "invalid number supplied";
        r = RuleFactory.createRule("test_:number;", msg);
        rt = r.test("sdasdasdasdasdddasd");
        f = (Failure) rt.getFailures().get(0);
        assertEquals(msg, f.getMessage());
        
    }
    
    public void testGroupOptional() {
        // number rule
        Rule r = RuleFactory.createRule("test_:number; goptional:true;");
        assertTrue(r.isGroupOptional());
        
        r = RuleFactory.createRule("test_:number; goptional:false;");
        assertFalse(r.isGroupOptional());
        
        r = RuleFactory.createRule("test_:number;");
        assertFalse(r.isGroupOptional());
    }
}
