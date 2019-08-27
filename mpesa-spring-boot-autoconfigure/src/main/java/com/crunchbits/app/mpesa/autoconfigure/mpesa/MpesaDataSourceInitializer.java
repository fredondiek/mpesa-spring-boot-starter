/*
 * The MIT License
 *
 * Copyright 2019 FMarube.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package com.crunchbits.app.mpesa.autoconfigure.mpesa;

import com.crunchbits.app.mpesa.autoconfigure.MpesaProperties;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.AbstractDataSourceInitializer;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

/**
 *
 * @author FMarube
 */
public class MpesaDataSourceInitializer extends AbstractDataSourceInitializer {

    private final MpesaProperties properties;

    /**
     * 
     * @param dataSource
     * @param resourceLoader
     * @param properties 
     */
    public MpesaDataSourceInitializer(DataSource dataSource, ResourceLoader resourceLoader,
            MpesaProperties properties) {
        super(dataSource, resourceLoader);
        Assert.notNull(properties, "MpesaProperties must not be null");
        this.properties = properties;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    protected DataSourceInitializationMode getMode() {
        return this.properties.getInitializeSchema();
    }

    
    /**
     * 
     * @return 
     */
    @Override
    protected String getSchemaLocation() {
        return this.properties.getSchema();
    }

    /**
     * 
     * @return 
     */
    @Override
    protected String getDatabaseName() {
        String databaseName = super.getDatabaseName();
        if ("oracle".equals(databaseName)) {
            return "oracle10g";
        }
        return databaseName;
    }

}
