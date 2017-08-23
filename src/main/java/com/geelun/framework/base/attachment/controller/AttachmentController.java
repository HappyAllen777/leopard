package com.geelun.framework.base.attachment.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.geelun.framework.base.attachment.model.Attachment;
import com.geelun.framework.base.attachment.service.AttachmentService;
import com.geelun.framework.base.attachment.util.AttachmentUtil;
import com.geelun.framework.util.AjaxResultUtil;
import com.geelun.framework.util.FileUtil;
import com.geelun.framework.util.IDUtil;
import com.geelun.framework.util.JsonUtil;
import com.geelun.framework.util.PictureUtil;
import com.geelun.framework.util.RequestUtil;
import com.geelun.framework.util.StringUtil;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(method = RequestMethod.POST, value = { "base64PictureUpload" })
	public void base64PictureUpload(HttpServletRequest request, HttpServletResponse response) {
		String base64PictureDataFieldName = "base64PictureData";
		String fileNameFieldName = "fileName";
		String categoryFieldName = "category";
		String base64PictureData = RequestUtil.getParam(request, base64PictureDataFieldName);
		String fileName = RequestUtil.getParam(request, fileNameFieldName);
		String category = RequestUtil.getParam(request, categoryFieldName);

		if (StringUtil.isBlank(base64PictureData)) {
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.fieldIsEmpty(base64PictureDataFieldName));
			return;
		}

		if (StringUtil.isBlank(fileName)) {
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.fieldIsEmpty(fileNameFieldName));
			return;
		}

		if (StringUtil.isBlank(category)) {
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.fieldIsEmpty(categoryFieldName));
			return;
		}

		// TODO : 判断category是否合法
		String pictureRootDir = FileUtil.getPictureRootDir();
		String fullFileDiskDir = request.getRealPath(pictureRootDir) + "/" + category;
		File pictureDir = new File(fullFileDiskDir);
		if (!pictureDir.exists()) {
			pictureDir.mkdirs();
		}

		String fileSubfix = PictureUtil.getFileSubfixByFileName(fileName);
		String randomFileName = IDUtil.getUUID() + fileSubfix;
		String fullFileDiskPath = fullFileDiskDir + "/" + randomFileName;

		byte[] pictureBytes = null;
		try {
			pictureBytes = PictureUtil.convertBase64DataToBytes(base64PictureData);
		} catch (IOException e) {
			e.printStackTrace();
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.convertPictureFailed());
			return;
		}
		if (pictureBytes == null) {
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.convertPictureFailed());
			return;
		}
		File uploadPicture = new File(fullFileDiskPath);
		try {
			FileUtil.writeBytesToFile(pictureBytes, uploadPicture);
		} catch (IOException e) {
			e.printStackTrace();
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.uploadPictureFailed());
			return;
		}

		Attachment attachment = AttachmentUtil.createAttachmentByFile(uploadPicture);
		attachment.setCreateBy("Allen");
		attachment.setName(fileName);
		attachment.setType("picture");
		attachment.setUri(pictureRootDir + "/" + category + "/" + randomFileName);
		try {
			attachmentService.add(attachment);
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.success(request.getContextPath() + attachment.getUri()));
		} catch (Exception e) {
			uploadPicture.delete();
			e.printStackTrace();
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.uploadPictureFailed());
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = { "uploadFile" })
	public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		// 上传文件每日单独保存
		String path = "/upload/";

		if (file.getSize() > 0) {
			// 获取绝对路径
			String uploadPath = request.getSession().getServletContext().getRealPath(path);
			try {
				// 创建目标文件
				File targetFile = new File(uploadPath, file.getOriginalFilename());
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				file.transferTo(targetFile);
				JsonUtil.writeJsonResponse(response, AjaxResultUtil.success("上传成功"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
