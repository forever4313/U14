/**
 *
 * Copyright 2014 Travo, Inc. All rights reserved.
 * TravoStorageManager.java
 *
 */
package online.u148.common.utils;

import java.util.Hashtable;

/**
 * Created by Kevin Dong on 2016/5/10.
 */
public class ProviderFactory
{
    
    public static final int PROVIDER_SHARED_PREFERENCE = 0;
    
    public static final int PROVIDER_FILE = 1;
    
    public static final int PROVIDER_DATA_BASE = 2;
    
    private Hashtable<String, IStorageProvider> providerCache;
    
    private static final ProviderFactory INSTANCE = new ProviderFactory(); 

    private  ProviderFactory()
    {
        providerCache = new Hashtable<>();
    }
  
    public static ProviderFactory getInstance()
    {
        return INSTANCE;
    }
    
    public IStorageProvider getProvider(String name)
    {
        return providerCache.get(name);
    }
    
    public PreferenceProvider getPreferenceProvider(String name, int mode)
    {
        IStorageProvider provider = getProvider(name);
        if(provider != null && provider instanceof PreferenceProvider)
            return ((PreferenceProvider) provider);
        PreferenceProvider preferenceProvider = new PreferenceProvider(name, mode);
        providerCache.put(name, preferenceProvider);
        return preferenceProvider;
    }
    
//    public FileProvider getFileProvider(String name)
//    {
//        IStorageProvider provider = getProvider(name);
//        if(provider != null && provider instanceof FileProvider)
//            return ((FileProvider) provider);
//        FileProvider fileProvider = new FileProvider(name);
//        providerCache.put(name, fileProvider);
//        return fileProvider;
//    }
//
//    public DatabaseProvider getDataProvider(String name)
//    {
//        IStorageProvider provider = getProvider(name);
//        if(provider != null && provider instanceof DatabaseProvider)
//            return ((DatabaseProvider) provider);
//        DatabaseProvider fileProvider = new DatabaseProvider(name, ApplicationUtil.getContext());
//        providerCache.put(name, fileProvider);
//        return fileProvider;
//    }
}
