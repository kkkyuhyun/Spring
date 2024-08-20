package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

import java.io.File;

import static org.springframework.util.StringUtils.getFilename;

@Controller
@Log4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService service;
    @GetMapping("/list")
    public void list(Model model) {

        log.info("list");
        model.addAttribute("list",service.getList());
    }
    @GetMapping("/create")
    public void create(){
        log.info("create");
    }
    @PostMapping("/create")
    public String create(BoardDTO board, RedirectAttributes ra){
        log.info("create:" + board);
        service.create(board);
        ra.addFlashAttribute("result", board.getNo());
        return "redirect:/board/list";
    }
    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no));
    }
    @PostMapping("/update")
    public String update(BoardDTO board,RedirectAttributes ra){
        //log.info("update:" + board);
        //service.update(board);
        if (service.update(board)){
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
    @PostMapping("/delete") //쿼리에 있든 바디에 있든 RequestParam
    public String delete(@RequestParam("no") Long no, RedirectAttributes ra) {
        //log.info("delete..." + no);
        //service.delete(no);
        if (service.delete(no)){
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
    @GetMapping("/download/{no}")
    @ResponseBody //view 를 사용하지 않고 직접 내보낸다.
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception{
        BoardAttachmentVO attach = service.getAttachment(no);
        File file = new File(attach.getPath()); //서버저장경로
        UploadFiles.download(response, file, attach.getFilename());//원본파일명 
    }
}
