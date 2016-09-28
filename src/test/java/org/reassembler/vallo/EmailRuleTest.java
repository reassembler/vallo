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

import junit.framework.TestCase;;

public class EmailRuleTest extends TestCase {
    public void testEmailRule() {
        String ruleS = "email:email;";
        String []goods = {
                "dave@cool.com",
                "nestor@plexor.org",
                "ozzy@cst.com",
                "ken@follet.com",
                "des.d.wellington@gmail.com",
                "pam@genscrape.org",
        };
        String []bads = {
                "nas@plos",
                "goody()null_ATT_notgood.com",
                "reass@dsoledotcom",
                "dave @cool.com",
                "dave@\tcool.com",
        };
        
        Rule rule = RuleFactory.createRule(ruleS);
        
        for (int i = 0; i < goods.length; i++) {
            assertTrue(rule.isValid(goods[i]));
        }
        
        for (int i = 0; i < bads.length; i++) {
            String test = bads[i];
            boolean re = rule.isValid(test);
            if (re) {
                System.out.println(";");
            }
            assertFalse(rule.isValid(bads[i]));
        }
    }
}
