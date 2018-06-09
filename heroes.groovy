import groovy.json.*

List heroes = [
    [ id: 11, name: 'Mr. Nice' ],
    [ id: 12, name: 'Narco' ],
    [ id: 13, name: 'Bombasto' ],
    [ id: 14, name: 'Celeritas' ],
    [ id: 15, name: 'Magneta' ],
    [ id: 16, name: 'RubberMan' ],
    [ id: 17, name: 'Dynama' ],
    [ id: 18, name: 'Dr IQ' ],
    [ id: 19, name: 'Magma' ],
    [ id: 20, name: 'Tornado' ]
]

response.contentType = 'application/json'

Map hero = null
List searchResults = []

String idString = request.getParameter('id')
String searchString = request.getParameter('name')
//id parameter takes precedence over name parameter
if (idString && idString.isInteger()) {
    Integer id = idString as Integer
    hero = heroes.find { it.id == id }
} else if (idString) {
    //provided an invalid request param
    response.status = 400
    println '{}'
    return
} else if (searchString) {
    searchResults = heroes.findAll { it.name.toLowerCase().contains(searchString.toLowerCase()) }
}

if (idString && hero) {
   println JsonOutput.toJson(hero)
} else if (idString) {
   response.status = 404
   println '{}'
   return
} else if (searchString) {
   println JsonOutput.toJson(searchResults)
} else {
   //empty or missing request parameter
   println JsonOutput.toJson(heroes)
}