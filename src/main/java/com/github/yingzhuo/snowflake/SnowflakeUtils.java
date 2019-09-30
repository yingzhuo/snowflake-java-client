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

import com.github.yingzhuo.snowflake.proto.SnowflakeProto;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author 应卓
 */
@SuppressWarnings("unchecked")
public final class SnowflakeUtils {

    private static final RestTemplate JSON_REST_TEMPLATE = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
    private static final RestTemplate PROTOBUF_REST_TEMPLATE = new RestTemplate(Collections.singletonList(new ProtobufJsonFormatHttpMessageConverter()));

    static Props props;

    public static long nextId() {
        return nextIds(1).get(0);
    }

    public static List<Long> nextIds(int n) {
        switch (props.getType()) {
            case PROTOBUF:
                return doNextProtobufIds(n);
            case JSON:
                return doNextJsonIds(n);
            default:
                throw new AssertionError();
        }
    }

    private static List<Long> doNextJsonIds(int n) {
        final String url = String.format("http://%s:%d/id?n={n}", props.getHostname(), props.getPort());
        return JSON_REST_TEMPLATE.getForEntity(url, List.class, n).getBody();
    }

    private static List<Long> doNextProtobufIds(int n) {
        final String url = String.format("http://%s:%d/id?n={n}", props.getHostname(), props.getPort());
        final SnowflakeProto.IdList idList = PROTOBUF_REST_TEMPLATE.getForEntity(url, SnowflakeProto.IdList.class, n).getBody();
        return idList.getIdsList();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static class SnowflakeUtilsBean implements ApplicationContextAware {
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            SnowflakeUtils.props = applicationContext.getBean(Props.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private SnowflakeUtils() {
    }
}
