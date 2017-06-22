/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.baidu.api.domain;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.baidu.api.utils.BaiduUtil;

/**
 * 用户上传文件信息类的封装
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public final class FileItem {

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 媒体类型
     */
    private String mimeType;

    /**
     * 文件字节流
     */
    private byte[] content;

    /**
     * File 文件
     */
    private File file;

    /**
     * 基于本地的File对象构造对象
     * 
     * @param file file对象
     */
    public FileItem(File file) {
        this.file = file;
    }

    /**
     * 基于本地的文件路径地址构建对象
     * 
     * @param filePath 文件路径地址
     */
    public FileItem(String filePath) {
        this(new File(filePath));
    }

    /**
     * 基于文件名称、字节流构建对象
     * 
     * @param fileName 文件名称
     * @param content 文件字节流
     */
    public FileItem(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    /**
     * 基于文件名、字节流和媒体类型的构造器。
     * 
     * @param fileName 文件名
     * @param content 文件字节流
     * @param mimeType 媒体类型
     */
    public FileItem(String fileName, byte[] content, String mimeType) {
        this(fileName, content);
        this.mimeType = mimeType;
    }

    /**
     * 获取文件名称
     * 
     * @return 文件名称
     */
    public String getFileName() {
        if (this.fileName == null && this.file != null && this.file.exists()) {
            this.fileName = this.file.getName();
        }
        return this.fileName;
    }

    /**
     * 获取文件的字节流信息
     * 
     * @return 文件的字节流信息
     * @throws IOException
     */
    public byte[] getContent() throws IOException {
        if (this.content == null && this.file != null && this.file.exists()) {
            InputStream in = null;
            byte [] readBuffer = new byte[1024*1024];
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read(readBuffer)) > 0) {
                    out.write(readBuffer,0,ch);
                }
                this.content = out.toByteArray();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                        in = null;
                    } catch (IOException e) {
                        in = null;
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                        out = null;
                    } catch (IOException e) {
                        out = null;
                    }
                }
            }
        }
        return this.content;
    }

    /**
     * 获取文件的媒体类型
     * 
     * @return 文件的媒体类型
     * @throws IOException
     */
    public String getMimeType() throws IOException {
        if (this.mimeType == null && this.file != null && this.file.exists()) {
            this.mimeType = BaiduUtil.getMimeType(getContent());
        }
        return this.mimeType;
    }
}
