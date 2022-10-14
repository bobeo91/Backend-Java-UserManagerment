package com.npv.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	String upLoadImage(MultipartFile image) throws IOException;
}
