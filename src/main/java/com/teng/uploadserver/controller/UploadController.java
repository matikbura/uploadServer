package com.teng.uploadserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {
    @Value("${savePath}")
    String path;
    @Value("${savePath1}")
    String path1;

    /**
     * 上传文件
     *
     * @param file
     * @return * @throws Exception
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file, String myPath) throws Exception {
        String base = "";
        if (myPath != null) {
            base = path + myPath;
        } else {
            base = path;
        }
        String fileName = "";
        String id = "";
        if (!file.isEmpty()) {
            BufferedOutputStream out = null;
            System.out.println(base);
            File fileSourcePath = new File(base);
            if (!fileSourcePath.exists()) {
                fileSourcePath.mkdirs();
            }
            fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            id = getId() + suffix;
            out = new BufferedOutputStream(
                    new FileOutputStream(new File(fileSourcePath, id)));
            out.write(file.getBytes());
            out.flush();
            out.close();
            System.out.println(id.toString());
        }
        return myPath != null ? myPath + "/" + id : "/" + id;
    }


    @RequestMapping("/uploadImg1")
    @ResponseBody
    public String uploadImg1(MultipartFile file, String myPath) throws Exception {
        String base = "";
        if (myPath != null) {
            base = path1 + myPath;
        } else {
            base = path1;
        }
        String fileName = "";
        String id = "";
        if (!file.isEmpty()) {
            BufferedOutputStream out = null;
            System.out.println(base);
            File fileSourcePath = new File(base);
            if (!fileSourcePath.exists()) {
                fileSourcePath.mkdirs();
            }
            fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            id = getId() + suffix;
            out = new BufferedOutputStream(
                    new FileOutputStream(new File(fileSourcePath, id)));
            out.write(file.getBytes());
            out.flush();
            System.out.println(id.toString());
        }

        return myPath != null ? myPath + "/" + id : "/" + id;
    }


    public String getId() {
        return UUID.randomUUID().toString().substring(24);
    }
}