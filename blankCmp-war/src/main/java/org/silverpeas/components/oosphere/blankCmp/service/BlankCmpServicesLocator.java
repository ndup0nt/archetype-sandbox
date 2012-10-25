package org.silverpeas.components.oosphere.blankCmp.service;

import javax.inject.Inject;

import org.silverpeas.components.oosphere.blankCmp.blankStuff.BlankStuffService;

/**
 * Workaround to be able to retrieve services from DI context in Silverpeas view layer
 *
 */
public class BlankCmpServicesLocator {
    @Inject private BlankStuffService blankStuffService;

    private static class SingletonLoader {
        private static final BlankCmpServicesLocator _instance = new BlankCmpServicesLocator();
    }

    public static BlankCmpServicesLocator getInstance() {
        return SingletonLoader._instance;
    }

    public BlankStuffService getBlankStuffService() {
        return blankStuffService;
    }

}
