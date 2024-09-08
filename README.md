Hello, Friends!   

# Tech stack of the base project:   
    `Clean Architecture, Single Activity, MVVM, Hilt/Dagger, Coroutines, 
     Jetpack Compose, Room, Ktor, JUnit, Mockk`

# The structure of the project:

* `app`               - main module
* `data`              - Remote and local data sources 
* `domain`            - Business logic and fake data 

# What the app allows you to do? 

* The project supports `Light` and `Dark` mode, `rotation of a screen`
* Start the static code analysis using Detekt - `./gradlew detekt`  
* Tests - `Data module: GithubUsersDatabaseTest, GithubDatasourceTest; 
           Domain module: GithubUserUseCaseImplTest`
* Analyzing dependencies for vulnerabilities - `./gradlew dependencyCheckAnalyze`

# Future plans

* Pagination
* Search 

# If you have any questions

    https://www.linkedin.com/in/igor-chebotarev 
  