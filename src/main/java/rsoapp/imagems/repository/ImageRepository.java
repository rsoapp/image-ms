package rsoapp.imagems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rsoapp.imagems.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
