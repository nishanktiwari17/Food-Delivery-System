package com.oviyahsridhar.fooddelivery.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImgService {

    String uploadImage(MultipartFile file);
}
