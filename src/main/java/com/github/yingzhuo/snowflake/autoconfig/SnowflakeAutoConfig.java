/*                        __ _       _              _                             _ _            _
 ___ _ __   _____      __/ _| | __ _| | _____      (_) __ ___   ____ _        ___| (_) ___ _ __ | |_
/ __| '_ \ / _ \ \ /\ / / |_| |/ _` | |/ / _ \_____| |/ _` \ \ / / _` |_____ / __| | |/ _ \ '_ \| __|
\__ \ | | | (_) \ V  V /|  _| | (_| |   <  __/_____| | (_| |\ V / (_| |_____| (__| | |  __/ | | | |_
|___/_| |_|\___/ \_/\_/ |_| |_|\__,_|_|\_\___|    _/ |\__,_| \_/ \__,_|      \___|_|_|\___|_| |_|\__|
                                                 |__/

  https://github.com/yingzhuo/snowflake
  https://github.com/yingzhuo/snowflake-java-client
*/
package com.github.yingzhuo.snowflake.autoconfig;

import com.github.yingzhuo.snowflake.SnowflakeProperties;
import com.github.yingzhuo.snowflake.Snowflake;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author 应卓
 */
@EnableConfigurationProperties(SnowflakeProperties.class)
@ConditionalOnProperty(prefix = "snowflake", name = "enabled", havingValue = "true", matchIfMissing = true)
public class SnowflakeAutoConfig {

    @Bean
    public Snowflake snowflakeUtils() {
        return Snowflake.INSTANCE;
    }

}
