package com.setvect.common.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.setvect.common.util.StringUtilAd;
import com.setvect.literatureboy.boot.ApplicationException;

/**
 * Spring 2.5에서는 동일한 이름으로 첨부파일 등록 폼이 2개 이상이 경우 에러남 spring mvc 2.5.5 multipart
 * multi file upload 지원<br>
 * 참고: http://nezah.egloos.com/3994564<br>
 * 
 * @version $Id$
 */
public class MultiFileCommonsMultipartResolver extends CommonsMultipartResolver {

	private CheckAllowUploadFile ckFile;

	protected MultipartParsingResult parseFileItems(List fileItems, String encoding) {
		Map multipartFiles = new HashMap();
		Map multipartParameters = new HashMap();

		for (Iterator it = fileItems.iterator(); it.hasNext();) {
			FileItem fileItem = (FileItem) it.next();
			if (fileItem.isFormField()) {
				String value = null;
				if (encoding != null) {
					try {
						value = fileItem.getString(encoding);
					} catch (UnsupportedEncodingException ex) {
						if (this.logger.isWarnEnabled()) {
							this.logger.warn("Could not decode multipart item '" + fileItem.getFieldName()
									+ "' with encoding '" + encoding + "': using platform default");
						}

						value = fileItem.getString();
					}
				}
				else {
					value = fileItem.getString();
				}
				String[] curParam = (String[]) (String[]) multipartParameters.get(fileItem.getFieldName());
				if (curParam == null) {
					multipartParameters.put(fileItem.getFieldName(), new String[] { value });
				}
				else {
					String[] newParam = StringUtils.addStringToArray(curParam, value);
					multipartParameters.put(fileItem.getFieldName(), newParam);
				}
			}
			else {
				CommonsMultipartFile file = new CommonsMultipartFile(fileItem);
				// if (multipartFiles.put(file.getName(), file) != null) {
				// throw new
				// MultipartException("Multiple files for field name [" +
				// file.getName()
				// + "] found - not supported by MultipartResolver");
				// }

				if (!StringUtilAd.isEmpty(file.getOriginalFilename())) {
					if(ckFile != null){
						boolean allow = ckFile.check(file.getOriginalFilename()); 
						if(!allow){
							throw new RuntimeException("[" + file.getOriginalFilename() + "] is not allow file");
						}
					}

					List list = (List) multipartFiles.get(file.getName());
					if (list != null) {
						list.add(file);
					}
					else {
						List fileList = new ArrayList();
						fileList.add(file);
						multipartFiles.put(file.getName(), fileList);
					}

					if (this.logger.isDebugEnabled()) {
						this.logger.debug("Found multipart file [" + file.getName() + "] of size " + file.getSize()
								+ " bytes with original filename [" + file.getOriginalFilename() + "], stored "
								+ file.getStorageDescription());
					}
				}
			}

		}

		return new MultipartParsingResult(multipartFiles, multipartParameters);
	}

	protected void cleanupFileItems(Collection multipartFiles) {
		for (Iterator it = multipartFiles.iterator(); it.hasNext();) {
			// CommonsMultipartFile file = (CommonsMultipartFile) it.next();
			List fileList = (List) it.next();
			Iterator iter = fileList.iterator();
			while (iter.hasNext()) {
				CommonsMultipartFile file = (CommonsMultipartFile) iter.next();
				if (logger.isDebugEnabled()) {
					logger.debug("Cleaning up multipart file [" + file.getName() + "] with original filename ["
							+ file.getOriginalFilename() + "], stored " + file.getStorageDescription());
				}
				file.getFileItem().delete();
			}
		}
	}

	/**
	 * @param ckFile
	 *            파일 업로드 체크
	 */
	public void setCkFile(CheckAllowUploadFile ckFile) {
		this.ckFile = ckFile;
	}
}
