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

public class SsnRule extends DefaultRule {
    public Result test(String val) {
        Result r = new Result();
        
        if (!val.matches("\\d{3}-\\d{2}-\\d{4}")
                && !val.matches("\\d{9}")) {
            r.addFailure(getFailureMessage("not a valid format for ssn number"));
        }
        
        return r;
    }
}
