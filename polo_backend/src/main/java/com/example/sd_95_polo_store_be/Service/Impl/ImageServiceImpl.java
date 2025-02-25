package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusImage;
import com.example.sd_95_polo_store_be.Model.Request.ImageRequest;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import com.example.sd_95_polo_store_be.Repository.ImageRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ImageProductResponse> gets(Integer id) {
        return imageRepository.findByProductDetail(id);
    }

    @Override
    public void updateStatus(Integer id, ChangeStatusImage changeStatusImage) {
        var image = imageRepository.findById(id).orElseThrow();

        if (changeStatusImage.getStatus() != 0) {
            image.setStatus(0);
            imageRepository.save(image);
        } else {
            image.setStatus(1);
            imageRepository.save(image);
        }
    }

    @Override
    public void deleteOne(Integer id) {
        var image = imageRepository.findById(id).orElseThrow();
        imageRepository.deleteImage(id);
    }

    @Override
    public void createOrUpdate(List<ImageRequest> images, Integer productDetailId) {
        var now = OffsetDateTime.now();
        var productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail not found."));

        if (images != null) {
            List<Images> existingImages = imageRepository.findByProductDetail(productDetail);

            for (int i = 0; i < images.size(); i++) {
                ImageRequest imageRequest = images.get(i);

                if (imageRequest.getId() == null) {
                    Images existingImage = imageRepository.findByNameAndProductDetail(imageRequest.getName(), productDetail);

                    if (existingImage == null) {
                        Images newImage = new Images();
                        newImage.setName(imageRequest.getName());
                        newImage.setUrl_image(String.valueOf(existingImages.size()));
                        newImage.setStatus(1);
                        newImage.setCreatedAt(now);
                        newImage.setUpdatedAt(now);
                        newImage.setProductDetail(productDetail);
                        imageRepository.save(newImage);
                    }
                } else {
                    var imageUpdate = imageRepository.findById(imageRequest.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Image not found."));

                    if (!imageUpdate.getName().equals(imageRequest.getName())) {
                        imageUpdate.setName(imageRequest.getName());
                        imageUpdate.setUpdatedAt(now);
                        imageRepository.save(imageUpdate);
                    }
                }
            }
        }
    }

    @Override
    public void updateImages(List<ImageRequest> images, Integer productDetailId) {
        var now = OffsetDateTime.now();
        var productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail not found."));

        if (images != null) {
            List<Images> existingImages = imageRepository.findByProductDetail(productDetail);
            Set<String> existingImageNames = existingImages.stream()
                    .map(Images::getName)
                    .collect(Collectors.toSet());

            for (int i = 0; i < images.size(); i++) {
                ImageRequest imageRequest = images.get(i);

                if (imageRequest.getId() == null) {
                    // Kiểm tra xem ảnh đã tồn tại hay chưa
                    Images existingImage = imageRepository.findByNameAndProductDetail(imageRequest.getName(), productDetail);

                    if (existingImage == null) {
                        // Tạo mới ảnh
                        Images newImage = new Images();
                        newImage.setName(imageRequest.getName());
                        newImage.setUrl_image(imageRequest.getUrl_image());  // Set the URL of the new image
                        newImage.setStatus(1);
                        newImage.setCreatedAt(now);
                        newImage.setUpdatedAt(now);
                        newImage.setProductDetail(productDetail);
                        imageRepository.save(newImage);
                    }
                } else {
                    var imageUpdate = imageRepository.findById(imageRequest.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Image not found."));

                    if (!imageUpdate.getName().equals(imageRequest.getName())) {
                        // Cập nhật tên ảnh
                        imageUpdate.setName(imageRequest.getName());
                        imageUpdate.setUpdatedAt(now);
                        imageRepository.save(imageUpdate);
                    }
                }
            }
        }
    }

    @Override
    public void delete(List<ImageRequest> imageDelete, Integer id) {
        imageDelete.forEach(imageRequest -> imageRepository.deleteImage(imageRequest.getId()));
    }


}

