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

import com.crunchbits.app.mpesa.config.Mpesa;
import com.crunchbits.app.mpesa.config.MpesaConfig;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author FMarube
 */
@Configuration
@ConditionalOnClass({Mpesa.class, DataSource.class})
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
@EnableConfigurationProperties(MpesaProperties.class)
public class MpesaAutoConfiguration {

    @Autowired
    private MpesaProperties mpesaProperties;

    /**
     * 
     * @return 
     */
    @Bean
    @ConditionalOnMissingBean
    public MpesaConfig mpesaConfig() {

        String applicationKey = mpesaProperties.getApplicationKey() == null ? "NULL" : mpesaProperties.getApplicationKey();
        String applicationSecret = mpesaProperties.getApplicationSecret() == null ? "NULL" : mpesaProperties.getApplicationSecret();
        String mpesaApiUrl = mpesaProperties.getMpesaApiUrl() == null ? "NULL" : mpesaProperties.getMpesaApiUrl();
        String refreshInterval = mpesaProperties.getCredentialRefreshInterval() == null ? "NULL" : mpesaProperties.getCredentialRefreshInterval();

        MpesaConfig mpesaConfig = new MpesaConfig();
        mpesaConfig.put("APPLICATION_KEY", applicationKey);
        mpesaConfig.put("APPLICATION_SECRET", applicationSecret);
        mpesaConfig.put("MPESA_API_URL", mpesaApiUrl);
        mpesaConfig.put("REFRESH_INTERVAL", refreshInterval);
        return mpesaConfig;
    }

    /**
     * 
     * @param mpesaConfig
     * @return 
     */
    @Bean
    @ConditionalOnMissingBean
    public Mpesa mpesa(MpesaConfig mpesaConfig) {
        return new Mpesa(mpesaConfig);
    }
}
