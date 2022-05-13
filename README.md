<h1 align="center">Welcome to Unpassable</h1>
<p align="center">
 
## Game Description

`Unpassable` is a game based off of Jeopardy. To play the game, one must select 'start', then a choose a subject to be quizzed on. As you answer the questions by clicking on the letter that corresponds to what you think is the right answer, your score is being kept. If your score is high enough, you will pass. Otherwise, you will fail.

 ![doodle](start.gif)
 ![doodle](subjects.gif)

 
## Usage

  Mouse: use to click on answers

# Classes
### ***Chemistry***
This class presents the chemistry questions and answer choices if the subject "Chemistry" is selected. When an answer is chosen, it checks the answer with the answer key, using an index to confirm the answer.

 <img src="https://github.com/bubbles232/unpassable/blob/main/psych.png" width="300" height="300">
 
### ***Psychology***
This class presents the psychology questions and answer choices if the subject "Psychology" is selected. When an answer is chosen, it checks the answer with the answer key, using an index to confirm the answer.

 <img src="https://github.com/bubbles232/unpassable/blob/main/psych.png" width="300" height="300">

### ***Japanese***
This class presents the japanese questions and answer choices if the subject "Japanese" is selected. When an answer is chosen, it checks the answer with the answer key, using an index to confirm the answer.

 <img src="https://github.com/bubbles232/unpassable/blob/main/psych.png" width="300" height="300">
 
### ***Music History***
This class presents the music history questions and answer choices if the subject "Music History" is selected. When an answer is chosen, it checks the answer with the answer key, using an index to confirm the answer.
 
 <img src="https://github.com/bubbles232/unpassable/blob/main/music%20history.png" width="300" height="300">
 
# Methods
### ***Paint***
This method draws the background, platform, and characters onto the screen. It also updates the position of the character and platforms throughout the game. An image of the moving platform is included because it requires position updates. Information on the updates will be continued in collisions.
 
![Paint 2](https://user-images.githubusercontent.com/29692869/148715685-d96bae98-7dd7-47b8-b5a8-c39c591d1ef1.jpg)
![Paint 2 (1)](https://user-images.githubusercontent.com/29692869/148718682-7d003562-ebda-4eb3-81bf-8c439e620b46.jpg)

### ***Collisions***
Collisions occur when the character land on a platform. There are two collisions as one collisions is for colliding with a normal platform and there is one method for the collision of the red block.
 
![Collision](https://user-images.githubusercontent.com/29692869/148716781-65e5a9fd-bdf8-4086-b66e-bc4523a0e3c5.jpg)
![Collision2](https://user-images.githubusercontent.com/29692869/148716786-296c12d5-efdd-4f99-91fc-f3798831fea4.jpg)

### ***Game Over***
When the character is unable to land on a platform or lands on a red platform, the character position and score are reset. This is done by setting the character to its inital position, turning gravity off and setting velocity to 0.
 
![Reset](https://user-images.githubusercontent.com/29692869/148715739-02aa51d9-0718-4431-85af-0f8a2d357c3c.jpg)

### ***Creating Objects***
Objects are created individually or in arrays using loops.
 
![Create objects](https://user-images.githubusercontent.com/29692869/148715735-4f49494b-6cae-46a0-880c-982eedcc20b6.jpg)

# Support
Contact Vianne, Swasti, or Maya for support
 
# Roadmap
 Though an update is not likely, if it were to happen, *suggestions to what we could do*

 
# Contributing
 Anyone may contribute.
 
# Authors and Acknowledgements
The game is inspired by Jeopardy. All images and code were made by us. Thank you Mr. David for helping us with XXXXXXXX.
