package es.santirivera.surveilfall.data.model

data class TournamentURLs(val legacy: List<String>,
                          val modern: List<String>,
                          val pauper: List<String>,
                          val vintage: List<String>)