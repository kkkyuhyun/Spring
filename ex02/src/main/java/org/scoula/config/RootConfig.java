package org.scoula.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration //한 개의 text를 만들어낸다
@ComponentScan(basePackages = {"org.scoula"})
//package 하위 패키지 포함해서 componet annotation 자동 등록
//context 레벨에서 빈이름으로 식별되는 경우 singleton 운영
public class RootConfig {
}