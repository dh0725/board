package kr.co.gidion.board.controller;

import kr.co.gidion.board.dto.BoardDTO;
import kr.co.gidion.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Class BoardController
 * @Description 게시판 Controller
 */
@RequestMapping("/board")
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * @Method openBoardList
     * @Description 게시판 목록을 조회한다.
     * @return ModelAndView
     */
    @RequestMapping("/boardList")
    public String boardList(Model model) {
        List<BoardDTO> list = boardService.selectBoardList();       // service 호출
        model.addAttribute("list", list);

        return "/board/boardList";

        /*
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/boardList");                         // view 설정

        List<BoardDTO> list = boardService.selectBoardList();       // service 호출
        mv.addObject("list", list);                   // view 로 넘겨줄 데이터 추가
        return mv;
        */
    }

    /**
     * @Method boardDetail
     * @Description 게시글 상세내용을 조회한다.
     * @return ModelAndView
     */
    @RequestMapping("/boardDetail/{id}")
    public String boardDetail(Model model, @PathVariable("id") int id) {
        BoardDTO boardDTO = boardService.selectBoardDetail(id);         // service 호출
        model.addAttribute("board", boardDTO);

        return "/board/boardDetail";

        /*
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/boardDetail");                           // view 설정

        BoardDTO boardDTO = boardService.selectBoardDetail(id);         // service 호출
        mv.addObject("board", boardDTO);                  // view 로 넘겨줄 데이터 추가

        return mv;
        */
    }

    /**
     * @Method boardWrite
     * @Description 글 쓰기 화면을 호출한다.
     * @return BoardWrite.html
     */
    @RequestMapping("/boardWrite")
    public String boardWrite() {
        return "/board/boardWrite";
    }

    /**
     * @Method insertBoard
     * @Description 글을 쓴다.
     * @return BoardList.html
     */
    @RequestMapping("/insertBoard")
    public String insertBoard(BoardDTO boardDTO) {
        boardService.insertBoard(boardDTO);

        return "redirect:/board/boardList";
    }


    /**
     * @Method boardEdit
     * @Description 글 수정 화면을 호출한다.
     * @return boardEdit.html
     */
    @RequestMapping("/boardEdit/{id}")
    public String boardEdit(Model model, @PathVariable("id") int id) {
        BoardDTO boardDTO = boardService.selectBoardDetail(id);         // service 호출
        model.addAttribute("board", boardDTO);

        return "/board/boardEdit";
    }

    /**
     * @Method updateBoard
     * @Description 글을 수정한다.
     * @return BoardList.html
     */
    @PutMapping("/updateBoard/{id}")
    public String updateBoard(BoardDTO boardDTO) {
        boardService.updateBoard(boardDTO);

        return "redirect:/board/boardList";
    }

    /**
     * @Method deleteBoard
     * @Description 글을 삭제한다.
     * @return BoardList.html
     */
    @DeleteMapping("/deleteBoard/{id}")
    public String deleteBoard(@PathVariable("id") int id) throws Exception {
        // System.out.println(id);
        boardService.deleteBoard(id);

        return "redirect:/board/boardList";
    }
}
