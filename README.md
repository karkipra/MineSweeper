# MineSweeper

Android game with multiple difficulty levels and graphics utilizing the observer design pattern.

## Motivation

I wanted to implement low level design Android application that would encapsulate the graphics and touch interface of the Android Medium. **Minesweeper** is a good starting point as it is a well known game with a predefined logic system. It was a fun project to implement and also helped me learn the core aspects of Android Development.

## Screenshots

###### Build 1.0

<img src="/Screenshots/b1-1.png?raw=true"  width="400" height="700">
<img src="/Screenshots/b1-2.png?raw=true"  width="400" height="700">
<img src="/Screenshots/b1-3.png?raw=true"  width="400" height="700">
<br />

###### Build 2.0

<img src="/Screenshots/b2-1.png?raw=true"  width="400" height="700">
<img src="/Screenshots/b2-2.png?raw=true"  width="400" height="700">
<img src="/Screenshots/b2-3.png?raw=true"  width="400" height="700">
<br />

## Features

- *Build 1.0* includes these features:
    - Launches the game activity.
    - Simple Minesweeper game on a 5x5 board.
    - Board is painted with grey and numbers are text inputs.
    - Bombs are the only bitmap images used. 
    - Can place flags; placing the flag where a bomb isn't immediately terminates the game.
    - Toast message that shows the number of flags remaining.
- *Build 2.0* includes these features:
    - Launches main activity where the user can select difficulty.
    - Timer shows the amount of time elapsed in the game.
    - Emoji in the top center mimics the original game and shows the user status.
    - Flags are actively shown in the bottom right.
    - Flags is now a switch for convenience.

A major design paradigm that I utilized in this project is the observer design pattern. More information on this can be found in [Wikipedia](https://en.wikipedia.org/wiki/Observer_pattern). This is important in relaying information between the different events. A simple diagram explaining its usage can be found below:  

<img src="/Screenshots/obs_des.png?raw=true"  width="700" height="400">
<br />

## Current Build.

Latest build is Minesweeper 2.0.

## Directions for installation

Best way is to use Android Studio. You can directly clone it with git ``` git clone https://github.com/karkipra/MineSweeper.git ``` or open it in Android Studio directly with 'import project from git'. 

## Future Updates

- Saving high scores.
- Undo a move (can use a simple stack implementation for this).
- Splash screen with a logo. 

## License 

This project is licensed under [Pratik Karki](https://github.com/karkipra).
