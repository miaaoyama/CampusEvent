README
(updated 10/13/2026)

# Object-Oriented Programming Team Project | Campus Event Management System - Java

## Project Overview

This project implements an event management system for college students. It serves as an avenue for students to find and register for a multitude of different campus events. Likewise, the program facilitates a way for event organizers to manage these events, their capacities, their event type, and more.

## Team Members:  

- Marina  
- Audrey  
- Swan  
- Kyle  
- Francisco  
- Jason  

The following is a generalized list of contributions from each student. It was very common for us to error check each other's code, resolve any outstanding issues, implement helper functions for each other, etc. 

| Name | Primary Contributions |
|---|---| 
| Marina | Overall project management, Event System, Organizer System, and Report System designer, main implementation |
| Audrey | Co-Registration System designer, main menu designer, main implementation, documentation |
| Swan | Student management system designer |
| Kyle |  |
| Francisco | Persistence designer, File Management System designer, main implementation |
| Jason | Co-Registration System designer, main implementation, documentation |

## Features:  

This is not an exhaustive list of all important features, but rather some of the more useful ones are detailed here.  

- Ability to save and load data on program closing / opening
- Exception handling for user input and persistence checking
- Search functions for finding and comparing students, events, registrations, and similar  
- Functions to add students and events to a registration list  
- Methods to keep track of which students are registered for which events

And much more!

## Class Design:  

Some of the more prominent classes used in the program are listed below. 

| Class | Responsibility |
|---|---| 
| Student | Creates a student object with name, ID, email, and major. Also features methods to display each. |
| Event | Creates an event object with an event id, name, description, date, time, location, capacity, and ways to display each.  Also acts as a superclass for more specific event-type subclasses. |
| Registration | Creates a registration object that combines a student and an event together, showing that a student is registered for or has cancelled a specific event. 
| Student Manager | Stores students while the program is running and handles student operations. Can add students, find them by ID, and display each to the console. |
| Event System | Similar to the Student Manager; can add an event, find an event by ID, search for an event by name, and display all events to the console. |
| Registration System | In charge of and holds all registered students with their respective events. Able to add, remove, and cancel registrations. Can also display all events a student is registered to, and likewise display all students registered for a specific event.|
| File Manager | Allows saving and loading of student / event data. Way more involved than we would have thought. |


Specifically for the Event class: we derived many sub-classes that exhibit similar features. For example, Academic Event and Social Event share a "is-a" relationship with Event, but not vice-versa. When these subclasses are called in main, they utilize polymorphism so that they can correctly be called as Event objects.

## Packages

Our package layout closely follows what was recommended in the team project guide.  

- App: Includes the Main.java file
- Model: Holds all of the Student, Event, and Registration object data. 
- Persistence: Holds info related to saving and loading objects, and the methods that require it.  
- Service: In general, specifically holds the lists of Student, Event, Registration, and Organizer objects, and the methods to change or alter the data for these lists.  



## Running:  

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
| 8 | View Student Registrations | Displays all events a student is registered for. |
| 9 | View Event Attendees | Displays all students attending the selected event. |
| 10 | Add Organizer | Adds a new organizer to the program, including their ID, event ID, etc. |
| 11 | View Organizers | Displays list of organizers managing events. |
| 12 | System Reports | Displays other miscellaneous event info that may be interesting. |
| 0 | Exit | Terminates the program. |

More to be added later~
