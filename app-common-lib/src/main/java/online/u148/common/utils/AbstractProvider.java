/**
 *
 * Copyright 2014 Travo, Inc. All rights reserved.
 * AbstractProvider.java
 *
 */
package online.u148.common.utils;

/**
 * Created by Kevin Dong on 2016/5/10.
 */
public abstract class AbstractProvider implements IStorageProvider
{
    protected String name;

    public AbstractProvider()
    {
        this("");
    }

    public AbstractProvider(String name)
    {
        setName(name);
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
