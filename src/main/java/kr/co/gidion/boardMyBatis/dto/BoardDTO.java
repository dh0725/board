package kr.co.gidion.boardMyBatis.dto;

import lombok.Data;

@Data
public class BoardDTO {

    private int id;
    private String title;
    private String contents;
    private int viewCnt;
    private String registDate;
    private String registId;
    private String updateDate;
    private String updateId;

}
