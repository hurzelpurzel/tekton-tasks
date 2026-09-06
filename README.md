# TektonTasks

A collection of Tekton [Tasks](https://tekton.dev/docs/pipelines/tasks/), each published to [Artifact Hub](https://artifacthub.io/).

## Tasks

| Task | Description |
| ---- | ----------- |
| [groovy](task/groovy-task/README.md) | Run a Groovy script. |

## Layout

Each task lives in its own subfolder under [`task/`](task/):

- `task/<task-name>/<task-name>.yml` — the Tekton `Task` resource (`tekton.dev/v1beta1`).
- `task/<task-name>/README.md` — usage documentation and pipeline example.
- `task/<task-name>/artifacthub-repo.yml` — Artifact Hub repository metadata.
- `task/<task-name>/test/` — example scripts plus `resources.yml` (PVC) and `run.yml` (Pipeline/PipelineRun) for manual end-to-end runs.

## License

[Apache-2.0](LICENSE)
