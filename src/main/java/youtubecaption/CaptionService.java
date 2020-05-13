package youtubecaption;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="CaptionService_table")
public class CaptionService {

    private Long videoId;
    private String captionInfo;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long captionId;

    @PrePersist
    public void onPrePersist(){
        CreatedCaption createdCaption = new CreatedCaption();
        BeanUtils.copyProperties(this, createdCaption);
        createdCaption.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        EditedCaption editedCaption = new EditedCaption();
        BeanUtils.copyProperties(this, editedCaption);
        editedCaption.publishAfterCommit();


    }

    @PreRemove
    public void onPreRemove(){
        DeletedCaption deletedCaption = new DeletedCaption();
        BeanUtils.copyProperties(this, deletedCaption);
        deletedCaption.publishAfterCommit();


    }


    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
    public String getCaptionInfo() {
        return captionInfo;
    }

    public void setCaptionInfo(String captionInfo) {
        this.captionInfo = captionInfo;
    }
    public Long getCaptionId() {
        return captionId;
    }

    public void setCaptionId(Long captionId) {
        this.captionId = captionId;
    }




}
