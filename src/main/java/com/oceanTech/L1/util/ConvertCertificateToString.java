package com.oceanTech.L1.util;

import com.oceanTech.L1.entity.Certificate;

public class ConvertCertificateToString {
    public String convertCertificateToString(String name, String validFrom, String validTo, String province, String employee) {
        return "Certificate{" +
                "name='" + name + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                ", province='" + province + '\'' +
                ", employee='" + employee + '\'' +
                '}';
    }

}
