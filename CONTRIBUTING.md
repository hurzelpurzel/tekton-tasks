# Contributing

Thanks for your interest in contributing to this repository!

## Report issues

Please report bugs and request features via the [GitHub issue tracker](https://github.com/hurzelpurzel/tekton-tasks/issues). Use the provided issue templates when possible.

## Add or update a Task

Each Task lives in its own subfolder under `task/<task-name>/` and is published to Artifact Hub. A complete task consists of:

- `task/<task-name>/<task-name>.yml` — the Tekton `Task` resource (`tekton.dev/v1beta1`).
- `task/<task-name>/README.md` — usage documentation with a pipeline example.
- `task/<task-name>/artifacthub-repo.yml` — Artifact Hub repository metadata.
- `task/<task-name>/test/` — example scripts plus `resources.yml` (PVC) and `run.yml` (Pipeline/PipelineRun) for manual end-to-end runs.

When adding a task, keep the existing conventions:

- Use the `tekton.dev/v1beta1` API version.
- Name the task resource `<task-name>-task` (e.g. `groovy-task`) while exposing the user-facing short name via the `artifacthub.io/alternativeName` annotation.
- Reference installed Tasks in pipeline examples by their actual `metadata.name`, not the alternative name.

## Versioning

Bump the `app.kubernetes.io/version` label when you make a functional change to a Task.

## License

By contributing, you agree that your contributions are licensed under the [Apache License 2.0](LICENSE).
