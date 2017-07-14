/**
 *
 * Copyright 2014 Travo, Inc. All rights reserved.
 * PreferenceProvider.java
 *
 */
package online.u148.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kevin Dong on 2016/5/10.
 * */
public class PreferenceProvider extends AbstractProvider
{
    
    public static int MODE_PRIVATE = Context.MODE_PRIVATE;
    
    public static int MODE_MULTI_PROCESS = Context.MODE_MULTI_PROCESS;
    
    public static int MODE_APPEND = Context.MODE_APPEND;
    
    public static int MODE_ENABLE_WRITE_AHEAD_LOGGING = Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;
    
    protected int mode;
    
    private SharedPreferences preference;
    
    public PreferenceProvider(String name, int mode)
    {
        super(name);
        setMode(mode);
        preference = ApplicationUtil.getContext().getSharedPreferences(name, mode);
    }
    
    private void reloadPreferenceIfNecessary()
    {
        if((mode & Context.MODE_MULTI_PROCESS) != 0)
        {
            preference = ApplicationUtil.getContext().getSharedPreferences(name, mode);
        }
    }
    
    public int getInt(String key)
    {
        reloadPreferenceIfNecessary();
        return preference.getInt(key, Integer.MIN_VALUE);
    }
    
    public int getInt(String key, int defaultValue)
    {
        reloadPreferenceIfNecessary();
        return preference.getInt(key, defaultValue);
    }

    public boolean putInt(String key, int value)
    {
        reloadPreferenceIfNecessary();
        return preference.edit().putInt(key, value).commit();
    }
    
    public long getLong(String key)
    {
        reloadPreferenceIfNecessary();
        return preference.getLong(key, Long.MIN_VALUE);
    }
    
    public long getLong(String key, long defaultValue)
    {
        reloadPreferenceIfNecessary();
        return preference.getLong(key, defaultValue);
    }

    public boolean putLong(String key, long value)
    {
        reloadPreferenceIfNecessary();
        return preference.edit().putLong(key, value).commit();
    }

    public boolean getBoolean(String key)
    {
        reloadPreferenceIfNecessary();
        return preference.getBoolean(key, false);
    }
    
    public boolean getBoolean(String key, boolean defaultValue)
    {
        reloadPreferenceIfNecessary();
        return preference.getBoolean(key, defaultValue);
    }

    public boolean putBoolean(String key, boolean value)
    {
        reloadPreferenceIfNecessary();
        return preference.edit().putBoolean(key, value).commit();
    }

    public double getFloat(String key)
    {
        reloadPreferenceIfNecessary();
        return preference.getFloat(key, Float.MIN_VALUE);
    }
    
    public float getFloat(String key, float defaultValue)
    {
        reloadPreferenceIfNecessary();
        return preference.getFloat(key, defaultValue);
    }

    public boolean putFloat(String key, int value)
    {
        reloadPreferenceIfNecessary();
        return preference.edit().putFloat(key, value).commit();
    }

    public String getString(String key)
    {
        reloadPreferenceIfNecessary();
        return preference.getString(key, "");
    }
    
    public String getString(String key, String defaultValue)
    {
        reloadPreferenceIfNecessary();
        return preference.getString(key, defaultValue);
    }

    public boolean putString(String key, String value)
    {
        reloadPreferenceIfNecessary();
        return preference.edit().putString(key, value).commit();
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }
}
