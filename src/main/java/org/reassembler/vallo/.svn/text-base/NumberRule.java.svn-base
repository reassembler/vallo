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

public class NumberRule extends DefaultRule {
    public Result test(String string) {
        double num;
        Result result = new Result();
        
            if (this.def.getType().equals("int")) {
                 try {
                    num = new Long(string).doubleValue();
                }
                catch (NumberFormatException e) {
                    result.addFailure(getFailureMessage("invalid integer format"));
                    return result;
                }
            }
            else {
                try {
                    num = new Double(string).doubleValue();
                }
                catch (NumberFormatException e) {
                    result.addFailure(getFailureMessage("invalid number format"));
                    return result;
                }
            }
        
        // check for min
        Double min = this.parsedRules.get("min") == null ? null : new Double((String) this.parsedRules.get("min"));
        
        if (min != null && num < min.doubleValue()) {
            String msg = "min test failed: expected( >= " + min + "), but found: " + num;
            result.addFailure(getFailureMessage(msg));
        }
        
        // check for max
        Double max = this.parsedRules.get("max") == null ? null : new Double((String) this.parsedRules.get("max"));
        
        if (max != null && num > max.doubleValue()) {
            String msg = "max test failed: expected( <= " + max + "), but found: " + num;
            result.addFailure(getFailureMessage(msg));
        }
        
        // check equals
        Double equal = this.parsedRules.get("equals") == null ? null : new Double((String) this.parsedRules.get("equals"));
        if (equal != null && num != equal.doubleValue()) {
            String msg = num + " equal failed; expected(" + equal + ") but found(" + num + ")";
            result.addFailure(getFailureMessage(msg));
        }
        
        return result;
    }
}
