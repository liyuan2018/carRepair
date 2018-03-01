package com.cys.service.impl;

import com.cys.component.NormalStorageComponent;
import com.cys.constants.HardCode;
import com.cys.constants.StorageTypeConstant;
import com.cys.exception.BusinessException;
import com.cys.service.IStorageComponent;
import com.cys.service.IStorageService;
import com.cys.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liyuan on 2018/2/28.
 */
@Service("storageService")
public class StorageServiceImpl implements IStorageService {

    @Override
    public Long getFileLength(String fileName) {
        return getStorageComponent().getFileLength(fileName);
    }

    @Override
    public boolean fileExist(String fileName) {
        return getStorageComponent().fileExist(fileName);
    }

    @Override
    public Object upload(String fileName, byte[] bytes) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            file.createNewFile();
            outputStream.write(bytes);
        }
        return getStorageComponent().upload(fileName, file);
    }

    @Override
    public Object upload(String fileName, String fileContent) throws IOException {
        return getStorageComponent().upload(fileName,fileContent);
    }

    @Override
    public Object upload(String fileName,File file) {
        return getStorageComponent().upload(fileName,file);
    }

    @Override
    public Object upload(String fileName,MultipartFile multipartFile) throws Exception {
        return getStorageComponent().upload(fileName, multipartFile);
    }

    @Override
    public File download(String fileName, File file) {
        return getStorageComponent().download(fileName, file);
    }

    @Override
    public File download(String fileName) {
        return getStorageComponent().download(fileName);
    }

    //@Override
    //public InputStream downloadAsStream(String fileName) throws IOException {
    //    return getStorageComponent().downloadAsStream(fileName);
    //}

    @Override
    public Object delete(String fileName) {
        getStorageComponent().delete(fileName);
        return null;
    }

    private IStorageComponent getStorageComponent(){
        String storageType = HardCode.STORAGE_TYPE;
        if (StringUtils.isEmpty(storageType)) {
            throw new BusinessException("未在系统配置中配置存储方式: HardCode.STORAGE_TYPE文件存储类型");
        }

        switch (storageType.toLowerCase()){
            case StorageTypeConstant.NORMAL:
            case StorageTypeConstant.NORMAL_1:
                return SpringUtils.getBean(NormalStorageComponent.class);
            default:
                return null;
        }
    }

}

