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

/**
 * A rule that makes a simple syntax check of the supplied String.
 * If the string contains one @ sign and one '.' in the domain portion
 * of the address, then it's a winner as far as EmailRule is concerned.
 * 
 *
 */
public class EmailRule extends DefaultRule {
    public Result test(String string) {
        Result result = new Result();
        
        if (!string.replaceAll("\\s", "").equals(string)) {
            result.addFailure(getFailureMessage("invalid email address"));
            return result;
            // EARLY OUT
        }
        
        
        String []parts = string.split("@");
        
        
        if (parts.length != 2) {
            result.addFailure(getFailureMessage("invalid email address"));
            return result;
            // EARLY OUT
        }
        
        String []smallerParts = parts[1].split("\\.");
        if (smallerParts.length != 2) {
            result.addFailure(getFailureMessage("invalid email address"));
        }
        
        return result;
    }
}
