/*
 *  Copyright 2008-2009 David Petersheim 
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
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ValloTest extends TestCase {
    public void testTestCreditCardDate() {
        Result r = Vallo.testCreditCardDate("1", "2020", null);
        
        if (!r.isValid()) {
            System.out.println(DefaultRule.dumpResult(r));
        }
        
        assertTrue(r.isValid()) ;
        
        
        r = Vallo.testCreditCardDate("1", "2000", null);
        
        assertFalse(r.isValid()) ;
 
    }
    
    public void testTestCreditCardType() {
        String []types = {
                "visa",
                "discover",
                "mastercard",
                "mastercard",
                "mastercard",
                "mastercard",
                "jcb",
                "diners",
                "amex",
                "switch"
        };
        
        String []accts = {
              "4128003178276773",  
              "6011000990139424",  
              "5555555555554444",  
              "5105105105105100",  
              "5263067393947775",  
              "5446882375129691",  
              "3566002020360505",  
              "30569309025904",  
              "378282246310005",  
              "6331101999990016",  
        };
        
        for (int i = 0; i < accts.length; i++) {
            String acct = accts[i];
            String type = types[i];
            
            Result r = Vallo.testTypeMatchesAccount(type, acct);
            
            if (!r.isValid()) {
                System.out.println(DefaultRule.dumpResult(r));
            }
            
            assertTrue(r.isValid());
        }
        
        
       types = new String[] {
                "visa",
                "discover",
                "amex",
                "mastercard",
        };
        
        accts = new String[] {
              "6011000990139424",  
              "4128003178276773",  
              "6331101999990016",  
              "378282246310005",  
        };
        
        for (int i = 0; i < accts.length; i++) {
            String acct = accts[i];
            String type = types[i];
            
            Result r = Vallo.testTypeMatchesAccount(type, acct);
            
            assertFalse(r.isValid());
        }
 
        
        Result r = Vallo.testTypeMatchesAccount("nonononoeeeeeeooo", accts[0]);
        assertFalse(r.isValid());
    }
    
    public void testIsValidMap() {
        Map rules = new HashMap();
        
        rules.put("name", "name; min:3; regex:[a-zA-Z]+; max:20;");
        rules.put("age", "age:number; min:18; max:46;");
        rules.put("address", "address; max:30; regex:[0-9a-zA-Z \\.]+;");        
        rules.put("zip", "zip; regex:[0-9]{5}(-[0-9]{4})?; max:10; min:5;");
        rules.put("username", "username; regex:[a-zA-Z]{4,13};");
        rules.put("social", RuleFactory.createRule("social:ssn;", "social must look like this xxx-xx-xxxx")); 
       
        Map values = new HashMap();
        
        values.put("name", "dave");
        values.put("age", "45");
        values.put("zip", "40203");
        values.put("address", "438 S. 12th ST");
        values.put("username", "valkyrie");
        values.put("social", "111-22-1111");
        
        
        Result r = Vallo.test(values, rules);
        
        assertTrue(r.isValid());
        
        
        values.remove("name");
        
        r = Vallo.test(values, rules);
        assertFalse(r.isValid());
        assertEquals(1, r.getFailures().size());
    }
    
    
    public void testAddRule() {
        Vallo v = new Vallo();
        
        v.addRule("age:number;", "${name} must be a number");
        
        assertEquals(1, v.getRules().size());
        

        v = new Vallo();
        
        try {
            v.addRule("Employee_Id_Cancel:regex:[0-9]{6};", "Employee ID # must be six digits");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            
        }
        
        
        v = new Vallo();
        try {
            v.addRule("num:int;", "Employee ID # must be six digits");
            v.addRule("num:date;", "Employee ID # must be six digits");
            
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    
    
    public void testTest() {
        Vallo v = new Vallo();
        v.addRule("social:ssn;", "invalid ssn");
        v.addRule("social2:ssn;", "invalid ssn2");
        v.addRule("birthday:date; when:past;", "invalid date");
        v.addRule("phone1:phone;", "invalid phone number");
        
        
        
        Map values = new HashMap();
        values.put("social", "111-22-2222");
        values.put("social2", "111222222");
        values.put("birthday", "3/1/2008");
        values.put("phone1", "(312) 583 - 7827");
        
        Result r = v.test(values);
        
        assertTrue(r.isValid());
    }
    
    public void testGroupOptional() {
        Vallo v = new Vallo();
        v.addRule("social:ssn; goptional:true;", "invalid ssn");
        v.addRule("social2:ssn;", "invalid ssn2; goptional:false;");
        v.addRule("birthday:date; when:past; goptional:true;", "invalid date");
        v.addRule("phone1:phone; goptional:true;", "invalid phone number");
        
        Map values = new HashMap();
        values.put("social2", "111222222");
        values.put("birthday", "3/1/2008");
        
        Result r = v.test(values);
        
        assertTrue(r.isValid());
        
        
        
        v = new Vallo();
        v.addRule("social:ssn; goptional:true;", "invalid ssn");
        v.addRule("social2:ssn; optional:false;", "invalid ssn2;");
        v.addRule("birthday:date; when:past; goptional:true;", "invalid date");
        v.addRule("phone1:phone;", "invalid phone number");
        
        values = new HashMap();
        values.put("social", "111222222");
        values.put("birthday", "3/1/2008");
        
        r = v.test(values);
        
        assertFalse(r.isValid());
 
        
        v = new Vallo();
        v.addRule("social:ssn; ", "invalid ssn");
        v.addRule("social2:ssn;", "invalid ssn2");
        v.addRule("birthday:date; when:past; goptional:true;", "invalid date");
        v.addRule("phone1:phone;", "invalid phone number");
        
        values = new HashMap();
        values.put("social", "111222222");
        values.put("birthday", "3/1/2008");
        
        r = v.test(values);
        
        assertFalse(r.isValid());
    }
    
    
    public void testFailureOrder() {
        Vallo v = new Vallo();
        int ruleCount = 100;
        
        for (int i = 0; i < ruleCount; i++) {
            String ruleName = "test_" + i;
            v.addRule(ruleName + ":number;", String.valueOf(i));
        }
        
        Result r = v.test(new HashMap());
        
        assertFalse(r.isValid());
        
        List fs = r.getFailures();
        for (int i = 0; i < ruleCount; i++) {
            assertEquals(String.valueOf(i), ((Failure) fs.get(i)).getMessage());
        }
    }
}
