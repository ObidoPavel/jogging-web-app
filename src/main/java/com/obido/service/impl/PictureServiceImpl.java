package com.obido.service.impl;

import com.obido.domain.PictureBin;
import com.obido.service.PictureService;
import com.obido.service.JogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogService.class);

    @Override
    public PictureBin save(MultipartFile file) {
        return null;
    }

    @Override
    public List<PictureBin> readAllPictures() {
        return new ArrayList<>();
    }

    private URI getFileDownloadUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pictures/")
                .path(fileName)
                .build().toUri();
    }
}
