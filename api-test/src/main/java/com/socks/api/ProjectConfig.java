package com.socks.api;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    String baseUrl();

    @DefaultValue("de")
    String locale();

    Boolean logging();
}
