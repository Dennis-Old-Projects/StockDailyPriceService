package com.multiplan.stockdaily.rest.config;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class InsecureTrustManager implements X509TrustManager {
   
    public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        // Everyone is trusted!
    }

    
    public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
        // Everyone is trusted!
    }

   
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
