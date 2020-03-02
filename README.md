# Developer assignment
Welcome to the GamesLabs developer assignment. The goal of this assignment is to test your ability to use :
- git
- maven
- component and event based programming
- java programming
- code quality
- follow vague descriptions

## Requirements
Before starting this assignment, you will need to have :
- java 8+
- maven 3+
- git

# The assignment
You will need to do different tasks. You may need to change the original code in order to complete these tasks.


# Component based project
A component is a piece of code handling one feature, and only one feature.
It can interact with other components through events.
A component must extends the `net.gameslabs.api.Component` class.

## Component cycle
When a component is loaded, the `onLoad` is called. You can register events in this method by doing so:
```java
@Override
public void onLoad() {
    registerEvent(GiveXpEvent.class, this::onGiveXPToPlayer);
}

private void onGiveXPToPlayer(GiveXpEvent event) {
    // Do something
}
```
The `onUnload` method is called when the component unloads.

## Events
You may send an event by using the `Component.send` method:
```java
private void onGiveXPToPlayer(GiveXpEvent event) {
    int previousLevel = ...
    int nextLevel = ...
    if (previousLevel != nextLevel) {
        send(new PlayerGainLevelEvent(event.getPlayer(), event.getSkill(), nextLevel));
    }
}
```
Events go through components in the order they're loaded in (i.e. priority). If an event is cancelled, it will not go through lower priority components.

## Main structure
Here is the list of the main packages of this project:
- **assignment** This is where most of your code will be made. assignment.Main is the main class to execute
- **net.gameslabs** This package contains mostly back-end code.
- **net.gameslabs.events**  A package containing the different events.

## The main class
The Main class:
```java
public static void main(String[] args) {
    new Assignment(
        new MyXPBoosterComponent()
    ).run();
}
```
The assignment class takes components in its constructor. Components are loaded in order.

# The assignment
Here is the list of tasks you need to achieve:
- Edit the MyXPBoosterComponent to enable DXP in the construction skill
- Add an inventory component in charge of giving, checking if a player has a given item and removing a given item.
    In order to complete this task, you may need to edit pre-existing code.
    Add new checks in Assignment.java to run checks on the features you have added.
- Add a mining skill and component with different ores. A player should only be able to mine coal at level 5 (meaning a, event needs to be cancelled according to his mining level). A player should receive xp from mining an ore.
    Add new checks in Assignment.java to run checks on the features you have added.
- Add a unique feature and add this feature to the README with a description of what it is supposed to do and checks in Assignment.

## Publishing
Fork this repository and add scipio3000 to the repository. PM scipio3000 with the link to your repository once you are done with the assignment