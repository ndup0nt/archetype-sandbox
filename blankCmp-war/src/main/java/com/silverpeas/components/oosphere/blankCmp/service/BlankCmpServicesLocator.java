package com.silverpeas.components.oosphere.blankCmp.service;

import javax.inject.Inject;

import com.silverpeas.components.oosphere.blankCmp.blankStuff.BlankStuffRepository;

/**
 * Workaround to be able to retrieve services from DI context in Silverpeas view layer
 *
 */
public class BlankCmpServicesLocator {
    @Inject private BlankStuffRepository blankStuffRepository;

    private static class SingletonLoader {
        private static final BlankCmpServicesLocator _instance = new BlankCmpServicesLocator();
    }

    public static BlankCmpServicesLocator getInstance() {
        return SingletonLoader._instance;
    }

    public BlankStuffRepository getBlankStuffRepository() {
        return blankStuffRepository;
    }

}
