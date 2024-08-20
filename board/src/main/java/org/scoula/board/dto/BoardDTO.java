package org.scoula.board.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardDTO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
    private List<BoardAttachmentVO> attachments;

    // 첨부파일
    private List<BoardAttachmentVO> attaches;
    List<MultipartFile> files = new ArrayList<>(); // 실제 업로드 된 파일(Multipart)목록

    public static BoardDTO of(BoardVO vo){
        return vo ==null ? null: BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .attaches(vo.getAttaches())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
    }
    public BoardVO toVo(){
        return BoardVO.builder()
                .no(no)
                .title(title)
                .content(content)
                .writer(writer)
                .attaches(attaches)//attaches 에 대한 세팅
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
    //상세보기할 때는 join 이 반드시 필요
    //가끔 보여주는 서비스 제목 옆에 첨부파일 3개 달려 있는 경우 개수 보여주는 경우 서비스?
    //일반적으로 목록 보여주기 첨부파일 일반적으로 목록 보기를 join 할 필요가 없다
    //상세보기할 때만 join 을 한다는 것을 알자.
    //join 의 종류 - 첨부파일이 없는 게시글,

}

