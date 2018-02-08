1. .gitignore is a list of files that we don't want Git to automatically 
    add because the files are automatically generated

2. Gradle automates a bunch of tasks that we would have to set up with the
command line otherwise.

3. Travis-CI automatically runs tests on the project every time we push changes
   to github.

4. A route is a url that causes the server to give a response.

5. The Server class sets up the database and includes the links on the webpage.
It also includes the methods for getting users. The UserController class manages requests for info about users.

    users - users.html is displayed.
  
    api/users - a list of all users is displayed.
    
    api/users?age=25 - a list of all users at age 25 is displayed.
    
    api/users/588935f5de613130e931ffd5 - the user info for Valerie Erickson is displayed.

6. The public folder contains the HTML and CSS files to display the webpage. about.html contains only text; 
index.html is the home page, and has a button to create an alert on the page; users.html contains javascript to
get the usernames and ages from the server. It has a button to get all users, and a button to get all users of a
certain age.

7. When you filter the users by age, the client sends the age you typed in to the server, and the server figures
out which users have that age, and sends the list of users back. Then the client receives the list of users and
 displays it on the webpage.

8. Under the /src/main/resources/public/javascript/ and the file name is users.js
