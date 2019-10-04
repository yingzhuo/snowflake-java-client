/*                        __ _       _              _                             _ _            _
 ___ _ __   _____      __/ _| | __ _| | _____      (_) __ ___   ____ _        ___| (_) ___ _ __ | |_
/ __| '_ \ / _ \ \ /\ / / |_| |/ _` | |/ / _ \_____| |/ _` \ \ / / _` |_____ / __| | |/ _ \ '_ \| __|
\__ \ | | | (_) \ V  V /|  _| | (_| |   <  __/_____| | (_| |\ V / (_| |_____| (__| | |  __/ | | | |_
|___/_| |_|\___/ \_/\_/ |_| |_|\__,_|_|\_\___|    _/ |\__,_| \_/ \__,_|      \___|_|_|\___|_| |_|\__|
                                                 |__/

  https://github.com/yingzhuo/snowflake
  https://github.com/yingzhuo/snowflake-java-client
*/
package com.github.yingzhuo.snowflake;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author 应卓
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "snowflake")
public class Props implements Serializable, InitializingBean {

    private boolean enabled = true;
    private Type type = Type.PROTOBUF;
    private String hostname = "localhost";
    private int port = 8080;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(hostname, "snowflake.hostname is null or empty.");
        Assert.notNull(type, "snowflake.type is null.");
    }

}
