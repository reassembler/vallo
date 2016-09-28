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

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

public class CcConstraintParserTest extends TestCase {
    public void testParseWord() {
        String def = "lengths:[15], prefixes:[301-332, 34, 37, 4, 6011 ] ";
        CcConstraintParser.Buffer b = new CcConstraintParser.Buffer(def);
        String word = CcConstraintParser.parseWord(b);

        assertEquals("lengths", word);
    }

    public void testParseIntArray() {
        int[] tints = new int[] { 15, 32, 666, 701, 702, 703, 704, 9000, 88888 };

        String def = "lengths:[15, 32,666, 701-704,    9000   , 88888], prefixes:[301-332, 34, 37, 4, 6011 ]";
        CcConstraintParser.Buffer b = new CcConstraintParser.Buffer(def);

        int[] ints = CcConstraintParser.parseIntArray(b);

        assertEquals(tints.length, ints.length);

        for (int i = 0; i < ints.length; i++) {
            assertEquals(tints[i], ints[i]);
        }
    }

    public void testParseStringArray() {
        String[] tstrings = { "301-332", "34", "37", "4", "6011" };

        String def = ":[301-332, 34, 37, 4, 6011 ] ";
        CcConstraintParser.Buffer b = new CcConstraintParser.Buffer(def);

        String[] strings = CcConstraintParser.parseStringArray(b);

        assertEquals(tstrings.length, strings.length);

        for (int i = 0; i < strings.length; i++) {
            assertEquals(tstrings[i], strings[i]);
        }
    }

    public void testMoveTo() {
        String def = "lengths:[15], prefixes:[301-332, 34, 37, 4, 6011 ] ";
        CcConstraintParser.Buffer b = new CcConstraintParser.Buffer(def);

        CcConstraintParser.moveTo("lengths", b);
        assertEquals(7, b.pos);

        CcConstraintParser.moveTo("prefixes", b);
        assertEquals(22, b.pos);

        try {
            b = new CcConstraintParser.Buffer(def);
            CcConstraintParser.moveTo("neverfindme", b);
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testParseConstraint() {
        String def = "lengths:[15], prefixes:[301-332, 34, 37, 4, 6011] ";

        Map constraint = CcConstraintParser.parseConstraint(def);
        assertEquals(2, constraint.size());

        assertNotNull(constraint.get("lengths"));
        assertNotNull(constraint.get("prefixes"));

        Object o = constraint.get("lengths");
        assertTrue(o instanceof int[]);

        o = constraint.get("prefixes");
        assertTrue(o instanceof String[]);

        String[] prefixes = (String[]) constraint.get("prefixes");
        int[] lengths = (int[]) constraint.get("lengths");

        assertEquals(5, prefixes.length);
        assertEquals(1, lengths.length);
    }

    public void testParseAllConstraints() throws IOException {
        Properties p = Vallo.creditCardConstraints;


        Iterator it = p.keySet().iterator();
        while (it.hasNext()) {
            String name = (String) it.next();
            String val = p.getProperty(name);
            CcConstraintParser.parseConstraint(val);
        }
    }
}
