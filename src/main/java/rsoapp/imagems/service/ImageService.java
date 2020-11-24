package rsoapp.imagems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsoapp.imagems.model.dto.AdImagesDto;
import rsoapp.imagems.model.entity.Image;
import rsoapp.imagems.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Optional<Image> getImageById(Integer imageId) {
        return imageRepository.findById(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getAdImages(Integer adId) {
        return imageRepository.getAllByAdId(adId);
    }



    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }



    public void updateImage(Integer imageId, Image imageData) {
        Optional<Image> queriedImage = getImageById(imageId);

        if (queriedImage.isPresent()) {
            Image image = queriedImage.get();
            image.setId(imageData.getId());
            image.setAdId(imageData.getAdId());
            image.setConsecutiveNumber(imageData.getConsecutiveNumber());
            image.setHeight(imageData.getHeight());
            image.setWidth(imageData.getWidth());
            image.setImage(imageData.getImage());

            saveImage(image);
        }
    }

    public AdImagesDto updateAdImages(Integer adId, AdImagesDto adImagesDto) {
        List<Image> adImages = adImagesDto.getImages();
        for(Image image : adImages) {
            Image adImage;
            if(image.getId() != null) {
                Optional<Image> queriedImage = getImageById(image.getId());
                adImage = queriedImage.orElseGet(Image::new);
                adImage.setId(image.getId());
                adImage.setAdId(adId);
                adImage.setConsecutiveNumber(image.getConsecutiveNumber());
                adImage.setHeight(image.getHeight());
                adImage.setWidth(image.getWidth());
                adImage.setImage(image.getImage());
                saveImage(adImage);
            }
            else {
                image.setAdId(adId);
                saveImage(image);
            }
        }
        return new AdImagesDto(adImages);
    }



    public void deleteImage(Integer imageId) {
        imageRepository.deleteById(imageId);
    }

    public void deleteAdImages(Integer adId) {
        imageRepository.removeAllByAdId(adId);
    }
}
