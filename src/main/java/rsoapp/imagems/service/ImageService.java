package rsoapp.imagems.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rsoapp.imagems.model.dto.AdImagesDto;
import rsoapp.imagems.model.entity.Image;
import rsoapp.imagems.repository.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        image.setImageBytes(imageCompressionService.compressBytes(imageFile.getBytes()));
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

//    public AdImagesDto updateAdImages(Integer adId, AdImagesDto adImagesDto) {
//        List<Image> adImages = adImagesDto.getImages();
//        for(Image image : adImages) {
//            Image adImage;
//            if(image.getId() != null) {
//                Optional<Image> queriedImage = getImageById(image.getId());
//                adImage = queriedImage.orElseGet(Image::new);
//                adImage.setId(image.getId());
//                adImage.setAdId(adId);
//                adImage.setHeight(image.getHeight());
//                adImage.setWidth(image.getWidth());
//                adImage.setImageBytes(image.getImageBytes());
//                saveImage(adImage);
//            }
//            else {
//                image.setAdId(adId);
//                saveImage(image);
//            }
//        }
//        return new AdImagesDto(adImages);
//    }



    public void deleteImage(Integer imageId) {
        imageRepository.deleteById(imageId);
    }

    public void deleteAdImages(Integer adId) {
        imageRepository.removeAllByAdId(adId);
    }
}
