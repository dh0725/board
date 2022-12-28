package kr.co.gidion.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDTO {

    private int boardId;
    private String boardTitle;
    private String boardContents;
    private int viewCount;
    private String registId;
    private Timestamp registDate;
    private String updateId;
    private Timestamp updateDate;

}
