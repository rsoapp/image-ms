package rsoapp.imagems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rsoapp.imagems.model.dto.AdImagesDto;
import rsoapp.imagems.model.entity.Image;
import rsoapp.imagems.service.ImageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("images")
    public ResponseEntity<List<Image>> getAllImages() {
        try {
            List<Image> images = imageService.getAllImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("images/{imageId}")
    public ResponseEntity<Optional<Image>> getImageById(@PathVariable Integer imageId) {
        try {
            return new ResponseEntity<>(imageService.getImageById(imageId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("images/ad/{adId}")
    public ResponseEntity<AdImagesDto> getAdImages(@PathVariable Integer adId) {
        try {
            return new ResponseEntity<>(new AdImagesDto(imageService.getAdImages(adId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("images/ad/{adId}")
    public ResponseEntity<Image> uploadImage(@PathVariable Integer adId, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            return new ResponseEntity<>(imageService.saveImage(adId, imageFile), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("images/{imageId}")
    public ResponseEntity<Void> updateImage(@PathVariable Integer imageId, @RequestBody Image imageData) {
        try {
            imageService.updateImage(imageId, imageData);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("images/ad/{adId}")
    public ResponseEntity<AdImagesDto> updateAdImages(@PathVariable Integer adId, @RequestBody List<MultipartFile> adImageFiles) {
        try {
            return new ResponseEntity<>(imageService.updateAdImages(adId, adImageFiles), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @DeleteMapping("images/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer imageId) {
        try {
            imageService.deleteImage(imageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("images/ad/{adId}")
    public ResponseEntity<Void> deleteAdImages(@PathVariable Integer adId) {
        try {
            imageService.deleteAdImages(adId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
