package rsoapp.imagems.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rsoapp.imagems.model.dto.AdImagesDto;
import rsoapp.imagems.model.entity.Image;
import rsoapp.imagems.repository.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageCompressionService imageCompressionService;

    public ImageService(ImageRepository imageRepository, ImageCompressionService imageCompressionService) {
        this.imageRepository = imageRepository;
        this.imageCompressionService = imageCompressionService;
    }

    public Optional<Image> getImageById(Integer imageId) {
        return imageRepository.findById(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getAdImages(Integer adId) {
        return imageRepository.getAllByAdId(adId);
    }



    public Image saveImage(Integer adId, MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setAdId(adId);
        BufferedImage bimg = ImageIO.read(imageFile.getInputStream());
        image.setHeight(bimg.getHeight());
        image.setWidth(bimg.getWidth());
//        image.setImageBytes(imageCompressionService.compressBytes(imageFile.getBytes()));
        image.setImageBytes(imageFile.getBytes());
        return imageRepository.save(image);
    }



    public void updateImage(Integer imageId, Image imageData) {
        Optional<Image> queriedImage = getImageById(imageId);

        if (queriedImage.isPresent()) {
            Image image = queriedImage.get();
            image.setId(imageData.getId());
            image.setAdId(imageData.getAdId());
            image.setHeight(imageData.getHeight());
            image.setWidth(imageData.getWidth());
            image.setImageBytes(imageData.getImageBytes());

            imageRepository.save(image);
        }
    }

    public AdImagesDto updateAdImages(Integer adId, List<MultipartFile> adImageFiles) throws IOException {
        deleteAdImages(adId);
        List<Image> updatedImages = new ArrayList<>();

        for(MultipartFile imageFile : adImageFiles) {
            updatedImages.add(saveImage(adId, imageFile));
        }

        return new AdImagesDto(updatedImages);
    }



    public void deleteImage(Integer imageId) {
        imageRepository.deleteById(imageId);
    }

    public void deleteAdImages(Integer adId) {
        imageRepository.removeAllByAdId(adId);
    }
}
