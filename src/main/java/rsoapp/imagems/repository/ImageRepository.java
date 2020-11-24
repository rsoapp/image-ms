package rsoapp.imagems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rsoapp.imagems.model.entity.Image;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Transactional
    List<Image> getAllByAdId(Integer adId);

    @Transactional
    void removeAllByAdId(Integer adId);
}
