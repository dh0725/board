package kr.co.gidion.boardMyBatis.service;

import kr.co.gidion.boardMyBatis.dto.BoardDTO;
import kr.co.gidion.boardMyBatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements  BoardService {

    @Autowired
    private BoardMapper boardMapper;

    // 게시판 목록을 조회한다.
    @Override
    public List<BoardDTO> selectBoardList() {
        return boardMapper.selectBoardList();
    }

    // 게시판 상세내용을 조회한다.
    @Override
    public BoardDTO selectBoardDetail(int id) {
        // 조회수 1 증가
        boardMapper.updateViewCnt(id);

        return boardMapper.selectBoardDetail(id);
    }

    // 글을 쓴다.
    @Override
    public void insertBoard(BoardDTO boardDTO) {
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
