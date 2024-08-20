package org.scoula.board.domain;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private Long no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
    private List<BoardAttachmentVO> attaches;
//select 문에 결과를 어떻게 채울 수 있는가? join 필요
//지금까지 만든 select 문 처리 join 을 통해 채우는 방법 -> resultMap 구성
    //DTO에도 추가한다.
}