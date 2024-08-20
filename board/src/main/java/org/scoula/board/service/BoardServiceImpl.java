/*package org.scoula.board.service;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.springframework.stereotype.Service;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {

    final private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {  }
    @Override
    public BoardDTO get(Long no) {  }
    @Override
    public void create(BoardDTO board) {  }
    @Override
    public boolean update(BoardDTO board) { }
    @Override
    public boolean delete(Long no) { }

    }
 }*/
/*
package org.scoula.board.service;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final static String BASE_DIR = "c:/upload/board";
    private final BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList.....");
        return mapper.getList().stream()
                .map(BoardDTO::of)
                .toList(); // List<BoardDTO>로 변환
    }

    @Override
    public BoardDTO get(Long no) {//매개변수 찾고자 하는 값 primary 값
        log.info("get....." + no);
        BoardVO boardVO = mapper.get(no);
        if (boardVO == null) {
            throw new NoSuchElementException("No board found with no: " + no);
        }
        return BoardDTO.of(boardVO);
    }
    // 2개 이상의 insert 문이 실행될 수 있으므로 트랜잭션 처리 필요

    // RuntimeException인 경우만 자동 rollback.
    @Transactional
    @Override
    public void create(BoardDTO board) {
        log.info("create....." + board);
        mapper.create(board.toVo());
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return mapper.delete(no) == 1;
    }

}*/
package org.scoula.board.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final static String BASE_DIR = "c:/upload/board";
    private final BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info("getList.....");
        return mapper.getList().stream()
                .map(BoardDTO::of)
                .toList(); // List<BoardDTO>로 변환
    }

    @Override
    public BoardDTO get(Long no) {
        log.info("get....." + no);
        BoardVO boardVO = mapper.get(no);
        if (boardVO == null) {
            throw new NoSuchElementException("No board found with no: " + no);
        }
        return BoardDTO.of(boardVO);
    }

    @Transactional
    @Override
    public void create(BoardDTO board) {
        log.info("create....." + board);
        BoardVO boardVO = board.toVo();
        mapper.create(boardVO);

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) { //첨부파일이 있는 경우에만 업로드 진행
            upload(boardVO.getNo(), files);//service 에 private 메소드
        }
    }

    @Override
    public boolean update(BoardDTO board) {
        log.info("update......" + board);
        return mapper.update(board.toVo()) == 1;
    }

    @Override
    public boolean delete(Long no) {
        log.info("delete...." + no);
        return mapper.delete(no) == 1;
    }

    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return null;
    }

    @Override
    public boolean deleteAttachment(Long no) {
        return false;
    }

    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            if (part.isEmpty()) continue;
            try {
                String uploadPath = UploadFiles.upload(BASE_DIR, part);//위치, 업로드(part)
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach); //실제 저장된 경로가 return 된다.
            } catch (IOException e) {
                throw new RuntimeException(e); // @Transactional 에서 감지, 자동 rollback
            }//RuntimeExceptional(e) @Transactional 에서 감지되기 때문에..?
            //RuntimeException 은 예외처리가 옵션 해도 되고 안해도 된다
            //SQL EXCEPTION 은 MyBatis RunTImeException 으로 바꿔준다. @Transactional 처리

        }
    }
}

