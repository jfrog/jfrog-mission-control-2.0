whichArtifactory = userInput (
    type : "ARTIFACTORY",
    name : "Which Artifactory",
    description : "Please specify the Artifactory service on which to create the repository"
  )

artifactory(whichArtifactory.name){
   localRepository("docker-local") {
     packageType "docker"
     description "My local Docker registry"
   }
}