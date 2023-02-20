package kr.co.gidion.board.service;

import kr.co.gidion.board.dto.FileDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	// 파일 그룹 ID를 생성한다.
	int getFileGroupId(String sequenceName);

	// 파일을 업로드 한다.
	int uploadFiles(Model model, @RequestParam MultipartFile[] uploadFiles, FileDTO fileDTO);

	// 게시글에 첨부된 파일을 조회한다.
	FileDTO selectFileList(int fileGroupId);
}
