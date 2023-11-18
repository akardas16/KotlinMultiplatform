import org.lighthousegames.logging.logging

object Log{
    private val log = logging()
    fun i(tag:String,message: String){
       log.i(tag) { message }
    }
}