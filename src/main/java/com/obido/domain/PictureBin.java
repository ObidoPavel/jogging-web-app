package com.obido.domain;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class PictureBin {

    private String fileName;

    private URI fileDownloadUri;

}
