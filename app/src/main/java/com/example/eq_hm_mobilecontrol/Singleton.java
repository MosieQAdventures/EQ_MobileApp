package com.example.eq_hm_mobilecontrol;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Singleton {

    // holds created instance of the singleton class
    private static Singleton Instance;
    public List<ProgressDescription> progressDescriptionList;

    // private constructor
    private Singleton () {

    }

    public static Singleton getInstance() {
        // return the created instance on all subsequent calls
        // checks if instance is created
        if (Instance == null)
        {
            // if note create one
            Instance = new Singleton();
        }
        // return the created instance on every subsequent call
        return Instance;
    }

    public List<ProgressDescription> getProgressDescriptionList() {
        return this.progressDescriptionList;
    }

    public void setProgressDescriptionList(List<ProgressDescription> progressDescriptionList) {
        this.progressDescriptionList = progressDescriptionList;
    }

    public void setProgressDescriptionIndividualListValue(String pdField_name, int index, int value) {
        getProgressDescriptionList().set(index, new ProgressDescription(pdField_name, value));
    }
}

/* not really working
// private constructor
    private Singleton (List<ProgressDescription> progressDescriptionList)
    {
        this.progressDescriptionList = progressDescriptionList;
    }

    public static Singleton getInstance(List<ProgressDescription> progressDescriptionList) {
        // return the created instance on all subsequent calls
        // checks if instance is created
        if (Instance == null)
        {
            // if note create one
            Instance = new Singleton(progressDescriptionList);
        }
        // return the created instance on every subsequent call
        return Instance;
    }*/
