package com.law.video.track.list.service;

import com.law.commons.Result;
import com.law.commons.interf.IValidService;
import com.law.video.track.list.bean.TrackForm;

public interface ITrackService extends IValidService {

	public Result getTrackList(TrackForm form);

}
