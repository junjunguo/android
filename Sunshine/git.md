Git
==
Git is a **distributed revision control system** with an emphasis on speed, data integrity, and support for distributed, non-linear workflows.
- initially designed & developed by Linus Torvalds in 2005
- most widely adopted version control system for software development.
- The **Git working directory** is a full-fledged repository with complete history & full version-tracking capabilities
- independent of network access or a central server.
- git is free software.

GitLab
--
GitLab is a software repository manager.
- GitLab is similar to GitHub, but GitLab allows developers to store the code on their own servers rather than servers operated by GitHub.
- free & open source software

GitHub
--
GitHub is a web-based Git repository hosting service
- offers all of the distributed revision control & source code management functionality of Git as wellas adding its own features.
- Git is strictly a command-line tool
- GitHub provides a web-based graphical interface ...
    - access control 
    - wikis
    - task management
    - bug tracking
    - feature requests for every project.
- Projects on GitHub can be accessed & manipulated using the standard git command-line interface &
- all of the standard git commands

git command line
--
show abbreviated stats for each commit:
- git log --stat

Creating branches:
- git branch branchName

Switch to branch
- git checkout branchName

build a branch and swith to it:
- git checkout -b branchName

move to a specific revisions:
- git reset --hard (commit revision: ie: d878ef47d42f4f8ce0fd12fd0b6ace623f3ad902)

see changes:
- git diff
