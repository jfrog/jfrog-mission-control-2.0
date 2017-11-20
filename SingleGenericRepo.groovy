//Artifactory
art1 = userInput (
    type : "ARTIFACTORY",
    description : "Artifactory instance name",
)

//repokey
repokeyArt1 = userInput (
    type : "STRING",
    description : "Repository Key",
)

//repo type
repotype = userInput (
    type : "STRING",
    description : "Repository Type",
)

//select packagetype
packagetype = userInput (
    type : "PACKAGE_TYPE",
    description : "Package Type",
)

//url of the repository you are proxying
remoteUrl = userInput (
    type : "STRING",
    value : " ",
    description : "Enter remote URL (for remotes only)",
)

//default deploy repository
defaultDeploy = userInput (
    type : "STRING",
    value : " ",
    description : "Enter default deploy repository key (virtual only)",
)


userName = userInput (
    type : "STRING",
    description : "Enter the username for the remote credentials",
)


pass = userInput (
    type : "STRING",
    description : "Enter the password for the remote credentials",
)

repolist = userInput (
    type : "STRING",
    value : " ",
    description : "Enter repository keys separated by commas (virtual only)",
)

// Choose the correct default layout based on the repository type
def getRepoLayout(rtype) {
    def specialtypes = ["bower", "gradle", "ivy", "npm", "nuget", "sbt", "vcs", "composer", "conan", "puppet"]
    if (rtype == "maven") return "maven-2-default"
    else if (rtype in specialtypes) return rtype + "-default"
    else return "simple-default"
}

def layout = getRepoLayout(packagetype)

if (repotype == "local") {
    artifactory(art1.name) {
        localRepository(repokeyArt1) {
            description "Public description"
            notes "Some internal notes"
            archiveBrowsingEnabled false
            packageType packagetype
            repoLayoutRef layout
        }
    }
} else if (repotype == "remote") {
    if (remoteUrl == " ") {

        throw new RuntimeException("Remote Repositories require URL of a central/source repository")

    }else{artifactory(art1.name) {
        remoteRepository(repokeyArt1) {
            url remoteUrl
            username userName
            password pass
            description "Public description"
            notes "Some internal notes"
            packageType packagetype
            repoLayoutRef layout
            remoteRepoChecksumPolicyType "generate-if-absent" // default | "fail" | "ignore-and-generate" | "pass-thru"
            xrayIndex false
            blockXrayUnscannedArtifacts false
            enableFileListsIndexing ""
            }
        }   
    }
    
} else if (repotype == "virtual") {

    if (repolist == " ") {

        throw new RuntimeException("Virtual Repositories require at least 1 repository nested inside")

    }else{
        def includedRepos = repolist.split(",")*.trim()
    artifactory(art1.name) {
        virtualRepository(repokeyArt1){
            repositories includedRepos
            description "Public description"
            notes "Some internal notes"
            packageType packagetype
            repoLayoutRef layout
            debianTrivialLayout false
            artifactoryRequestsCanRetrieveRemoteArtifacts false
            pomRepositoryReferencesCleanupPolicy "discard_active_reference" // default | "discard_any_reference" | "nothing"
            defaultDeploymentRepo defaultDeploy
            }
        }
    }

    
}