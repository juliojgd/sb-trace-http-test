package com.example.traceforbidden1;

import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.server.ConditionalOnManagementPort;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.context.annotation.Import;

@ManagementContextConfiguration
@ConditionalOnManagementPort(ManagementPortType.DIFFERENT)
@ConditionalOnWebApplication(type = Type.SERVLET)
@Import({org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class})
public class TraceHttpBlockedManagementConfiguration {

}
