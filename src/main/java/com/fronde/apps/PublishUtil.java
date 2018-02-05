package com.fronde.apps;
import java.io.IOException;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PubsubMessage.Builder;
import com.google.pubsub.v1.TopicName;

public class PublishUtil {

	public static ApiFuture<String> publishMessage(Publisher publisher, String message)
			throws Exception {
		ByteString data = ByteString.copyFromUtf8(message);
		Builder msgBuilder = PubsubMessage.newBuilder();
		PubsubMessage pubsubMessage = msgBuilder.setData(data).build();
		System.out.println("Sending message....to pubsub publisher ");
		return publisher.publish(pubsubMessage);
	}
	
	public static TopicName createTopic(String project, String topicName) {
		return TopicName.newBuilder().setProject(project).setTopic(topicName).build();
	}
	
	public static Publisher createPublisher(TopicName topicName) throws IOException  {
		return Publisher.newBuilder(topicName).build();
	}
	
	public static void shutdown(Publisher publisher) throws Exception {
		if (publisher !=  null) {
			publisher.shutdown();
		}
	}

}
