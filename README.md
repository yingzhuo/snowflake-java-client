## snowflake-java-client

maven依赖:

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>snowflake-java-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

`spring-boot`配置

```yaml
spring: 
  snowflake:
    response-type: json
    hostname: "127.0.0.1"
    port: 18080
```

直接使用`com.github.yingzhuo.snowflake.SnowflakeUtils`即可。
