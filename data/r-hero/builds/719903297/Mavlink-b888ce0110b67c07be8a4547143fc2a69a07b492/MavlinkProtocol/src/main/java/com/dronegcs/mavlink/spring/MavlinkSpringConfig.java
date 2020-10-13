package com.dronegcs.mavlink.spring;

import com.dronegcs.mavlink.core.drone.MyDroneImpl;
import com.generic_tools.devices.SerialConnection;
import com.generic_tools.devices.internal.TwoWaySerialComm;
import com.generic_tools.devices.internal.TwoWaySerialCommJSSC;
//import com.generic_tools.devices.internal.TwoWaySerialCommNG;
import com.generic_tools.environment.Environment;
import com.generic_tools.logger.Logger;
import com.generic_tools.validations.RuntimeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by taljmars on 4/5/17.
 */
@Import({MyDroneImpl.class})
@Configuration
public class MavlinkSpringConfig {

    @Bean
    public SerialConnection serialConnection(@Autowired Logger logger) {
        return new TwoWaySerialCommJSSC(logger);
    }

}
