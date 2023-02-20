package com.eoemusic.eoebackend.controller;

import com.eoemusic.eoebackend.config.HitCounter;
import com.eoemusic.eoebackend.dao.MusicDao;
import com.eoemusic.eoebackend.domain.PlaylistResponse;
import com.eoemusic.eoebackend.domain.QueryRequest;
import com.eoemusic.eoebackend.domain.QueryResult;
import com.eoemusic.eoebackend.repository.MusicRepository;
import com.eoemusic.eoebackend.service.MusicService;
import com.eoemusic.eoebackend.utils.JSON;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: some desc
 * @author: Xiaoyu Wu
 * @email: wu.xiaoyu@northeastern.edu
 * @date: 20/01/23 12:49 AM
 */
@RestController
@Slf4j
@RequestMapping("/eoebackend/music")
public class MusicController {

  @Autowired
  private MusicService musicService;


  @GetMapping("/playlists/{userId}")
  public Map<String, List<PlaylistResponse>> getPlaylistsByUserId(
      @PathVariable("userId") Long userId) {
    return musicService.getPlaylistsByUserId(userId);
  }


  @PostMapping("/search")
  public QueryResult search(@RequestBody QueryRequest query,
      @RequestHeader(value = "region") String region) throws Exception {
    query.addCondition("region", region);
    if (log.isDebugEnabled()) {
      String json = JSON.toJson(query);
      log.info("Music search, param: {}", json);
      log.info("region is: {}", region);
    }
    return musicService.search(query);
  }

  @Autowired
  public MusicRepository musicRepository;

  @Autowired
  public MusicDao musicDao;

  @GetMapping("/hit/{musicId}")
  public void hit(@PathVariable("musicId") String musicId) {
    HitCounter.addHit(musicId);
  }

}
