README
(updated 10/14/2026)

# Object-Oriented Programming Team Project | Campus Event Management System - Java

## Project Overview

This project implements an event management system for college students. It serves as an avenue for students to find and register for a multitude of different campus events. Likewise, the program facilitates a way for event organizers to manage these events, their capacities, their event type, and more.

## Team Members  

- Marina  
- Audrey  
- Swan  
- Kyle  
- Francisco  
- Jason  

The following is a generalized list of responsibilities for each student. It was very common for us to error check each other's code, resolve any outstanding issues, implement helper functions for each other, etc. 

| Name | Primary Responsibilities |
|---|---| 
| Marina | Overall project management, Event System, Organizer System, and Report System designer, main implementation |
| Audrey | Co-Registration System designer, main menu designer, main implementation, documentation |
| Swan | Student management system designer |
| Francisco | Persistence designer, File Management System designer, main implementation |
| Jason | Co-Registration System designer, main implementation, documentation |

## Features  

This is not an exhaustive list of all important features, but rather a snapshot of some of the most notable: 

- Ability to save and load data on program closing / opening
- Exception handling for user input and persistence checking
- Search functions for finding and comparing students, events, registrations, and similar  
- Functions to add students and events to a registration list  
- Methods to keep track of which students are registered for which events
- Organizers that can keep track of an array of different event-types

And much more!

## Class Design  

Some of the more prominent classes used in the program are listed below. 

| Class | Responsibility |
|---|---| 
| Student | Creates a student object with name, ID, email, and major. Also features methods to display each. |
| Event | Creates an event object with an event id, name, description, date, time, location, capacity, and ways to display each.  Also acts as a superclass for more specific event-type subclasses. |
| Registration | Creates a registration object that combines a student and an event together, showing that a student is registered for or has cancelled a specific event. 
| Organizer | Creates and manages events. Contains a unique organizer ID, name, email, and a list of event IDs. |
| Student Manager | Stores students while the program is running and handles student operations. Can add students, find them by ID, and display each to the console. |
| Event System | Similar to the Student Manager; can add an event, find an event by ID, search for an event by name, and display all events to the console. |
| Registration System | In charge of and holds all registered students with their respective events. Able to add, remove, and cancel registrations. Can also display all events a student is registered to, and likewise display all students registered for a specific event.|
| Organizer Manager | Holds a list of organizers. Able to add a new organizer, search for one by ID, return a copy of an organizer list, and display all organizers and their associated events. |
| File Manager | Allows saving and loading of student / event / registration / organizer data. |


Specifically for the Event class, we derived many sub-classes that exhibit similar features. For example, Academic Event and Social Event share a "is-a" relationship with Event, but not vice-versa. When these subclasses are called in main, they utilize polymorphism so that they can correctly be called as Event objects.

## Packages

Our package layout closely follows what was recommended in the team project guide.  

- App: Includes the Main.java file
- Model: Holds all of the Student, Event, and Registration object data. 
- Persistence: Holds info related to saving and loading objects, and the methods that require it.  
- Service: In general, specifically holds the lists of Student, Event, Registration, and Organizer objects, and the methods to change or alter the data for these lists.

## Building  

This specific project was built entirely in VS Code using that program's recommended Java extensions and add-ons, including a JDK. Note that an in-program pdf viewer is required to observe the project's UML diagram.  

## Data Storage

All data is saved and loaded during the program using comma-separated values (CSV) files.

## Running  

The program is run through a console menu user-interface; the following are options the user can select when presented with the main menu. Note that many of these options will prompt for additional information from the user.

| # | Option | Description |
|---|---|---|
| 1 | Add Student | Adds a new student to the program, including their name, ID number, etc. |
| 2 | View Students | Displays all students currently entered or saved into the program. |
| 3 | Create Event | Adds a new event with a unique 4-digit ID, name, description, date, etc.  |
| 4 | View Events | Displays all events entered or saved in the program. |
| 5 | Search Events | Searches for an event by its event type. |
| 6 | Register Student for Event | Signs a student up for a chosen event. |
| 7 | Cancel Registration | Cancels a student registration with a particular event. |
| 8 | View Student Registrations | Displays all events a student is currently registered for. |
| 9 | View Event Attendees | Displays all students attending the selected event. |
| 10 | View Organizers | Displays list of organizers managing events. |
| 11 | System Reports | Displays other miscellaneous event info that may be interesting. |
| 0 | Exit | Terminates the program. |  

## Notable Design Decisions and Feedback

Our group is satisfied with how our project turned out, design-wise.  
- All classes work quite well together. We separated the Student objects from the Student Manager, Registration objects from the Registration System, etc. We tried our best to avoid a "god class."
- The use of inheritance for the Event subclasses help streamline its related code.  
- We decided to follow most of the guidelines illustrated in the project instruction packet (package layout, workflow responsibilities, etc.), and it seemed to work out well.  

A few things to think about for next time:  
- Had we learned about it a bit earlier, perhaps adding an abstract class to act as a superclass (for say, all Event-type objects) might have reduced code complexity a bit. Likewise, adding an interface of some sort would have assisted in keeping some of our methods more organized and improved readability.  
- Helper functions in our main file to reduce code redundancy could be utilized to greater effect. Stuff like error checking and parsing user input tends to take up a bunch of room; narrowing those checks down to a simple function call would help condense main and improve readability.  
- When using Github, smaller and more frequent pull requests may streamline the build process a bit more. Admittedly, this was the first time several of us had used Git and Github in any major capacity. Overall though, everyone did a great job modifying and pushing the project code.  

## Known Limitations  

As of the last update (10/13/2026), the program appears to be fully functional. No known issues currently.  

Thanks for reading!
