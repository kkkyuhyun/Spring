package org.scoula.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.common.util.UploadFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j
public class BoardAttachmentVO {
    private Long no;
    private Long bno; //
    private String filename;
    private String path;
    // 서버에 저장된 파일 경로
    private String contentType; // 파일 mime-type
    private Long size;
    private Date regDate;

    // 팩토리 메서드
    public static BoardAttachmentVO of(MultipartFile part, Long bno, String path) { // bno의 타입 명시
        return builder()
                .bno(bno) // bno 필드에 값 설정
                .filename(part.getOriginalFilename())
                .path(path)
                .contentType(part.getContentType())
                .size(part.getSize())
                .build();
    }
    public String getFileSize() {
        return UploadFiles.getFormatSize(size);
    }
}
