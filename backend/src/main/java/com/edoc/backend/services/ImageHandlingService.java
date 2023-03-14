package com.edoc.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings({"unused"})
@Transactional
public class ImageHandlingService {
  @Value("${content.upload.folder}")
  private String folderName;

  @PostConstruct
  public void myInit() {
    System.out.println("in myInit " + folderName);
    File path = new File(folderName);
    if (!path.exists()) {
      System.out.println("creating images folder...");
      path.mkdirs();
    } else
      System.out.println("folder alrdy exists....");
  }

  public String uploadImage(Long id, MultipartFile imageFile) throws IOException {
    String targetPath = folderName +  File.separator + id + "_" + imageFile.getOriginalFilename();
    System.out.println(targetPath);
    Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
    return targetPath;
  }

  public byte[] serveImage(String path) throws IOException {
    return Files.readAllBytes(Paths.get(path));
  }

  public String[] uploadImages(Long id, MultipartFile[] documents) throws IOException {
    List<String> paths = new ArrayList<>();
    for (MultipartFile file : documents) {
      paths.add(uploadImage(id, file));
    }
    return (String[]) paths.toArray();
  }
}
