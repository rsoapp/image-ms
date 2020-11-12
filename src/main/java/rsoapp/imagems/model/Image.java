package rsoapp.imagems.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "ad_id")
    private Integer adId;

    @Column(name = "consecutive_number")
    private Integer consecutiveNumber;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

//    @Column(name = "created")
//    private Instant created;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private Byte[] image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getConsecutiveNumber() {
        return consecutiveNumber;
    }

    public void setConsecutiveNumber(Integer consecutiveNumber) {
        this.consecutiveNumber = consecutiveNumber;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

//    public Instant getCreated() {
//        return created;
//    }
//
//    public void setCreated(Instant created) {
//        this.created = created;
//    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
