package org.scoula;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Nested
@ExtendWith(SpringExtension.class) //spring 테스트
@ContextConfiguration(classes = {RootConfig.class})
@Log4j //단위 테스트할떄마다 필요한 구문

class RestaurantTest {
    @Autowired
    private Restaurant restaurant;
    @Test
    void getChef() {
        assertNotNull(restaurant);
        log.info(restaurant);
        log.info("------------------------------");
        log.info(restaurant.getChef());
    }
}
