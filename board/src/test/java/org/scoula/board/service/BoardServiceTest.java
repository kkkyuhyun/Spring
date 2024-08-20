package org.scoula.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Slf4j
class BoardServiceTest {

    @Autowired
    private BoardService service;

    @Test
    public void getList() {
        for (BoardDTO board : service.getList()) {
            log.info(board.toString());
        }
    }

    @Test
    void get() {
        log.info(service.get(1L).toString());
        //없는 경우 NoSuchElementException 이 발생하는지 확인한다
    }

    @Test
    void create() {
        BoardDTO board = new BoardDTO();
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는내용");
        board.setWriter("user1");
        service.create(board);
        log.info("생성된 게시물의 번호: " + board.getNo());
    }

    @Test
    void update() {
        BoardDTO board = service.get(1L);
        if (board != null) {
            board.setTitle("제목 수정합니다.");
            log.info("update RESULT: " + service.update(board));
        } else {
            log.warn("업데이트할 게시물을 찾을 수 없습니다.");
        }
    }

    @Test
    public void delete() {
        // 게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("delete RESULT: " + service.delete(2L));
    }


}
