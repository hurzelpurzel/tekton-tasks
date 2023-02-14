# Groovy Task
[![Artifact Hub](https://img.shields.io/endpoint?url=https://artifacthub.io/badge/repository/groovy-task)](https://artifacthub.io/packages/search?repo=groovy-task)

This Task can be used to run a Groovy script. This might be helpfull if you would like to reuse Jenkins Pipelines, but at present it does not contain any Jenkins dependencies.


Below see a example how to use it in a Pipeline.
You can find the complete source code in the test subfolder.

```

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
        name: groovy
      runAfter:
        - fetch-code
      params:
        - name: SCRIPT
          value: "groovy-task/test/main.groovy"
      workspaces:
        - name: source
          workspace: shared-workspace
    - name: groovy-show
      taskRef:
        name: groovy
      runAfter:
        - groovy-run
      params:
        - name: SCRIPT
          value: "test/display.groovy"
      workspaces:
        - name: source
          workspace: shared-workspace
---
apiVersion: tekton.dev/v1beta1
kind: PipelineRun
metadata:
  name: groovy-test-pipeline-rune
spec:
  pipelineRef:
    name: groovy-test-pipeline
  workspaces:
    - name: shared-workspace
      persistentvolumeclaim:
        claimName: groovy-source-pvc

```


