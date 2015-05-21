# Appstax Android SDK

This is the official SDK for [Appstax](https://appstax.com).
Please read the [Android Guide](https://appstax.com/docs/Android-SDK-Guide) to get up and running.

## Installing

The easiest way to get started, is to try out the Android Studio project in `examples/basic`.

Here's how you can add the SDK to your Android Studio project:

* [Download the latest release](https://github.com/appstax/appstax-java/releases).
* Add the downloaded JAR to the `app/libs` folder.
* Add the dependency to your `app/build.gradle`:

```gradle
dependencies {
    compile 'com.android.support:appcompat-v7:22.1.1'
    compile files('libs/appstax-android.jar')
}
```

* Permit internet access in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

* Set your app key:

```java
import com.appstax.android.Appstax;

// ...

Appstax.setAppKey("YourAppKey");
```

## Example usage

```java
import com.appstax.android.Appstax;
import com.appstax.AppstaxObject;

// ...

Appstax.setAppKey("YourAppKey");

AppstaxObject object = new AppstaxObject("Contacts");
object.put("name", "Foo McBar");
object.put("email", "foo@example.com");

Appstax.save(object, new Callback<AppstaxObject>() {
    public void onSuccess(AppstaxObject object) {
        showMessage("saved", object.get("name"));
    }
    public void onError(Exception e) {
        showMessage("error", e.getMessage());
    });
});
```

See the [Android Guide](https://appstax.com/docs/Android-SDK-Guide) for more info on how to set up your app and data model.


License
-------

[MIT License](LICENSE)

