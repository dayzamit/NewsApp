<h1 align="center">NewsApp</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>

</p>

<p align="center">  
NewsApp is a an android project based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on the new jetpack libraries.<br>
Also fetching data from the network and integrating persisted data in the database via repository pattern.
</p>
<br>




## Libraries and tech used
- Minimum SDK level 23
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous handling.
- Hilt (stable) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - observing data when lifecycle state changes.
  - ViewModel - lifecycle aware UI related data holder.
  - Room Persistence - construct a database to store news article.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - for REST APIs and network data.
- [Gson](https://github.com/google/gson/) - A JSON library for parsing network response.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components for CardView, ShapeableImageView.
<br>

## API
The project uses [NewsApi](https://newsapi.org/). If you want to run the project then obtain an API key from [NewsApi](https://newsapi.org/) and paste it in ``app level -> build.gradle``, like this ``API_KEY = "YOUR_API_KEY"``
<br>

# License
```xml
Designed and developed by 2021 Amit Dixit

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
