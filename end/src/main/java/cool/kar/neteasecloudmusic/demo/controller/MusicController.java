package cool.kar.neteasecloudmusic.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.kar.neteasecloudmusic.demo.entity.Music;
import cool.kar.neteasecloudmusic.demo.mapper.MusicMapper;
import cool.kar.neteasecloudmusic.demo.utils.RandomUtils;
import cool.kar.neteasecloudmusic.demo.vo.SuccessVO;
import cool.kar.neteasecloudmusic.demo.vo.VO;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/music/")

public class MusicController {
    static String baseMusicPath = "/home/kar/IdeaProjects/netease-cloud-music/static/music/";
    static String baseImgPath = "/home/kar/IdeaProjects/netease-cloud-music/static/img/";
    @Autowired
    MusicMapper musicMapper;


    @PostMapping("/upload")
    public VO upload(MultipartFile musicFile, MultipartFile imgFile, Music music) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        music.setUploadTime(new Date().toString());
        RandomUtils randomUtils = new RandomUtils();
        music.setImgSrc(randomUtils.generalRandom() + currentTimeMillis + ".jpg");
        music.setSrc(randomUtils.generalRandom() + currentTimeMillis + ".mp3");
        File file = new File(baseMusicPath + music.getSrc());
        File file2 = new File(baseImgPath + music.getImgSrc());
        musicFile.transferTo(file);
        imgFile.transferTo(file2);
        MP3File f = (MP3File) AudioFileIO.read(file);
        MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
        music.setMusicTime(audioHeader.getTrackLength());
        musicMapper.insert(music);
        return new SuccessVO();
    }

    @GetMapping("/get")
    public VO get(String key) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.like("music_name", key).or().like("author", key).or().like("id", key);


        List<Music> musics = musicMapper.selectList(wrapper);

        for (Music music : musics) {
            music.setId(music.getId().toString());
        }
        return new SuccessVO(musics);
    }


    @PostMapping("/update")
    public VO update(MultipartFile musicFile, MultipartFile imgFile, Music music) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        music.setUploadTime(new Date().toString());
        RandomUtils randomUtils = new RandomUtils();
        music.setImgSrc(randomUtils.generalRandom() + currentTimeMillis + ".jpg");
        music.setSrc(randomUtils.generalRandom() + currentTimeMillis + ".mp3");
        File file = new File(baseMusicPath + music.getSrc());
        File file2 = new File(baseImgPath + music.getImgSrc());
        musicFile.transferTo(file);
        imgFile.transferTo(file2);
        MP3File f = (MP3File) AudioFileIO.read(file);
        MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
        music.setMusicTime(audioHeader.getTrackLength());
        musicMapper.updateById(music);
        return new SuccessVO();
    }


    @DeleteMapping("/delete")
    public VO delete(Long id) {
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        System.out.println(id);
        System.out.println(musicMapper.delete(wrapper));
        return new SuccessVO();

    }
}
