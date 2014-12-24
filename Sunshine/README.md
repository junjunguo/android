Developing Android Apps: Android Fundamentals
--
source: [Developing Android Apps - Udacity](https://www.udacity.com/course/ud853)
- Course provide by [Udacity](https://www.udacity.com/)
- Course build by Google.

SQLite
--
[Command Line Shell For SQLite](http://www.sqlite.org/cli.html)

[Datatypes in SQLite Version 3](http://www.sqlite.org/datatype3.html)

Git
--
[Git](git.md)

Android 
--
Android is a full software stack

Application Layer 
- (my app)
- Application Framework
    - C/C++ Libs
    - Android Runtime
        - Linux Kernel
            - (handles low level tasks: hardware fibers, panel management)

Android project
- --> Build(Gradle)
    - (Gradle)
    - Byte code
    - resources
        - : images, strings, uixml in to an application package
    - Manifest
    -
    - APK
        - apk: a specially formatted zip file
- --> (Jar signer) Sign
- --> (ADB) Install on Device

Layout:
- frame layout
- linear layout
- relative layout
- always use the simplest layout which can get the job done!

Frame Layout:
- simple layouts
- with a single view or
- stack or
- views
- views are all aligned against the frame boundaries only

Linear Layout:
- perfect for stacking views vertically or horizontally, one after another.

Relative Layout:
- powerful but more complicated
- sophisticated layout that allows the positioning of views relative to other views relative to other views or the boundaries of the view.

