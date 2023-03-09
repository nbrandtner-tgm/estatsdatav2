# e-Stats Data
---
#### made by niklas brandtner
This is the repository for my part of the estats austria project. This will contain a Generator, a Java MySQL Connection, SQL Files to create the database as well as all other required library JARs or anything else needed to set-up and run this application.
My part of this project mainly consists of 2 sections, the datagenerator and the database. 


## Prerequisites
You need to install docker and have an IDE for developing Java (Intellij works best)
Basic knowledge of docker and how to use it is required
Install Maven and add to Environment Variables

## Set up the database
Es ist empfohlen die docker-compose Version zu verwenden statt der Dockerfile Version. <br>
### Docker-compose Version:
For the docker-compose version it is important to first install docker-compose on your system and add it to the environment variablesJetzt einfach das Git Repository klonen und in dem Ordner eine Konsole öffnen. <br>
Now you can clone the git repository in the IDE of your choice.<br>
IntelliJ: https://www.jetbrains.com/help/idea/set-up-a-git-repository.html <br>
Eclipse: https://www.geeksforgeeks.org/how-to-clone-a-project-from-github-using-eclipse/ <br>
Or just run `git clone https://github.com/nbrandtner-tgm/estatsdatav2.git` in the terminal. <br>

Next open a terminal in the root folder of the project and run the following commands:
```bash
docker-compose down -v
docker-compose up -d
```
This will create a docker container cluster with 3 containers, one for mysql, one for phpmyadmin and one for the datagenerator. <br>
When running it the first time, please give the mysql container about 2-3 minutes to start up. <br>
You can access the database on localhost:3306 and the phpmyadmin GUI on localhost:8080. <br>
### Dockerfile Version:
Create a docker network using 
```bash
docker network create networkName
```
Next you need to create two docker container, the first one is for mysql, the second one for phpmyadmin <br>
(phpmyadmin isn't needed but can give a good GUI to work with, and it's easy to set up) <br>
Use the ROOT_PASSWORD="root", otherwise you're gonna have to change the password in the java code in line 35 of the `MySQLnew.java`
```bash
docker run -d --name proj_2223_eStatsAustria_mysql --network networkName -e MYSQL_ROOT_PASSWORD="yourPassword" -v C:/path/where/you/want/the/container:/var/lib/mysql -p 3306:3306 mysql
docker run -d --name proj_2223_eStatsAustria_phpmyadmin --network networkName -e PMA_HOST=mysqlContainer -p 8080:80 phpmyadmin
```
Change the networkName to whatever you want and the path to where you want the container to be stored. <br>

You can access the phpmyadmin GUI at localhost:8080 now and login with your password and username 'root' <br>

Next you need to create a new database, best name it "estatsdb" and run the `generator.sql` file located in the sql 
folder to create the table where the data from our generator is gonna be saved in. 
(When naming it something else you might need to change line 35 in `MySQLnew.java`)<br> 

Now you can clone the git repository in the IDE of your choice.<br>
IntelliJ: https://www.jetbrains.com/help/idea/set-up-a-git-repository.html <br>
Eclipse: https://www.geeksforgeeks.org/how-to-clone-a-project-from-github-using-eclipse/ <br>

Once you cloned the repository, open the project in your IDE and open the terminal either inside the ide or in the root folder of the project. <br>
In the terminal run the following command to build the project:
```bash
mvn clean
mvn compile package install
docker pull nbrandtner/datagenerator:latest
docker run -it -d --name containerName --network networkName nbrandtner/datagenerator:latest
```
Change the networkName to the one you chose before and the containerName to whatever you want. <br>
Now you should have three docker containers running, one for mysql, one for phpmyadmin and one for the datagenerator. <br>
You can access the phpmyadmin at localhost:8080. <br>
There should be a database called "estatsdb" <br>
Now you can execute your preferred sql file in the sql folder to get the structure of the table. <br>
As long as the datagenerator container is running, it will generate data every minute and save it in the database. <br>

