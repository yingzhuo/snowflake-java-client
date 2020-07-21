## snowflake-java-client

maven依赖:

```xml
<dependency>
    <groupId>com.github.yingzhuo</groupId>
    <artifactId>snowflake-java-client</artifactId>
    <version>1.0.6-1</version>
</dependency>
```

`spring-boot`配置

```yaml
snowflake:
  enabled: true
  response-type: json
  host: "localhost:8080"
```

直接使用`com.github.yingzhuo.snowflake.Snowflake`即可。
