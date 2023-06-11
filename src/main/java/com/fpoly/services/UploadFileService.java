package com.fpoly.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	public File save(MultipartFile file, String path) {

		try {
			// Tạo đối tượng File từ đường dẫn và tên tệp
			File directory = new File(path);
			// Tạo thư mục mới nếu thư mục không tồn tại
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String filename = file.getOriginalFilename();
			File savedFile = new File(directory.getAbsolutePath() + File.separator + filename);

			// Lưu tệp vào thư mục
			FileOutputStream os = new FileOutputStream(savedFile);
			os.write(file.getBytes());
			os.close();

			// Trả về đối tượng File đã lưu
			return savedFile;
		} catch (IOException e) {
			throw new RuntimeException("Lỗi lưu tệp", e); // Ném lỗi nếu xảy ra lỗi khi lưu tệp
		}
	}
}
