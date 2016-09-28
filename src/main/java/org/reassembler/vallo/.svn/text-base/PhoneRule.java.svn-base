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

public class PhoneRule extends DefaultRule {
    public Result test(String string) {
        String format = (String) this.parsedRules.get("format");
        int minDigits = -1;
        int maxDigits = 10;
        
        if (format == null || format.equals("us")) {
            minDigits = 10;
        }
        else if (format.equals("us.local")) {
            minDigits = 7;
        }
        else {
            throw new IllegalArgumentException("unknown phone format: '" + format + "'");
        }
        
        int digitCount = 0;
        char []digits = string.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (Character.isDigit(digits[i])) {
                digitCount++;
            }
        }
        
        Result result = new Result();
        if (digitCount < minDigits) {
            String msg = "not enough digits";
            result.addFailure(getFailureMessage(msg));
        }
        else if (digitCount > maxDigits) {
            String msg = "too many digits";
            result.addFailure(getFailureMessage(msg));
        }
        
        return result;
    }
}
