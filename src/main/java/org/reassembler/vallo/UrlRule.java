package org.reassembler.vallo;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlRule extends DefaultRule {

    public Result test(String string) {
        Result r = new Result();
        
        try {
            new URL(string);
        }
        catch (MalformedURLException e) {
            r.addFailure("invalid url: " + string);            
        }

        return r;
    }
}
