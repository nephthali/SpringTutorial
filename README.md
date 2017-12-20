# SpringTutorial – Learn Spring Framework step by step
This tutorial will guide you on how to prepare a development environment to start your work with Spring Framework.
This tutorial will also teach you how to setup JDK, Tomcat and Eclipse on your machine before you setup Spring Framework:

## Step 1 – Setup Java Development Kit (JDK)
[Download](http://www.oracle.com/technetwork/java/javase/downloads/index.html) latest java (JDK ) version from oracle’s java site.
Set to the class path for this JDK version:
1. For Linux OS
	1.1. Unzip the folder into directory like /opt/java/<version of jdk>
	1.2. Add into your /etc/profile file (Your need to be a sudoer)
	```
	export JAVA_HOME="path that you found"
	export PATH=$JAVA_HOME/bin:$PATH
	```
	1.3. Source your /etc/profile
	```
	source /etc/profile
	```
2. On Unix (Solaris etc.), if the SDK is installed in /usr/local/jdk1.6.0_23 and you use the C shell, you would put the following into your .cshrc file.
	```
	setenv PATH /usr/local/jdk1.6.0_23/bin:$PATH
	setenv JAVA_HOME /usr/local/jdk1.6.0_23
	```

3. For Windows OS:
	select Properties, then Advanced, then Environment Variables.Then, you would update the PATH value and press the OK button
	<br />
	![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/06/homepath.jpg?resize=530%2C239&ssl=1)
	<br />

## Step 2 – Install Apache Common Logging API:
You can download the latest version of Apache Commons Logging API from [http://commons.apache.org/logging/](http://commons.apache.org/logging/).
![image](https://i1.wp.com/www.dineshonjava.com/wp-content/uploads/2012/06/commonlogging.jpg?resize=530%2C267&ssl=1)

## Setup SpringSource Tool Suite (STS) IDE
To install SpringSource Tool Suite (STS)IDE, [download](http://www.springsource.org/springsource-tool-suite-download) the latest SpringSource Tool binaries from http://www.springsource.org/springsource-tool-suite-download .
Once you downloaded the installation, unpack the binary distribution into a convenient location.
After a successful startup, if everything is fine then it should display following result: <br />
![image](https://i2.wp.com/www.dineshonjava.com/wp-content/uploads/2012/06/sts.jpg?resize=530%2C298&ssl=1)

## Step 4 – Setup Spring Framework Libraries
Your can deside to locate spring under the directory of your system or let maven tool download it for you into your project
classpath. <br />
This is a [link](http://www.springsource.org/download) to download spring framework