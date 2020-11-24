package rsoapp.imagems.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rsoapp.imagems.model.entity.Image;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdImagesDto {

    public List<Image> images;
}
