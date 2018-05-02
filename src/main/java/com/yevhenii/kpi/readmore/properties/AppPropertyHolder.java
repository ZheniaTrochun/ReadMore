package com.yevhenii.kpi.readmore.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class AppPropertyHolder {

    @NestedConfigurationProperty
    private Googlebooks googlebooks;
    @NestedConfigurationProperty
    private Security security;

    public AppPropertyHolder() {
    }

    public AppPropertyHolder(Googlebooks googlebooks, Security security) {
        this.googlebooks = googlebooks;
        this.security = security;
    }

    @Data
    public static class Security {
        private Long expiration;
        private String secret;
        private String prefix;
        private String header;

        public Security() {
        }

        public Security(Long expiration, String secret, String prefix, String header) {
            this.expiration = expiration;
            this.secret = secret;
            this.prefix = prefix;
            this.header = header;
        }
    }

    @Data
    public static class Googlebooks {
        private String key;

        public Googlebooks() {
        }

        public Googlebooks(String key) {
            this.key = key;
        }
    }
}
