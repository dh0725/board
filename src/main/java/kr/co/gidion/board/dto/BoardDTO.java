package kr.co.gidion.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardDTO {

    private int boardId;
    private int fileGroupId;
    private String orgnFileName;
    private String boardTitle;
    private String boardContents;
    private int viewCount;
    private String isDeleted;
    private String registId;
    private Timestamp registDate;
    private String updateId;
    private Timestamp updateDate;

}
