package kr.co.gidion.board.dto;

import lombok.Data;

@Data
public class FileDTO {

	private String fileId;
	private String orgFileName;
	private String chgFileName;
	private String filePath;
	private String fileType;

}
