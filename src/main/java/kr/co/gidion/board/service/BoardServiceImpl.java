package kr.co.gidion.board.service;

import kr.co.gidion.board.dto.BoardDTO;
import kr.co.gidion.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    // 게시판 목록을 조회한다.
    @Override
    public List<BoardDTO> selectBoardList() {

        return boardMapper.selectBoardList();
    }

    // 조회한 게시글의 조회수를 1 증가한다.
    @Override
    public void updateViewCnt(int id) {
        boardMapper.updateViewCnt(id);
    }

    // 게시글 상세내용을 조회한다.
    @Override
    public BoardDTO selectBoardDetail(int id) {

        return boardMapper.selectBoardDetail(id);
    }

    // 글을 쓴다.
    @Override
    public void insertBoard(BoardDTO boardDTO) {
        // 최초 글 작성 시 삭제 여부는 'N' 으로 서정한다.
        boardDTO.setIsDeleted("N");

        boardMapper.insertBoard(boardDTO);
    }

    // 글을 수정한다.
    @Override
    public void updateBoard(BoardDTO boardDTO) {
        boardMapper.updateBoard(boardDTO);
    }

    // 글을 삭제한다.
    @Override
    public void deleteBoard(int id) throws Exception {
        boardMapper.deleteBoard(id);
    }
}
