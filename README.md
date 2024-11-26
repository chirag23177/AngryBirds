# AngryBirds

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## Group Members
- Sanskar Garg(2023485) - [sanskargarg27](https://github.com/sanskargarg27)
- Chirag Yadav(2023177) - [chirag23177](https://github.com/chirag23177)
## Resources Link
- Learning libGDX:
    - https://libgdx.com
    - https://libgdx.com/wiki/graphics/2d/scene2d/scene2d-ui
    - https://gamedev.stackexchange.com/questions/121115/libgdx-simple-button-with-image
    - https://stackoverflow.com/questions/21488311/how-to-create-a-button-in-libgdx
    - https://www.youtube.com/watch?v=p2lUdy68s_M&list=PLLwCf-qdpyEnB_FO_1HkUFh7smwGNjAaC&index=13
    - https://www.youtube.com/watch?v=Pjg4KzckbrU&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&index=17
    - https://www.youtube.com/watch?v=knF6o8nYAMM&list=PLZm85UZQLd2SXQzsF-a0-pPF6IWDDdrXt&index=21
- Images:
    - https://angrybirds.fandom.com/wiki/Items/Gallery
    - https://www.google.com/images
- Music:
    - https://youtu.be/uQeychfPYVA
- Buttons Images
  - Downloaded one and else designed manually using Gimp

## Running Commands

1. Clone the repository:
 ```bash
https://github.com/chirag23177/AngryBirds.git
 ```
2. Build the project:
```bash
./gradlew build
```
3. Run the desktop application
```bash
./gradlew lwjgl3:run
```

## OOPS Concept Applied
- Inheritance: 
  - The GameLauncher class extends the Game class from the libGDX framework, inheriting its properties and methods.  
- Class Relationships:
  - Association: The GameLauncher class has a relationship with various screen classes (MainGameScreen, PauseScreen, WonScreen, LoseScreen, etc.) as it manages the transitions between these screens.
  - Aggregation: The GameLauncher class contains instances of SpriteBatch and FitViewport, which are essential for rendering and viewport management.
- Encapsulation
  - The GameLauncher class encapsulates the batch and viewport fields.  
- Polymorphism
  - Each screen class (MainGameScreen, PauseScreen, WonScreen, LoseScreen, etc.) implements the Screen interface and provides its own implementation of the show, render, resize, pause, resume, hide, and dispose methods.  
- Interface
  - The Screen interface from the libGDX framework is implemented by all screen classes (MainGameScreen, PauseScreen, WonScreen, LoseScreen, etc.). This ensures that each screen class provides implementations for the methods defined in the Screen interface.  
- Abstraction
  - The Screen interface abstracts the common functionalities of different screens, allowing each screen class to provide specific implementations.
