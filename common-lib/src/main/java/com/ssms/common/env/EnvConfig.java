package com.ssms.common.env;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

// environment related configuration
@Data
@Builder
public class EnvConfig {

    private String name;
    private boolean debug;
    private String externalApex;
    private String internalApex;
    private String scheme;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Map<String, EnvConfig> map;

    static {
        map = new HashMap<String, EnvConfig>();
        EnvConfig envConfig = EnvConfig.builder().name(EnvConstant.ENV_DEV)
                .debug(true)
                .externalApex("ssms-v2.local")
                .internalApex(EnvConstant.ENV_DEV)
                .scheme("http")
                .build();
        map.put(EnvConstant.ENV_DEV, envConfig);

        envConfig = EnvConfig.builder().name(EnvConstant.ENV_TEST)
                .debug(true)
                .externalApex("ssmsv2.local")
                .internalApex(EnvConstant.ENV_DEV)
                .scheme("http")
                .build();
        map.put(EnvConstant.ENV_TEST, envConfig);

        // for aliyun k8s demo, enable debug and use http and ssms-uat.local
        // in real world, disable debug and use http and ssms-uat.xyz in UAT environment
        envConfig = EnvConfig.builder().name(EnvConstant.ENV_UAT)
                .debug(true)
                .externalApex("ssms-uat.local")
                .internalApex(EnvConstant.ENV_UAT)
                .scheme("http")
                .build();
        map.put(EnvConstant.ENV_UAT, envConfig);

//        envConfig = EnvConfig.builder().name(EnvConstant.ENV_UAT)
//                .debug(false)
//                .externalApex("ssms-uat.xyz")
//                .internalApex(EnvConstant.ENV_UAT)
//                .scheme("https")
//                .build();
//        map.put(EnvConstant.ENV_UAT, envConfig);

        envConfig = EnvConfig.builder().name(EnvConstant.ENV_PROD)
                .debug(false)
                .externalApex("ssms.com")
                .internalApex(EnvConstant.ENV_PROD)
                .scheme("https")
                .build();
        map.put(EnvConstant.ENV_PROD, envConfig);
    }

    public static EnvConfig getEnvConfg(String env) {
        EnvConfig envConfig = map.get(env);
        if (envConfig == null) {
            envConfig = map.get(EnvConstant.ENV_DEV);
        }
        return envConfig;
    }
}
