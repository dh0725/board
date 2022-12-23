package kr.co.gidion.boardMyBatis.service;

import kr.co.gidion.boardMyBatis.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    // 게시판 목록을 조회한다.
    List<BoardDTO> selectBoardList();

    // 게시판 상세내용을 조회한다.
    BoardDTO selectBoardDetail(int id);

    // 글을 쓴다.
    void insertBoard(BoardDTO boardDTO);

    // 글을 수정한다.
    void updateBoard(BoardDTO boardDTO);

    // 글을 삭제한다.
    void deleteBoard(int id) throws Exception;
}