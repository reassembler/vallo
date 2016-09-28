package org.reassembler.vallo;

import junit.framework.TestCase;

public class UrlRuleTest extends TestCase {
    public void testTest() {
        UrlRule rule = (UrlRule) RuleFactory.createRule("site:url;");
        
        Result r = rule.test("http://www.google.com/");
        
        if (!r.isValid()) {
            System.out.println(DefaultRule.dumpResult(r));
        }
        
        assertTrue(r.isValid());

        r = rule.test("htstp://www.google.com/");
        
        if (!r.isValid()) {
            System.out.println(DefaultRule.dumpResult(r));
        }
        
        assertFalse(r.isValid());
    }
}
