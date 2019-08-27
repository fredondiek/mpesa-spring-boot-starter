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
package com.crunchbits.app.mpesa.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceInitializationMode;

/**
 *
 * @author FMarube
 */
@ConfigurationProperties(prefix = "crunchbits.safaricom.mpesa")
public class MpesaProperties {
 
    private static final String DEFAULT_SCHEMA_LOCATION = "classpath:com/crunchbits/app/" + "mpesa/core/schema-@@platform@@.sql";

    /**
     * Path to the SQL file to use to initialize the database schema.
     */
    private String schema = DEFAULT_SCHEMA_LOCATION;

    /**
     * Table prefix for all the batch meta-data tables.
     */
    private String tablePrefix;

    /**
     * Database schema initialization mode.
     */
    private DataSourceInitializationMode initializeSchema = DataSourceInitializationMode.EMBEDDED;
    
    
    /**
     * Database schema initialization mode.
     */
    private String applicationKey;
    
    
    /**
     * Database schema initialization mode.
     */
    private String applicationSecret;
    
    
    /**
     * Database schema initialization mode.
     */
    private String mpesaApiUrl;
    
    
    /**
     * Database schema initialization mode.
     */
    private String credentialRefreshInterval;
    
    
    /**
     * 
     * @return 
     */
    public String getSchema() {
        return schema;
    }

    /**
     * 
     * @param schema 
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    
    /**
     * 
     * @return 
     */
    public String getTablePrefix() {
        return tablePrefix;
    }

    /**
     * 
     * @param tablePrefix 
     */
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    
    /**
     * 
     * @return 
     */
    public DataSourceInitializationMode getInitializeSchema() {
        return initializeSchema;
    }

    
    /**
     * 
     * @param initializeSchema 
     */
    public void setInitializeSchema(DataSourceInitializationMode initializeSchema) {
        this.initializeSchema = initializeSchema;
    }

    
    /**
     * 
     * @return 
     */
    public String getApplicationKey() {
        return applicationKey;
    }

    
    /**
     * 
     * @param applicationKey 
     */
    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    
    /**
     * 
     * @return 
     */
    public String getApplicationSecret() {
        return applicationSecret;
    }

    
    /**
     * 
     * @param applicationSecret 
     */
    public void setApplicationSecret(String applicationSecret) {
        this.applicationSecret = applicationSecret;
    }

    
    /**
     * 
     * @return 
     */
    public String getMpesaApiUrl() {
        return mpesaApiUrl;
    }

    
    /**
     * 
     * @param mpesaApiUrl 
     */
    public void setMpesaApiUrl(String mpesaApiUrl) {
        this.mpesaApiUrl = mpesaApiUrl;
    }

    
    /**
     * 
     * @return 
     */
    public String getCredentialRefreshInterval() {
        return credentialRefreshInterval;
    }

    
    /**
     * 
     * @param credentialRefreshInterval 
     */
    public void setCredentialRefreshInterval(String credentialRefreshInterval) {
        this.credentialRefreshInterval = credentialRefreshInterval;
    }
        
}
