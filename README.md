
[![Open in Cloud Shell](http://gstatic.com/cloudssh/images/open-btn.svg)](https://console.cloud.google.com/cloudshell/open?git_repo=https%3A%2F%2Fgithub.com%2Fgt2985%2Fsimulate-events&page=editor)

Simple Project showing the capability of reading a GCS file.
It also includes a simple GCP Pub/Sub publisher

This progam includes a main program that does the following :
1. Connect to GCS
2. Read the file specified in args , bucketname and filename to be provided
3. Read every line of the file
4. Submit the lineRead to PubSub , specify the pubsub topic in args. 

This project can be executed as follows : 

1. Clone the repo.
2. Build the project
  mvn clean install
3. Execute the main class
  mvn exec:java -Dexec.arge="<projectname> <bucketname> <filename> <topicname>"
 
 
You would be able to see sysouts diplaying the read data on the console.
