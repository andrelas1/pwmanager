package com.andrelas1.pwmanager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String applicationName;
    private String secret;

    protected Password() {
    }

    public Password(String applicationName, String secret) {
        this.applicationName = applicationName;
        this.secret = secret;
    }

    @Override
    public String toString() {
        return String.format(
                "Password[id=%d, applicationName='%s', secret='%s']",
                id, applicationName, secret);
    }

    public Long getId() {
        return id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getSecret() {
        return secret;
    }
}
