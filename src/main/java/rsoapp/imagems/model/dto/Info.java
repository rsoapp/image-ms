package rsoapp.imagems.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Info {

    private String[] clani;
    private String opis_projekta;
    private String[] mikrostoritve;
    private String[] github;
    private String[] travis;
    private String[] dockerhub;
}
