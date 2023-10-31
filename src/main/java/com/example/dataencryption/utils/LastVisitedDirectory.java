package com.example.dataencryption.utils;

import java.io.File;


public class LastVisitedDirectory {

    private static final LastVisitedDirectory instance = new LastVisitedDirectory();
    private boolean changed;
    private String path;

    private LastVisitedDirectory() {
        changed = false;
    }

    public static LastVisitedDirectory getInstance() {
        return instance;
    }

    public boolean wasVisited() {
        return changed;
    }

    public String getPath() {
        return path;
    }
}