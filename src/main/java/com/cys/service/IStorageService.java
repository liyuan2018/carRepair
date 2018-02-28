package com.cys.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by liyuan on 2018/2/28.
 */
public interface IStorageService {

    public Long getFileLength(String fileName);

    public boolean fileExist(String fileName);

    public Object upload(String fileName, byte[] bytes) throws IOException;

    public Object upload(String fileName, String fileContent) throws IOException;

    public Object upload(String fileName,File file);

    public Object upload(String fileName, MultipartFile multipartFile) throws Exception;

    public File download(String fileName);

    public File download(String fileName,File file);

    public Object delete(String fileName);
}
