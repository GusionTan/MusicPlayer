package com.example.test12;

import java.io.File;
import java.io.FilenameFilter;

class Tanr implements FilenameFilter {
    private String type;

    public Tanr(String type) {
        this.type = type;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(type);
    }
}