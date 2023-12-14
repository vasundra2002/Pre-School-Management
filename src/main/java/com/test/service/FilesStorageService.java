package com.test.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  
  public void init();//it will simply creating directory to store the images

  public void save(MultipartFile file);//save multipartFile file

  public Resource load(String filename);
  
  public boolean delete(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
  
  //public  Stream<Path> findByFileName(String fileName) throws IOException;
}
