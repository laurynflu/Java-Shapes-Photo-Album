# Homework 9 - Shapes Photo Album Part 2: Controller & Views

## Overview

This project implements **two types of views** for a model: a **Graphical View** using Java Swing and a **Web View** using HTML with embedded SVG. The primary goal is to explore how different views can render the same model data, following the MVC (Model-View-Controller) design pattern and adhering to the **SOLID principles**.

## Project Structure

```
src/
├── controller/         # Controller logic
├── model/              # Model logic (from Part 1, potentially refactored)
├── view/               # View interfaces and implementations
│   ├── GraphicalView.java
│   └── WebView.java
├── utils/              # Optional: utility/helper classes
└── PhotoAlbumMain.java # Application entry point
```

## Features

- Two view modes: interactive graphical interface and static web interface
- Snapshot navigation: next, previous, jump to, and view snapshot description
- Vector-based drawing using Java's `Graphics` and HTML+SVG
- Snapshot rendering order preserves creation sequence
- MVC design for modularity and testability
- Command-driven input to apply transformations to shapes

## Usage

### Command Line Arguments

```
MyProgram -in input-file.txt -v [graphical|web] [-out output.html] [xmax ymax]
```

### Argument Options

| Argument        | Description                                                                 |
|----------------|-----------------------------------------------------------------------------|
| `-in`          | Input file with shape and snapshot commands                                 |
| `-v` / `-view` | Type of view: `graphical` or `web`                                          |
| `-out`         | Output file for web view (ignored for graphical view)                       |
| `xmax ymax`    | Optional dimensions for the view window; defaults to 1000x1000 if omitted   |

### Example Commands

```bash
java PhotoAlbumMain -in buildings.txt -v web -out myWeb.html
java PhotoAlbumMain -v graphical -in buildings.txt 800 800
```

## View Implementations

### Graphical View (Swing)

- Renders snapshots inside a Java Swing window
- Interactive UI with buttons:
  - Next snapshot
  - Previous snapshot
  - Jump to selected snapshot
  - View snapshot ID and description
- Draws shapes using `Graphics` in the order they are created
- Responds to user events with window updates via `repaint()`

### Web View (HTML & SVG)

- Produces an HTML file with embedded SVG elements for each snapshot
- Each snapshot rendered in order with:
  - Title (ID and description)
  - Vector-based rendering of shapes
- No interactivity; output is a single scrollable HTML document

## Controller

- Coordinates the flow of information between model and views
- Parses command-line arguments
- Loads command file and directs model to execute commands
- Passes model data to appropriate view for rendering

## Model

- Stores and manipulates the state of shapes and snapshots
- Executes operations such as:
  - `shape`, `move`, `resize`, `color`, `remove`, and `snapshot`
- Exposes a clean interface that the controller and views interact with
- Does not handle command file parsing directly (Separation of Concerns)

## Input Command File

The command file contains a list of commands that build and modify the shape album.

### Supported Commands

- `shape id type x y width height r g b`
- `move id x y`
- `resize id width height`
- `color id r g b`
- `remove id`
- `snapshot "optional description"`

Lines beginning with `#` are treated as comments.

## Development Notes

### Refactoring and Changes from Part 1

- Ensured interface/implementation separation in both model and views
- Extracted reusable functionality into utility classes
- Applied the Interface Segregation Principle: separate interfaces for Graphical and Web views
- Adjusted model interface to facilitate better interaction with controller and views
- Minor changes made to model to improve snapshot access and state immutability

## Resources

- Java Swing Documentation: https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
- Java Graphics Class: https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
- SVG Specification: https://www.w3.org/TR/SVG2/
- HTML Basics for SVG: https://developer.mozilla.org/en-US/docs/Web/SVG

## License

This project is part of a homework assignment for academic purposes. Reuse or redistribution outside of educational use is not permitted.
