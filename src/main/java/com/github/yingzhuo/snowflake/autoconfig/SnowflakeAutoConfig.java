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

import com.github.yingzhuo.snowflake.Props;
import com.github.yingzhuo.snowflake.SnowflakeUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author 应卓
 */
@EnableConfigurationProperties(Props.class)
public class SnowflakeAutoConfig {

    @Bean
    public SnowflakeUtils.SnowflakeUtilsBean snowflakeUtilsBean() {
        return new SnowflakeUtils.SnowflakeUtilsBean();
    }

}
