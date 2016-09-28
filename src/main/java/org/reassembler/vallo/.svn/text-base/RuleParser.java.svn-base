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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleParser {
    public static RuleDefinition parseRuleString(String ruleString) {
        char[] chars = ruleString.trim().toCharArray();
        boolean escaped = false;
        boolean foundName = false;
        RuleDefinition pr = new RuleDefinition();

        List parsed = new ArrayList();
        StringBuffer sb = new StringBuffer();

        int ecount = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (escaped) {
                sb.append(ch);
                escaped = false;
                continue;
            }

            switch (ch) {
            case '\\':
                escaped = true;
                ecount++;
                if ((i + 1) == chars.length) {
                    throw new IllegalStateException(
                            "bad syntax: 'escape character(\\) found at end of line'");
                }

                char peek = chars[i + 1];
                if (peek != ';') {
                    sb.append(ch);
                }

                break;

            case ';':
                String val = sb.toString();
                sb.setLength(0);
                
                if (!foundName) {
                    // first param is the name
                    foundName = true;
                    
                    String []parts = val.split(":");
                    
                    if (parts.length > 2) {
                        String msg = "invalid <name>:<type>; syntax '" 
                                + val + "', too many colons ':'";
                        
                        throw new IllegalArgumentException(msg);
                    }
                    
                    pr.setName(parts[0]);
                    
                    
                    // default type is string
                    if (parts.length != 2) {
                        pr.setType("string");
                    }
                    else {
                        pr.setType(parts[1]);
                    }
                }
                else {
                  parsed.add(val); 
                }
                break;

            default:
                sb.append(ch);
            }
        }

        if (sb.length() > 0) {
            throw new IllegalStateException(
             "rule not terminated, please end all rules with a semi-colon: '"
             + sb + "'");
        }
        
        if (!foundName) {
            throw new IllegalArgumentException("rule definition must have a name");
        }

        Map paired = new HashMap();
        String pattern = "\\s*([a-zA-Z]+)\\s*:\\s*(.+)\\s*";
        Pattern p = Pattern.compile(pattern);

        Iterator it = parsed.iterator();
        while (it.hasNext()) {
            String rule = (String) it.next();

            Matcher m = p.matcher(rule.trim());

            if (!m.matches()) {
                throw new IllegalArgumentException("invalid rule: '" + rule
                        + "'");
            }

            paired.put(m.group(1), m.group(2));
        }
        
        pr.setParams(paired);

        return pr;
    }
}
