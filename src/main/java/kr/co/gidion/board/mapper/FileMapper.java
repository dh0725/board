package kr.co.gidion.board.mapper;

import kr.co.gidion.board.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

	// 시퀀스를 최초 생성한다.
	int createSeqName(String sequenceName);

	// 파일 그룹 ID를 생성한다.
	int selectFileGroupId(String sequenceName);

	// DB 에 파일에 대한 정보를 생성한다.
	void insertUploadFiles(FileDTO fileDTO);

	// 게시글에 첨부된 파일을 조회한다.
	FileDTO selectFileList(int fileGroupId);
}
