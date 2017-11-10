Star Topology
=============

Overview
--------

This script creates repositories and configures them for replication over a [star topology][].  Users may wish to add or alter parameters to fine tune replication.

[star topology]: https://www.jfrog.com/support-service/whitepapers/using-artifactory-manage-binaries-across-multi-site-topologies/

User Inputs
-----------

- RepoName

  The name of the repository to create. This repository will be created on all associated Artifactory instances, and replication will be configured between these.

- RepoType

  The package type to use for the new repository.

- Master

  An Artifactory instance that should act as the replication origin and hub.

- Slaves

  A list of Artifactory instances the master should replicate to.

Additional parameters can be found at: https://www.jfrog.com/confluence/display/MC/Configuration+DSL+2.0#ConfigurationDSL2.0-Replication
