package com.medochemie.ordermanagement.agentservice.service.utils;

import java.util.UUID;

public class GenerateAgentCode {

    public static String generateAgentCode(String agentName, String countryName) {
//        UUID temp = UUID.randomUUID();
//        String uuidString = Long.toHexString(temp.getMostSignificantBits())
//                + Long.toHexString(temp.getLeastSignificantBits());

        String agentPrefix = agentName.length() < 3 ? agentName : agentName.substring(0, 3);
        String countryPrefix = countryName.length() < 3 ? countryName : countryName.substring(0, 3);;
        return agentPrefix.toUpperCase() + "/"+ countryPrefix.toUpperCase() + "/00001";
    }
}
