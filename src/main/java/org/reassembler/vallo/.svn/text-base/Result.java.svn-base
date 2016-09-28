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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Result {
    private List failures;
    
    public Result() {}
    
    public Result(Failure f) {
        this.failures = new ArrayList();
        this.failures.add(f);
    }
    
    public boolean isValid() {
        if (failures == null || failures.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    void addFailures(Collection c) {
        if (this.failures == null) {
            this.failures = new ArrayList();
        }
        
        this.failures.addAll(c);
    }
    
    void addFailure(Failure failure) {
        if (this.failures == null) {
            this.failures = new ArrayList();
        }
        
        this.failures.add(failure);
    }
    
    void addFailure(String msg) {
        addFailure(new Failure(msg));
    }

    public List getFailures() {
        if (this.failures != null) {
            return this.failures;
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }

     
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        if (isValid()) {
            sb.append("passed");
        }
        else {
            sb.append("failed");
        }
        
        
        if (this.failures == null) {
            return sb.toString();
            // EARLY OUT
        }
        
        sb.append(", ");
        Iterator it = this.failures.iterator();
        while (it.hasNext()) {
            Failure f = (Failure) it.next();
            sb.append(f.getMessage());
            
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        
        return sb.toString();
    }
}
