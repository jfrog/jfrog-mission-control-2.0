Replication Script
==================

Overview
--------

This script takes user inputs and configures replication.  Users may be required to add more parameters and/or access the UI to fine tune Repository configuration. 


Instructions
------------

This Script requires a pre-existing remote or local repository.
This script configures the "host" repository for replication.  A host repository hosts the replication configuration information.  
Select your host artifactory instance and host repository
	
	-For push, you will need to specify your target URL
	-For push you will need to specify your package type

Configure additional parameters as found here:
https://www.jfrog.com/confluence/display/RTF/Repository+Configuration+JSON#RepositoryConfigurationJSON-RepositoryConfigurationJSON


User Inputs
-----------

- art1

  User specifies artifactory that will host he replication configuration from a dropdown.  This will be stored as a Artifactory object

- repokey

  User specifies which repository will host the replication. Note that pull only works with remote repositories and Push only works with Remote repositories.

- replicationtype

  User specifies "push" or "pull" replication.

- replicationUrl

  User provides a string of a repository URL.  For Pull, this is not needed.  For push, specify the target URL.

- eventbased

  User specifies if they require even-based replication.  Response is stored as a Boolean

Additional parameters for each repository type can be found at: https://www.jfrog.com/confluence/display/MC/Configuration+DSL+2.0#ConfigurationDSL2.0-Replication

Here are some additional parameters that must be configured for each repo type to work:

- username/password

  If required, please provide the credentials for the artifactory instance
