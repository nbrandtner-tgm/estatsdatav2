# e-Stats Data
---
#### made by niklas brandtner
This is the repository for my part of the estats austria project. This will contain a Generator, a Java MySQL Connection, SQL Files to create the database as well as all other required library JARs or anything else needed to set-up and run this application.
My part of this project mainly consists of 2 sections, the datagenerator and the database. 

## To set up the project:
### Prerequisites
You need to install docker and have an IDE for developing Java (Intellij works best)
Then create a docker network using 
```bash
docker network create networkName
```
Next you need to create two docker container, the first one is for mysql, the second one for phpmyadmin <br>
(phpmyadmin isn't needed but can give a good GUI to work with, and it's easy to set up, if you don't want phpmyadmin you also don't need the network) <br>
I recommend to make the password in the following command "root", otherwise you're gonna have to change the password in the java code in line 24 of the `MySQLnew.java`
```bash
docker run -d --name mysqlContainer --network networkName -e MYSQL_ROOT_PASSWORD="yourPassword" -v C:/path/where/you/want/the/container:/var/lib/mysql -p 3306:3306 mysql
docker run -d --name phpmyadminContainer --network networkName -e PMA_HOST=mysqlContainer -p 8080:80 phpmyadmin
```
You can access the phpmyadmin GUI at localhost:8080 now and login with your password and the username 'root' <br>
Next you need to create a new database, best name it "testing" and run the `generator.sql` file located in the sql folder to create the table where the data from our generator is gonna be saved in. (When naming it something else you might need to change line 24 in `MySQLnew.java`<br> 
Now you can clone the git repository in the IDE of your choice.<br>
IntelliJ: https://www.jetbrains.com/help/idea/set-up-a-git-repository.html <br>
Eclipse: https://www.geeksforgeeks.org/how-to-clone-a-project-from-github-using-eclipse/ <br>
Once you have cloned the repository you can find the `Generatornew.java` and `MySQLnew.java` in the src folder. <br>
Upon opening the MySQLnew.java, you can execute it and it will connect to your MySQL database and continuosly generate data and save it in said database.

To create Docker Image for our generator just go into the diplomprojekt root folder and use:
```bash
docker build -t datagenerator .
```
