# Learning Objectives

* Basic design patterns and their purpose
    * Composite pattern
    * Decorator pattern
    * Visitor pattern

# Setting up the Environment

Check out the project using IntelliJ IDEA. This creates the `local.properties` file
with the required line

    sdk.dir=<root folder of Android Studio's Android SDK installation>

# Running the Application

In IntelliJ: `Run > Run app`

# Running the Tests

## Unit tests (there are no Android tests for shapes)

In IntelliJ's Android view:

* right-click on `app/java/edu...shapes (test)`, then choose `Run Tests in edu...`

You can also use Gradle in a Terminal window:

    $ ./gradlew testDebug # on all platforms

You can view the resulting test reports in HTML by opening this file in your browser:

    app/build/reports/tests/debug/index.html

(So far, this example does not include any Android instrumentation tests.)
