package kr.co.gidion.board.dto;

import lombok.Data;

@Data
public class FileDTO {

	private int fileSeqId;
	private String sequenceName;
	private int fileGroupId;
	private String orgnFileName;
	private String chngFileName;
	private long fileSize;
	private String fileType;
	private String registId;
	private String registDate;

}
