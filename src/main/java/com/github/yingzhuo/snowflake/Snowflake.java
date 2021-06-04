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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author 应卓
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public final class Snowflake implements ApplicationContextAware {

    public static final Snowflake INSTANCE = new Snowflake();
    private static final RestTemplate JSON_REST_TEMPLATE = new RestTemplate(Collections.singletonList(new MappingJackson2HttpMessageConverter()));

    private static SnowflakeProperties props;

    private Snowflake() {
    }

    public static long nextId() {
        return nextIds(1).get(0);
    }

    public static List<Long> nextIds(int n) {
        final String url = String.format("http://%s/id?n={n}", props.getHost());
        return JSON_REST_TEMPLATE.getForEntity(url, List.class, n).getBody();
    }

    public static void setId(List collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }

        List<Long> ids = nextIds(collection.size());

        for (int i = 0; i < ids.size(); i++) {
            Object obj = collection.get(i);

            if (obj == null) continue;

            if (obj instanceof SnowflakeIdable) {
                ((SnowflakeIdable) obj).setId(ids.get(i));
                continue;
            }

            setIdForObjQuietly(obj, ids.get(i));
        }

    }

    private static void setIdForObjQuietly(Object obj, Long snowflake) {

        Method method = null;

        try {
            method = obj.getClass().getMethod("setId", Long.class);
        } catch (NoSuchMethodException e) {
            try {
                method = obj.getClass().getMethod("setId", long.class);
            } catch (NoSuchMethodException ex) {
                // NOP
            }
        } catch (Exception ignore) {
            // NOP
        }

        if (method != null) {
            try {
                method.setAccessible(true);
                method.invoke(obj, snowflake);
            } catch (Exception e) {
                // NOP
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Snowflake.props = applicationContext.getBean(SnowflakeProperties.class);
    }

}
