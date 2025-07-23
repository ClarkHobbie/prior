package com.ltsllc.prior.spec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specification {
    protected String regex;
    protected Map<String, String> characterClasses = new HashMap<>();
    protected String mustHaves;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Map<String, String> getCharacterClasses() {
        return characterClasses;
    }

    public void setCharacterClasses(Map<String, String> characterClasses) {
        this.characterClasses = characterClasses;
    }

    public String getMustHaves() {
        return mustHaves;
    }

    public void setMustHaves(String mustHaves) {
        this.mustHaves = mustHaves;
    }

    /**
     * A method for parsing in a {@link Specification}.
     */
    public void parse(String string) {

    }
}
