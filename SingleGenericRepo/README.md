Single Repo
===================

Overview
--------

This script takes user inputs and configures a single repository.  Users may be required to add more parameters and/or access the UI to fine tune Repository configuration.

User Inputs
-----------

- art1

  Specify the target Artifactory instance to create a repository.  Returns an Artifactory object

- repokey

  User provides the name for the new repository.  Returns a string, naming rules for repository keys apply (ie no space etc)

- repoType

  User specifies the type of repository desired (local, remote, virtual)

- packageType

  User selects from a dropdown the package type the repository is to support.

- remoteUrl (only for remote repos)

  URL as a String that the remote repository will be proxying

- defaultDeploy (only for Virtual repositories)
  A String of the repository key you want to be the target or deploys using the virtual repository URL

- Repolist (only for Virtual repositories)

  A String of repositories you want contained in your virtual repository.  Generates list



Additional parameters for each repository type can be found at: https://www.jfrog.com/confluence/display/MC/Configuration+DSL+2.0#ConfigurationDSL2.0-RepositoryConfigurationBlocks

Here are some additional parameters that must be configured for each repo type to work:

- username/password

  If required, please provide the credentials for the artifactory instances
