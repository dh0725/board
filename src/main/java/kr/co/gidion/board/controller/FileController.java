package kr.co.gidion.board.controller;

import kr.co.gidion.board.dto.FileDTO;
import kr.co.gidion.board.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Class FileController
 * @Description 게시판 파일 업로드 Controller
 */
@RequestMapping("/file")
@Controller
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;

	@PostMapping("/upload")
	public String upload(Model model, @RequestParam MultipartFile[] uploadFiles, HttpServletRequest request, FileDTO fileDTO) {
		List<FileDTO> fileList = new ArrayList<>();
		String sequenceName = request.getParameter("sequenceName");
		sequenceName = sequenceName.replaceAll("-", "_");

		try {
			// 파일 그룹 ID를 생성한다.
			int fileGroupId = fileService.getFileGroupId(sequenceName);
			logger.debug("fileGroupdId : {}", fileGroupId);

			// 생성한 파일 그룹 ID 를 DTO에 설정한다.
			fileDTO.setFileGroupId(fileGroupId);

			// 파일 업로드 메소드를 호출한다.
			fileService.uploadFiles(model, uploadFiles, fileDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/board/boardList";
	}
}
