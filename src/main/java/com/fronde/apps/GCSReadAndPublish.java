package com.fronde.apps;

import java.io.BufferedReader;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GCSReadAndPublish {
	  
	  private static Storage storage = StorageOptions.getDefaultInstance().getService();
	
	public static void main(String [] args) {
		//[project-name] [bucket-name] [file-name] [topic]
		if(args.length < 4) {
			throw new IllegalArgumentException("Ensure the parameters are provided in the format [project-name] [bucket-name] [file-name] [topic]");
		} else {
			try {
			String project= args[0];
			String bucketName= args[1];
			String fileName = args[2];
			String topicName = args[3];
			Publisher publisher = PublishUtil.createPublisher(PublishUtil.createTopic(project, topicName));
			BufferedReader reader = new BufferedReader(Channels.newReader(storage.reader(bucketName, fileName), "UTF-8"));
			List<ApiFuture<String>> messageIdFutures = new ArrayList<>();
			String read=null;
			while ((read =reader.readLine()) != null) {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("read::" + read);
				PublishUtil.publishMessage(publisher, read);
			}
			
			} catch(Exception e) {
				System.err.println("Error encountered:" + e.getMessage());
				e.printStackTrace(System.err);
			}
		}
		
	}

}
