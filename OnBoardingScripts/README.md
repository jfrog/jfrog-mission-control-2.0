On-Boarding Scripts
===================

Overview
--------

This script includes all actions related to adding a new team to Artifactory, including creating a repo, adding users, groups and permission targets.

User Inputs
-----------

- RepoKey

  The base name of the repository to create.

- Maturity

  The repository's maturity, in terms of its stage in the project lifecycle (for example: dev,stage,pre-prod,prod,release).

- Package Type

  The repository's package type. Select from the dropdown list.

- Location

  The type of the repository for example local, virtual.

- Instances

  A list of Artifactory instances on which to execute this script.

- User Name

  The name of the user to be created.

- Group Names

  The names of groups to be created. Note that group names can be comma separated.

- Permission Name

  The name of the permission which should be applied to the users.

- Permissions

  Permissions to be applied ('manage', 'delete', 'deploy', 'annotate', 'read'). Note that permissions can be comma separated.

- Repository Names

  Specific repositories on which to apply the permission target. This is a comma separated value.

Additional information can be found at: https://www.jfrog.com/confluence/display/MC/Configuration+DSL+2.0
