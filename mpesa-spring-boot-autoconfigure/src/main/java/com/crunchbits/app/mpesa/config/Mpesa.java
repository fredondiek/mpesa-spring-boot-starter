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
package com.crunchbits.app.mpesa.config;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeoutException;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author FMarube
 */
public class Mpesa {

    
    
    private MpesaConfig mpesaConfig;

    
    /**
     * 
     * @param mpesaConfig 
     */
    public Mpesa(MpesaConfig mpesaConfig) {
        this.mpesaConfig = mpesaConfig;
    }

    
    /**
     * 
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws TimeoutException 
     */
    public String authenticate() throws IOException, InterruptedException, TimeoutException {

        String appKeySecret = mpesaConfig.get("APPLICATION_KEY") + ":" + mpesaConfig.get("APPLICATION_SECRET");
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);

        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoded)
                .build();

        String response = webClient.get().retrieve().bodyToMono(String.class).block();

        return response;
    }

    /**
     * 
     * @param shortCode
     * @param commandID
     * @param amount
     * @param MSISDN
     * @param billRefNumber
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String C2B(String shortCode, String commandID, String amount, String MSISDN, String billRefNumber, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("Amount", amount);
        jsonObject.put("Msisdn", MSISDN);
        jsonObject.put("BillRefNumber", billRefNumber);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
 
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }

    
    /**
     * 
     * @param initiatorName
     * @param securityCredential
     * @param commandID
     * @param amount
     * @param partyA
     * @param partyB
     * @param remarks
     * @param queueTimeOutURL
     * @param resultURL
     * @param occassion
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String B2C(String initiatorName, String securityCredential, String commandID, String amount, String partyA, String partyB, String remarks, String queueTimeOutURL, String resultURL, String occassion, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("InitiatorName", initiatorName);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Occassion", occassion);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");

        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }

   /**
    * 
    * @param initiatorName
    * @param accountReference
    * @param securityCredential
    * @param commandID
    * @param senderIdentifierType
    * @param receiverIdentifierType
    * @param amount
    * @param partyA
    * @param partyB
    * @param remarks
    * @param queueTimeOutURL
    * @param resultURL
    * @param occassion
    * @param encodedPassword
    * @return
    * @throws IOException
    * @throws JSONException 
    */
    public String B2B(String initiatorName, String accountReference, String securityCredential, String commandID, String senderIdentifierType, String receiverIdentifierType, float amount, String partyA, String partyB, String remarks, String queueTimeOutURL, String resultURL, String occassion, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiatorName);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("SenderIdentifierType", senderIdentifierType);
        jsonObject.put("RecieverIdentifierType", receiverIdentifierType);
        jsonObject.put("Amount", amount);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("AccountReference", accountReference);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
 
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;

    }

    /**
     * 
     * @param businessShortCode
     * @param password
     * @param timestamp
     * @param transactionType
     * @param amount
     * @param phoneNumber
     * @param partyA
     * @param partyB
     * @param callBackURL
     * @param queueTimeOutURL
     * @param accountReference
     * @param transactionDesc
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String STKPush(String businessShortCode, String password, String timestamp, String transactionType, String amount, String phoneNumber, String partyA, String partyB, String callBackURL, String queueTimeOutURL, String accountReference, String transactionDesc, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", businessShortCode);
        jsonObject.put("Password", password);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("TransactionType", transactionType);
        jsonObject.put("Amount", amount);
        jsonObject.put("PhoneNumber", phoneNumber);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("CallBackURL", callBackURL);
        jsonObject.put("AccountReference", accountReference);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("TransactionDesc", transactionDesc);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }

    
    /**
     * 
     * @param initiator
     * @param securityCredential
     * @param commandID
     * @param transactionID
     * @param amount
     * @param receiverParty
     * @param recieverIdentifierType
     * @param resultURL
     * @param queueTimeOutURL
     * @param remarks
     * @param ocassion
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String reversal(String initiator, String securityCredential, String commandID, String transactionID, String amount, String receiverParty, String recieverIdentifierType, String resultURL, String queueTimeOutURL, String remarks, String ocassion, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiator);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("TransactionID", transactionID);
        jsonObject.put("Amount", amount);
        jsonObject.put("ReceiverParty", receiverParty);
        jsonObject.put("RecieverIdentifierType", recieverIdentifierType);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("Occasion", ocassion);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
 
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }

    /**
     * 
     * @param initiator
     * @param commandID
     * @param securityCredential
     * @param partyA
     * @param identifierType
     * @param remarks
     * @param queueTimeOutURL
     * @param resultURL
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String balanceEnquiry(String initiator, String commandID, String securityCredential, String partyA, String identifierType, String remarks, String queueTimeOutURL, String resultURL, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Initiator", initiator);
        jsonObject.put("SecurityCredential", securityCredential);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("IdentifierType", identifierType);
        jsonObject.put("Remarks", remarks);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("ResultURL", resultURL);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
 
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "no-cache")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + encodedPassword)
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }

    /**
     * 
     * @param shortCode
     * @param responseType
     * @param confirmationURL
     * @param validationURL
     * @param encodedPassword
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public String registerURL(String shortCode, String responseType, String confirmationURL, String validationURL, String encodedPassword) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("ResponseType", responseType);
        jsonObject.put("ConfirmationURL", confirmationURL);
        jsonObject.put("ValidationURL", validationURL);
        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
 
        WebClient webClient = WebClient.builder()
                .baseUrl(mpesaConfig.get("MPESA_API_URL").toString())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .defaultHeader(HttpHeaders.CACHE_CONTROL, "Basic " + encodedPassword)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "no-cache")
                .build();

        String response = webClient.post().uri(mpesaConfig.get("MPESA_API_URL").toString()).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(requestJson)).retrieve().bodyToMono(String.class).block();

        return response;
    }
}
