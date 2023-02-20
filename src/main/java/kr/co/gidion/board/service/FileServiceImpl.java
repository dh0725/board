package kr.co.gidion.board.service;

import kr.co.gidion.board.dto.FileDTO;
import kr.co.gidion.board.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileMapper fileMapper;

	// 파일 그룹 ID를 생성한다.
	@Override
	public int getFileGroupId(String sequenceName) {
		/*
			시퀀스 최초 생성 시
			시퀀스 테이블에 name 추가 후 커스텀 함수 nextval() 을 통해 조회한다.
		 */
		// fileMapper.createSeqName(sequenceName);

		return fileMapper.selectFileGroupId(sequenceName);
	}

	// 파일을 업로드 한다.
	@Override
	public int uploadFiles(Model model, @RequestParam MultipartFile[] uploadFiles, FileDTO fileDTO) {
		List<FileDTO> fileList = new ArrayList<>();

		try {
			for (MultipartFile files : uploadFiles) {
				if (!files.isEmpty()) {
					int fileGroupId = fileDTO.getFileGroupId();
					String orgnFileName = files.getOriginalFilename();
					String chngFileName = UUID.randomUUID().toString();
					long fileSize = files.getSize();
					String fileType = files.getContentType();
					String registId = "임동혁";
					// String registDate = "2023";

					// DTO 에 setter 를 통해 값을 설정한다.
					fileDTO.setFileGroupId(fileGroupId);
					fileDTO.setOrgnFileName(orgnFileName);
					fileDTO.setChngFileName(chngFileName);
					fileDTO.setFileSize(fileSize);
					fileDTO.setFileType(fileType);
					fileDTO.setRegistId(registId);
					// fileDTO.setRegistDate(registDate);

					fileList.add(fileDTO);

					// 실제 DB INSERT 메소드를 호출한다.
					this.insertUploadFiles(fileDTO);

					// 파일을 실제 물리적인 파일로 저장한다.
					File newFile = new File(fileDTO.getChngFileName());
					files.transferTo(newFile);
				} else {
					logger.info("file 없어요.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("끝!");

		return 0;
	}

	// DB 에 파일에 대한 정보를 생성한다.
	private void insertUploadFiles(FileDTO fileDTO) {
		fileMapper.insertUploadFiles(fileDTO);
	}

	// 게시글에 첨부된 파일을 조회한다.
	@Override
	public FileDTO selectFileList(int fileGroupId) {

		return fileMapper.selectFileList(fileGroupId);
	}

}
