package com.http.cq2;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;


public class SignatureDemo {

    public static void main(String[] args) {
        String supplierKeyId = "e6fe9dd5-58af-11e8-857d-00163e32d704";
        String supplierSecretKey = "Ny35o694RgXURrNQ7hCBbI4wyearCWxx7H4n";

        String projectKeyId = "1b0f28a4-a5a8-4ea8-ae1a-3b80d6e72397";
        String projectSecretKey = "RMSRvZBhviBiWlddbXil5EqVQhkHJ0WJUbH7";

        SignatureDTO dto = createSignature(supplierKeyId + "_" + projectKeyId, supplierSecretKey, projectSecretKey);
        
//        System.out.println("keyId: " + dto.getKeyId());
//        System.out.println("ts: " + dto.getTs());
//        System.out.println("rCode: " + dto.getrCode());
//        System.out.println("signature: " + dto.getSignature());
        
        System.out.println("ts: 0XeDDKraKVwJ" );
        System.out.println("rCode: 1542941504");
        System.out.println("keyid: b80348fa-3b8e-461d-a508-64ac4df2bf13_ebd99521-93f1-4834-9c6c-ca8aa5432536");
        
        String tmp = "0XeDDKraKVwj"+"_1542941504"+"_CWHXNtrootfW2ht11dgiGPJRI2CF0voSteRs"+"_O46C6Nv9JygA477mm69RzCTzqUzOoGYSGiyC";
        String signature = DigestUtils.sha1Hex(tmp);
        System.out.println(tmp);
        System.out.println(signature);
    }

    public static SignatureDTO createSignature(String keyId, String supplierSecretKey, String projectSecretKey) {

        String rCode = RandomStringUtils.randomAlphanumeric(12);
        Long ts = DateTime.now().getMillis() / 1000;
        String preSignStr = "" + rCode + "_"+ts + "_"+supplierSecretKey +"_"+ projectSecretKey;
        String signature = DigestUtils.sha1Hex(preSignStr);

        SignatureDTO dto = new SignatureDTO();
        dto.setKeyId(keyId);
        dto.setrCode(rCode);
        dto.setTs(ts);
        dto.setSignature(signature);

        return dto;
    }

}
