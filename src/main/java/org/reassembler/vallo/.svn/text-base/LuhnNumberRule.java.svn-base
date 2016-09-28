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

public class LuhnNumberRule extends DefaultRule {
    public Result test(String string) {
        char[] chs = string.toCharArray();
        int count = 0;
        int maxDigits = 19;
        int minDigits = 8; 
        Result result = new Result();
        int total = 0;
        boolean doubleDigit = false;

        for (int i = chs.length - 1; i >= 0; i--) {
            char ch = chs[i];

            if (!Character.isDigit(ch)) {
                continue;
            }

            count++;

            int digit = Character.getNumericValue(ch);

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit = ((digit % 10) + 1);
                }
            }

            total += digit;
            
            doubleDigit = !doubleDigit;
        }
        
        if (count > maxDigits) {
            result.addFailure(getFailureMessage("Number is too long"));
        }
        else if (count < minDigits) {
            result.addFailure(getFailureMessage("Number is too short"));
        }

        if (total % 10 > 0) {
            result.addFailure(getFailureMessage("number fails Luhn test"));
        }

        return result;
    }
}
