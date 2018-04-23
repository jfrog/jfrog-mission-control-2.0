Mesh Topology
=============

Overview
--------

This script creates repositories and configures them for replication over a [full-mesh topology][].  Users may wish to add or alter parameters to fine tune replication.

[full-mesh topology]: https://www.jfrog.com/support-service/whitepapers/using-artifactory-manage-binaries-across-multi-site-topologies/

User Inputs
-----------

- RepoName

  The base name of the repository to create. Multiple repositories derived from this base name will be created on each Artifactory instance, and replication will be configured between these.

- RepoType

  The package type to use for the new repository.

- Instances

  A list of Artifactory instances to configure this replication over.

Additional parameters can be found at: https://www.jfrog.com/confluence/display/MC/Configuration+DSL
