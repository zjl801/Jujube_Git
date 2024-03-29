package com.bgi.supervision.jujubelog.handler

import com.alibaba.fastjson.JSON
import com.bgi.supervision.jujubelog.parser.Parser
import com.bgi.supervision.jujubeutils.Utils
import com.bgi.supervision.jujubeutils.j_log.L
import com.bgi.supervision.jujubeutils.j_log.LoggerPrinter
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by tony on 2017/11/27.
 */
class MapHandler: BaseHandler(), Parser<Map<*, *>> {

    override fun handle(obj: Any): Boolean {

        if (obj is Map<*,*>) {
            val s = L.getMethodNames()
            println(String.format(s, parseString(obj)))
            return true
        }

        return false
    }

    override fun parseString(map: Map<*, *>): String {

        val keys = map.keys
        val values = map.values
        val value = values.firstOrNull()
        val isPrimitiveType = Utils.isPrimitiveType(value)

        var msg = map.javaClass.toString() + LoggerPrinter.BR + "║ "

        val jsonObject = JSONObject()
        keys.map {

            it ->

            try {

                if (isPrimitiveType) {
                    jsonObject.put(it.toString(), map.get(it))
                } else {
                    jsonObject.put(it.toString(), JSONObject(JSON.toJSONString(map.get(it))))
                }
            } catch (e: JSONException) {
                L.e("Invalid Json")
            }
        }

        var message = jsonObject.toString(LoggerPrinter.JSON_INDENT)
        message = message.replace("\n".toRegex(), "\n║ ")

        return msg + message
    }

}