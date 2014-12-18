Gradle is a project automation tool.
- Gradle can automate the building, testing, publishing, deployment and more of software packages or other thypes of projects such as generated static websites, generated documentation or indeed anything else.
- Gradle is build upon the concepts of Apache Ant and Apache Maven;
- Gradle introduces a Groovy-based domain-specific language (DSL) instead of more traditional XML form of declaring the project configuration.
- Gradle uses a directed acyclic graph ("DAG") to determine the order in which tasks can be run.
    - Unlike Apache Maven, which defines lifecycles, and Apache Ant, where targets are invoked based upon a depends-on partial ordering.
- Gradle was designed for multi-project builds which can grow to be quite large, and supports incremental builds by intelligently determining which parts of the build tree are up-to-date, so that any task dependent upon those parts will not need to be re-executed.

topic
--
[Android Studio](AndroidStudio.md)

[Android Build Process](buildprocess.md)

[Developer Workflow](DeveloperWorkflow.md)

[Android System Architechture](androidSystemArchitechture.md)

[Gradle](Gradle.md)

[Design](design.md)
