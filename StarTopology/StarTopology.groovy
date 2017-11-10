RepoName = userInput(type: "STRING", description: "Repository Name")
RepoType = userInput(type: "PACKAGE_TYPE", description: "Repository Package Type")
Master = userInput(type: "ARTIFACTORY", description: "Master Instance")
Slaves = userInput(type: "ARTIFACTORY", multivalued: true, description: "Other Instances")

// Choose the correct default layout based on the repository type
def getRepoLayout(rtype) {
  def specialtypes = ["bower", "gradle", "ivy", "npm", "nuget", "sbt", "vcs", "composer", "conan", "puppet"]
  if (rtype == "maven") return "maven-2-default"
  else if (rtype in specialtypes) return rtype + "-default"
  else return "simple-default"
}

def layout = getRepoLayout(RepoType)

for (slave in Slaves) {
  if (Master.name == slave.name) continue
  artifactory(slave.name) {
    localRepository(RepoName) {
      packageType RepoType
      repoLayoutRef layout
    }
  }
}

artifactory(Master.name) {
  localRepository(RepoName) {
    packageType RepoType
    repoLayoutRef layout
    for (slave in Slaves) {
      if (Master.name == slave.name) continue
      def url = (slave.url - ~'/$') + '/' + RepoName
      replication(url) {
        cronExp "0 0 0 * * ?"
        username slave.credentials.userName
        password slave.credentials.password
        enableEventReplication true
        syncProperties true
        syncDeletes false
        enabled true
      }
    }
  }
}
