# OnBoarding

## Screenshots

<img src="https://github.com/vermasourav/OnBoarding/blob/main/Screenshots/screen_1.png" width="250px" />

<img src="https://github.com/vermasourav/OnBoarding/blob/main/Screenshots/screen_2.png" width="250px" />


## Installation

Add Jitpack to your project build.gralde file

```
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```

Then add this dependency to your app build.gradle file.

```
dependencies {
   implementation 'https://github.com/souravverma/OnBoarding:latest-release'
   Or
    implementation 'com.github.vermasourav:OnBoarding:Tag'
}

Stable Version

implementation com.github.vermasourav:OnBoarding:1.0.7

```

Then activity to your app AndroidManifest.xml file.

```
      <activity
            android:name="com.verma.android.onboarding.OnBoardingActivity"
            android:exported="true">
        </activity>
```



Add Json file (boarding.config.json) at res/raw folder
```
    {
        "header": "On Boarding",
        "next": "Next",
        "previous": "Back",
        "skip": "Skip",
        "onboards": [
        {
            "heading": "Heading 1",
            "description": "desc 1",
            "image": "boarding_image_1"
        },
        {
            "heading": "Heading 2",
            "description": "desc 2",
            "image": "https://upload.wikimedia.org/wikipedia/commons/6/66/Sachin-Tendulkar.jpg"
        },
        {
            "heading": "Heading 3",
            "description": "desc 3",
            "image": "boarding_image_3"
    }
  ]
}
```

Launch
```
public void launchOnBoarding(){
      Intent intent = new Intent(this, OnBoardingActivity.class);
     onBoardingLauncher.launch(intent);
}


private ActivityResultLauncher<Intent> onBoardingLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    //Do Task After OnBoarding Done
                 
                }
            });
            
```


## License

Copyright (c) 2020 Sourav Kumar Verma

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
