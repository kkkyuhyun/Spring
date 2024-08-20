package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
//@Select("select*from tbl_board order by no desc")
    public List<BoardVO>getList();
    public BoardVO get(Long no);
    public void create(BoardVO board);
    public int update(BoardVO board);
    public int delete(Long no);

    public void createAttachment(BoardAttachmentVO attach);
    public List<BoardAttachmentVO> getAttachmentList(Long bno); //상세보기 목록출력
    public BoardAttachmentVO getAttachment(Long no); //게시글의 상세보기
    public int deleteAttachment(Long no);
}