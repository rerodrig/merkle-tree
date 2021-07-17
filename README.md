# Merkle Tree

## Running
Command line: `./gradlew run --args <<path to file>>`

List hashes: `curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/hashes`

Get piece: `curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/piece/5e1f680a5d61ee9f50fcf37287bcfc743962934dde99fffb341de57ac5997b79/8`