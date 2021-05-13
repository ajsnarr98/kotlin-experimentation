# HBO + other work I have done - related to Engineering Rubric (Platform)

## Craft

### Design

1. When handling the new error type for showing the End Card, iOS was having trouble finding a clean solution for parsing the new error. I coordinated with Matt B and Matt K on iOS, and Matt B and I walked through the android and iOS code together to see if the solution I had used could have any parallel in the iOS codebase.
1. Used knowledge of android lifecycle to strategize about the best way to report certain metrics
    * App backgrounding/foregrounding was a metric that HBO wanted to report
    * But resources also needed to get cleaned up at the end of a session
    * Before this we were cleaning up resources and then re-initializing a session on app foregrounding/backgrounding
    * I had to make it clear that we could not gaurentee session cleanup would always happen if we did not clean up on backgrounding and instead cleaned up in onDestroy
    * In the end, HBO seemed fine with not gaurenteeing a conviva session would "end" in analytics in exchange for reporting app foregrounding/backgrounding during video playback
1. (Side project): learned many of the benefits of using dependency injection not only for testing but for more loosely coupled code
1. Have seen how dagger is used for dependency injection (DI) on HBO project, and have used DI without a large library like dagger on my own on a side project
1. (Side project) Refactored to start using model-viewmodel architecture to help data be more independent of views. Also have some familiarity with redux-like architecture from working on HBO.

### Build

1. Have given strong code reviews when I could, for example on test code PRs
    * I am the only android developer at the moment on HBO, which is why I look at test PRs more than other types
1. Helped a team member work through making a custom View Matcher for thier first UI test in kotlin
1. Helped a team member work through a unit test involving datetimes and timezones in java
1. Has strong experience using JUnit testing from school and also some from my side project
1. (side project) Refactored repository pattern and database-related code to be more testable
    1. I initally worked on this project making no tests.
    1. While attemping to make fakes for use in writing unit tests for major database-related code, I realized that my existing architecture was not very testable
    1. I ended up using a dependency injection pattern to improve testing
    1. Making the code more testable ended up also making the codebase feel more clean, and easier to refactor
    1. Unit tests made refactoring the database-related code later on much easier
1. When working on fixing a few bugs in our video player analytics, I recommended why it would be feasable to update our analytics library, conviva, from version 2.x to version 4.x.

### Maintain and Test

1. Used knowledge of android lifecycle to strategize about the best way to report certain metrics
    * App backgrounding/foregrounding was a metric that HBO wanted to report
    * But resources also needed to get cleaned up at the end of a session
    * Before this we were cleaning up resources and then re-initializing a session on app foregrounding/backgrounding
    * I had to make it clear that we could not gaurentee session cleanup would always happen if we did not clean up on backgrounding and instead cleaned up in onDestroy
    * In the end, HBO seemed fine with not gaurenteeing a conviva session would "end" in analytics in exchange for reporting app foregrounding/backgrounding during video playback
1. (side project) Refactored repository pattern and database-related code to be more testable
    1. I initally worked on this project making no tests.
    1. While attemping to make fakes for use in writing unit tests for major database-related code, I realized that my existing architecture was not very testable
    1. I ended up using a dependency injection pattern to improve testing
    1. Making the code more testable ended up also making the codebase feel more clean, and easier to refactor
    1. Unit tests made refactoring the database-related later on much easier
1. Have had to update networking-based tests on HBO when modifying error handling to show the End Card screen (for migration to HBO Max)
1. (side project) Added unit tests for markdown-editing code (markdown tests were made alongside markdown-related code)
    1. The markdown-related code used for editing notes had to be able to work correctly under many different scenarios
    1. Making these unit tests made adding to and modifying the complicated logic in the markdown-related code much easier
1. Comments code and seeks out feedback to get a second opionion on design decisions that might make less sense to someone else
1. Coordinated w/ iOS team what would be good solutions for showing the end card at app launch for an unauthenticated user instead of making them go through sign in before getting the screen
    * (End card screen is shown after a special error response from the api, and authentication is the first time the api is called for a non-signed in user)
    * Could not make new api endpoint (client said no)
    * (later) I suggested that we try making a call to some simple existing endpoint, and pointed out languages as a good option
    * Matt K on iOS and I talked over it, and eventually made a plan for calling the languages endpoint on startup
1. The end card error (as it is now) will suddenly start showing up at some point, and so the end card could theoreticaly show up on any screen of the app. But the overwhelming majority of users in practice will not have the app open when the error start appearing, and so will see the end card only from the network calls on app launch. Testing everything on app launch is a higher priority in this case.

### Deliver

1. (side project) Have used github actions to implement CI during pull requests
    * Unlike the build type I usually use in developement for debugging, the build type used by CI uses code obfuscation, which might help catch any odd code-obfuscation bugs I forgot to build for
    * CI in PRs in this side project will only ever run unit tests, since they test more critical code in the app and also run faster than UI tests
1. Added Snyk security to the HBO CI build steps

### Measure

1. On HBO, I have used raygun to keep track of crashes in production, and have used crash reports to try to search for the cause.
1. Am familiar with the Android Profiler tool in Android Studio (memory, cpu, network, energy) and its usefullness in beginning to find the source of problems like a memory leak.
1. Am familiar with helpful tools for diagnosing UI bugs, like the layout inspecter in Android Studio and the ability to show layout bounds via the developer options

## Sustainable Flow

1. Used knowledge of android lifecycle to strategize about the best way to report certain metrics
    * App backgrounding/foregrounding was a metric that HBO wanted to report
    * But resources also needed to get cleaned up at the end of a session
    * Before this we were cleaning up resources and then re-initializing a session on app foregrounding/backgrounding
    * I had to make it clear that we could not gaurentee session cleanup would always happen if we did not clean up on backgrounding and instead cleaned up in onDestroy
    * In the end, HBO seemed fine with not gaurenteeing a conviva session would "end" in analytics in exchange for reporting app foregrounding/backgrounding during video playback
1. Implemented system for tracking average frame rates during video playback
    * According to their docs at the time, conviva had a system for tracking average framerate over time
    * It turned out that despite what the docs said, the actual sdk did not do this automatically
    * I wrote a class to roughly calculate a weighted average based on changes in the stream encoding, so that HBO would at least get some idea of this metric
1. While implemnenting End Card, figured out a relatively clean workaround when it turned out that the Android built-in bullet point span could not do what we needed it to (custom bullet span implementation)
1. Asked questions wherever I could to help clear up functionality related to the end card screen
    * Would the user be shown the end card wherever they are in the app? For example after a bookmarking call in the middle of playback
1. Worked with iOS team to figure out the best way of going about including bolded words for the End Card screen in different translations
1. Coordinated w/ iOS team what would be good solutions for showing the end card at app launch for an unauthenticated user instead of making them go through sign in before getting the screen
    * (End card screen is shown after a special error response from the api, and authentication is the first time the api is called for a non-signed in user)
    * Could not make new api endpoint (client said no)
    * (later) I suggested that we try making a call to some simple existing endpoint, and pointed out languages as a good option
    * Matt K on iOS and I talked over it, and eventually made a plan for calling the languages endpoint on startup

## Ownership

### Accountability

1. Updated conviva (video player analytics from version 2 to version 4)
    1. Tracked down a particularly difficult bug caused by code obfuscation preventing conviva from working correctly.
        * Most analytics for one person testing would act like a video failed to start, when it actually had
        * Code obfuscation would only happen on CI, and some people would mostly use CI builds, some people would mostly build locally (myself included), making the error appear intermittent to QA
        * The conviva docs did not mention anything about requiring an exception for certain classes in code obfuscation
        * I found this bug by stepping through the decompiled source code for the conviva sdk, and eventually noticing how some of its functionality depended on class names in different modules
        * Lessons learned: It is a good idea to make sure "release" builds locally and on CI both use code obfuscation, among other things. A re-inforced idea, documentation is often incomplete, and can even miss common, very important details.
1. Figured out how to handle a new type of error response from the API on my own using the existing android app architecture
1. Figured out a relatively clean workaround when it turned out that the Android built-in bullet point span could not do what we needed it to (custom bullet span implementation)

### Profesional Development

1. Helped new team members where I could
    1. Reached out and helped a team member when they mentioned they were having trouble getting the android project running
    1. Helped a team member work through making a custom View Matcher for thier first UI test in kotlin
1. Attends android office hours frequently
1. Active in the #kotlin and #hardware slack channals and have posted neat/ugly things I have run into in the past
1. In my spare allocation, have developed and tried to keep improving on an android side project (note taking app) to help myself learn more

### Internal Processes

1. When working on fixing a few bugs in our video player analytics, I recommended why it would be feasable to update our analytics library, conviva, from version 2.x to version 4.x.
    * This update also could (but hopefully should not) help us in the future if the app lives past september without the HBO Max migration happening. Past that point conviva 2.x would not be supported

## Optimism

1. Assumes positive intent from team members
1. Tries to approach problems slowly as something to fix, solve, or improve instead of just an obstacle

## Open Communication

1. Helped new team members where I could
    1. Reached out and helped a team member when they mentioned they were having trouble getting the android project running
    1. Helped a team member work through making a custom View Matcher for thier first UI test in kotlin
    1. Reached out to iOS team members whenever we were working on similar problems and one of us was having trouble
        * When handling the new error type for showing the End Card, iOS was having trouble finding a clean solution for parsing the new error. I coordinated with Matt B and Matt K on iOS, and Matt B and I walked through the android and iOS code together to see if the solution I had used could have any parallel in the iOS codebase.
1. Explains problems I am working on in 1-1s
1. Has had to formulate questions correctly to non technical people working for the client (HBO)
