package rsoapp.imagems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsoapp.imagems.model.Image;
import rsoapp.imagems.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getImageById(Integer imageId) {
        return imageRepository.getOne(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void updateImage(Integer imageId, Image imageData) {
        Image image = getImageById(imageId);

        image.setId(imageData.getId());
        image.setUserId(imageData.getUserId());
        image.setAdId(imageData.getAdId());
        image.setConsecutiveNumber(imageData.getConsecutiveNumber());
        image.setHeight(imageData.getHeight());
        image.setWidth(imageData.getWidth());
//        image.setCreated(imageData.getCreated());
        image.setImage(imageData.getImage());

        saveImage(image);
    }

    public void deleteImage(Integer imageId) {
        imageRepository.deleteById(imageId);
    }
}
