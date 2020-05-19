/*                        __ _       _              _                             _ _            _
 ___ _ __   _____      __/ _| | __ _| | _____      (_) __ ___   ____ _        ___| (_) ___ _ __ | |_
/ __| '_ \ / _ \ \ /\ / / |_| |/ _` | |/ / _ \_____| |/ _` \ \ / / _` |_____ / __| | |/ _ \ '_ \| __|
\__ \ | | | (_) \ V  V /|  _| | (_| |   <  __/_____| | (_| |\ V / (_| |_____| (__| | |  __/ | | | |_
|___/_| |_|\___/ \_/\_/ |_| |_|\__,_|_|\_\___|    _/ |\__,_| \_/ \__,_|      \___|_|_|\___|_| |_|\__|
                                                 |__/

  https://github.com/yingzhuo/snowflake
  https://github.com/yingzhuo/snowflake-java-client
*/
package com.github.yingzhuo.snowflake.actuator;

import com.github.yingzhuo.snowflake.SnowflakeUtils;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

/**
 * @author 应卓
 * @since 1.0.3
 */
public class SnowflakeHealthIndicator extends AbstractHealthIndicator implements HealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        try {
            SnowflakeUtils.nextId();
            builder.status(Status.UP)
                    .withDetail("snowflake", "Available");
        } catch (Exception e) {
            builder.status(Status.DOWN)
                    .withDetail("snowflake", "Not Available");
        }
    }

}
