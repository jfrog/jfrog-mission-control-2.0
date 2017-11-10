OnBoarding Scripts
=============

Overview
--------

This script includes all action related to adding a new team to Artifactory including: 
creating a repo, adding users,group and permission target

User Inputs
-----------

- RepoKey

  The base name of the repository to create.
  
- Maturity

  The repository maturity in terms which stage in the lifecycle is the project in (For Example dev,stage,pre-prod,prod,release).

- Package Type

  The repository's package type. Select from the dropdown list.
  
- Location
   
   The type of the repository for example local, virtual.

- Instances

  A list of Artifactory instances to execute this script

- User Name

   The name of the user to be created.

- Group Names

   The name of the group to be created. (devgroup,qagroup)
   Note the group names can be comma separated.

- Permission Name

   The name of the permission which can be applied to users

- Permissions

   Permissions to be applied.('manage', 'delete', 'deploy', 'annotate', 'read') 
   Note the permissions can be comma separated.

- Repository Names

    Specific repositories on which to apply the permission target. This is a comma separated value.

Additional information can be found at : https://www.jfrog.com/confluence/display/MC/Configuration+DSL+2.0
