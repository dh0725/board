package kr.co.gidion.board.controller;

import kr.co.gidion.board.dto.FileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Class FileController
 * @Description 게시판 파일 업로드 Controller
 */
@RequestMapping("/file")
@Controller
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/upload")
	public String upload(Model model, @RequestParam MultipartFile[] uploadFiles) {
		List<FileDTO> fileList = new ArrayList<>();

		for (MultipartFile files : uploadFiles) {
			if (!files.isEmpty()) {
				logger.debug("OriginalFilename : {}", files.getOriginalFilename());
				logger.debug("ContentType : {}", files.getContentType());

				double fileSize = (double) files.getSize();
				double fileSizeInKB = fileSize / 1024;
				double fileSizeInMB = fileSizeInKB / 1024;

				String strFileSize = fileSize + "Byte";
				String strFileSizeInKB = String.format("%.2f", fileSizeInKB) + "KB";
				String strFileSizeInMB = String.format("%.2f", fileSizeInMB) + "MB";

				// logger.debug("fileSize : {}", strFileSize);
				// logger.debug("fileSizeInKB : {}", strFileSizeInKB);
				logger.debug("fileSizeInMB : {}", strFileSizeInMB);

				FileDTO fileDTO = new FileDTO();
			} else {
				logger.info("file 없어요.");
			}
		}

		logger.info("끝!");

		return "redirect:/board/boardList";
	}
}
