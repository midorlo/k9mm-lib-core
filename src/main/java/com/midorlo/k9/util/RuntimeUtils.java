package com.midorlo.k9.util;


import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RuntimeUtils {

    public static boolean isProfileActive(String profileName) {
        return Arrays.asList(new StandardEnvironment().getActiveProfiles()).contains(profileName);
    }
}
