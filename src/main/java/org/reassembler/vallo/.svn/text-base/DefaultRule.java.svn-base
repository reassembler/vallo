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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DefaultRule implements Rule {
    private String ruleString;
    private String name;
    private String message;
    protected Map parsedRules = new HashMap();
    protected RuleDefinition def;
    private boolean optional;
    
    public RuleDefinition getDefinition() {
        return this.def;
    }
    
    final protected String getFailureMessage(String msg) {
        if (this.message == null) {
            return msg;
        }
         
        return this.message;
    }
    
    public boolean isGroupOptional() {
        return this.optional; 
    }
    
    
    public void init(RuleDefinition pr) {
        this.def = pr;
        this.name = pr.getName();
        this.parsedRules = pr.getParams();
        
        this.optional = "true".equals(this.parsedRules.get("goptional"));
        
        // check for min and max
        String min = (String) this.parsedRules.get("min");
        String max = (String) this.parsedRules.get("max");
        
        if (min == null || max == null) {
            return;
            // EARLY OUT
        }
        
        try {
            Float fmin = new Float(min);
            Float fmax = new Float(max);
            
            if (fmin.floatValue() > fmax.floatValue()) {
                throw new IllegalArgumentException("min must be less than max: min(" + min + "), " +
                		"max(" + max + ") in rule: '" + ruleString + "'");
            }       
        }
        catch (NumberFormatException e) {
            // trouble down the road
        }
    }
    
    public boolean isValid(String value) {
        return test(value).isValid();
    }
    
    public String getRuleString() {
        return ruleString;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }
    
    public String toString() {
        return this.name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    protected boolean matches(String string, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        
        return m.matches();
    }
    
    static String dumpResult(Result r) {
        if (r.isValid()) {
            return "no failures";            
        }
       
        StringBuffer sb = new StringBuffer();
        for (Iterator it = r.getFailures().iterator(); it.hasNext();) {
            Failure f = (Failure) it.next();
            sb.append(f.getMessage()).append("\n");
        }
        
        return sb.toString();
    }
}
