package youtube;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

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

        System.out.println(("**********자막이 생성되었습니다**********"));
    }

    @PreUpdate
    public void onPreUpdate(){
        EditedCaption editedCaption = new EditedCaption();
        BeanUtils.copyProperties(this, editedCaption);
        editedCaption.publishAfterCommit();

        System.out.println(("**********자막이 수정되었습니다**********"));
    }

    @PreRemove
    public void onPreRemove(){
        DeletedCaption deletedCaption = new DeletedCaption();
        BeanUtils.copyProperties(this, deletedCaption);
        deletedCaption.publishAfterCommit();

        System.out.println(("**********자막이 삭제되었습니다**********"));
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
