<%
/*
 *  Copyright 2009 David Petersheim 
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
 %>

<%@page import='java.sql.*,java.util.*,org.reassembler.vallo.*' %>

<h2>Debug</h2>
<%
// a kindler, gentler way to protect ourselves from null values
final HttpServletRequest _request = request;
class FormValues {
    String get(String name) {
        String val = _request.getParameter(name);
        return val == null ? "" : val;
    }    
}

FormValues form = new FormValues();

Vallo v = new Vallo();

v.addRule("name; min:12;");
v.addRule("email:email; ", "invalid email address");
v.addRule("age:int;", "age must be a number");
v.addRule("social:ssn;");
v.addRule("phone:phone;", "invalid phone number");
v.addRule("birth_date:date; when:past; format:MM/dd/yyyy;", "you can't be born today");
v.addRule("cc_number:cc;", "what's in a number?");
v.addRule("zip:string; regex:\\s*\\d{5}(-\\d{4})?\\s*;");
v.addRule("integer:int;");

List errors = v.test(request.getParameterMap()).getFailures();


Map vals = new HashMap();
Enumeration keys = request.getParameterNames();
while (keys.hasMoreElements()) {
    String key = (String) keys.nextElement();
    String val = request.getParameter(key);

    out.println("<b>" + key + "</b>: " + val + "<br />\n");

    vals.put(key, val);
}    

%>

<h2>Form</h2>
<form action='./vallo.jsp' method='GET'>
<input type='hidden' name='paction' value='post' />
<br />Name: <input type='text' name='name' value='<%=form.get("name")%>'/>
<br />Email: <input type='text' name='email' value='<%=form.get("email")%>'/>
<br />Age: <input type='text' name='age' value='<%=form.get("age")%>'/>
<br />Social: <input type='text' name='social' value='<%=form.get("social")%>'/>
<br />Phone: <input type='text' name='phone' value='<%=form.get("phone")%>'/>
<br />BirthDate: <input type='text' name='birth_date' value='<%=form.get("birth_date")%>'/>
<br />CCNumber: <input type='text' name='cc_number' value='<%=form.get("cc_number")%>'/>
<br />Zip: <input type='text' name='zip' value='<%=form.get("zip")%>'/>
<br />Integer: <input type='text' name='integer' value='<%=form.get("integer")%>'/>
<br />Float: <input type='text' name='float' value='<%=form.get("float")%>'/>

<input type='submit' />
</form>

<%

if (errors.size() > 0) {
    out.print("<h2>Errors</h2>");
    out.print("<ul>\n");
    Iterator it = errors.iterator();
    while (it.hasNext()) {
      Failure f = (Failure) it.next();
      out.print("<li>" + f.getMessage() + "</li>\n");
    }
    out.print("</ul>\n");
}
%>
