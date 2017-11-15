//Artifactory
art1 = userInput (
    type : "ARTIFACTORY",
    description : "Please provide the target Artifactory you want to host the replication",
)

repokey = userInput (
    type : "STRING",
    description : "Please provide the target Repository you want to host the replication",
)

replicationType = userInput (
    type : "STRING",
    description : "Please specify push or pull replication",
)

replicationUrl = userInput (
    type : "STRING",
    value : " ",
    description : "For pull, specify the URL of the repository you want to Proxy. For push, speicify target repository URL",
)

eventBased = userInput (
    type : "BOOLEAN",
    description : "event based replication?"
)

packagetype = userInput (
    type : "PACKAGE_TYPE",
    description : "select package type",
)

if (replicationType == "pull") {
    if (replicationUrl == " ") {

        throw new RuntimeException("Please provide URL or repository you want to proxy")

    }else{
        artifactory(art1.name) {
            remoteRepository(repokey) {

                url replicationUrl
                packageType packagetype

                replication (replicationUrl) {
                    cronExp "0 0/9 14 * * ?"
                    socketTimeoutMillis 15000
                    username "remote-repo-user"
                    password "pass"
                    enableEventReplication eventBased
                    enabled true
                    syncDeletes false
                    syncProperties true
                    clientTlsCertificate ""
                }
            }
        }
    }


} else if (replicationType == "push") {
    artifactory(art1.name) {
        localRepository(repokey) {
            replication(replicationUrl) {
                cronExp "0 0/9 14 * * ?"
                socketTimeoutMillis 15000
                username "remote-repo-user"
                password "pass"
                enableEventReplication eventBased
                enabled true
                syncDeletes false
                syncProperties true
                clientTlsCertificate ""
            }
        }
    }
}
