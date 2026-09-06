# Groovy Task
[![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/groovy-task)](https://artifacthub.io/packages/search?repo=groovy-task)

This Task can be used to run a Groovy script. This might be helpful if you would like to reuse Jenkins Pipelines, but it does not contain any Jenkins dependencies.

## Parameters

| Name | Description | Default |
| ---- | ----------- | ------- |
| `GROOVY_IMAGE` | Groovy base image. | `docker.io/library/groovy:latest` |
| `PROJECT_DIR` | The directory containing the groovy scripts. | `.` |
| `SCRIPT` | The groovy script to run. | `main.groovy` |

## Workspaces

| Name | Description |
| ---- | ----------- |
| `source` | The workspace consisting of the project. |

The base image runs `groovy $(params.SCRIPT)` from `$(workspaces.source.path)/$(params.PROJECT_DIR)`. The workspace root is available to scripts via the `WORKSPACE_SOURCE` environment variable (set to `$(workspaces.source.path)`).

## Example Pipeline

Below is an example of how to use it in a Pipeline. The complete source code can be found in the `test` subfolder.

```yaml
---
apiVersion: tekton.dev/v1beta1
kind: Pipeline
metadata:
  name: groovy-test-pipeline
spec:
  workspaces:
    - name: shared-workspace
  tasks:
    - name: fetch-code
      taskRef:
        name: git-clone
      workspaces:
        - name: output
          workspace: shared-workspace
      params:
        - name: url
          value: https://github.com/hurzelpurzel/tekton-tasks.git
        - name: deleteExisting
          value: "true"
    - name: groovy-run
      taskRef:
        name: groovy-task
      runAfter:
        - fetch-code
      params:
        - name: SCRIPT
          value: "task/groovy-task/test/main.groovy"
      workspaces:
        - name: source
          workspace: shared-workspace
    - name: groovy-show
      taskRef:
        name: groovy-task
      runAfter:
        - groovy-run
      params:
        - name: SCRIPT
          value: "task/groovy-task/test/display.groovy"
      workspaces:
        - name: source
          workspace: shared-workspace
---
apiVersion: tekton.dev/v1beta1
kind: PipelineRun
metadata:
  name: groovy-test-pipeline-run
spec:
  pipelineRef:
    name: groovy-test-pipeline
  workspaces:
    - name: shared-workspace
      persistentvolumeclaim:
        claimName: groovy-source-pvc
```
