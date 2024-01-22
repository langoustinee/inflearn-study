package tobyspring.study;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @DisplayName("하나의 빈을 의존하는 두 개의 빈을 생성할 때 주소값은 다르다.")
    @Test
    void configurationNotSameBean() {
        // then
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();
        assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @DisplayName("스프링 컨테이너에 등록한 구성정보는 하나의 빈을 의존하는 두 개의 빈을 생성하는 데, 같은 주소값을 가진다.")
    @Test
    void configurationSameBean() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        // when
        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        // then
        assertThat(bean1.common).isSameAs(bean2.common);
    }

    @DisplayName("스프링 컨테이너를 흉내 내어 프록시 객체를 통해 Common 객체 빈을 하나로 제한한다.")
    @Test
    void proxyCommonMethod() {
        // given
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        // when
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        // then
        assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;
        @Override
        Common common() {
            if(this.common == null) this.common = super.common();
            return this.common;
        }
    }

    @Configuration(proxyBeanMethods = true)
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }
}
