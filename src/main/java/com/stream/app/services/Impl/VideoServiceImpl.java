package com.stream.app.services.Impl;

import com.stream.app.entities.Video;
import com.stream.app.repositories.VideoRepository;
import com.stream.app.services.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Value("${files.video}")
    String DIR;

    @Autowired
    private VideoRepository videoRepository;

    @PostConstruct
    public void init(){
        File file = new File(DIR);
        if(!file.exists()){
            file.mkdir();
            System.out.println("Folder created: "+DIR);
        }else{
            System.out.println("Folder already created: "+DIR);
        }
    }
    @Override
    public Video save(Video video, MultipartFile file) {
        try{
            //original file name
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            //file path
            String cleanFileName = StringUtils.cleanPath(filename);

            //folder path : create
            String cleanFolder = StringUtils.cleanPath(DIR);

            //folder path with filename
            Path path = Paths.get(cleanFolder , cleanFileName);

            System.out.println(path);
            System.out.println(contentType);

            //copy files to the folder
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            //video meta data

            video.setContentType(contentType);
            video.setFilePath(path.toString());

            //metadata save to db
            return videoRepository.save(video);
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return null;
    }
}
