package com.machao.base.service.imp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.machao.base.mq.QueueName;
import com.machao.base.mq.static_resource.video.request.VideoConvertRequest;
import com.machao.base.mq.static_resource.video.request.VideoDeleteRequest;
import com.machao.base.mq.static_resource.video.request.VideoPlayListRequest;
import com.machao.base.mq.static_resource.video.response.VideoDeleteResponse;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;
import com.machao.base.service.VideoService;

@Service
public class VideoServiceImp implements VideoService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Async
	@Override
	public void convert(VideoConvertRequest videoConvertRequest) {
		this.rabbitTemplate.convertAndSend(QueueName.VideoConvert, videoConvertRequest);
	}

	@Override
	public VideoDeleteResponse delete(VideoDeleteRequest videoDeleteRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.VideoDelete, videoDeleteRequest, ParameterizedTypeReference.forType(VideoDeleteResponse.class));		
	}

	@Override
	public VideoPlayListResponse palylist(VideoPlayListRequest videoPlayListRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.VideoPlayList, videoPlayListRequest, ParameterizedTypeReference.forType(VideoPlayListResponse.class));		
	}
}