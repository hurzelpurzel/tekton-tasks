# AGENTS.md

This repo is a collection of Tekton Tasks. Each Task lives in its own subfolder under `task/<task-name>/` and is published to Artifact Hub. There is no build, test, lint, or CI tooling — everything is declarative YAML.

## Repo layout
- `task/<task-name>/<task-name>.yml` — the Tekton `Task` resource (API `tekton.dev/v1beta1`).
- `task/<task-name>/README.md` — usage doc + pipeline example users follow.
- `task/<task-name>/artifacthub-repo.yml` — Artifact Hub repo metadata (one per task).
- `task/<task-name>/test/` — example Groovy scripts plus `resources.yml` (PVC) and `run.yml` (Pipeline/PipelineRun) for manual end-to-end runs.

## Conventions and gotchas
- Tekton Task resources are named `<task-name>-task` (e.g. `groovy-task`), while Artifact Hub indexes them under the shorter `artifacthub.io/alternativeName` (e.g. `groovy`). README and `test/run.yml` examples MUST reference the installed Task by its real `metadata.name` (`groovy-task`), NOT the alternative name.
- Because the example Pipeline clones this repo into the workspace root, `SCRIPT` param values for the test scripts are given relative to that root (`task/groovy-task/test/main.groovy`, not `groovy-task/test/...` — a historical mistake to avoid repeating in READMEs).
- Scripts read the workspace root via the env var `WORKSPACE_SOURCE` (set from `$(workspaces.source.path)`), not from the step's own `workingDir`/project dir — matching that env var keeps test scripts working.
- Root `README.md` lists every task in a table with a link to its per-task README; keep the table in sync when adding a task.
- Bump the `app.kubernetes.io/version` label on a Task when making functional changes.
