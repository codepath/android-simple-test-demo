# android-simple-test-demo

Simple Android App used for Demonstrating Testing. 
See our [Android Testing Guide](https://github.com/thecodepath/android_guides/wiki/Android-Unit-and-Integration-Testing).

<img src="http://i.imgur.com/BhD9S8n.png" width="350" alt="Screen 1" />
&nbsp;
<img src="http://i.imgur.com/YOssiuC.png" width="350" alt="Screen 2" />

This app is just two activities. The first has a text field and a button. When you type in the text field and hit the button, a second activity appears that displays the text entered.

## Setup

### Robolectric

[Robolectric](https://github.com/thecodepath/android_guides/wiki/Robolectric-Installation-for-Unit-Testing) - Install robolectric, setup new project as explained and then link in the "roboelectric-tests" folder from this repo as a source folder. 

![Robolectric](http://i.imgur.com/Csa8mGK.png)

To run robolectric unit tests, right-click project and select `Run As => JUnit Test`

### Robotium

[Robotium](https://github.com/thecodepath/android_guides/wiki/Android-Unit-and-Integration-testing#robotium) - Select `File => Import => Existing Projects Into Workspace` and then select the "robotium-tests" folder. This loads the folder into eclipse. 

![Robotium](http://i.imgur.com/v0aCcqK.png)

To run robotium tests, right-click project and select `Run As => Android JUnit Test`
