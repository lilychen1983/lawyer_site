package com.law.video.track.list.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.law.commons.AbsService;
import com.law.commons.BaseForm;
import com.law.commons.Result;
import com.law.commons.redis.cache.ICacheClient;
import com.law.commons.redis.cache.service.RedisClient;
import com.law.commons.util.PpsConfig;
import com.law.commons.util.StringUtil;
import com.law.video.get.list.dao.IVideoDao;
import com.law.video.record.upload.bean.Account_Video_REL;
import com.law.video.record.upload.bean.VideoPo;
import com.law.video.track.list.bean.TrackForm;
import com.law.video.track.list.bean.TrackList;
import com.law.video.track.list.bean.TrackVo;

@Service("trackService")
public class TrackService extends AbsService implements ITrackService {
	private static final Logger logger = Logger.getLogger(TrackService.class);
	public static final String MESSAGE_URL = PpsConfig.getString("messageUrl");
	@Resource(name = "videoDao")
	private IVideoDao videoDao;

	@Override
	public Result getTrackList(TrackForm form) {
		Result result = this.valid(form);
		if (!Result.SUCCESS_CODE.equals(result.getStatus())) {
			return result;
		}

		ICacheClient cacheClient = null;
		try {
			cacheClient = new RedisClient();
			List<TrackVo> videos = this.getTracks(cacheClient, form);
			// List<TrackVo> videos = this.videoDao.getTrackList(form);
			TrackList trackList = new TrackList();
			trackList.setVideoList(videos);
			result.setResultBody(trackList);
		} catch (Exception e) {
			logger.error("send sms error:", e);
			result.setStatus(Result.OTHER_ERROR);
			result.setDesc("获取已直播视频列表（我的足迹）失败!");
		} finally {
			cacheClient.release();

		}

		return result;
	}

	private List<TrackVo> getTracks(ICacheClient cacheClient, TrackForm form) throws Exception {

		List<TrackVo> videos = null;
		String accountRelVideoKey = Account_Video_REL.KEY  + form.getAccount();
		int start = 0;
		int count = 20;
		if (!StringUtils.isBlank(form.getStart())) {
			start = StringUtil.string2int(form.getStart());
		}  

		if (!StringUtils.isBlank(form.getCount())) {
			count = StringUtil.string2int(form.getCount());
		}  
		if (cacheClient.exists(accountRelVideoKey)) {
			videos = new ArrayList<TrackVo>();
			Set<String> set = cacheClient.zrange(accountRelVideoKey, start, start + count);

			for (String videoId : set) {
				String videoKey = VideoPo.KEY+videoId;
				TrackVo video = new TrackVo();
				String videoID = cacheClient.hget(videoKey, VideoPo.VIDEOID);
				String account =  cacheClient.hget(videoKey, VideoPo.ACCOUNT);
				String title =  cacheClient.hget(videoKey, VideoPo.VIDEO_TITLE);
				String videoImage = cacheClient.hget(videoKey, VideoPo.VIDEO_IMAGE);
				int type = StringUtil.string2int(cacheClient.hget(videoKey, VideoPo.VIDEO_TYPE));
				String playUrl = cacheClient.hget(videoKey, VideoPo.PLAY_URL);

				video.setVideoID(videoID);
				video.setAccount(account);
				video.setTitle(title);
				video.setVideoImage(videoImage);
				video.setType(type);
				video.setPlayUrl(playUrl);
				videos.add(video);
			}
		} else {
			videos = this.videoDao.getTrackList(form);
		}

		return videos;
	}

	@Override
	protected void subValid(BaseForm form, Result result) {

		TrackForm videoForm = (TrackForm) form;

		if (StringUtils.isBlank(videoForm.getAccount())) {
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("account字段为空");
			return;
		}

		if (StringUtils.isNotBlank(videoForm.getStart())) {
			try {
				Integer.valueOf(videoForm.getStart());
			} catch (Exception e) {
				result.setStatus(Result.NECESSARY_FIELD_TYPE_ERROR);
				result.setDesc("start字段应为整数");
				return;
			}
		} else {
			result.setStatus(Result.NECESSARY_FIELD_IS_NULL);
			result.setDesc("start字段不能为空");
			return;
		}
		if (StringUtils.isNotBlank(videoForm.getCount())) {
			try {
				Integer.valueOf(videoForm.getCount());
			} catch (Exception e) {
				result.setStatus(Result.NECESSARY_FIELD_TYPE_ERROR);
				result.setDesc("count字段应为整数");
				return;
			}
		}

	}

	@Override
	protected void init() {
		this.type = "m1_get_myvideoList";
	}

}
