// Script to create a user, group, local repository and permissions.
repokey = userInput (
  type : "STRING",
  name : "RepoKey",
  description : "Please provide a Repository Key"
)

maturity = userInput (
  type : "STRING",
  name : "Maturity",
  description : "Please provide a value for maturity (example - dev, stage or prod)"
)

packagetype = userInput (
  name : "Package Type",
  type : "PACKAGE_TYPE",
  value : "",
  description : "Please provide a value for package type"
)

location = userInput (
  type : "STRING",
  name : "Location",
  description : "Please provide a value for location (example - local, remote)"
)

art = userInput (
  type : "ARTIFACTORY",
  name : "Instance",
  description : "Please select the artifactory instance to run against"
)

userName = userInput (
  type : "STRING",
  name : "User Name",
  description : "Please provide a username"
)

groupNames = userInput (
  type : "STRING",
  name : "Group Names",
  description : "Please provide a group/s name (comma separated, if multiple groups need to be created)"
)

permissionName = userInput (
  type : "STRING",
  name : "Permission Name",
  description : "Please provide a permission name"
)

permissions = userInput (
  type : "STRING",
  name : "Permissions",
  description : "Please enter the permissions that need to be granted (manage,delete,deploy,annotate,read) comma separated, if multiple permissions need to be granted"
)

repoNames = userInput (
  type : "STRING",
  name : "Repository Names",
  description : "Please provide a repo/s name (comma separated, if multiple repositories need to be granted permissions)"
)
//construct a namespace
//team-technology-maturity-location
def localName = repokey + "-" + packagetype + "-" + maturity + "-" + location

// Choose the correct default layout based on the repository type
def getRepoLayout(rtype) {
  def specialtypes = ["bower", "gradle", "ivy", "npm", "nuget", "sbt", "vcs", "composer", "conan", "puppet"]
  if (rtype == "maven") return "maven-2-default"
  else if (rtype in specialtypes) return rtype + "-default"
  else return "simple-default"
}

artifactory(art.name) {
  localRepository(localName) {
    description "Public description"
    notes "Some internal notes"
    archiveBrowsingEnabled false
    packageType packagetype
    repoLayoutRef getRepoLayout(packagetype)
  }
  security {
    groups {
      for(groupName in groupNames.split(",")) {
        group(groupName) {
          description ''
          autoJoin false
        }
      }
    }
    users {
      user(userName) {
        email 'login_1@jfrog.com'
        password 'password'
        admin false
        profileUpdatable false
        internalPasswordDisabled false
        groups (groupNames.split(",") as List)
      }
    }
    permissions {
      permission(permissionName) {
        includesPattern '**'
        excludesPattern ''
        anyLocal false
        anyRemote false
        anyDistribution false
        repositories (repoNames.split(",") as List)
        users {
          "$userName" (permissions.split(",") as List)
        }
        groups {
          def myGroups = groupNames.split(",") as List
          myGroups.each {
            "$it" (permissions.split(",") as List)
          }
        }
      }
    }
  }
}
