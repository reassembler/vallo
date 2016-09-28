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

public class LuhnRuleTest extends TestCase {
    public void testLuhnRule() {
        String ruleS = "ccnumber:cc;";
        String []goods = {"5105105105105100", 
                "5105 1051 0510 5100",
                "4012 8888 8888 1881",
                "3566002020360505",
                "3787-3449-3671-000",
                "305,6930,9025,904",
                };
        
        String []bads = {"some twonky stuff 2",
                "4412 8888 8882 1881",
                "1231231238712491732498732497324983274892a",
                "123",
                "asdkjhdaskhdaskhdaskhdaskhd"
        };
        
        Rule rule = RuleFactory.createRule(ruleS);
        
        for (int i = 0; i < goods.length; i++) {
            Result r = rule.test(goods[i]);
            assertTrue(r.isValid());
            
            if (!r.isValid()) {
                System.out.println(r.getFailures());
            }
        }
        
        for (int i = 0; i < bads.length; i++) {
            assertFalse(rule.isValid(bads[i]));
        }
    }

}
