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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateRule extends DefaultRule {
    public void init(RuleDefinition definition) {
        super.init(definition);
        
        if (!this.parsedRules.containsKey("format")) {
            this.parsedRules.put("format", "MM/dd/yyyy");
        }
    }

    public Result test(String string) {
        String format = (String) this.parsedRules.get("format");
        String when = (String) this.parsedRules.get("when");
        
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        
        Result result = new Result();
        
        try {
            Date d = sdf.parse(string);
            
            if (when != null) {
                validateWhen(d, when, result);
            }
        } 
        catch (ParseException e) {
            result.addFailure(getFailureMessage("invalid date: " + e));
        }
        
        return result;
    }

    private void validateWhen(Date date, String when, Result result) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        
        if (when.equals("past")) {
            if(!cal.before(GregorianCalendar.getInstance())) {
                result.addFailure(getFailureMessage("date must be in the past"));
            }
        }
        else if (when.equals("future")) {
            if (!cal.after(GregorianCalendar.getInstance())) {
                result.addFailure(getFailureMessage("date must be in the future"));
            }
        }
        else {
            throw new IllegalArgumentException("invalid 'when' value :'" + when + "'");
            // TODO move this check to the constructor
        }
    }
}
