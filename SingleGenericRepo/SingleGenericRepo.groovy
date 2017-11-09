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

//packagetype
packagetype = userInput (
    type : "PACKAGE_TYPE",
    description : "Package Type",
)

remoteUrl = userInput (
    type : "STRING",
    description : "Enter remote URL (for remotes only)",
)

repolist = userInput (
    type : "STRING",
    description : "Enter repository keys separated by commas",
)

if (repotype == "local") {
    artifactory(art1.name) {
        localRepository(repokeyArt1) {
            description "Public description"
            notes "Some internal notes"
            archiveBrowsingEnabled false
            packageType packagetype
        }
    }
} else if (repotype == "remote") {
    artifactory(art1.name) {
        remoteRepository(repokeyArt1) {
            url remoteUrl
            username "remote-repo-user"
            password "pass"
            description "Public description"
            notes "Some internal notes"
            packageType packagetype
            remoteRepoChecksumPolicyType "generate-if-absent" // default | "fail" | "ignore-and-generate" | "pass-thru"
            xrayIndex false
            blockXrayUnscannedArtifacts false
            enableFileListsIndexing ""
        }
    }
} else if (repotype == "virtual") {
    artifactory(art1.name) {
        virtualRepository(repokeyArt1) {
            repositories repolist.split(",")*.trim()
            description "Public description"
            notes "Some internal notes"
            packageType packagetype
            debianTrivialLayout false
            artifactoryRequestsCanRetrieveRemoteArtifacts false
            pomRepositoryReferencesCleanupPolicy "discard_active_reference" // default | "discard_any_reference" | "nothing"
        }
    }
}


