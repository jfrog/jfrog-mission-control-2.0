RepoName = userInput(type: "STRING", description: "Repository Name")
RepoType = userInput(type: "PACKAGE_TYPE", description: "Repository Package Type")
Instances = userInput(type: "ARTIFACTORY", multivalued: true, description: "All Instances")

// Choose the correct default layout based on the repository type
def getRepoLayout(rtype) {
  def specialtypes = ["bower", "gradle", "ivy", "npm", "nuget", "sbt", "vcs", "composer", "conan", "puppet"]
  if (rtype == "maven") return "maven-2-default"
  else if (rtype in specialtypes) return rtype + "-default"
  else return "simple-default"
}

def layout = getRepoLayout(RepoType)

for (instance in Instances) {
    artifactory(instance.name) {
        def names = [RepoName + '-local']
        for (remote in Instances) {
            def locator = remote.name
            if (instance.name == remote.name) locator = 'local'
            def name = RepoName + '-' + locator
            if (instance.name != remote.name) names << name
            localRepository(name) {
                packageType RepoType
                repoLayoutRef layout
                if (instance.name == remote.name) {
                    for (replicate in Instances) {
                        if (instance.name == replicate.name) continue
                        def url = replicate.url - ~'/$'
                        url += '/' + RepoName + '-' + instance.name
                        replication(url) {
                            cronExp "0 0 0 * * ?"
                            username replicate.credentials.userName
                            password replicate.credentials.password
                            enableEventReplication true
                            syncProperties true
                            syncDeletes false
                            enabled true
                        }
                    }
                }
            }
        }
        virtualRepository(RepoName) {
            packageType RepoType
            repoLayoutRef layout
            repositories names
            defaultDeploymentRepo RepoName + '-local'
        }
    }
}
