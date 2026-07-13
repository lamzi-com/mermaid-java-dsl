## Project goals

This library is a Java DSL for Mermaid.

The primary goal is a coherent and stable public API.

Readability and API consistency are more important than reducing the number of classes.


## Coding rules

- Follow the existing architecture.
- Reuse existing abstractions.
- Do not introduce new abstractions unless they remove significant duplication.
- Prefer immutable value objects.
- Do not use reflection.
- Do not introduce new dependencies.

## API conventions

- Methods that add an element to a collection must be named addXxx().
- Methods that set a unique property must be named xxx().
- Public API consistency is more important than internal implementation elegance.

## Working rules

- Modify only the files necessary for the requested task.
- Do not perform unrelated refactorings.
- Do not rename APIs unless explicitly requested.
- Do not run Maven or tests unless explicitly requested.
- Preserve backward compatibility unless instructed otherwise.

## Tests

- Do not modify existing tests unless required.
- Add tests following the existing golden-file strategy.
- Compare generated Mermaid output with expected .mmd files.