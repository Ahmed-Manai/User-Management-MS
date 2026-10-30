# Contributing

Thanks for your interest in improving this project.

## Getting started

1. Fork the repository and create a branch from `main`:
   `git checkout -b feat/short-description`
2. Install JDK 17+ and, for the full test suite, run MongoDB on `localhost:27017`
   (for example `docker run -d -p 27017:27017 mongo`).
3. Build and test:
   ```bash
   ./mvnw -B verify
   ```

## Guidelines

- Keep changes focused; one topic per pull request.
- Follow the existing package layout (`controller`, `service`, `repository`, `model`).
- Add or update tests for any behavior change. Prefer Mockito unit tests and `@WebMvcTest`
  slices that do not need a running database.
- Use [Conventional Commits](https://www.conventionalcommits.org/) for commit messages,
  e.g. `feat: add pagination to user listing` or `fix: return 404 for unknown user`.
- Update `README.md` when you change endpoints, configuration keys or build steps.

## Reporting issues

Use the issue templates for bug reports and feature requests and include enough detail
to reproduce the problem.
