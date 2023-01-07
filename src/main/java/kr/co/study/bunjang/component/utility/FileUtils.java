package kr.co.study.bunjang.component.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import kr.co.study.bunjang.component.properties.SystemProperties;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class FileUtils {

	public final String FILE_SEPARATOR = File.separator;

	public final List<String> EXCEL_EXTENSIONS = Arrays.asList("application/vnd.ms-excel"
																  , "application/msexcel"
																  , "application/x-msexcel"
																  , "application/x-ms-excel"
																  , "application/x-excel"
																  , "application/x-dos_ms_excel"
																  , "application/xls"
																  , "application/x-xls"
																  , "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	public final List<String> PDF_EXTENSIONS = Arrays.asList("application/pdf");

	public final List<String> HWP_EXTENSIONS = Arrays.asList("application/x-hwp"
																, "application/haansofthwp"
																, "application/vnd.hancom.hwp"
																, "application/x-hwt"
																, "application/haansofthwt"
																, "application/vnd.hancom.hwt"
																, "application/vnd.hancom.hml"
																, "application/haansofthml"
																, "application/vnd.hancom.hwpx");

	public String getMimeType(InputStream is) {
		String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		try {
			mimeType = new Tika().detect(is);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return mimeType;
	}

	public boolean isImage(InputStream is) {
		return !(getMimeType(is).indexOf("image/") == -1);
	}
	
	public boolean isExcel(InputStream is) {
		return EXCEL_EXTENSIONS.contains(getMimeType(is));
	}
	
	public boolean isPDF(InputStream is) {
		return PDF_EXTENSIONS.contains(getMimeType(is));
	}
	
	public boolean isHWP(InputStream is) {
		return PDF_EXTENSIONS.contains(getMimeType(is));
	}

	public String getExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}
	
	public String nameToUUID(String fileName) {
		return UUID.randomUUID().toString() + "." + getExt(fileName);
	}

	public final String[] FILE_SIZE_UNIT = { "bytes", "KB", "MB", "GB", "TB", "PB" };

	public String sizeFormat(Double size) {
		int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
		double ret = ((size / Math.pow(1024, Math.floor(idx))));

		DecimalFormat df = new DecimalFormat("#,###.##");
		return df.format(ret) + " " + FILE_SIZE_UNIT[idx];
	}

	public synchronized File upload(String uploadPath, MultipartFile multipartFile) throws IOException {
		/* 폴더 생성 */
		File folder = new File(SystemProperties.FILE_BASE_UPLOAD_PATH);
		if(!folder.exists()) folder.mkdirs();
		
		String fileName = nameToUUID(multipartFile.getOriginalFilename());
		
		/* 파일 업로드 */
		File file = new File(folder.getCanonicalPath() + FILE_SEPARATOR + fileName);
		multipartFile.transferTo(file);
		return file;
	}

	public synchronized File download(String filePath) throws IOException {
	   return new File(filePath);
	}
}