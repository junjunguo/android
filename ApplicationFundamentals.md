Application Fundamentals
==
Android SDK compile code
- into APK: android package

When app installed on a device, each Android app lives in its own security sandbox:
- Android operating system is a multi-user Linux system in which each app is a different user.
- each app will be assigned with a unique Linux user ID
    - ID is used only by the system
    - is unknown to the app
    - The system sets permissions for all the files in an app so that only the user ID assigned to that app can access them.
- each process has its own virtual machine(VM), so an app's code runs in isolation from other apps.
- apps run in its own Linux process.
    - Android starts the process when any of the app's components need to be excuted, then shuts down the process when it's no longer needed or when the system must recover memory for other apps.

Android system implements the principle of least privilege. 
- each app, by default, has access only to the components that it requires to do its work and no more. 
- an app cannot access parts of the system for which it is not given permission.

There are ways for an app to share data with other app & for app to access system services:
- possible for apps to share Linux user ID
    - so they can run in the same Linux process and share the same VM.
- An app can request permission to access device data such as contacts, SMS message, ...
- All app permissions must be granted by the user at install time.


- the core framework components define the app
- the manifest file declare components and required device features for your app.
- Resources allow the app to gracefully optimize its behavior for a variety of device configurations.

App Components
--
####Activities
an activity represents a single screen with a user interface.
- the Activity class creating a window, UI can be placed with 
    - setContentView(View)
- two most implemented subclasses:
    - onCreate(Bundle)
        - initialize the activity
        - call
            - setContentView(int)
            - with a layout resource defining UI
        - use
            - findViewById(int)
            - to retrieve the widgets in that UI 
    - onPause()
        - where user leaving the activity
        - any changes made should be committed 
        - usually to ContentProvider holding the data

![activities life cycle](files/activity_lifecycle.png)
