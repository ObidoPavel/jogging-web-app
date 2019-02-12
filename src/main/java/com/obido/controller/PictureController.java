package com.obido.controller;

import com.obido.domain.PictureBin;
import com.obido.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
class PictureController {

    @Autowired
    private
    PictureService pictureService;

    @PostMapping("/uploadPicture")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        PictureBin pictureBin = pictureService.save(file);
        return ResponseEntity.created(pictureBin.getFileDownloadUri()).build();
    }

    @PostMapping("/allPictures")
    List<PictureBin> retrieveAllPictures() {
        return pictureService.readAllPictures();
    }
}
