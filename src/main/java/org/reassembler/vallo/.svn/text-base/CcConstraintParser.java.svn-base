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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class CcConstraintParser {
    static class Buffer {
        final String original;
        int pos;
        char[] buff;
        int max;
        int length;

        Buffer(String original) {
            this.original = original;
            this.buff = original.toCharArray();
            this.pos = 0;
            this.max = buff.length - 1;
            this.length = buff.length;
        }
    }

    public static Map parseConstraint(String def) {
        Buffer b = new Buffer(def);

        String lengths = "lengths";
        String prefixes = "prefixes";
        Map constraint = new HashMap();

        moveTo(lengths, b);
        constraint.put(lengths, parseIntArray(b));
        
        moveTo(prefixes, b);
        constraint.put(prefixes, parseStringArray(b));

        if (constraint.get(lengths) == null) {
            throw new IllegalArgumentException("could not find lengths array");
        }

        if (constraint.get(prefixes) == null) {
            throw new IllegalArgumentException("could not find prefixes array");
        }

        return constraint;
    }

    static void moveTo(String string, Buffer b) {
        int index = b.original.indexOf(string, b.pos);
        
        if (index == -1) {
            throw new IllegalArgumentException("could not find '" + string + "' in '" + b.original + "'");
        }
        
        b.pos = index + string.length();
    }

    static int[] parseIntArray(Buffer buff) {
        StringBuffer sb = new StringBuffer();
        boolean collect = false;

        for (int i = buff.pos; buff.pos < buff.length; buff.pos++, i++) {
            char ch = buff.buff[i];

            switch (ch) {
            case '[':
                collect = true;
                break;

            case ']':
                String[] intStrings = sb.toString().trim().split(",");
                List lints = new ArrayList();
                
                for (int j = 0; j < intStrings.length; j++) {
                    String []parts = intStrings[j].split("-");
                    if (parts.length == 1) {
                        lints.add(new Integer(parts[0].trim()));
                    }
                    else {
                        int start = Integer.parseInt(parts[0].trim());
                        int stop = Integer.parseInt(parts[1].trim());
                        
                        for (int k = start; k <= stop; k++) {
                            lints.add(new Integer(k));
                        }
                    }
                }
                int[] ints = new int[lints.size()];
                
                int j = 0;
                for (Iterator it = lints.iterator(); it.hasNext(); j++) {
                    Integer ii = (Integer) it.next();
                    ints[j] = ii.intValue();
                }
                return ints;

            default:
                if (collect) {
                    sb.append(ch);
                }
            }
        }

        return null;
    }

    static String parseWord(Buffer buff) {
        StringBuffer sb = new StringBuffer();

        for (int i = buff.pos; buff.pos < buff.length; buff.pos++, i++) {
            char ch = buff.buff[i];

            switch (ch) {
            case ':':
                return sb.toString().trim();

            default:
                sb.append(ch);
            }
        }

        return null;
    }

    static String[] parseStringArray(Buffer buff) {
        StringBuffer sb = new StringBuffer();
        boolean collect = false;
        List l = new ArrayList();

        for (int i = buff.pos; buff.pos < buff.length; buff.pos++, i++) {
            char ch = buff.buff[i];

            switch (ch) {
            case '\'':
                // fall through
            case '\"':
                if (collect) {
                    l.add(sb.toString().trim());
                    sb.setLength(0);
                }
                collect = !collect;
                break;
                
            case '[':
                collect = true;
                break;
                
            case ',':
                l.add(sb.toString().trim());
                sb.setLength(0);
                break;

            case ']':
                l.add(sb.toString().trim());
                return (String[]) l.toArray(new String[l.size()]);

            default:
                if (collect) {
                    sb.append(ch);
                }
            }
        }

        return null;
    }

}
