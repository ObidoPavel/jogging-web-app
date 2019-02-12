package com.obido.service;

import com.obido.domain.PictureBin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {

    PictureBin save(MultipartFile file);

    List<PictureBin> readAllPictures();
}
