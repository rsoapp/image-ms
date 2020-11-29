package rsoapp.imagems.controller;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsoapp.imagems.model.dto.AdImagesDto;
import rsoapp.imagems.model.dto.Info;
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

    @GetMapping("info")
    public Info getAppInfo() {
        String[] clani = {"ms1004"};
        String opis_projekta = "Moj projekt implementira oglasnik za glasbenike. Uporabniki lahko objavljajo, urejajo in pregledujejo oglase ter si med seboj lahko pošiljajo sporočila.";
        String[] mikrostoritve = {"http://35.239.153.130:8080/v1/image"};
        String[] github = {"https://github.com/rsoapp/image-ms"};
        String[] travis = {"https://travis-ci.org/github/rsoapp/image-ms"};
        String[] dockerhub = {"https://hub.docker.com/repository/docker/rsolinux/image-ms"};
        return new Info(clani, opis_projekta, mikrostoritve, github, travis, dockerhub);
    }

    @GetMapping("image")
    public ResponseEntity<List<Image>> getAllImages() {
        try {
            List<Image> images = imageService.getAllImages();
            return new ResponseEntity<>(images, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("image/{imageId}")
    public ResponseEntity<Optional<Image>> getImageById(@PathVariable Integer imageId) {
        try {
            return new ResponseEntity<>(imageService.getImageById(imageId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("ads/{adId}/images")
    public ResponseEntity<AdImagesDto> getAdImages(@PathVariable Integer adId) {
        try {
            return new ResponseEntity<>(new AdImagesDto(imageService.getAdImages(adId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("ads/{adId}/images")
    public ResponseEntity<AdImagesDto> updateAdImages(@PathVariable Integer adId, @RequestBody AdImagesDto adImagesDto) {
        try {
            return new ResponseEntity<>(imageService.updateAdImages(adId, adImagesDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("ads/{adId}/images")
    public ResponseEntity<Void> deleteAdImages(@PathVariable Integer adId) {
        try {
            imageService.deleteAdImages(adId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("image")
    public ResponseEntity<Image> saveImage(@RequestBody Image image) {
        try {
            return new ResponseEntity<>(imageService.saveImage(image), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("image/{imageId}")
    public ResponseEntity<Void> updateImage(@PathVariable Integer imageId, @RequestBody Image imageData) {
        try {
            imageService.updateImage(imageId, imageData);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("image/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer imageId) {
        try {
            imageService.deleteImage(imageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
