package cool.kar.neteasecloudmusic.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Music {
    Object id;
    String musicName;
    String author;
    String src;
    Integer musicTime;
    String imgSrc;
    String uploadTime;


}
