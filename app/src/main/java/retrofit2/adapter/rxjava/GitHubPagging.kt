package retrofit2.adapter.rxjava

import com.xiaoya.common.log.logger
import okhttp3.HttpUrl

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/

class GitHubPaging<T> : ArrayList<T>(){
    companion object {
        const val URL_PATTERN = """(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""
    }

    private val realMap = HashMap<String,String?>().withDefault { null }

    private val first by realMap
    private val last by realMap
    private val next by realMap
    private val prev by realMap

    val isLast
    get() = last == null

    val hasNext
    get() = next != null

    val isFirst
        get() = first == null

    val hasPrev
     get() = prev != null

    var since : Int = 0

    operator  fun get(key:String):String?{
        return realMap[key]
    }

    fun setupLinks(link : String){
        logger.warn("setupLinks: $link")
        Regex("""<($URL_PATTERN)>; rel="(\w+)"""").findAll(link)
                .asIterable()
                .map {
                    matchResult ->
                    val url = matchResult.groupValues[1]
                    realMap[matchResult.groupValues[3]] = url // next=....
                    if(url.contains("since")){
                        HttpUrl.parse(url)?.queryParameter("since")?.let{
                            since = it.toInt()
                        }
                    }
                    logger.warn("${matchResult.groupValues[3]} => ${matchResult.groupValues[1]}")
                }
    }

    fun mergeData(pageing:GitHubPaging<T>):GitHubPaging<T>{
        addAll(pageing)
        realMap.clear()
        realMap.putAll(pageing.realMap)
        since = pageing.since
        return this
    }

}