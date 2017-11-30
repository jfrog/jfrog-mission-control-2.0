artifactory($whichArtifactory){
   localRepository("docker-temp") {
     packageType "docker"
     description "My local Docker registry"
   }
}