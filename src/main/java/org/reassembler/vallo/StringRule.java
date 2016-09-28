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


public class StringRule extends DefaultRule {
    public Result test(String string) {
        Result result = new Result();
        
        Integer min = 
            this.parsedRules.get("min") == null ? null : new Integer((String) this.parsedRules.get("min"));
        
        Integer max = 
            this.parsedRules.get("max") == null ? null : new Integer((String) this.parsedRules.get("max"));

        String regex = (String) this.parsedRules.get("regex");       
        
        if (max != null && string.length() > max.intValue()) {
            String msg = this.def.getName() + " length must be <= " + max;
            result.addFailure(getFailureMessage(msg));
        }

        if (min != null && string.length() < min.intValue()) {
            String msg = this.def.getName() + " length must be >= " + min;
            result.addFailure(getFailureMessage(msg));
        }

        if (regex != null && !matches(string, regex)) {
            String msg = this.def.getName() + " does not match regex: " + regex;
            result.addFailure(getFailureMessage(msg));
        }
        
        // check equals
        String equal = (String) this.parsedRules.get("equals");
        if (equal != null && !(equal.equals(string))) {
            String msg = "string equal failed; expected(" + equal + ") but found(" + string + ")";
            result.addFailure(getFailureMessage(msg));
        }
        
        return result;
    }
}
