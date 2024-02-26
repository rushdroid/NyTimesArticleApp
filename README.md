# Nytimes Article App README

Welcome to our Nytimes Article App project! This README will guide you through the folder structure, provide information about constant files, and explain how to use GitHub Actions for building and testing.

## Folder Structure

## Constant Files

Constant files are located in util package:

You can replase

```kotlin
object Constants {
    const val API_BASE_URL = "https://api.nytimes.com/svc/"
    const val API_KEY = "REPLACE_YOUR_API_KEY"
}
```

### To create an API key for the New York Times (NYT) API, follow these steps:

1. Sign Up for an Account:
If you don't already have one, sign up for an account on the New York Times Developer Network website.

2. Create an Application:
After signing in, navigate to the My Apps section of the developer portal.

3. Register a New Application:
Click on the "Create App" button or a similar option to register a new application.

4. Fill Out Application Details:
Provide the required details for your application, such as the name, description, and website URL. Make sure to provide accurate and descriptive information about your application.

5. Agree to Terms of Use:
Review and agree to the New York Times API Terms of Use.

6. Submit the Form:
Once you've filled out all the necessary information, submit the form to create your application.

7. Retrieve API Key:
After your application is created, you should receive an API key or application ID. This key is typically displayed on the application details page or sent to you via email.

8. Use the API Key:
You can now use the API key in your application to authenticate requests to the New York Times API. Make sure to include the API key as a query parameter or in the request headers when making API calls.

That's it! You've successfully created an OpenWeatherMap API key. You can now use this key in your application to access weather data from the OpenWeatherMap API.

## This project follows best practices and utilizes the following libraries:

- Hilt: For dependency injection
- Retrofit: For making network requests
- Coroutine: For asynchronous programming
- Flow: For asynchronous data streams
- Jetpack compose: For User Interface
- JUnit: Unit test cases
- Github Actions: For build and test unit test cases
- Other best practices for Android development


## GitHub Actions

We use GitHub Actions for generating builds and running unit tests. The workflow is defined in .github/workflows/main.yml:

```
name: Android CI
on: [push]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4.1.0
      - name: Set up JDK 11
        uses: actions/setup-java@v3.13.0
        with:
          distribution: 'adopt'
          java-version: '17'

      - name: Grant execute permissions for gradlew
        run: chmod +x ./gradlew

      - name: Run Tests with Gradle
        run: ./gradlew test
      - name: Unit Test
        run: ./gradlew testDebugUnitTest
      - name: Build with Gradle
        run: ./gradlew assembleDebug
```

This workflow will trigger on every push to the main branch, checking out the code, setting up Java 11, building the project with Gradle, and running unit tests.

Make sure to replace "REPLACE YOUR API KEY" with your actual NYTIMES API key.

Feel free to reach out if you have any questions or need further assistance!
